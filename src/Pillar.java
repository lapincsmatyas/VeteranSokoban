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
    //TODO implementalni a forceos mukodest, egyelore csak parameterben van
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        Logger.getInstance().log("Pillar", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir, force);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
