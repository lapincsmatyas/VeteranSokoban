import java.util.ArrayList;
import java.util.List;

public class Switch extends Cell {
    private List<Hole> holes = new ArrayList<Hole>();

    public Switch() {
        super();
        Logger.getInstance().logWithDec("Switch", "Switch()");
    }

    public void addHole(Hole hole) {
        holes.add(hole);
        Logger.getInstance().logWithDec("Switch", "addHole(Hole)");
    }

    @Override
    public StepResult accept(Visitor visitor, Direction dir) {
        //TODO implementálni a Player és a Crate visit függvényeket
        Logger.getInstance().log("Switch", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }



    public void change(){
        Logger.getInstance().log("Switch", "change()");

        for (Hole hole : holes) {
            hole.change();
        }

        Logger.getInstance().decIndentDepth();
    }
}
