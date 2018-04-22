import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private List<Cell> cells;
    private List<Crate> crates;
    private List<Player> players;
    private boolean running = true;


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
        //}
    }
}