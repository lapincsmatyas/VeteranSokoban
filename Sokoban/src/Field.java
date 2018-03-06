public class Field extends Cell {
    @Override
    public boolean accept(Visitor visitor, int dir) {
        return visitor.visit(this, dir);
    }
}
