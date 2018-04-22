/**
 * Ez az osztaly valositja meg az ures mezoket
 */
public class Field extends Cell {
    /**
     * Az ures mezo osztaly konstruktora.
     */
    public Field() {
        super();
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Meghivja a visitor visit fuggvenyet.
     * @param visitor Az aktualis visitor.
     * @param dir Ebbe az iranyba szeretne a visitor haladni.
     * @param force A tolas erossege.
     * @return A lepes sikeressegevel ter vissza (StepResult tipusu).
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        return visitor.visit(this, dir, force);
    }

    @Override
    public void draw() {
        super.draw('.');
    }
}
