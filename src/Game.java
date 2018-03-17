import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Cell> cells;
    private List<Crate> crates;
    private List<Player> players;


    public Game(){
        Logger.getInstance().log("Game", "Game()");

        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();

        Logger.getInstance().decIndentDepth();
    }

    public void init(){
        Logger.getInstance().log("Game", "init()");

        Level level = new Level();
        level.loadMap(1);
        level.getLevelData();

        Logger.getInstance().decIndentDepth();
    }
}
