import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A palyak betolteseert felel.
 */
public class LevelLoader {


    Level level;
    /**
     * Az osztaly default konstruktora.
     */
    //TODO implementalni
    public LevelLoader() {
        level = new Level();
    }

    /**
     * A parameterben megadott azonositoju
     * palya adatait tolti be.
     * @param id A betoltendo palya azonositoja.
     */
    //TODO implementalni
    public void loadMap(int id) throws IOException {
        String path = String.valueOf(id);
        if(id < 10) path = "0" + id;
        path+=".map";

        Files.lines(Paths.get("res/Levels/" +path)).forEach(
                line -> level.addLine(line)
        );
    }

    /**
     * Megadja a betoltott palya adatait.
     * @return A betoltott palya adatai.
     */
    //TODO implementalni
    public Level getLevel() {
        return level;
    }
}
