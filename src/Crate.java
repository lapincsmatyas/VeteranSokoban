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
        if (force < this.actCell.getFriction()) {
            return StepResult.FAIL;
        }

        if (field.isEmpty()) {
            actCell.stepOff();
            field.stepOn(this);
        } else {
            result = field.getActPushable().push(this, dir, force - this.actCell.getFriction());
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
        if (force < this.actCell.getFriction()) {
            return StepResult.FAIL;
        }

        //Csak gyakorlok

        if (hole.isEmpty()) {
            actCell.stepOff();
            hole.stepOn(this);
        } else {
            result = hole.getActPushable().push(this, dir, force - this.actCell.getFriction());
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                hole.stepOn(this);
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
        if (force < this.actCell.getFriction()) {
            return StepResult.FAIL;
        }

        if (lever.isEmpty()) {
            actCell.stepOff();
            lever.stepOn(this);
            lever.change();
        } else {
            result = lever.getActPushable().push(this, dir, force - this.actCell.getFriction());
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
        StepResult result;
        if (force < this.actCell.getFriction()) {
            return StepResult.FAIL;
        }

        if (target.isEmpty()) {
            actCell.stepOff();
            target.stepOn(this);
            result = StepResult.SUCCESS_POINT;
        } else {
            result = target.getActPushable().push(this, dir, force - this.actCell.getFriction());
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                target.stepOn(this);
            }
        }

        return result;
    }
}
