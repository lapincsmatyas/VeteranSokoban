public class Hole extends Cell {
    public void open(){}
    public void close(){}

    @Override
    public boolean accept(Visitor visitor, Direction dir) {
        return visitor.visit(this, dir);
    }

    @Override
    public void printCell() {
        System.out.print("O");
    }
}
