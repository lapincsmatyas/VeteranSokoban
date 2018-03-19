/**
 * Tolható osztály. Absztrakt, nem példányosítható.
 */
public abstract class Pushable implements Visitor{
    /**
     * A mező, amelyen a tolható tartózkodik.
     */
    protected Cell actCell;

    /**
     * A tolható konstruktora. Beállítja a mezőt, amelyen tartózkodik
     * és a mezőn is beállítja magát ott tartózkodó tolhatóként.
     *
     * @param actCell A mező, amelyen a tolható tartózkodik.
     */
    public Pushable(Cell actCell){
        Logger.getInstance().log("Pushable", "Pushable(Cell)");

        this.actCell = actCell;
        actCell.setActPushable(this);

        Logger.getInstance().decIndentDepth();
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A játékos objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @return A tolás sikeressége.
     */
    public StepResult push(Player actor, Direction dir) {
        Logger.getInstance().log("Pushable", "push(Player, Direction)");

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A láda objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @return A tolás sikeressége.
     */
    public StepResult push(Crate actor, Direction dir) {
        Logger.getInstance().log("Pushable", "push(Crate, Direction)");

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir);

        Logger.getInstance().decIndentDepth();
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
    public StepResult visit(Pillar pillar, Direction dir) {
        Logger.getInstance().logWithDec("Pushable", "visit(Pillar, Direction)");
        return StepResult.FAIL;
    }

    /**
     * Ha meghívják, akkor a tohlató meghal és nem vesz részt a játékban többé.
     */
    public void die(){
        Logger.getInstance().logWithDec("Pushable", "die()");
        actCell = null;
    }
}
