public class Player extends Pushable{
    private int points;

    public Player(Cell actCell) {
        super(actCell);
        Logger.getInstance().logWithDec("Player", "Player(Cell)");
        points = 0;
    }

    @Override
    public boolean visit(Field field, Direction dir) {
        Logger.getInstance().log("Player", "visit(Field, Direction)");

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
        Logger.getInstance().log("Player", "visit(Switch, Direction)");

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
        Logger.getInstance().log("Player", "visit(Switch, Direction)");

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

        if (result) {
            lever.change();
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    @Override
    public boolean visit(Target target, Direction dir) {
        Logger.getInstance().log("Player", "visit(Switch, Direction)");

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

    public boolean push(Direction dir) {
        Logger.getInstance().log("Player", "push(Direction)");

        Cell nextCell = actCell.getNext(dir);
        boolean result = nextCell.accept(this, dir);

        Logger.getInstance().decIndentDepth();

        return result;
    }

    public boolean push(Player actor, Direction dir) {
        Logger.getInstance().logWithDec("Player", "push(Player, Direction)");
        return false;
    }

    public void addOnePoint() {
        Logger.getInstance().logWithDec("Player", "addOnePoint()");
        points++;
    }

    public boolean move(Direction dir){
        Logger.getInstance().log("Player", "move(Direction)");

        boolean result = push(dir);

        Logger.getInstance().decIndentDepth();

        return result;
    }
}
