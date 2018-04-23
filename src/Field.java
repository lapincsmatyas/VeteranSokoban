/**
 * Ez az osztaly valositja meg az ures mezoket
 */
public class Field extends Cell {
<<<<<<< .merge_file_a00612
    /**
     * Az ures mezo osztaly konstruktora.
     */
    public Field() {
        super();
=======
    private char display;
    public Field() {
        super();
        display = '.';
>>>>>>> .merge_file_a13892
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
<<<<<<< .merge_file_a00612
        return visitor.visit(this, dir, force);
    }

    @Override
    public void draw() {
        super.draw('.');
=======
        StepResult result = visitor.visit(this, dir, force);
        return result;
    }


    @Override
    public void draw() {
        if(actPushable != null){
            actPushable.draw();
        }
        else
            System.out.print(display);
>>>>>>> .merge_file_a13892
    }
}
