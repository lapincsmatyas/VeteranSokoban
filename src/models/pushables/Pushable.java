package models.pushables;

import models.ViewData;
import models.cells.Cell;
import models.cells.Pillar;
import push_enums.Direction;
import push_enums.StepResult;
import visitor_pattern.Visitor;

/**
 * Tolható osztály. Absztrakt, nem példányosítható.
 */
public abstract class Pushable implements Visitor, ViewData {
    /**
     * Azt jelzi, hogy el-e meg a models.pushables.Pushable
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

    protected boolean disabled;

    /**
     * A tolható konstruktora. Beállítja a mezőt, amelyen tartózkodik
     * és a mezőn is beállítja magát ott tartózkodó tolhatóként.
     *
     * @param actCell A mező, amelyen a tolható tartózkodik.
     * @param friction A models.pushables.Pushable tapadasi surlodasi egyutthatoja.
     */
    public Pushable(Cell actCell, int friction) {
        this.actCell = actCell;
        this.friction = friction;
        disabled = false;
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A játékos objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @return A tolás sikeressége.
     */
    public abstract StepResult push(Player actor, Direction dir, int force);

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A láda objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @return A tolás sikeressége.
     */
    public abstract StepResult push(Crate actor, Direction dir, int force);

    /**
     * A visitor_pattern.Visitor patternt megvalósító függvény.
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
        if(actCell != null) {
            actCell.stepOff();
            actCell = null;
        }
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
     * Azt lehet lekerdezni vele, hogy a models.pushables.Pushable halott-e.
     *
     * @return Halott-e a models.pushables.Pushable.
     */
    public boolean isDead() {
        return dead;
    }

    public void setActCell(Cell actCell) {
        this.actCell = actCell;
    }

    public void enable() {
        disabled = false;
    }

    public void disable() {
        disabled = true;
    }

    public Cell getActCell() {
        return actCell;
    }
}
