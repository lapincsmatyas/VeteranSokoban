public abstract class Pushable implements Visitor{
    protected Cell actCell;

    public Pushable(Cell actCell){
        Logger.getInstance().log("Pushable", "Pushable(Cell)");

        this.actCell = actCell;
        actCell.setActPushable(this);

        Logger.getInstance().decIndentDepth();
    }

    public StepResult push(Player actor, Direction dir) {
        Logger.getInstance().log("Pushable", "push(Player, Direction)");

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }

    public StepResult push(Crate actor, Direction dir) {
        Logger.getInstance().log("Pushable", "push(Crate, Direction)");

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }

    public StepResult visit(Pillar pillar, Direction dir) {
        Logger.getInstance().logWithDec("Pushable", "visit(Pillar, Direction)");
        return StepResult.FAIL;
    }

    public void die(){
        Logger.getInstance().logWithDec("Pushable", "die()");
        actCell = null;
    }
}
