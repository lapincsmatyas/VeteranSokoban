import java.util.ArrayList;
import java.util.List;

/**
 * A jatekban talalhato kapcsolokat valositja meg
 */
public class Switch extends Cell {
    private List<Hole> holes = new ArrayList<Hole>();
    private final char display;

    public Switch() {
        super();
        display = 's';
    }

    /**
     * Ezzel a fuggvennyel lyukak adhatok a
     * kapcsolo felelossege ala.
     * @param hole
     */
    public void addHole(Hole hole) {
        holes.add(hole);
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Mehivja a visitor visit fuggvenyet.
     * @param visitor
     * @param dir
     * @return
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {

        StepResult result = visitor.visit(this, dir, force);
        return result;
    }

    /**
     * A kapcsolo felelossege ala tartozo lyukakat
     * valtoztatja meg az ellenekezo allapotukba
     */
    public void change(){

        for (Hole hole : holes) {
            hole.change();
        }

    }

    @Override
    public void draw() {
        System.out.print(display);
    }
}
