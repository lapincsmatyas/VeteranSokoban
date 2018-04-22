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
    }
}
