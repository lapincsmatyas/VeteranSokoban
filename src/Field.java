public class Field extends Cell {
    @Override
    public boolean accept(Visitor visitor, Direction dir) {
        Logger.getInstance().log("Field", "accept(Visitor, Direction)");

        boolean result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
