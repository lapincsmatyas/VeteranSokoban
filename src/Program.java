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
        Game g = new Game();
        g.init();
        g.draw();
    }
}
