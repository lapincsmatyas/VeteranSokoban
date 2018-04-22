import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Interpreter {
    private Game game;

    public Interpreter(Game game) {
        this.game = game;
    }

    public void run(String[] args) throws IOException {
        File file = Paths.get("tests/" + args[0]).toFile();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        line = br.readLine();
        int levelId = Integer.parseInt(line);

        game.start(levelId);

        while ((line = br.readLine()) != null) {
            String[] lineArgs = line.split(" ");
            int id = Integer.parseInt(lineArgs[1]);
            switch (lineArgs[0]) {
                case "add":
                    switch (lineArgs[2]) {
                        case "oil":
                            game.giveSlime(id, new Oil());
                            break;
                        case "honey":
                            game.giveSlime(id, new Honey());
                            break;
                    }
                    break;
                case "step":
                    Direction dir = Direction.RIGHT;
                    switch (lineArgs[2]) {
                        case "up":
                            dir = Direction.UP;
                            break;
                        case "down":
                            dir = Direction.DOWN;
                            break;
                        case "left":
                            dir = Direction.LEFT;
                            break;
                        case "right":
                            dir = Direction.RIGHT;
                            break;
                    }
                    game.movePlayer(id, dir);
                    break;
                case "put":
                    game.putSlime(id);
                    break;
            }

            game.drawMap();
        }
    }


}
