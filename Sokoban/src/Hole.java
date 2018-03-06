public class Hole extends Cell {
    public void open(){}
    public void close(){}

    @Override
    public boolean accept(Visitor visitor, int dir) {
        return false;
    }

    @Override
    public void printCell() {
        System.out.print("O");
    }
}
