public interface Visitable {
    public StepResult accept(Visitor visitor, Direction dir);
}
