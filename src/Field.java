/**
 * Ez az osztaly valositja meg az ures mezoket
 */
public class Field extends Cell {
    private char display;
    public Field() {
        super();
        display = '.';
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Meghivja a visitor visit fuggvenyet.
     * @param visitor az aktualis visitor
     * @param dir ebbe az iranyba szeretne a visitor haladni
     * @return a lepes sikeressegevel ter vissza (StepResult tipusu)
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
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
    }
}
