public abstract class Pushable implements Visitor{
    protected Cell actCell;

    public Pushable(Cell actCell){
        this.actCell = actCell;
        actCell.setActPushable(this);
    }

    public boolean push(Player actor, int dir){
        Cell nextCell = actCell.getNext(dir);
        return nextCell.accept(this, dir);
    }

    public boolean push(Crate actor, int dir){
        Cell nextCell = actCell.getNext(dir);
        return nextCell.accept(this, dir);
    }

    public boolean visit(Pillar pillar, int dir) {
        return false;
    }
    public boolean visit(Hole hole, int dir) {
        actCell.stepOff();
        this.die();
        return true;
    }

    public void die(){
        actCell = null;
    }
    public abstract void printPushable();
}
