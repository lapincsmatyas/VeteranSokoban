/**
 * Ez az osztaly valositja meg a jatekban
 * levo lyukakat.
 */
public class Hole extends Cell {
<<<<<<< .merge_file_a02352
    private boolean open;

    /**
     * Az osztaly default konstruktora.
     */
    public Hole() {
        super();
        open = false;
=======
    private char display;
    private boolean isOpen;

    public Hole(boolean isOpen) {
        this.isOpen = isOpen;
        display = isOpen ? 'O' : '*';
>>>>>>> .merge_file_a13600
    }

    /**
     * Ezzel a fuggvennyel kinyithato a lyuk.
     */
    public void open() {
<<<<<<< .merge_file_a02352
        open = true;
=======
        isOpen = true;
        display = 'O';
>>>>>>> .merge_file_a13600
    }

    /**
     * Ezzel a fuggvennyel bezarhato egy lyuk.
     */
    public void close() {
<<<<<<< .merge_file_a02352
        open = false;
=======
        isOpen = false;
        display = '*';
>>>>>>> .merge_file_a13600
    }

    /**
     * Ezzen fuggveny meghivasaval a zart lyukbol nyitott lyuk,
     * nyitott lyukbol zart lyuk lesz.
     */
    public void change() {
<<<<<<< .merge_file_a02352
        open = !open;
=======
        if(isOpen){
            isOpen = false;
            display = '*';
        } else{
            isOpen = true;
            display = 'O';
        }
>>>>>>> .merge_file_a13600
    }

    /**
     * A fuggveny segitsegevel lekerdezheto, hogy a lyuk
     * nyitott allapotban van-e.
     * @return True, ha a lyuk nyitva van, false, ha zarva.
     */
    public boolean isOpened() {
<<<<<<< .merge_file_a02352
        return open;
=======
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
>>>>>>> .merge_file_a13600
    }

    /**
     * A visitor patternt megvalosito visit fuggveny.
     * Meghivja a visitor visit fuggvenyet.
     * @param visitor Az aktuals latogato.
     * @param dir A latogatas iranya - ebbe az iranyba szeretne haladni a visitor.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
<<<<<<< .merge_file_a02352
        return visitor.visit(this, dir, force);
    }

    @Override
    public void stepOn(Pushable pushable) {
        super.stepOn(pushable);

        if (open) {
            pushable.die();
        }
    }

    @Override
    public void draw() {
        if (open) {
            super.draw('O');
        } else {
            super.draw('*');
        }

=======
        StepResult result = visitor.visit(this, dir, force);
        return result;
>>>>>>> .merge_file_a13600
    }

    @Override
    public void draw() {
        System.out.print(display);
    }
}
