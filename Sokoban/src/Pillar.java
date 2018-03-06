public class Pillar extends Cell{
    @Override
    public boolean accept(Visitor visitor, int dir) {
        return false;
    }

    @Override
    public void printCell() {
        System.out.print("X");
    }
}
