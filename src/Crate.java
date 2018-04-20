/**
 * A lada osztaly. A munkasok ezt tologathatjak a palyan.
 */
public class Crate extends Pushable{
    /**
     * A lada konstruktora.
     * @param actCell Az a mezo, amin a lada all.
     * @param friction A lada tapadasi surlodasi egyutthatoja.
     */
    public Crate(Cell actCell, int friction) {
        super(actCell, friction);
    }

    /**
     * Azt adja meg, hogy a lada tolhato-e meg vagy be van ragadva valahova.
     * @return Tolhato-e a lada.
     */
    public boolean cratePushable() {
        boolean result = true;

        // TODO implementálni

        return result;
    }

    /**
     * A Visitor patternt megvalosito fuggveny.
     * A lada ellenorzi, hogy tud-e lepni a mezore.
     * @param field A mezo, amit a lada visitel.
     * @param dir Az irany, amelyrol a lada van.
     * @param force Az ero, amivel a ladat toljak.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Field field, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;
        if (field.isEmpty() && force >= field.getFriction()) {
            actCell.stepOff();
            field.stepOn(this);
        } else if (force >= field.getFriction()) {
            result = field.getActPushable().push(this, dir, force - field.getFriction());
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                field.stepOn(this);
            }
        }

        return result;
    }

    /**
     * A Visitor patternt megvalosito fuggveny.
     * A lada ellenorzi, hogy tud-e lepni a lyukra.
     * Ha nyitva van a lyuk, akkor a láda meghal
     * @param hole A lyuk, amit a lada visitel.
     * @param dir Az irany, amelyrol a lada van.
     * @param force Az ero, amivel a ladat toljak.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Hole hole, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;
        if (hole.isEmpty() && force >= hole.getFriction()) {
            actCell.stepOff();

            if (hole.isOpened()) {
                this.die();
            } else {
                hole.stepOn(this);
            }
        } else if (force >= hole.getFriction()) {
            result = hole.getActPushable().push(this, dir, force - hole.getFriction());
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
     * A Visitor patternt megvalosito fuggveny.
     * A lada ellenorzi, hogy tud-e lepni a kapcsolora.
     * Ha ralepett, akkor atallitja a kapcsolot, ami kinyitja a hozzatartozo lyukakat.
     * @param lever A kapcsolo, amit a lada visitel.
     * @param dir Az irany, amelyrol a lada van.
     * @param force Az ero, amivel a ladat toljak.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Switch lever, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;
        if (lever.isEmpty() && force >= lever.getFriction()) {
            actCell.stepOff();
            lever.stepOn(this);
            lever.change();
        } else if (force >= lever.getFriction()) {
            result = lever.getActPushable().push(this, dir, force - lever.getFriction());
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                lever.stepOn(this);
                lever.change();
            }
        }

        return result;
    }

    /**
     * A Visitor patternt megvalosito fuggveny.
     * A lada ellenorzi, hogy tud-e lepni a kapcsolora.
     * Ha ralepett, akkor a jatekos, aki kezdemenyezte az egesz tolast, ponttal jutalmazodik
     * @param target A cel, amit a lada visitel.
     * @param dir Az irany, amelyrol a lada van.
     * @param force Az ero, amivel a ladat toljak.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Target target, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;
        if (target.isEmpty() && force >= target.getFriction()) {
            actCell.stepOff();
            target.stepOn(this);
            result = StepResult.SUCCESS_POINT;
        } else if (force >= target.getFriction()) {
            result = target.getActPushable().push(this, dir, force - target.getFriction());
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                target.stepOn(this);
            }
        }

        return result;
    }
}
