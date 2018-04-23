import java.io.IOException;

/**
 * A program feladata a skeleton mukodesenek megvalositasa,
 * a bemenetek kezelese, a megfelelo tesztesetek lefuttatasa
 *
 * @author Veteranok
 */

public class Program {

    /**
     * Ez a fuggveny kezdemenyezi a program futasat
     * @param args unused
     */
<<<<<<< .merge_file_a12600
    public static void main(String [] args) {
        //System.out.println("VeteranSokoban");
        /*for(int i = 0; i <args.length; i++){
            System.out.print(args[i] + " ");
        }*/
        //System.out.println();
        Game game = new Game();
        game.init();

        if (args.length > 0) {
            Interpreter i = new Interpreter(game);
            try {
                i.run(args);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
=======
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
>>>>>>> .merge_file_a13584
    }
}
