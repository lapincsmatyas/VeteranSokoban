public class Crate extends Pushable{
    public Crate(Cell actCell){
        super(actCell);
    }


    @Override
    public void printPushable() {
        System.out.print("||");
    }

    public boolean cratesPushable(){return true;}

    public boolean visit(Field cell, int dir) {
        if(cell.isEmpty()){
            actCell.stepOff();
            cell.stepOn(this);
            return true;
        } else {
            boolean isPushSuccess = cell.getActPushable().push(this, dir);
            if (isPushSuccess) {
                actCell.stepOff();
                cell.stepOn(this);
                return true;
            }
        }
        return false;
    }
}
