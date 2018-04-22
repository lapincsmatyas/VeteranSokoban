import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A palyak betolteseert felel.
 */
public class LevelLoader {
    /**
     * Az osztaly default konstruktora.
     */
    public LevelLoader() {
    }

    /**
     * A parameterben megadott azonositoju
     * palya adatait tolti be.
     * @param id A betoltendo palya azonositoja.
     */
    public Level loadMap(int id) throws IOException {
        Level level = new Level(id);
        String path = String.valueOf(id);
        if (id < 10) path = "0" + id;
        path += ".map";

        File file = Paths.get("res/Levels/" + path).toFile();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";

        boolean cellsEnded = false;
        while ((line = br.readLine()) != null) {
            if ("".equals(line)) {
                cellsEnded = true;
            }

            if (!cellsEnded) {
                level.addLine(line);
            } else {
                String[] attrs = line.split(" ");
                switch (attrs[0]) {
                    case "player":
                        level.addPlayer(Integer.parseInt(attrs[1]),Integer.parseInt(attrs[2]),Integer.parseInt(attrs[3]));
                        break;
                    case "crate":
                        level.addCrate(Integer.parseInt(attrs[1]), Integer.parseInt(attrs[2]));
                        break;
                    case "oil":
                        level.addOil(Integer.parseInt(attrs[1]), Integer.parseInt(attrs[2]));
                        break;
                    case "honey":
                        level.addHoney(Integer.parseInt(attrs[1]), Integer.parseInt(attrs[2]));
                        break;
                }
            }
        }

        return level;
    }
}
