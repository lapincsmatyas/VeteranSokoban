import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Cell> cells;
    private List<Crate> crates;
    private List<Player> players;


    public Game(){
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();
    }

    public void init(){
        Level level = new Level();
        level.loadMap(1);
        level.getLevelData();
    }
}