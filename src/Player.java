public class Player extends Pushable{
    private int points;

    public Player(Cell actCell) {
        super(actCell);
        Logger.getInstance().logWithDec("Player", "Player(Cell)");
        points = 0;
    }

    @Override
    public StepResult visit(Field field, Direction dir) {
        Logger.getInstance().log("Player", "visit(Field, Direction)");

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
        Logger.getInstance().log("Player", "visit(Switch, Direction)");

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
        Logger.getInstance().log("Player", "visit(Switch, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (lever.isEmpty()) {
            actCell.stepOff();
            lever.stepOn(this);
        } else {
            result = lever.getActPushable().push(this, dir);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                lever.stepOn(this);
            }
        }

        if (result != StepResult.FAIL) {
            lever.change();
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    @Override
    public StepResult visit(Target target, Direction dir) {
        Logger.getInstance().log("Player", "visit(Switch, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (target.isEmpty()) {
            actCell.stepOff();
            target.stepOn(this);
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

    public StepResult push(Direction dir) {
        Logger.getInstance().log("Player", "push(Direction)");

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir);

        Logger.getInstance().decIndentDepth();

        return result;
    }

    public StepResult push(Player actor, Direction dir) {
        Logger.getInstance().logWithDec("Player", "push(Player, Direction)");
        return StepResult.FAIL;
    }

    public void addOnePoint() {
        Logger.getInstance().logWithDec("Player", "addOnePoint()");
        points++;
    }

    public StepResult move(Direction dir) {
        Logger.getInstance().log("Player", "move(Direction)");

        StepResult result = push(dir);
        if (result == StepResult.SUCCESS_POINT) {
            addOnePoint();
            result = StepResult.SUCCESS;
        }

        Logger.getInstance().decIndentDepth();

        return result;
    }
}
