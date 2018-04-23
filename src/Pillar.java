/**
 * A jatekban levo oszlopokat valositja meg.
 */
public class Pillar extends Cell{
<<<<<<< .merge_file_a05916
    /**
     * Az osztaly default konstruktora.
     */
    public Pillar() {
        super();
=======
    private char display;

    public Pillar() {
        super();
        display = 'p';
>>>>>>> .merge_file_a09616
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Meghivja a visitor visit fuggvenyet.
     * @param visitor Az aktualis visitor.
     * @param dir A visitor ebbe az iranyba szeretne lepni.
     * @param force A tolas ereje.
     * @return A lepes sikeressege (StepResult tipusu).
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
<<<<<<< .merge_file_a05916
        return visitor.visit(this, dir, force);
    }

    @Override
    public void draw() {
        super.draw('p');
=======

        StepResult result = visitor.visit(this, dir, force);

        return result;
>>>>>>> .merge_file_a09616
    }

    @Override
    public void draw() {
        System.out.print(display);
    }
}
