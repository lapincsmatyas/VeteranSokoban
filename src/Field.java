public class Field extends Cell {
    public Field() {
        super();
        Logger.getInstance().logWithDec("Field", "Field()");
    }

    @Override
    public StepResult accept(Visitor visitor, Direction dir) {
        Logger.getInstance().log("Field", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
