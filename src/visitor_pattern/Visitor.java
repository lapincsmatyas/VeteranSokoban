package visitor_pattern;

import models.cells.*;
import push_enums.Direction;
import push_enums.StepResult;

/**
 * A visitor_pattern patternhez szukseges visitor_pattern interface
 */
public interface Visitor {
    /**
     * A visitor_pattern ures mezot latogat
     * @param field az ures mezo referenciaja
     * @param dir ebbe az iranyba lepne a visitor_pattern
     * @return a lepes sikeressege (push_enums.StepResult tipusu)
     */
    StepResult visit(Field field, Direction dir, int force);

    /**
     * A visitor_pattern falat/oszlopot latogat
     * @param pillar az oszlop referenciaja
     * @param dir  ebbe az iranyba lepne a visitor_pattern
     * @return ebbe az iranyba lepne a visitor_pattern
     */
    StepResult visit(Pillar pillar, Direction dir, int force);

    /**
     * A visitor_pattern lyukat latogat
     * @param hole a lyuk referenciaja
     * @param dir ebbe az iranyba lepne a visitor_pattern
     * @return a lepes sikeressege (push_enums.StepResult tipusu)
     */
    StepResult visit(Hole hole, Direction dir, int force);

    /**
     * A visitor_pattern kapcsolot latogat
     * @param lever a kapcsolo referenciaja
     * @param dir ebbe az iranyba lepne a visitor_pattern
     * @return a lepes sikeressege (push_enums.StepResult tipusu)
     */
    StepResult visit(Switch lever, Direction dir, int force);

    /**
     * A visitor_pattern celt latogat
     * @param target a target referenciaja
     * @param dir ebbe az iranyba lepne a visitor_pattern
     * @return a lepes sikeressege (push_enums.StepResult tipusu)
     */
    StepResult visit(Target target, Direction dir, int force);
}
