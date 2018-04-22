import java.io.IOException;
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

    //TODO implementalni rendesen, mert ez igy hianyos
    public void init(){
        LevelLoader levelLoader = new LevelLoader();
        try {
            levelLoader.loadMap(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Level level = levelLoader.getLevel();
        System.out.print(level);
    }

    /**
     * A parameterben megadott azonostioju palyaval indit egy jatekot
     * @param id Level azonosito
     */
    public void start(int id){
        buildLevel();
    }

    private void buildLevel(){
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();


    }
}