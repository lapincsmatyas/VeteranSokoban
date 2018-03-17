public class Target extends Cell {
    @Override
    public boolean accept(Visitor visitor, Direction dir) {
        Logger.getInstance().logWithDec("Target", "accept(Visitor, Direction)");

        boolean result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
