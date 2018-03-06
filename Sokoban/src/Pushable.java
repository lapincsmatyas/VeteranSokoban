public abstract class Pushable implements Visitor{
    protected Cell actCell;

    public Pushable(Cell actCell){
        this.actCell = actCell;
        actCell.setActPushable(this);
    }

    public boolean push(Player actor, Direction dir){
        Cell nextCell = actCell.getNext(dir);
        return nextCell.accept(this, dir);
    }

    public boolean push(Crate actor, Direction dir){
        Cell nextCell = actCell.getNext(dir);
        return nextCell.accept(this, dir);
    }

    public boolean visit(Pillar pillar, Direction dir) {
        return false;
    }

    public boolean visit(Hole hole, Direction dir) {
        actCell.stepOff();
        this.die();
        return true;
    }
    public boolean visit(Switch lever, Direction dir) {
        //TODO van-e rajta valami, ha nincs akkor lépjen rá vagy tolja el
        return true;
    }

    public void die(){
        actCell = null;
    }
    public abstract void printPushable();
}
