public class Target extends Cell {
    @Override
    public boolean accept(Visitor visitor, Direction dir) {
        return false;
    }
}
