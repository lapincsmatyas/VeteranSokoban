/**
 * A visitor patternhez szukseges visitable interface
 */
public interface Visitable {
    StepResult accept(Visitor visitor, Direction dir, int force);
}
