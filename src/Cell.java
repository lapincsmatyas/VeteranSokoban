/**
 * A jatekban talalhato cellak ososztolya.
 * Tartalmazza a szomszedjait es a rajta levo aktualis pushable objektumot
 */

public abstract class  Cell implements Visitable{
    protected Pushable actPushable;

    private Cell[] neighbors;

    /**
     * A konstruktor inicializalja a szomszedokat.
     */
    public Cell(){
        Logger.getInstance().log("Cell", "Cell()");

        this.actPushable = null;
        neighbors = new Cell[4];
        for (int i = 0; i < 4; i++) {
            neighbors[i] = null;
        }

        Logger.getInstance().decIndentDepth();
    }

    /**
     * Ezzel a fuggvennyel lekerhetok a szomszedok.
     * @param dir az adott irany
     * @return a dir iranyban levo szomszed referenciaja
     */
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

    /**
     * Ezzel a fuggvennyel lehet ralepni a cellara
     * @param pushable a pushable, ami ralep a cellara
     */
    public void stepOn(Pushable pushable){
        Logger.getInstance().logWithDec("Cell", "stepOn(Pushable)");

        actPushable = pushable;
        actPushable.actCell = this;
    }

    /**
     * Ezzel a fuggvennyel adhato meg egy cella adott iranyban
     * levo szomszedja
     * @param dir irany
     * @param nextCell a dir-en levo szomszed
     */
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

    /**
     * Ezzel a fuggvennyel lelephetunk a cellarol
     */
    public void stepOff(){
        Logger.getInstance().logWithDec("Cell", "stepOff()");
        actPushable = null;
    }

    /**
     * Ezzel a fuggvennyel beallithato a cellan
     * levo aktualis pushable objektum-
     * @param item
     */
    public void setActPushable(Pushable item) {
        Logger.getInstance().logWithDec("Cell", "setActPushable(Pushable)");
        this.actPushable = item;
    }

    /**
     * 
     * @return
     */
    public Pushable getActPushable() {
        Logger.getInstance().logWithDec("Cell", "getActPushable()");
        return actPushable;
    }

    public boolean isEmpty() {
        Logger.getInstance().logWithDec("Cell", "isEmpty()");
        return actPushable == null;
    }
}
