/**
 * Tolható osztály. Absztrakt, nem példányosítható.
 */
<<<<<<< .merge_file_a12720
public abstract class Pushable implements Visitor, Drawable {
    /**
     * Azt jelzi, hogy el-e meg a Pushable
     */
    private boolean dead = false;

=======
public abstract class Pushable implements Visitor, Drawable{
>>>>>>> .merge_file_a11604
    /**
     * A mező, amelyen a tolható tartózkodik.
     */
    protected Cell actCell;
    protected int friction;

    protected char display;
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
<<<<<<< .merge_file_a12720
    public Pushable(Cell actCell, int friction) {
=======
    public Pushable(Cell actCell, int friction){
        display = ' ';
>>>>>>> .merge_file_a11604
        this.actCell = actCell;
        actCell.stepOn(this);

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
<<<<<<< .merge_file_a12720
        Cell nextCell = actCell.getNext(dir);

        return nextCell.accept(this, dir, force);
=======

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir, force - friction - actCell.getFriction());
        return result;
>>>>>>> .merge_file_a11604
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A láda objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @param force A tolashoz hasznalt ero
     * @return A tolás sikeressége.
     */
    public StepResult push(Crate actor, Direction dir, int force) {
<<<<<<< .merge_file_a12720
        Cell nextCell = actCell.getNext(dir);

        return nextCell.accept(this, dir, force);
=======

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir, force - friction - actCell.getFriction());

        return result;
>>>>>>> .merge_file_a11604
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
<<<<<<< .merge_file_a12720
        actCell.stepOff();
=======
>>>>>>> .merge_file_a11604
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

    public void draw() {
        System.out.print(this.getDisplay());
    }

    public char getDisplay(){
        return this.display;
    }
}
