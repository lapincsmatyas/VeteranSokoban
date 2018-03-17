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

    public boolean visit(Switch lever, Direction dir) {
        //TODO van-e rajta valami, ha nincs akkor lépjen rá vagy tolja el
        lever.change();
        return true;
    }

    @Override
    public boolean visit(Target target, Direction dir) {
        //TODO implementálni
        return false;
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
