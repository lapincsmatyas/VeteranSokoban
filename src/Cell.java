import java.util.HashMap;
import java.util.Map;

/**
 * A jatekban talalhato cellak ososztolya.
 * Tartalmazza a szomszedjait es a rajta levo aktualis pushable objektumot
 */

<<<<<<< .merge_file_a12808
public abstract class Cell implements Visitable, Drawable {
    /**
     * A Pushable, ami eppen a mezon all.
     */
=======
public abstract class  Cell implements Visitable, Drawable{
>>>>>>> .merge_file_a00612
    protected Pushable actPushable;

    /**
     * A mezo szomszedos mezoi.
     */
    private Map<Direction, Cell> neighbors;

    /**
     * A Slime, ami a mezon van.
     */
    protected Slime slime;

    Slime slime;

    /**
     * A konstruktor inicializalja a szomszedokat.
     */
    public Cell(){
        this.actPushable = null;
<<<<<<< .merge_file_a12808

        neighbors = new HashMap<>();
        neighbors.put(Direction.LEFT, null);
        neighbors.put(Direction.RIGHT, null);
        neighbors.put(Direction.DOWN, null);
        neighbors.put(Direction.UP, null);

=======
        neighbors = new Cell[4];
        for (int i = 0; i < 4; i++) {
            neighbors[i] = null;
        }
>>>>>>> .merge_file_a00612
        slime = null;
    }

    /**
     * Ezzel a fuggvennyel lekerhetok a szomszedok.
     * @param dir az adott irany
     * @return a dir iranyban levo szomszed referenciaja
     */
    public Cell getNext(Direction dir){
<<<<<<< .merge_file_a12808
        return neighbors.get(dir);
=======

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
>>>>>>> .merge_file_a00612
    }

    /**
     * Ezzel a fuggvennyel lehet ralepni a cellara
     * @param pushable a pushable, ami ralep a cellara
     */
    public void stepOn(Pushable pushable){
<<<<<<< .merge_file_a12808
=======

>>>>>>> .merge_file_a00612
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
<<<<<<< .merge_file_a12808
        neighbors.put(dir, nextCell);
=======

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
>>>>>>> .merge_file_a00612
    }

    /**
     * Ezzel a fuggvennyel lelephetunk a cellarol
     */
<<<<<<< .merge_file_a12808
    public void stepOff()
    {
=======
    public void stepOff(){
>>>>>>> .merge_file_a00612
        actPushable = null;
    }

    /**
<<<<<<< .merge_file_a12808
=======
     * Ezzel a fuggvennyel beallithato a cellan
     * levo aktualis pushable objektum-
     * @param item
     */
    public void setActPushable(Pushable item) {
        this.actPushable = item;
    }

    /**
>>>>>>> .merge_file_a00612
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
     * @return Ures-e a mezo.
     */
    public boolean isEmpty() {
        return actPushable == null;
    }

    /**
<<<<<<< .merge_file_a12808
     * Lehelyezi a parameterben megadott Slimeot a mezore.
     *
     * @param slime A kenoanyag, amit le szeretnenek helyezni.
     */
    public void putSlime(Slime slime) {
=======
     * A parameterben megadott Slime-ot lehet vele a mezon elhelyezni.
     * @param slime Ezt a slime-ot helyezi a mezore
     */
    public void putSlime(Slime slime){
>>>>>>> .merge_file_a00612
        this.slime = slime;
    }

    /**
<<<<<<< .merge_file_a12808
     * Eltunteti a Slimeot a mezorol.
     */
    public void clearSlime() {
        this.slime = null;
    }

    /**
     * Visszaadja, hogy mennyi a tapadasi surlodasi egyutthatoja a mezonek.
     * @return A tapadasi surlodasi egyutthato.
     */
    public int getFriction() {
        int friction = 0;

        if (slime != null) {
            friction += slime.getFriction();
        }

        if (actPushable != null) {
            friction += actPushable.getFriction();
        }

        return friction;
    }

    public void draw(char toDraw) {
        boolean pushbool = actPushable != null;
        boolean slimebool = slime != null;

        if (pushbool || slimebool) {
            System.out.print("{");
        }

        System.out.print(toDraw);

        if (slime != null) {
            slime.draw();
        }

        if (actPushable != null) {
            actPushable.draw();
        }

        if (pushbool || slimebool) {
            System.out.print("}");
=======
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
>>>>>>> .merge_file_a00612
        }
    }
}
