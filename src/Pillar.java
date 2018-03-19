/**
 * A jatekban levo oszlopokat valositja meg.
 */
public class Pillar extends Cell{
    public Pillar() {
        super();
        Logger.getInstance().logWithDec("Pillar", "Pillar()");
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Meghivja a visitor visit fuggvenyet.
     * @param visitor az aktualis visitor
     * @param dir a visitor ebbe az iranyba szeretne lepni
     * @return a lepes sikeressege (StepResult tipusu)
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir) {
        Logger.getInstance().log("Pillar", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
