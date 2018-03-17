public abstract class  Cell implements Visitable{
    protected Pushable actPushable;

    private Cell[] neighbors;

    public Cell(){
        Logger.getInstance().log("Cell", "Cell()");

        this.actPushable = null;
        neighbors = new Cell[4];
        for (int i = 0; i < 4; i++) {
            neighbors[i] = null;
        }

        Logger.getInstance().decIndentDepth();
    }

    public Cell getNext(Direction dir){
        Logger.getInstance().logWithDec("Cell", "getNext(Direction)");

        switch (dir) {
            case LEFT:
                return neighbors[0];
            case RIGHT:
                return neighbors[1];
            case UP:
                return neighbors[2];
            case DOWN:
                return neighbors[3];
            default:
                return null;
        }
    }

    public void stepOn(Pushable pushable){
        Logger.getInstance().logWithDec("Cell", "stepOn(Pushable)");

        actPushable = pushable;
        actPushable.actCell = this;
    }

    public void setNeighbor(Direction dir, Cell nextCell){
        Logger.getInstance().logWithDec("Cell", "setNeighbor(Direction, Cell)");

        switch (dir) {
            case LEFT:
                neighbors[0] = nextCell;
                break;
            case RIGHT:
                neighbors[1] = nextCell;
                break;
            case UP:
                neighbors[2] = nextCell;
                break;
            case DOWN:
                neighbors[3] = nextCell;
                break;
        }
    }

    public void stepOff(){
        Logger.getInstance().logWithDec("Cell", "stepOff()");
        actPushable = null;
    }

    public void setActPushable(Pushable item) {
        Logger.getInstance().logWithDec("Cell", "setActPushable(Pushable)");
        this.actPushable = item;
    }

    public Pushable getActPushable() {
        Logger.getInstance().logWithDec("Cell", "getActPushable()");
        return actPushable;
    }

    public boolean isEmpty() {
        Logger.getInstance().logWithDec("Cell", "isEmpty()");
        return actPushable == null;
    }
}
