import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ez az osztaly valositja meg a jatekban
 * levo lyukakat.
 */

public class Hole extends Cell {
    private char display;
    private boolean isOpen;

    public Hole(boolean isOpen) {
        this.isOpen = isOpen;
        display = isOpen ? 'O' : '*';
    }

    /**
     * Ezzel a fuggvennyel kinyithato a lyuk.
     */
    public void open() {
        isOpen = true;
        display = 'O';
    }

    /**
     * Ezzel a fuggvennyel bezarhato egy lyuk.
     */
    public void close() {
        isOpen = false;
        display = '*';
    }

    /**
     * Ezzen fuggveny meghivasaval a zart lyukbol nyitott lyuk,
     * nyitott lyukbol zart lyuk lesz.
     */
    public void change() {
        if(isOpen){
            isOpen = false;
            display = '*';
        } else{
            isOpen = true;
            display = 'O';
        }
    }

    /**
     * A fuggveny segitsegevel lekerdezheto, hogy a lyuk
     * nyitott allapotban van-e
     * @return true, ha a lyuk nyitva van, false, ha zarva
     */
    public boolean isOpened() {
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
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        StepResult result = visitor.visit(this, dir, force);
        return result;
    }

    @Override
    public void draw() {
        System.out.print(display);
    }
}
