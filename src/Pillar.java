/**
 * A jatekban levo oszlopokat valositja meg.
 */
public class Pillar extends Cell{
    /**
     * Az osztaly default konstruktora.
     */
    public Pillar() {
        super();
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Meghivja a visitor visit fuggvenyet.
     * @param visitor Az aktualis visitor.
     * @param dir A visitor ebbe az iranyba szeretne lepni.
     * @param force A tolas ereje.
     * @return A lepes sikeressege (StepResult tipusu).
     */
    //TODO implementalni a forceos mukodest, egyelore csak parameterben van
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        StepResult result = visitor.visit(this, dir, force);

        return result;
    }
}
