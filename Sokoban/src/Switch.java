public class Switch extends Cell {



    @Override
    public boolean accept(Visitor visitor, int dir) {
        return visitor.visit(this, dir);
    }

    public void change(){

    }
}
