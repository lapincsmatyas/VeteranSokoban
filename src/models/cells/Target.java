package models.cells;

import push_enums.Direction;
import push_enums.StepResult;
import visitor_pattern.Visitor;

/**
 * A jatekban talalhato celmezoket valositja meg.
 */
public class Target extends Cell {
    /**
     * Az osztaly default konstruktora.
     */
    public Target() {
        super();
    }

    /**
     * A visitor_pattern pattern megvalositasahoz szukseges accept fuggveny.
     * Meghivja az aktualis visitor_pattern visit fuggvenyet.
     * @param visitor Az aktualis visitor_pattern.
     * @param dir Ebbe az iranyba szeretne lepni a visitor_pattern.
     * @param force A tolas ereje.
     * @return A lepes sikeressege (push_enums.StepResult tipusu).
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        return visitor.visit(this, dir, force);
    }

    @Override
    public String getData() {
        return super.getData('t');
    }
}
