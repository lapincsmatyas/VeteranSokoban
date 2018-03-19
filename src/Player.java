/**
 * A játékos, amit a felhasználók irányíthatnak.
 * Ők kezdeményezhetnek tolást és kaphatnak pontokat.
 */
public class Player extends Pushable{
    /**
     * A kapott pontok száma.
     */
    private int points;

    /**
     * A játékos konstruktora.
     * Beállítja a mezőt, amin tartózkodik és nullázza a pontokat.
     *
     * @param actCell A mező, amin a játékos tartózkodik.
     */
    public Player(Cell actCell) {
        super(actCell);
        Logger.getInstance().logWithDec("Player", "Player(Cell)");
        points = 0;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A játékos ellenőrzi, hogy tud-e lépni a mezőre.
     * @param field A mező, amit a játékos visitel.
     * @param dir Az irány, amelyről a játékos van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Field field, Direction dir) {
        Logger.getInstance().log("Player", "visit(Field, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (field.isEmpty()) {
            actCell.stepOff();
            field.stepOn(this);
        } else {
            result = field.getActPushable().push(this, dir);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                field.stepOn(this);
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A játékos ellenőrzi, hogy tud-e lépni a lyukra.
     * Ha a lyuk nyitva van, akkor a játékos meghal, ha rálép.
     * @param hole A lyuk, amit a játékos visitel.
     * @param dir Az irány, amelyről a játékos van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Hole hole, Direction dir) {
        Logger.getInstance().log("Player", "visit(Switch, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (hole.isEmpty()) {
            actCell.stepOff();

            if (hole.isOpened()) {
                this.die();
            } else {
                hole.stepOn(this);
            }
        } else {
            result = hole.getActPushable().push(this, dir);
            if (result != StepResult.FAIL) {
                actCell.stepOff();

                if (hole.isOpened()) {
                    this.die();
                } else {
                    hole.stepOn(this);
                }
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A játékos ellenőrzi, hogy tud-e lépni a kapcsolóra.
     * @param lever A kapcsoló, amit a játékos visitel.
     * @param dir Az irány, amelyről a játékos van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Switch lever, Direction dir) {
        Logger.getInstance().log("Player", "visit(Switch, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (lever.isEmpty()) {
            actCell.stepOff();
            lever.stepOn(this);
        } else {
            result = lever.getActPushable().push(this, dir);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                lever.stepOn(this);
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A játékos ellenőrzi, hogy tud-e lépni a célra.
     * @param target A cél, amit a játékos visitel.
     * @param dir Az irány, amelyről a játékos van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Target target, Direction dir) {
        Logger.getInstance().log("Player", "visit(Switch, Direction)");

        StepResult result = StepResult.SUCCESS;
        if (target.isEmpty()) {
            actCell.stepOff();
            target.stepOn(this);
        } else {
            result = target.getActPushable().push(this, dir);
            if (result != StepResult.FAIL) {
                actCell.stepOff();
                target.stepOn(this);
            }
        }

        Logger.getInstance().decIndentDepth();
        return result;
    }

    /**
     * A játékost lehet vele odébbtolni a megadott irányba.
     * @param dir Az irány, amelyre a játékost tolni akarják.
     * @return A tolás sikeressége.
     */
    public StepResult push(Direction dir) {
        Logger.getInstance().log("Player", "push(Direction)");

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir);

        Logger.getInstance().decIndentDepth();

        return result;
    }

    /**
     * A játékost lehet vele odébbtolni
     * @param actor A játékos objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a játékost.
     * @return Mindig hamis, mert játékos nem tolhat játékost.
     */
    public StepResult push(Player actor, Direction dir) {
        Logger.getInstance().logWithDec("Player", "push(Player, Direction)");
        return StepResult.FAIL;
    }

    /**
     * A játékost lehet vele odébbtolni
     * @param actor A láda objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a játékost.
     * @return A tolás sikeressége.
     */
    @Override
    public StepResult push(Crate actor, Direction dir) {
        Logger.getInstance().log("Player", "push(Crate, Direction)");

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir);

        if (result == StepResult.FAIL) {
            this.die();
        }

        Logger.getInstance().decIndentDepth();
        return StepResult.SUCCESS;
    }

    /**
     * A játékos pontjait növeli eggyel.
     */
    public void addOnePoint() {
        Logger.getInstance().logWithDec("Player", "addOnePoint()");
        points++;
    }

    /**
     * A játékost ezzel mozgathatja a felhasználó.
     * @param dir Az irány, amelyre mozgatni akarják a játékost.
     * @return A mozgatás sikeressége.
     */
    public StepResult move(Direction dir) {
        Logger.getInstance().log("Player", "move(Direction)");

        StepResult result = push(dir);

        Logger.getInstance().decIndentDepth();

        return result;
    }
}
