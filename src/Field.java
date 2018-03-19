/**
 * Ez az osztaly valositja meg az ures mezoket
 */
public class Field extends Cell {
    public Field() {
        super();
        Logger.getInstance().logWithDec("Field", "Field()");
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Meghivja a visitor visit fuggvenyet.
     * @param visitor az aktualis visitor
     * @param dir ebbe az iranyba szeretne a visitor haladni
     * @return a lepes sikeressegevel ter vissza (StepResult tipusu)
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir) {
        Logger.getInstance().log("Field", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
