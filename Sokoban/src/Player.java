public class Player extends Pushable{
    private int points;

    public Player(Cell actCell) {
        super(actCell);
        points = 0;
    }

    public boolean visit(Field cell, Direction dir) {
        if (cell.isEmpty()) {
            actCell.stepOff();
            cell.stepOn(this);
            return true;
        } else {
            boolean isPushSuccess = cell.getActPushable().push(this, dir);
            if (isPushSuccess) {
                actCell.stepOff();
                cell.stepOn(this);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean visit(Hole hole, Direction dir) {
        if (hole.isOpened()) {
            this.die();
            return true;
        }



        return false;
    }

    public boolean visit(Switch lever, Direction dir) {
        //TODO van-e rajta valami, ha nincs akkor lépjen rá vagy tolja el
        lever.change();
        return true;
    }

    public boolean push(Direction dir) {
        Cell nextCell = actCell.getNext(dir);
        return nextCell.accept(this, dir);
    }

    public boolean push(Player actor, Direction dir) {
        return false;
    }

    @Override
    public void printPushable() {
        System.out.print("o");
    }

    public void addOnePoint(){
        points++;
    }

    public boolean move(Direction dir){
        return push(dir);
    };
}
