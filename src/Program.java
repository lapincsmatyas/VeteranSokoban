import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
    private static boolean exit = false;

    public static void main(String [ ] args) {
        Program p = new Program();
        /*Game g = new Game();
        g.init();*/

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

        switch (code) {
            case 1:
                break;
            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
            case 24:
                break;
            case 251:
                break;
            case 252:
                break;
            case 26:
                break;
            case 27:
                break;
            case 31:
                break;
            case 32:
                break;
            case 33:
                break;
            case 34:
                break;
            case 351:
                break;
            case 352:
                break;
            case 36:
                break;
            case 371:
                break;
            case 372:
                break;
            case 373:
                break;
            case 374:
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
