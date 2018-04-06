/**
 * A visitor patternhez szukseges visitable interface
 */
public interface Visitable {
    public StepResult accept(Visitor visitor, Direction dir, int force);
}
