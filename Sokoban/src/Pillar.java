public class Pillar extends Cell{
    @Override
    public boolean accept(Visitor visitor, Direction dir) {
        return visitor.visit(this, dir);
    }

    @Override
    public void printCell() {
        System.out.print("X");
    }
}
