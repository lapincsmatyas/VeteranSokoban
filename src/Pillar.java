public class Pillar extends Cell{
    public Pillar() {
        super();
        Logger.getInstance().logWithDec("Pillar", "Pillar()");
    }

    @Override
    public StepResult accept(Visitor visitor, Direction dir) {
        Logger.getInstance().log("Pillar", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
