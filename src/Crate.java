/**
 * A láda osztály. A munkások ezt tologathatják a pályán.
 */
public class Crate extends Pushable{

    /**
     * A láda konstruktora.
     *
     * @param actCell Az a mező, amin a láda áll.
     * @param friction A surlodas, amivel a padlot nyomja maga alatt
     */
    public Crate(Cell actCell, int friction){
        super(actCell, friction);
        display = 'c';
    }

    /**
     * Azt adja meg, hogy a láda tolható-e még vagy be van ragadva valahova.
     * @return Tolható-e a láda.
     */
    public boolean cratePushable() {
        boolean result = true;
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
    public StepResult visit(Field field, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;
        if (field.isEmpty()) {
            if(force > 0) {
                actCell.stepOff();
                field.stepOn(this);
            } else
                result = StepResult.FAIL;
        } else {
            result = field.getActPushable().push(this, dir, force);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                field.stepOn(this);
            }
        }
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
    public StepResult visit(Hole hole, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;
        if (hole.isEmpty()) {
            if(force > 0) {
                actCell.stepOff();

                if (hole.isOpened()) {
                    this.die();
                } else {
                    hole.stepOn(this);
                }
            } else{
                result = StepResult.FAIL;
            }
        } else {
            result = hole.getActPushable().push(this, dir, force);
            if (result != StepResult.FAIL) {
                actCell.stepOff();

                if (hole.isOpened()) {
                    this.die();
                } else {
                    hole.stepOn(this);
                }
            }
        }
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
    public StepResult visit(Switch lever, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;
        if (lever.isEmpty()) {
            if(force >0) {
                actCell.stepOff();
                lever.stepOn(this);
                lever.change();
            }  else {
                result = StepResult.FAIL;
            }
        } else {
            result = lever.getActPushable().push(this, dir, force);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                lever.stepOn(this);
                lever.change();
            }
        }
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
    public StepResult visit(Target target, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;
        if (target.isEmpty()) {
            if(force >0) {
                actCell.stepOff();
                target.stepOn(this);
                result = StepResult.SUCCESS_POINT;
            } else{
                result = StepResult.FAIL;
            }
        } else {
            result = target.getActPushable().push(this, dir, force);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                target.stepOn(this);
            }
        }
        return result;
    }

    /**
     * Ezzel e metodussal lekerdezheto a lada tapadasi surlodasi egyutthatoja.
     * @return a lada tapadasi egyutthatoja
     */
    public int getFriction(){
        return friction;
}


}
