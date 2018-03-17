public class Crate extends Pushable{
    public Crate(Cell actCell){
        super(actCell);
    }

    public boolean cratesPushable(){return true;}

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
    public boolean visit(Target target, Direction dir) {
        //TODO implementálni
        return false;
    }
}
