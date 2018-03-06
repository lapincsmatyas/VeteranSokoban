public class Hole extends Cell {
    private boolean opened = false;

    public void open() {
        opened = true;
    }

    public void close() {
        opened = false;
    }

    public void change() {
        opened = !opened;
    }

    public boolean isOpened() {
        return opened;
    }

    @Override
    public boolean accept(Visitor visitor, Direction dir) {
        return visitor.visit(this, dir);
    }

    @Override
    public void printCell() {
        System.out.print("O");
    }
}
