/**
 * A láda osztály. A munkások ezt tologathatják a pályán.
 */
public class Crate extends Pushable{
    /**
     * A láda konstruktora.
     *
     * @param actCell Az a mező, amin a láda áll.
     */
    public Crate(Cell actCell){
        super(actCell);
        Logger.getInstance().logWithDec("Crate", "Crate(Cell)");
    }

    /**
     * Azt adja meg, hogy a láda tolható-e még vagy be van ragadva valahova.
     * @return Tolható-e a láda.
     */
    public boolean cratePushable() {
        Logger.getInstance().log("Crate", "craePushable()");

        boolean result = true;

        // TODO implementálni

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A láda ellenőrzi, hogy tud-e lépni a mezőre.
     * @param field A mező, amit a láda visitel.
     * @param dir Az irány, amelyről a láda van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Field field, Direction dir) {
        Logger.getInstance().log("Crate", "visit(Field, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (field.isEmpty()) {
            actCell.stepOff();
            field.stepOn(this);
        } else {
            result = field.getActPushable().push(this, dir);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                field.stepOn(this);
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A láda ellenőrzi, hogy tud-e lépni a lyukra.
     * Ha nyitva van a lyuk, akkor a láda meghal
     * @param hole A lyuk, amit a láda visitel.
     * @param dir Az irány, amelyről a láda van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Hole hole, Direction dir) {
        Logger.getInstance().log("Crate", "visit(Switch, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (hole.isEmpty()) {
            actCell.stepOff();

            if (hole.isOpened()) {
                this.die();
            } else {
                hole.stepOn(this);
            }
        } else {
            result = hole.getActPushable().push(this, dir);
            if (result != StepResult.FAIL) {
                actCell.stepOff();

                if (hole.isOpened()) {
                    this.die();
                } else {
                    hole.stepOn(this);
                }
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A láda ellenőrzi, hogy tud-e lépni a kapcsolóra.
     * Ha rálépett, akkor átállítja a kapcsolót, ami kinyitja a hozzátartozó lyukakat.
     * @param lever A kapcsoló, amit a láda visitel.
     * @param dir Az irány, amelyről a láda van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Switch lever, Direction dir) {
        Logger.getInstance().log("Crate", "visit(Switch, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (lever.isEmpty()) {
            actCell.stepOff();
            lever.stepOn(this);
            lever.change();
        } else {
            result = lever.getActPushable().push(this, dir);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                lever.stepOn(this);
                lever.change();
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A láda ellenőrzi, hogy tud-e lépni a célra.
     * Ha rálépett, akkor a játékos, aki kezdeményezte az egész tolást, ponttal jutalmazódik
     * @param target A cél, amit a láda visitel.
     * @param dir Az irány, amelyről a láda van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Target target, Direction dir) {
        Logger.getInstance().log("Crate", "visit(Switch, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (target.isEmpty()) {
            actCell.stepOff();
            target.stepOn(this);
            result = StepResult.SUCCESS_POINT;
        } else {
            result = target.getActPushable().push(this, dir);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                target.stepOn(this);
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
