import java.util.ArrayList;
import java.util.List;

public class Game {
    public void checkGameOver(){}
    private void GameOver(){}
    private void startGame(){}

    private List<Cell> cells;
    private List<Crate> crates;
    private List<Player> players;


    public Game(){
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();

        int numOfCells = 6;

        for(int i = 0; i < numOfCells; i++) {
            cells.add(new Field());
        }
        cells.add(new Hole());

        cells.get(0).setNeighbor(Direction.LEFT, null);
        cells.get(0).setNeighbor(Direction.RIGHT, cells.get(1));
        for(int i = 1; i < cells.size() - 1; i++){
            cells.get(i).setNeighbor(Direction.LEFT, cells.get(i-1));
            cells.get(i).setNeighbor(Direction.RIGHT, cells.get(i+1));
        }
        cells.get(cells.size()-1).setNeighbor(Direction.RIGHT, null);

        players.add(new Player(cells.get(0)));
        players.add(new Player(cells.get(2)));
        crates.add(new Crate(cells.get(1)));


        printLevel();
    }

    public void init(){

    }

    public void printLevel(){
        Cell actCell = cells.get(0);
        while(actCell != null){
            actCell.printCell();
            actCell = actCell.getNext(Direction.RIGHT);
        }

        System.out.println();
    }

}
