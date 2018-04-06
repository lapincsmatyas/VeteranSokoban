/**
 * A jatekban talalhato celmezoket valositja meg
 */
public class Target extends Cell {
    private final char display;
    public Target() {
        super();
        display = 't';
    }

    /**
     * A visitor pattern megvalositasahoz szukseges accept fuggveny
     * Meghivja az aktualis visitor visit fuggvenyet
     * @param visitor az aktualis visitor
     * @param dir ebbe az iranyba szeretne lepni a visitor
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
