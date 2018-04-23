/**
 * A jatekban talalhato celmezoket valositja meg.
 */
public class Target extends Cell {
<<<<<<< .merge_file_a12224
    /**
     * Az osztaly default konstruktora.
     */
    public Target() {
        super();
=======
    private final char display;
    public Target() {
        super();
        display = 't';
>>>>>>> .merge_file_a03976
    }

    /**
     * A visitor pattern megvalositasahoz szukseges accept fuggveny.
     * Meghivja az aktualis visitor visit fuggvenyet.
     * @param visitor Az aktualis visitor.
     * @param dir Ebbe az iranyba szeretne lepni a visitor.
     * @param force A tolas ereje.
     * @return A lepes sikeressege (StepResult tipusu).
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
<<<<<<< .merge_file_a12224
        return visitor.visit(this, dir, force);
    }

    @Override
    public void draw() {
        super.draw('t');
=======

        StepResult result = visitor.visit(this, dir, force);
        return result;
>>>>>>> .merge_file_a03976
    }

    @Override
    public void draw() {
        System.out.print(display);
    }
}
