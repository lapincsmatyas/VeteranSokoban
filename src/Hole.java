import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ez az osztaly valositja meg a jatekban
 * levo lyukakat.
 */

public class Hole extends Cell {

    public Hole() {
        Logger.getInstance().logWithDec("Hole", "Hole()");
    }

    /**
     * Ezzel a fuggvennyel kinyithato a lyuk.
     */
    public void open() {
        Logger.getInstance().logWithDec("Hole", "open()");
    }

    /**
     * Ezzel a fuggvennyel bezarhato egy lyuk.
     */
    public void close() {
        Logger.getInstance().logWithDec("Hole", "close()");
    }

    /**
     * Ezzen fuggveny meghivasaval a zart lyukbol nyitott lyuk,
     * nyitott lyukbol zart lyuk lesz.
     */
    public void change() {
        Logger.getInstance().logWithDec("Hole", "change()");
    }

    /**
     * A fuggveny segitsegevel lekerdezheto, hogy a lyuk
     * nyitott allapotban van-e
     * @return true, ha a lyuk nyitva van, false, ha zarva
     */
    public boolean isOpened() {
        Logger.getInstance().logWithDec("Hole", "isOpened()");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Lyukra lépés történt. Nyitva van a lyuk? I/N: ");
            String input = br.readLine().toUpperCase();
            if(input.equals("I")){
                return true;
            }
            else
                return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * A visitor patternt megvalosito visit fuggveny.
     * Meghivja a visitor visit fuggvenyet
     * @param visitor az aktuals latogato
     * @param dir a latogatas iranya - ebbe az iranyba szeretne haladni a visitor
     * @return
     */
    //TODO implementalni a forceos mukodest, egyelore csak parameterben van
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        Logger.getInstance().log("Hole", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir, force);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
