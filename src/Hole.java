import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ez az osztaly valositja meg a jatekban
 * levo lyukakat.
 */
public class Hole extends Cell {
    private boolean open;

    /**
     * Az osztaly default konstruktora.
     */
    public Hole() {
        super();
        open = false;
    }

    /**
     * Ezzel a fuggvennyel kinyithato a lyuk.
     */
    public void open() {
        open = true;
    }

    /**
     * Ezzel a fuggvennyel bezarhato egy lyuk.
     */
    public void close() {
        open = false;
    }

    /**
     * Ezzen fuggveny meghivasaval a zart lyukbol nyitott lyuk,
     * nyitott lyukbol zart lyuk lesz.
     */
    public void change() {
        open = !open;
    }

    /**
     * A fuggveny segitsegevel lekerdezheto, hogy a lyuk
     * nyitott allapotban van-e.
     * @return True, ha a lyuk nyitva van, false, ha zarva.
     */
    public boolean isOpened() {
        return open;
    }

    /**
     * A visitor patternt megvalosito visit fuggveny.
     * Meghivja a visitor visit fuggvenyet.
     * @param visitor Az aktuals latogato.
     * @param dir A latogatas iranya - ebbe az iranyba szeretne haladni a visitor.
     * @param force A tolas ereje.
     * @return
     */
    //TODO implementalni a forceos mukodest, egyelore csak parameterben van
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        StepResult result = visitor.visit(this, dir, force);

        return result;
    }
}
