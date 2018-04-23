import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game {
    private List<Cell> cells;
    private List<Crate> crates;
    private List<Player> players;

<<<<<<< .merge_file_a12968
    private LevelLoader levelLoader;

    public Game(){
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();
    }

    public void init(){
        levelLoader = new LevelLoader();
    }

    public void drawMap(){
        Cell cell = cells.get(0);

        boolean nomore = false;
        Direction dir = Direction.RIGHT;
        while (!nomore) {
            cell.draw();
            while (cell.getNext(dir) != null) {
                cell = cell.getNext(dir);
                cell.draw();
            }

            if (cell.getNext(Direction.DOWN) != null) {
                System.out.println();
                cell = cell.getNext(Direction.DOWN);
                dir = Direction.LEFT;
                while (cell.getNext(dir) != null) {
                    cell = cell.getNext(dir);
                }

                dir = Direction.RIGHT;
            } else {
                nomore = true;
            }
        }

        System.out.println("\n");
    }

    /**
     * A parameterben megadott azonostioju palyaval indit egy jatekot
     * @param id Level azonosito
     */
    public void start(int id){
        Level level = null;
        try {
            level = levelLoader.loadMap(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildLevel(level);
        drawMap();
    }

    public void movePlayer(int playerId, Direction dir) {
        for (Player p : players) {
            if (p.getId() == playerId) {
                StepResult result = p.move(dir);
                if (result == StepResult.SUCCESS_POINT) {
                    p.addOnePoint();

                    Random r = new Random();

                    if (r.nextInt(2) == 0) {
                        p.giveSlime(new Oil());
                    } else {
                        p.giveSlime(new Honey());
                    }
                }
            }
        }
    }

    public void putSlime(int playerId) {
        for (Player p : players) {
            if (p.getId() == playerId) {
                p.putSlime();
            }
        }
    }

    public void giveSlime(int playerId, Slime slime) {
        for (Player p : players) {
            if (p.getId() == playerId) {
                p.giveSlime(slime);
            }
        }
    }

    private void buildLevel(Level level){
=======
    int width;
    int height;

    public Game(){
>>>>>>> .merge_file_a13600
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();

<<<<<<< .merge_file_a12968
        List<Hole> holes = new ArrayList<>();
        List<Switch> levers = new ArrayList<>();

        for(int i = 0; i < level.getHeight(); i++){
            for(int j = 0; j < level.getWidth(); j++){
                char act = level.getCellAt(i,j);
                switch (act){
                    case '.':
                        cells.add(new Field());
                        break;
                    case 'p':
                        cells.add(new Pillar());
                        break;
                    case 's':
                        Switch lever = new Switch();
                        levers.add(lever);
                        cells.add(lever);
                        break;
                    case 't':
                        cells.add(new Target());
                        break;
                    case '*':
                    {
                        Hole hole = new Hole();
                        holes.add(hole);
                        cells.add(hole);
                        break;
                    }
                    case 'O':
                        Hole hole = new Hole();
                        hole.open();
                        holes.add(hole);
                        cells.add(hole);
                        break;
                }
            }
        }

        for (Switch lever: levers) {
            lever.addHoles(holes);
        }

        connectNeighbors(level.getWidth(), level.getHeight());
        placeEntities(level, levers);
    }

    private void placeEntities(Level level, List<Switch> levers) {
        for (Point p : level.getOil()) {
            int x = (int) p.getX() - 1;
            int y = (int) p.getY() - 1;

            Cell c = cells.get(y * level.getWidth() + x);
            c.putSlime(new Oil());
        }

        for (Point p : level.getHoney()) {
            int x = (int) p.getX() - 1;
            int y = (int) p.getY() - 1;

            Cell c = cells.get(y * level.getWidth() + x);
            c.putSlime(new Honey());
        }

        for (Point p : level.getCrates()) {
            int friction = (int) p.getX();
            int x = (int) p.getY() - 1;
            int y = (int) p.getZ() - 1;

            Cell c = cells.get(y * level.getWidth() + x);
            for (Switch lever: levers) {
                if(lever == c){
                    lever.change();
                }
            }
            crates.add(new Crate(c, friction));
        }

        for (Map.Entry<Integer, Point> entry : level.getPlayers().entrySet()) {
            Point p = entry.getValue();
            Integer id = entry.getKey();

            int force = (int) p.getX();
            int x = (int) p.getY() - 1;
            int y = (int) p.getZ() - 1;
            Cell c = cells.get(y * level.getWidth() + x);

            players.add(new Player(c, force, id));
        }
    }

    private void connectNeighbors(int width, int height){
        //Elso sor
        cells.get(0).setNeighbor(Direction.RIGHT, cells.get(1));
        cells.get(0).setNeighbor(Direction.DOWN, cells.get(0 + width));
        for(int i = 1; i < width-1; i++){
            cells.get(i).setNeighbor(Direction.LEFT, cells.get(i-1));
            cells.get(i).setNeighbor(Direction.RIGHT, cells.get(i+1));
            cells.get(i).setNeighbor(Direction.DOWN, cells.get(i + width));
        }
        cells.get(width-1).setNeighbor(Direction.LEFT, cells.get(width-2));
        cells.get(width-1).setNeighbor(Direction.DOWN, cells.get(width - 1 + width));

        for(int i = 1; i < height-1; i++){
            for(int j = 0; j < width; j++){
                int actPos = i*width + j;
                if(j == 0){
                    cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos-width));
                    cells.get(actPos).setNeighbor(Direction.RIGHT, cells.get(actPos+1));
                    cells.get(actPos).setNeighbor(Direction.DOWN, cells.get(actPos+width));
                } else if(j == width -1){
                    cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos-width));
                    cells.get(actPos).setNeighbor(Direction.LEFT, cells.get(actPos-1));
                    cells.get(actPos).setNeighbor(Direction.DOWN, cells.get(actPos+width));
                } else{
                    cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos-width));
                    cells.get(actPos).setNeighbor(Direction.RIGHT, cells.get(actPos+1));
                    cells.get(actPos).setNeighbor(Direction.DOWN, cells.get(actPos+width));
                    cells.get(actPos).setNeighbor(Direction.LEFT, cells.get(actPos-1));
                }
            }
        }

        //Utolso sor

        int actPos = (height-1)*width;

        cells.get(actPos).setNeighbor(Direction.RIGHT, cells.get(actPos+1));
        cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos - width));
        actPos++;

        for(; actPos < height*width-1; actPos++){
            cells.get(actPos).setNeighbor(Direction.LEFT, cells.get(actPos - 1));
            cells.get(actPos).setNeighbor(Direction.RIGHT, cells.get(actPos + 1));
            cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos - width));
        }
        cells.get(actPos).setNeighbor(Direction.LEFT, cells.get(actPos - 1));
        cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos - width));
=======
        width = 2;
        height = 2;

        for(int i = 0; i < width*height; i++){
            cells.add(new Field());
        }

        cells.get(0).setNeighbor(Direction.RIGHT, cells.get(1));
        cells.get(0).setNeighbor(Direction.DOWN, cells.get(2));

        cells.get(1).setNeighbor(Direction.LEFT, cells.get(0));
        cells.get(1).setNeighbor(Direction.DOWN, cells.get(3));

        cells.get(2).setNeighbor(Direction.UP, cells.get(0));
        cells.get(2).setNeighbor(Direction.RIGHT, cells.get(3));

        cells.get(3).setNeighbor(Direction.UP, cells.get(1));
        cells.get(3).setNeighbor(Direction.LEFT, cells.get(2));

    }

    public void init(){
        Level level = new Level();
        level.loadMap(1);
        level.getLevelData();
    }

    public void draw(){
        Direction dir = Direction.RIGHT;

        Cell actCell = cells.get(0);

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                actCell.draw();
                if(i == width - 1){
                    dir = Direction.LEFT;
                } if(i == 0){
                    dir = Direction.RIGHT;
                }
            }
            System.out.println();
            dir = Direction.DOWN;
            actCell = actCell.getNext(dir);
        }
>>>>>>> .merge_file_a13600
    }
}