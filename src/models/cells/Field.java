package models.cells;

import push_enums.Direction;
import push_enums.StepResult;
import visitor_pattern.Visitor;

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
     * A visitor_pattern patternt megvalosito accept fuggveny.
     * Meghivja a visitor_pattern visit fuggvenyet.
     * @param visitor Az aktualis visitor_pattern.
     * @param dir Ebbe az iranyba szeretne a visitor_pattern haladni.
     * @param force A tolas erossege.
     * @return A lepes sikeressegevel ter vissza (push_enums.StepResult tipusu).
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        return visitor.visit(this, dir, force);
    }

    @Override
    public String getData() {
        return super.getData('.');
    }
}
