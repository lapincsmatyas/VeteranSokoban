public class Crate extends Pushable{
    public Crate(Cell actCell){
        super(actCell);
    }

    public boolean cratePushable() {
        Logger.getInstance().log("Crate", "craePushable()");

        boolean result = true;

        // TODO implementálni

        Logger.getInstance().decIndentDepth();
        return result;
    }

    @Override
    public boolean visit(Field field, Direction dir) {
        Logger.getInstance().log("Crate", "visit(Field, Direction)");

        boolean result = true;
        if (field.isEmpty()) {
            actCell.stepOff();
            field.stepOn(this);
        } else {
            result = field.getActPushable().push(this, dir);
            if (result) {
                actCell.stepOff();
                field.stepOn(this);
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    @Override
    public boolean visit(Hole hole, Direction dir) {
        Logger.getInstance().log("Crate", "visit(Switch, Direction)");

        boolean result = true;
        if (hole.isEmpty()) {
            actCell.stepOff();

            if (hole.isOpened()) {
                this.die();
            } else {
                hole.stepOn(this);
            }
        } else {
            result = hole.getActPushable().push(this, dir);
            if (result) {
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
    public boolean visit(Switch lever, Direction dir) {
        Logger.getInstance().log("Crate", "visit(Switch, Direction)");

        boolean result = true;
        if (lever.isEmpty()) {
            actCell.stepOff();
            lever.stepOn(this);
        } else {
            result = lever.getActPushable().push(this, dir);
            if (result) {
                actCell.stepOff();
                lever.stepOn(this);
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    @Override
    public boolean visit(Target target, Direction dir) {
        //TODO pontozást valahogy beletoszni
        Logger.getInstance().log("Crate", "visit(Switch, Direction)");

        boolean result = true;
        if (target.isEmpty()) {
            actCell.stepOff();
            target.stepOn(this);
        } else {
            result = target.getActPushable().push(this, dir);
            if (result) {
                actCell.stepOff();
                target.stepOn(this);
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
