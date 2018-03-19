public class Crate extends Pushable{
    public Crate(Cell actCell){
        super(actCell);
        Logger.getInstance().logWithDec("Crate", "Crate(Cell)");
    }

    public boolean cratePushable() {
        Logger.getInstance().log("Crate", "craePushable()");

        boolean result = true;

        // TODO implementálni

        Logger.getInstance().decIndentDepth();
        return result;
    }

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
