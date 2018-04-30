package models.cells;

import push_enums.Direction;
import push_enums.StepResult;
import visitor_pattern.Visitor;

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

    /**
     * Az osztaly default konstruktora.
     */
    public Switch() {
        super();
    }

    /**
     * Ezzel a fuggvennyel lyukak adhatok a
     * kapcsolo felelossege ala.
     * @param holes A hozzaadando lyukak listaja
     */
    public void addHoles(Collection<Hole> holes) {
        this.holes.addAll(holes);
    }

    /**
     * A visitor_pattern patternt megvalosito accept fuggveny.
     * Mehivja a visitor_pattern visit fuggvenyet.
     * @param visitor Az aktualis visitor_pattern.
     * @param dir A visitor_pattern ebbe az iranyba szeretne lepni.
     * @param force A tolas ereje.
     * @return A lepes sikeressege (push_enums.StepResult tipusu).
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        return visitor.visit(this, dir, force);
    }

    /**
     * A kapcsolo felelossege ala tartozo lyukakat
     * valtoztatja meg az ellenekezo allapotukba.
     */
    public void change(){
        for (Hole hole : holes) {
            hole.change();
        }
        hasChanged = !hasChanged;
    }

    @Override
    public void stepOff(){
        super.stepOff();
        if(hasChanged)
            change();
    }

    @Override
    public String getData() {
        return super.getData('s');
    }
}
