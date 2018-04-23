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
<<<<<<< .merge_file_a10196
    StepResult visit(Field field, Direction dir, int force);
=======
    public StepResult visit(Field field, Direction dir, int force);
>>>>>>> .merge_file_a07436

    /**
     * A visitor falat/oszlopot latogat
     * @param pillar az oszlop referenciaja
     * @param dir  ebbe az iranyba lepne a visitor
     * @return ebbe az iranyba lepne a visitor
     */
<<<<<<< .merge_file_a10196
    StepResult visit(Pillar pillar, Direction dir, int force);
=======
    public StepResult visit(Pillar pillar, Direction dir, int force);
>>>>>>> .merge_file_a07436

    /**
     * A visitor lyukat latogat
     * @param hole a lyuk referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
<<<<<<< .merge_file_a10196
    StepResult visit(Hole hole, Direction dir, int force);
=======
    public StepResult visit(Hole hole, Direction dir, int force);
>>>>>>> .merge_file_a07436

    /**
     * A visitor kapcsolot latogat
     * @param lever a kapcsolo referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
<<<<<<< .merge_file_a10196
    StepResult visit(Switch lever, Direction dir, int force);
=======
    public StepResult visit(Switch lever, Direction dir, int force);
>>>>>>> .merge_file_a07436

    /**
     * A visitor celt latogat
     * @param target a target referenciaja
     * @param dir ebbe az iranyba lepne a visitor
     * @return a lepes sikeressege (StepResult tipusu)
     */
<<<<<<< .merge_file_a10196
    StepResult visit(Target target, Direction dir, int force);
=======
    public StepResult visit(Target target, Direction dir, int force);
>>>>>>> .merge_file_a07436
}
