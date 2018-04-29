package models.pushables;

import models.cells.*;
import models.slimes.Slime;
import push_enums.Direction;
import push_enums.StepResult;

/**
 * A jatekos, amit a felhasznalok iranyithatnak.
 * Ok kezdemenyezhetnek tolast es kaphatnak pontokat.
 */
public class Player extends Pushable{
    /**
     * A jatekos azonositoja.
     */
    private int id = 0;

    /**
     * A kapott pontok szama.8
     */
    private int points;

    /**
     * A models.slimes.Slime, amit a jatekos lerakhat.
     */
    private Slime slime;

    /**
     * A jatekos tolasanak ereje.
     */
    private int force;

    /**
     * A jatekos konstruktora.
     * Beallítja a mezot, amin tartozkodik es nullazza a pontokat.
     * @param actCell A mezo, amin a jatekos tartozkodik.
     * @param force Az ero, amivel a jatekos tolhat.
     */
    public Player(Cell actCell, int force, int playerId) {
        super(actCell, 0);
        points = 0;
        slime = null;
        this.force = force;
        id = playerId;
    }

    /**
     * A visitor_pattern.Visitor patternt megvalosito fuggveny.
     * A jatekos ellenorzi, hogy tud-e lepni a mezore.
     * @param field A mezo, amit a jatekos visitel.
     * @param dir Az irany, amelyrol a játékos van.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Field field, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;

        if (field.isEmpty()) {
            actCell.stepOff();
            field.stepOn(this);
        } else {
            result = field.getActPushable().push(this, dir, force);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                field.stepOn(this);
            }
        }

        return result;
    }

    /**
     * A visitor_pattern.Visitor patternt megvalosito fuggveny.
     * A jatekos ellenorzi, hogy tud-e lepni a lyukra.
     * Ha a lyuk nyitva van, akkor a jatekos meghal, ha ralep.
     * @param hole A lyuk, amit a játekos visitel.
     * @param dir Az irany, amelyrol a játékos van.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Hole hole, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;


        if (hole.isEmpty()) {
            actCell.stepOff();
            hole.stepOn(this);
        } else {
            result = hole.getActPushable().push(this, dir, force);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                hole.stepOn(this);
            }
        }

        return result;
    }

    /**
     * A visitor_pattern.Visitor patternt megvalosito fuggveny.
     * A jatekos ellenorzi, hogy tud-e lepni a kapcsolora.
     * @param lever A kapcsolo, amit a jatekos visitel.
     * @param dir Az irany, amelyrol a játékos van.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Switch lever, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;

        if (lever.isEmpty()) {
            actCell.stepOff();
            lever.stepOn(this);
        } else {
            result = lever.getActPushable().push(this, dir, force);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                lever.stepOn(this);
            }
        }

        return result;
    }

    /**
     * A visitor_pattern.Visitor patternt megvalosito fuggveny.
     * A jatekos ellenorzi, hogy tud-e lepni a celra.
     * @param target A cel, amit a jatekos visitel.
     * @param dir Az irany, amelyrol a játékos van.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Target target, Direction dir, int force) {
        StepResult result = StepResult.SUCCESS;


        if (target.isEmpty()) {
            actCell.stepOff();
            target.stepOn(this);
        } else {
            result = target.getActPushable().push(this, dir, force);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                target.stepOn(this);
            }
        }

        return result;
    }

    /**
     * A jatekost lehet vele odebbtolni a megadott iranyba.
     * @param dir Az irany, amelyre a jatekost tolni akarjak.
     * @param force A tolas ereje.
     * @return A tolas sikeressege.
     */
    private StepResult push(Direction dir, int force) {
        Cell nextCell = actCell.getNext(dir);
        return nextCell.accept(this, dir, force);
    }

    /**
     * A jatekost lehet vele odebbtolni a megadott iranyba.
     * @param actor A jatekos objektum, ami kezdemenyezte a tolast.
     * @param dir Az irany, amelyre a jatekost tolni akarjak.
     * @param force A tolas ereje.
     * @return Mindig hamis, mert jatekos nem tolhat jatekost.
     */
    public StepResult push(Player actor, Direction dir, int force) {
        return StepResult.FAIL;
    }

    /**
     * A jatekost lehet vele odebbtolni a megadott iranyba.
     * @param actor A lada objektum, ami kezdemenyezte a tolast.
     * @param dir Az irany, amelyre a jatekost tolni akarjak.
     * @param force A tolas ereje.
     * @return A tolas sikeressege.
     */
    @Override
    public StepResult push(Crate actor, Direction dir, int force) {
        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir, force);

        if (result == StepResult.FAIL) {
            this.die();
        }

        return StepResult.SUCCESS;
    }

    /**
     * A jatekos pontjait noveli eggyel.
     */
    public void addOnePoint() {
        points++;
    }

    /**
     * A jatekost ezzel mozgathatja a felhasznalo.
     * @param dir Az irany, amelyre mozgatni akarjak a jatekost.
     * @return A mozgatas sikeresege.
     */
    public StepResult move(Direction dir) {
        return push(dir, force);
    }

    /**
     * Ezzel lehet lehelyezni a jatekos Slimejat a mezore, amin all.
     */
    public void putSlime() {
        if (actCell != null) {
            actCell.putSlime(slime);
            slime = null;
        }
    }

    /**
     * A jatekost ezzel lehet jutalmazni Slimemal.
     *
     * @param slime A models.slimes.Slime, amit a jatekos kap.
     */
    public void giveSlime(Slime slime) {
        this.slime = slime;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getData() {
        String res = "";

        res += id;

        if (slime != null) {
            res += slime.getData();
        }

        return res;
    }
}
