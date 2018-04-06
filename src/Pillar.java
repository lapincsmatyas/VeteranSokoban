/**
 * A jatekban levo oszlopokat valositja meg.
 */
public class Pillar extends Cell{
    private char display;

    public Pillar() {
        super();
        display = 'p';
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Meghivja a visitor visit fuggvenyet.
     * @param visitor az aktualis visitor
     * @param dir a visitor ebbe az iranyba szeretne lepni
     * @return a lepes sikeressege (StepResult tipusu)
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
