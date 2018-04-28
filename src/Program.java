import controller.Game;

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
        Game game = new Game();
        game.init();
    }
}
