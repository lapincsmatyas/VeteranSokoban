/**
 * Tolható osztály. Absztrakt, nem példányosítható.
 */
public abstract class Pushable implements Visitor, Drawable{
    /**
     * A mező, amelyen a tolható tartózkodik.
     */
    protected Cell actCell;
    protected int friction;

    protected char display;
    /**
     * A tolható konstruktora. Beállítja a mezőt, amelyen tartózkodik
     * és a mezőn is beállítja magát ott tartózkodó tolhatóként.
     *
     * @param actCell A mező, amelyen a tolható tartózkodik.
     */
    public Pushable(Cell actCell, int friction){
        display = ' ';
        this.actCell = actCell;
        actCell.setActPushable(this);

        this.friction = friction;
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A játékos objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @param force A tolashoz hasznalt ero
     * @return A tolás sikeressége.
     */
    public StepResult push(Player actor, Direction dir, int force) {

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir, force - friction - actCell.getFriction());
        return result;
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A láda objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @param force A tolashoz hasznalt ero
     * @return A tolás sikeressége.
     */
    public StepResult push(Crate actor, Direction dir, int force) {

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir, force - friction - actCell.getFriction());

        return result;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A tolható ellenőrzi, hogy tud-e lépni az oszlopra.
     * Mindig hamissal fog visszatérni.
     * @param pillar Az oszlop, amit a tolható visitel.
     * @param dir Az irány, amelyről a tolható van.
     * @return A lépés sikeressége, mindig sikertelen.
     */
    public StepResult visit(Pillar pillar, Direction dir, int force) {
        return StepResult.FAIL;
    }

    /**
     * Ha meghívják, akkor a tohlató meghal és nem vesz részt a játékban többé.
     */
    public void die(){
        actCell = null;
    }

    public void draw() {
        System.out.print(this.getDisplay());
    }

    public char getDisplay(){
        return this.display;
    }
}
