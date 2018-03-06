public class Player extends Pushable{
    int points;
    public Player(Cell actCell) {
        super(actCell);
        points = 0;
    }

    public boolean visit(Field cell, int dir) {
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

    public boolean visit(Switch lever, int dir) {
        //TODO van-e rajta valami, ha nincs akkor lépjen rá vagy tolja el
        lever.change();
        return true;
    }

    public boolean push(int dir) {
        Cell nextCell = actCell.getNext(dir);
        return nextCell.accept(this, dir);
    }

    public boolean push(Player actor, int dir) {
        return false;
    }

    @Override
    public void printPushable() {
        System.out.print("o");
    }

    public void addOnePoint(){
        points++;
    }
    public void removeOnePoint(){
        points--;
    }

    public boolean move(int dir){
        return push(dir);
    };


}
