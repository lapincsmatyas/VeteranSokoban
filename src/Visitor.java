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
    public StepResult visit(Field field, Direction dir);

    /**
     * A visitor falat/oszlopot latogat
     * @param pillar az oszlop referenciaja
     * @param dir  ebbe az iranyba lepne a visitor
     * @return ebbe az iranyba lepne a visitor
     */
    public StepResult visit(Pillar pillar, Direction dir);

    /**
     * A visitor lyukat latogat
     * @param hole a lyuk referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
    public StepResult visit(Hole hole, Direction dir);

    /**
     * A visitor kapcsolot latogat
     * @param lever a kapcsolo referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
    public StepResult visit(Switch lever, Direction dir);

    /**
     * A visitor celt latogat
     * @param target a target referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
    public StepResult visit(Target target, Direction dir);
}
