import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
    private static boolean exit = false;

    public static void main(String [ ] args) {
        Program p = new Program();
        p.run();
    }

    private void run() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (!exit) {
                System.out.print("Bemenet: ");
                String input = br.readLine();
                this.handleInput(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(String input) {
        int code = parseCode(input);

        //A tesztesetek csak próbára voltak, nem ezek az igaziak.
        //A kódokat is át lehet írni, ha máshogy lesz a menürendszer.
        switch (code) {
            case 1:
                SkeletonTests.initTest();
                break;
            case 21:
                SkeletonTests.playerMovesToEmpty();
                break;
            case 22:
                SkeletonTests.playerMovesToPillar();
                break;
            case 23:
                SkeletonTests.playerMovesToTarget();
                break;
            case 24:
                SkeletonTests.playerMovesToSwitch();
                break;
            case 25:
                SkeletonTests.playerMovesToHole();
                break;
            case 26:
                SkeletonTests.playerMovesACrate();
                break;
            case 27:
                SkeletonTests.playerMovesToPlayer();
                break;
            case 31:
                SkeletonTests.crateMovesToEmpty();
                break;
            case 32:
                SkeletonTests.crateMovesToPillar();
                break;
            case 33:
                SkeletonTests.crateMovesToTarget();
                break;
            case 34:
                SkeletonTests.crateMovesToSwitch();
                break;
            case 35:
                SkeletonTests.crateMovesToHole();
                break;
            case 36:
                SkeletonTests.crateMovesToCrate();
                break;
            case 371:
                SkeletonTests.crateMovesToUserEmpty();
                break;
            case 372:
                SkeletonTests.crateMovesToUserWall();
                break;
            case 373:
                SkeletonTests.crateMovesToPlayerCrate();
                break;
            case 374:
                SkeletonTests.crateMovesToCratePlayerWall();
                break;
            case 41:
                SkeletonTests.MLMMLLMF();
                break;
            case 42:
                SkeletonTests.MLMMF();
                break;
            case 4:
                exit = true;
                break;
            default:
                System.out.println("Ismeretlen parancskód.");
        }
    }

    private int parseCode(String input) {
        //Kell a 2 visszaper, mert regexben a sima pont a bármilyen karakter.
        String[] codeSegments = input.split("\\.");
        StringBuilder codeStr = new StringBuilder();

        for (String codeSegment : codeSegments) {
            codeStr.append(codeSegment);
        }

        return Integer.parseInt(codeStr.toString());
    }
}
