package models.cells;

import push_enums.Direction;
import push_enums.StepResult;
import visitor_pattern.Visitor;

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
     * A visitor_pattern patternt megvalosito accept fuggveny.
     * Meghivja a visitor_pattern visit fuggvenyet.
     * @param visitor Az aktualis visitor_pattern.
     * @param dir A visitor_pattern ebbe az iranyba szeretne lepni.
     * @param force A tolas ereje.
     * @return A lepes sikeressege (push_enums.StepResult tipusu).
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        return visitor.visit(this, dir, force);
    }

    @Override
    public String getData() {
        return super.getData('p');
    }
}
