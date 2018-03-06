import java.util.ArrayList;
import java.util.List;

public abstract class Cell implements Visitable{
    protected Pushable actPushable;

    Cell[] neighbors;
    Cell up;
    Cell right;
    Cell down;
    Cell left;

    public Cell(){
        this.actPushable = null;
        neighbors = new Cell[4];
    }


    public Cell getNext(int dir){
        return neighbors[dir];
    }

    public void stepOn(Pushable pushable){
        actPushable = pushable;
        actPushable.actCell = this;
    }

    public void setNeighbor(int dir, Cell nextCell){
        neighbors[dir] = nextCell;
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
        boolean empty = actPushable == null;
        return empty;
    }
}
