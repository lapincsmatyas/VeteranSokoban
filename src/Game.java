import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Cell> cells;
    private List<Crate> crates;
    private List<Player> players;

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
        Cell c1 = cells.get(0);

        boolean nomore = false;
        Direction dir = Direction.RIGHT;
        while (!nomore) {
            c1.draw();
            while (c1.getNext(dir) != null) {
                c1 = c1.getNext(dir);
                c1.draw();
            }

            if (c1.getNext(Direction.DOWN) != null) {
                System.out.println();
                c1 = c1.getNext(Direction.DOWN);
                dir = Direction.LEFT;
                while (c1.getNext(dir) != null) {
                    c1 = c1.getNext(dir);
                }

                dir = Direction.RIGHT;
            } else {
                nomore = true;
            }
        }
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

    private void buildLevel(Level level){
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();

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
                        cells.add(new Switch());
                        break;
                    case 't':
                        cells.add(new Target());
                        break;
                    case '*':
                        cells.add(new Hole());
                        break;
                }
            }
        }

        connectNeighbors(level.getWidth(), level.getHeight());
        placeEntities(level);
    }

    private void placeEntities(Level level) {
        for (Point p : level.getOil()) {
            int x = (int) p.getX();
            int y = (int) p.getY();

            Cell c = cells.get(y * level.getHeight() + x);
            c.putSlime(new Oil());
        }

        for (Point p : level.getHoney()) {
            int x = (int) p.getX();
            int y = (int) p.getY();

            Cell c = cells.get(y * level.getHeight() + x);
            c.putSlime(new Honey());
        }

        for (Point p : level.getCrates()) {
            int x = (int) p.getX();
            int y = (int) p.getY();

            Cell c = cells.get(y * level.getHeight() + x);
            crates.add(new Crate(c, 0));
        }

        for (Map.Entry<Integer, Point> entry : level.getPlayers().entrySet()) {
            Point p = entry.getValue();
            Integer id = entry.getKey();

            int x = (int) p.getX();
            int y = (int) p.getY();
            Cell c = cells.get(y * level.getHeight() + x);

            players.add(new Player(c, 0, id));
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
    }
}