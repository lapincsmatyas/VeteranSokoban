import java.util.ArrayList;
import java.util.List;

public class Game {
    public void checkGameOver(){}
    private void GameOver(){}
    private void startGame(){}

    List<Cell> cells;
    List<Crate> crates;
    List<Player> players;


    public Game(){
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();

        int numOfCells = 6;

        for(int i = 0; i < numOfCells; i++) {
            cells.add(new Field());
        }
        cells.add(new Hole());

        cells.get(0).setNeighbor(0, null);
        cells.get(0).setNeighbor(1, cells.get(1));
        for(int i = 1; i < cells.size() - 1; i++){
            cells.get(i).setNeighbor(0, cells.get(i-1));
            cells.get(i).setNeighbor(1, cells.get(i+1));
        }
        cells.get(cells.size()-1).setNeighbor(1, null);

        players.add(new Player(cells.get(0)));
        //players.add(new Player(cells.get(2)));
        crates.add(new Crate(cells.get(1)));
        crates.add(new Crate(cells.get(2)));
        //crates.add(new Crate(cells.get(2)));


        printLevel();
        players.get(0).move(1);
        players.get(0).move(0);
        players.get(0).move(1);
        players.get(0).move(1);
        players.get(0).move(1);
        players.get(0).move(1);
        players.get(0).move(1);
        printLevel();

    }

    public void init(){

    }

    public void printLevel(){
        Cell actCell = cells.get(0);
        while(actCell != null){
            actCell.printCell();
            actCell = actCell.getNext(1);
        }

        System.out.println();
    }

}
