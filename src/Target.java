/**
 * A jatekban talalhato celmezoket valositja meg
 */
public class Target extends Cell {
    public Target() {
        super();
        Logger.getInstance().logWithDec("Target", "Target()");
    }

    /**
     * A visitor pattern megvalositasahoz szukseges accept fuggveny
     * Meghivja az aktualis visitor visit fuggvenyet
     * @param visitor az aktualis visitor
     * @param dir ebbe az iranyba szeretne lepni a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir) {
        Logger.getInstance().logWithDec("Target", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
