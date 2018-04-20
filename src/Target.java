/**
 * A jatekban talalhato celmezoket valositja meg.
 */
public class Target extends Cell {
    /**
     * Az osztaly default konstruktora.
     */
    public Target() {
        super();
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
        return visitor.visit(this, dir, force);
    }
}
