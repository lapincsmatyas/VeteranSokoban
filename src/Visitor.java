public interface Visitor {
    public StepResult visit(Field field, Direction dir);

    public StepResult visit(Pillar pillar, Direction dir);

    public StepResult visit(Hole hole, Direction dir);

    public StepResult visit(Switch lever, Direction dir);

    public StepResult visit(Target target, Direction dir);
}
