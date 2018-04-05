import java.util.ArrayList;
import java.util.List;

/**
 * A jatekban talalhato kapcsolokat valositja meg
 */
public class Switch extends Cell {
    /**
     * A kapcsolohoz tartozo lyukak listaja.
     */
    private List<Hole> holes = new ArrayList<Hole>();

    /**
     * Az osztaly default konstruktora.
     */
    public Switch() {
        super();
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
     * @param visitor Az aktualis visitor.
     * @param dir A visitor ebbe az iranyba szeretne lepni.
     * @param force A tolas ereje.
     * @return A lepes sikeressege (StepResult tipusu).
     */
    //TODO implementalni a forceos mukodest, egyelore csak parameterben van
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        //TODO implementálni a Player és a Crate visit függvényeket

        StepResult result = visitor.visit(this, dir, force);

        return result;
    }

    /**
     * A kapcsolo felelossege ala tartozo lyukakat
     * valtoztatja meg az ellenekezo allapotukba.
     */
    public void change(){
        for (Hole hole : holes) {
            hole.change();
        }
    }
}
