public class Field extends Cell {
    @Override
    public boolean accept(Visitor visitor, Direction dir) {
        return visitor.visit(this, dir);
    }
}
