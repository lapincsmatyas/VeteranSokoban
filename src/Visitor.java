/**
 * A visitor patternhez szukseges visitor interface
 */
public interface Visitor {
    /**
     * A visitor ures mezot latogat
     * @param field az ures mezo referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
    StepResult visit(Field field, Direction dir, int force);

    /**
     * A visitor falat/oszlopot latogat
     * @param pillar az oszlop referenciaja
     * @param dir  ebbe az iranyba lepne a visitor
     * @return ebbe az iranyba lepne a visitor
     */
    StepResult visit(Pillar pillar, Direction dir, int force);

    /**
     * A visitor lyukat latogat
     * @param hole a lyuk referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
    StepResult visit(Hole hole, Direction dir, int force);

    /**
     * A visitor kapcsolot latogat
     * @param lever a kapcsolo referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
    StepResult visit(Switch lever, Direction dir, int force);

    /**
     * A visitor celt latogat
     * @param target a target referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
    StepResult visit(Target target, Direction dir, int force);
}
