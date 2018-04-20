/**
 * Tolható osztály. Absztrakt, nem példányosítható.
 */
public abstract class Pushable implements Visitor{
    /**
     * Azt jelzi, hogy el-e meg a Pushable
     */
    private boolean dead = false;

    /**
     * A mező, amelyen a tolható tartózkodik.
     */
    protected Cell actCell;

    /**
     * A tapadasi surlodasi egyutthato.
     */
    protected int friction;

    /**
     * A tolható konstruktora. Beállítja a mezőt, amelyen tartózkodik
     * és a mezőn is beállítja magát ott tartózkodó tolhatóként.
     *
     * @param actCell A mező, amelyen a tolható tartózkodik.
     * @param friction A Pushable tapadasi surlodasi egyutthatoja.
     */
    public Pushable(Cell actCell, int friction) {
        this.actCell = actCell;
        actCell.stepOn(this);

        this.friction = friction;
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A játékos objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @return A tolás sikeressége.
     */
    public StepResult push(Player actor, Direction dir, int force) {
        Cell nextCell = actCell.getNext(dir);

        return nextCell.accept(this, dir, force);
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A láda objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @return A tolás sikeressége.
     */
    public StepResult push(Crate actor, Direction dir, int force) {
        Cell nextCell = actCell.getNext(dir);

        return nextCell.accept(this, dir, force);
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
        actCell.stepOff();
        actCell = null;
        dead = true;
    }

    /**
     * A tapadasi surlodasi egyutthatot adja vissza.
     *
     * @return A tapadasi surlodasi egyutthato.
     */
    public int getFriction() {
        return friction;
    }

    /**
     * Azt lehet lekerdezni vele, hogy a Pushable halott-e.
     *
     * @return Halott-e a Pushable.
     */
    public boolean isDead() {
        return dead;
    }
}
