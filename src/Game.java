import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Cell> cells;
    private List<Crate> crates;
    private List<Player> players;

    int width;
    int height;

    public Game(){
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();

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
    }
}