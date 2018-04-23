/**
 * A lada osztaly. A munkasok ezt tologathatjak a palyan.
 */
public class Crate extends Pushable{

    /**
<<<<<<< .merge_file_a13368
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
=======
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
>>>>>>> .merge_file_a11008
     */
    @Override
    public StepResult visit(Field field, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;
        if (force < this.actCell.getFriction()) {
            return StepResult.FAIL;
        }

        if (field.isEmpty()) {
            if(force > 0) {
                actCell.stepOff();
                field.stepOn(this);
            } else
                result = StepResult.FAIL;
        } else {
<<<<<<< .merge_file_a13368
            result = field.getActPushable().push(this, dir, force - this.actCell.getFriction());
=======
            result = field.getActPushable().push(this, dir, force);
>>>>>>> .merge_file_a11008
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                field.stepOn(this);
            }
        }
<<<<<<< .merge_file_a13368

=======
>>>>>>> .merge_file_a11008
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
<<<<<<< .merge_file_a13368
            actCell.stepOff();
            hole.stepOn(this);
        } else {
            result = hole.getActPushable().push(this, dir, force - this.actCell.getFriction());
=======
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
>>>>>>> .merge_file_a11008
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                hole.stepOn(this);
            }
        }
<<<<<<< .merge_file_a13368

=======
>>>>>>> .merge_file_a11008
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
            if(force >0) {
                actCell.stepOff();
                lever.stepOn(this);
                lever.change();
            }  else {
                result = StepResult.FAIL;
            }
        } else {
<<<<<<< .merge_file_a13368
            result = lever.getActPushable().push(this, dir, force - this.actCell.getFriction());
=======
            result = lever.getActPushable().push(this, dir, force);
>>>>>>> .merge_file_a11008
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                lever.stepOn(this);
                lever.change();
            }
        }
<<<<<<< .merge_file_a13368

=======
>>>>>>> .merge_file_a11008
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
<<<<<<< .merge_file_a13368
        StepResult result;
        if (force < this.actCell.getFriction()) {
            return StepResult.FAIL;
        }

=======
        StepResult result = StepResult.SUCCESS;
>>>>>>> .merge_file_a11008
        if (target.isEmpty()) {
            if(force >0) {
                actCell.stepOff();
                target.stepOn(this);
                result = StepResult.SUCCESS_POINT;
            } else{
                result = StepResult.FAIL;
            }
        } else {
<<<<<<< .merge_file_a13368
            result = target.getActPushable().push(this, dir, force - this.actCell.getFriction());
=======
            result = target.getActPushable().push(this, dir, force);
>>>>>>> .merge_file_a11008
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                target.stepOn(this);
            }
        }
<<<<<<< .merge_file_a13368

        return result;
    }

    @Override
    public void draw() {
        System.out.print("c");
    }
=======
        return result;
    }

    /**
     * Ezzel e metodussal lekerdezheto a lada tapadasi surlodasi egyutthatoja.
     * @return a lada tapadasi egyutthatoja
     */
    public int getFriction(){
        return friction;
}


>>>>>>> .merge_file_a11008
}
