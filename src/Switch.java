import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A jatekban talalhato kapcsolokat valositja meg
 */
public class Switch extends Cell {

    private boolean hasChanged = false;

    /**
     * A kapcsolohoz tartozo lyukak listaja.
     */
    private List<Hole> holes = new ArrayList<Hole>();
    private final char display;

    /**
     * Az osztaly default konstruktora.
     */
    public Switch() {
        super();
<<<<<<< .merge_file_a08984
=======
        display = 's';
>>>>>>> .merge_file_a07864
    }

    /**
     * Ezzel a fuggvennyel lyukak adhatok a
     * kapcsolo felelossege ala.
     * @param holes A hozzaadando lyukak listaja
     */
<<<<<<< .merge_file_a08984
    public void addHoles(Collection<Hole> holes) {
        this.holes.addAll(holes);
=======
    public void addHole(Hole hole) {
        holes.add(hole);
>>>>>>> .merge_file_a07864
    }

    /**
     * A visitor patternt megvalosito accept fuggveny.
     * Mehivja a visitor visit fuggvenyet.
     * @param visitor Az aktualis visitor.
     * @param dir A visitor ebbe az iranyba szeretne lepni.
     * @param force A tolas ereje.
     * @return A lepes sikeressege (StepResult tipusu).
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
<<<<<<< .merge_file_a08984
        return visitor.visit(this, dir, force);
=======

        StepResult result = visitor.visit(this, dir, force);
        return result;
>>>>>>> .merge_file_a07864
    }

    /**
     * A kapcsolo felelossege ala tartozo lyukakat
     * valtoztatja meg az ellenekezo allapotukba.
     */
    public void change(){
<<<<<<< .merge_file_a08984
=======

>>>>>>> .merge_file_a07864
        for (Hole hole : holes) {
            hole.change();
        }
        hasChanged = !hasChanged;
    }

<<<<<<< .merge_file_a08984
    @Override
    public void stepOff(){
        super.stepOff();
        if(hasChanged)
            change();
=======
>>>>>>> .merge_file_a07864
    }

    @Override
    public void draw() {
<<<<<<< .merge_file_a08984
        super.draw('s');
=======
        System.out.print(display);
>>>>>>> .merge_file_a07864
    }
}
