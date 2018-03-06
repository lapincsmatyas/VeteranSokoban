public abstract class  Cell implements Visitable{
    protected Pushable actPushable;

    private Cell[] neighbors;
    private Cell up = null;
    private Cell right = null;
    private Cell down = null;
    private Cell left = null;

    public Cell(){
        this.actPushable = null;
        neighbors = new Cell[4];
        for (int i = 0; i < 4; i++) {
            neighbors[i] = null;
        }
    }


    public Cell getNext(Direction dir){
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
        actPushable = pushable;
        actPushable.actCell = this;
    }

    public void setNeighbor(Direction dir, Cell nextCell){
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
        actPushable = null;
    }

    public void setActPushable(Pushable item) {
        this.actPushable = item;
    }

    public Pushable getActPushable() {
        return actPushable;
    }

    public void printCell(){
        System.out.print("[");
        if(actPushable != null){
            actPushable.printPushable();
        }
        System.out.print("] ");
    }

    public boolean isEmpty() {
        return actPushable == null;
    }
}
