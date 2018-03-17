public class Target extends Cell {
    public Target() {
        super();
        Logger.getInstance().logWithDec("Target", "Target()");
    }

    @Override
    public StepResult accept(Visitor visitor, Direction dir) {
        Logger.getInstance().logWithDec("Target", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
