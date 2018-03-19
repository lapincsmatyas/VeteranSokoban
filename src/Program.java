import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A program feladata a skeleton mukodesenek megvalositasa,
 * a bemenetek kezelese, a megfelelo tesztesetek lefuttatasa
 *
 * @author Veteranok
 */

public class Program {
    private static boolean exit = false;

    /**
     * Ez a fuggveny kezdemenyezi a program futasat
     * @param args unused
     */
    public static void main(String [ ] args) {
        Program p = new Program();
        p.run();
    }

    /**
     * Ez a fuggvény felel a bemenetek bekereseert,
     * prompt kiirasaert es az esetleges bemenethez
     * kapcsolodo hibak kezeleseert
     */
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

    /**
     * A megfelelo bemenet alapjan lefuttatja a hozza
     * tartozo tesztesetet.
     * @param input a bemenetkent kapott teszteset szama
     */
    private void handleInput(String input) {
        int code = parseCode(input);

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

    /**
     * Ez a fuggveny konvertalja at a megadott bemenetet
     * olyan formatumra, amit a handleInput kezelni tud
     * @param input a kapott bemenet
     * @return az atkonvertalt egesz szam
     */
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
