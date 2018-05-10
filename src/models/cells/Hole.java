package models.cells;

import models.pushables.Pushable;
import push_enums.Direction;
import push_enums.StepResult;
import visitor_pattern.Visitor;

/**
 * Ez az osztaly valositja meg a jatekban
 * levo lyukakat.
 */
public class Hole extends Cell {
    private boolean open;

    /**
     * Az osztaly default konstruktora.
     */
    public Hole() {
        super();
        open = false;
    }

    /**
     * Ezzel a fuggvennyel kinyithato a lyuk.
     */
    public void open() {
        open = true;
    }

    /**
     * Ezzel a fuggvennyel bezarhato egy lyuk.
     */
    public void close() {
        open = false;
    }

    /**
     * Ezzen fuggveny meghivasaval a zart lyukbol nyitott lyuk,
     * nyitott lyukbol zart lyuk lesz.
     */
    public void change() {
        open = !open;
    }

    /**
     * A fuggveny segitsegevel lekerdezheto, hogy a lyuk
     * nyitott allapotban van-e.
     * @return True, ha a lyuk nyitva van, false, ha zarva.
     */
    public boolean isOpened() {
        return open;
    }

    /**
     * A visitor_pattern patternt megvalosito visit fuggveny.
     * Meghivja a visitor_pattern visit fuggvenyet.
     * @param visitor Az aktuals latogato.
     * @param dir A latogatas iranya - ebbe az iranyba szeretne haladni a visitor_pattern.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult accept(Visitor visitor, Direction dir, int force) {
        return visitor.visit(this, dir, force);
    }

    @Override
    public boolean stepOn(Pushable pushable) {
        boolean res = super.stepOn(pushable);

        if (open) {
            pushable.die();
            return false;
        }

        return res;
    }

    @Override
    public String getData() {
        String res = "";

        if (open) {
            res += super.getData('O');
        } else {
            res += super.getData('*');
        }

        return res;
    }
}
