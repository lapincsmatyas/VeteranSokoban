public class Pillar extends Cell{
    @Override
    public boolean accept(Visitor visitor, Direction dir) {
        Logger.getInstance().log("Pillar", "accept(Visitor, Direction)");

        boolean result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
