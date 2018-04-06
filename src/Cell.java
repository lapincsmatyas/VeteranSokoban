/**
 * A jatekban talalhato cellak ososztolya.
 * Tartalmazza a szomszedjait es a rajta levo aktualis pushable objektumot
 */

public abstract class  Cell implements Visitable, Drawable{
    protected Pushable actPushable;

    private Cell[] neighbors;

    Slime slime;

    /**
     * A konstruktor inicializalja a szomszedokat.
     */
    public Cell(){
        this.actPushable = null;
        neighbors = new Cell[4];
        for (int i = 0; i < 4; i++) {
            neighbors[i] = null;
        }
        slime = null;
    }

    /**
     * Ezzel a fuggvennyel lekerhetok a szomszedok.
     * @param dir az adott irany
     * @return a dir iranyban levo szomszed referenciaja
     */
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

    /**
     * Ezzel a fuggvennyel lehet ralepni a cellara
     * @param pushable a pushable, ami ralep a cellara
     */
    public void stepOn(Pushable pushable){

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
        actPushable = null;
    }

    /**
     * Ezzel a fuggvennyel beallithato a cellan
     * levo aktualis pushable objektum-
     * @param item
     */
    public void setActPushable(Pushable item) {
        this.actPushable = item;
    }

    /**
     * Ezzel a fuggvennyel lekerdezheto a mezon talalhato
     * aktualis pushable objektum
     * @return a mezon levo aktualis pushable objektum
     */
    public Pushable getActPushable() {
        return actPushable;
    }

    /**
     * Ezzel a fuggvennyel lekerdezheto, hogy az
     * a cellan van-e pushable objektum
     * @return
     */
    public boolean isEmpty() {
        return actPushable == null;
    }

    /**
     * A parameterben megadott Slime-ot lehet vele a mezon elhelyezni.
     * @param slime Ezt a slime-ot helyezi a mezore
     */
    public void putSlime(Slime slime){
        this.slime = slime;
    }

    /**
     * Ezzel a metodussal eltavolithato a mezon levo slime
     */
    public void clearSlime(){
        slime = null;
    }

    public int getFriction(){
        if(slime != null){
            return slime.getFriction();
        } else{
            return 0;
        }
    }
}
