public abstract class Pushable implements Visitor{
    protected Cell actCell;

    public Pushable(Cell actCell){
        Logger.getInstance().log("Pushable", "Pushable(Cell)");

        this.actCell = actCell;
        actCell.setActPushable(this);

        Logger.getInstance().decIndentDepth();
    }

    public boolean push(Player actor, Direction dir){
        Logger.getInstance().log("Pushable", "push(Player, Direction)");

        Cell nextCell = actCell.getNext(dir);
        boolean result = nextCell.accept(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }

    public boolean push(Crate actor, Direction dir){
        Logger.getInstance().log("Pushable", "push(Crate, Direction)");

        Cell nextCell = actCell.getNext(dir);
        boolean result = nextCell.accept(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }

    public boolean visit(Pillar pillar, Direction dir) {
        Logger.getInstance().logWithDec("Pushable", "visit(Pillar, Direction)");
        return false;
    }

    public boolean visit(Hole hole, Direction dir) {
        Logger.getInstance().log("Pushable", "visit(Hole, Direction)");

        actCell.stepOff();

        if (hole.isOpened()) {
            this.die();
        } else {
            hole.stepOn(this);
        }

        Logger.getInstance().decIndentDepth();
        return true;
    }

    public boolean visit(Switch lever, Direction dir) {
        Logger.getInstance().log("Pushable", "visit(Switch, Direction)");

        //TODO van-e rajta valami, ha nincs akkor lépjen rá vagy tolja el

        Logger.getInstance().decIndentDepth();
        return true;
    }

    public void die(){
        Logger.getInstance().logWithDec("Pushable", "die()");
        actCell = null;
    }
}
