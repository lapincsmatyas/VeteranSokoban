import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void run() {
        //while (running) {
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
    //TODO implementalni rendesen, mert ez igy hianyos
    public void init(){
        levelLoader = new LevelLoader();
    }

    /**
     * A parameterben megadott azonostioju palyaval indit egy jatekot
     * @param id Level azonosito
     */
    public void start(int id){
        try {
            levelLoader.loadMap(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Level level = levelLoader.getLevel();

        buildLevel(level);
    }

    private void buildLevel(Level level){
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();

        for(int i = 0; i < level.getHeight(); i++){
            for(int j = 0; j < level.getWidth(); j++){
                char act = level.getItemAt(i,j);
                switch (act){
                    case '.':
                        cells.add(new Field());
                        break;
                    case 'p':
                        cells.add(new Pillar());
                        break;
                    case 'c':
                        Field temp = new Field();
                        Crate crate = new Crate(temp, 0);
                        cells.add(temp);
                        crates.add(crate);
                        break;
                    case 's':
                        //TODO
                        break;
                    case 't':
                        //TODO
                        break;
                    case 'O':
                        //TODO
                        break;
                    case '*':
                        //TODO
                        break;
                    case 'm':
                        //TODO
                        break;
                    case 'o':
                        //TODO
                        break;
                    default:
                        //TODO jatekos felismerese
                        break;
                }
            }
        }
        connectNeighbors(level.getWidth(), level.getHeight());
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
        cells.get(0).setNeighbor(Direction.RIGHT, cells.get(1));
        cells.get(0).setNeighbor(Direction.DOWN, cells.get(0 + width));
        for(int i = 1; i < width-1; i++){
            cells.get(i).setNeighbor(Direction.LEFT, cells.get(i-1));
            cells.get(i).setNeighbor(Direction.RIGHT, cells.get(i+1));
            cells.get(i).setNeighbor(Direction.DOWN, cells.get(i + width));
        }
        cells.get(width-1).setNeighbor(Direction.LEFT, cells.get(width-2));
        cells.get(width-1).setNeighbor(Direction.DOWN, cells.get(width - 1 + width));
    }
}