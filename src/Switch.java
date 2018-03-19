import java.util.ArrayList;
import java.util.List;

/**
 * A jatekban talalhato kapcsolokat valositja meg
 */
public class Switch extends Cell {
    private List<Hole> holes = new ArrayList<Hole>();

    public Switch() {
        super();
        Logger.getInstance().logWithDec("Switch", "Switch()");
    }

    /**
     * Ezzel a fuggvennyel lyukak adhatok a
     * kapcsolo felelossege ala.
     * @param hole
     */
    public void addHole(Hole hole) {
        holes.add(hole);
        Logger.getInstance().logWithDec("Switch", "addHole(Hole)");
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Mehivja a visitor visit fuggvenyet.
     * @param visitor
     * @param dir
     * @return
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir) {
        //TODO implementálni a Player és a Crate visit függvényeket
        Logger.getInstance().log("Switch", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * A kapcsolo felelossege ala tartozo lyukakat
     * valtoztatja meg az ellenekezo allapotukba
     */
    public void change(){
        Logger.getInstance().log("Switch", "change()");

        for (Hole hole : holes) {
            hole.change();
        }

        Logger.getInstance().decIndentDepth();
    }
}
