package models.pushables;

import models.cells.*;
import push_enums.Direction;
import push_enums.StepResult;

/**
 * A lada osztaly. A munkasok ezt tologathatjak a palyan.
 */
public class Crate extends Pushable{
    private boolean onTarget = false;

    /**
     * A lada konstruktora.
     * @param actCell Az a mezo, amin a lada all.
     * @param friction A lada tapadasi surlodasi egyutthatoja.
     */
    public Crate(Cell actCell, int friction) {
        super(actCell, friction);
        actCell.stepOn(this);
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A játékos objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @return A tolás sikeressége.
     */
    @Override
    public StepResult push(Player actor, Direction dir, int force) {
        if (onTarget) {
            return StepResult.FAIL;
        }

        Cell nextCell = actCell.getNext(dir);

        if (nextCell == null) {
            return StepResult.FAIL;
        }

        return nextCell.accept(this, dir, force);
    }

    /**
     * Odébbtolja a tolhatót, ha az lehetséges.
     * @param actor A láda objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a tolhatót.
     * @return A tolás sikeressége.
     */
    @Override
    public StepResult push(Crate actor, Direction dir, int force) {
        if (onTarget) {
            return StepResult.FAIL;
        }

        Cell nextCell = actCell.getNext(dir);

        if (nextCell == null) {
            return StepResult.FAIL;
        }

        return nextCell.accept(this, dir, force);
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
            if (!disabled) {
                actCell.stepOff();
                field.stepOn(this);
            }
        } else {
            result = field.getActPushable().push(this, dir, force - this.actCell.getFriction());
            if (result != StepResult.FAIL && !disabled) {
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

        if (hole.isEmpty()) {
            if (!disabled) {
                actCell.stepOff();
                hole.stepOn(this);
            }
        } else {
            result = hole.getActPushable().push(this, dir, force - this.actCell.getFriction());
            if (result != StepResult.FAIL && !disabled) {
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
            if (!disabled) {
                actCell.stepOff();
                lever.stepOn(this);
                lever.change();
            }
        } else {
            result = lever.getActPushable().push(this, dir, force - this.actCell.getFriction());
            if (result != StepResult.FAIL && !disabled) {
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
        StepResult result = StepResult.FAIL;
        if (force < this.actCell.getFriction()) {
            return StepResult.FAIL;
        }

        if (target.isEmpty()) {
            if (!disabled) {
                actCell.stepOff();
                target.stepOn(this);
                result = StepResult.SUCCESS_POINT;
                onTarget = true;
            }
        } else {
            result = target.getActPushable().push(this, dir, force - this.actCell.getFriction());
            if (result != StepResult.FAIL && !disabled) {
                actCell.stepOff();
                target.stepOn(this);
            }
        }

        return result;
    }

    @Override
    public String getData() {
        return "c";
    }
}
