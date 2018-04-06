/**
 * A játékos, amit a felhasználók irányíthatnak.
 * Ők kezdeményezhetnek tolást és kaphatnak pontokat.
 */
public class Player extends Pushable{
    /**
     * A kapott pontok száma.
     */
    private int points;
    private int force;
    private Slime slime;
    private int id;

    /**
     * A játékos konstruktora.
     * Beállítja a mezőt, amin tartózkodik és nullázza a pontokat.
     *
     * @param actCell A mező, amin a játékos tartózkodik.
     */
    public Player(int id, Cell actCell, int force) {
        super(actCell, force);
        this.id = id;
        points = 0;
        this.force = force;
        this.slime = null;
    }

    /**
     * A Visitor patternt megvalósító függvény.
     * A játékos ellenőrzi, hogy tud-e lépni a mezőre.
     * @param field A mező, amit a játékos visitel.
     * @param dir Az irány, amelyről a játékos van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Field field, Direction dir, int force) {

        StepResult result = StepResult.SUCCESS;
        if (field.isEmpty()) {
            if(force >0) {
                actCell.stepOff();
                field.stepOn(this);
            } else{
                result = StepResult.FAIL;
            }
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
     * A Visitor patternt megvalósító függvény.
     * A játékos ellenőrzi, hogy tud-e lépni a lyukra.
     * Ha a lyuk nyitva van, akkor a játékos meghal, ha rálép.
     * @param hole A lyuk, amit a játékos visitel.
     * @param dir Az irány, amelyről a játékos van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Hole hole, Direction dir, int force) {

        StepResult result = StepResult.SUCCESS;
        if (hole.isEmpty()) {
            if(force > 0) {
                actCell.stepOff();

                if (hole.isOpened()) {
                    this.die();
                } else {
                    hole.stepOn(this);
                }
            } else{
                result = StepResult.FAIL;
            }
        } else {
            result = hole.getActPushable().push(this, dir, force);
            if (result != StepResult.FAIL) {
                actCell.stepOff();

                if (hole.isOpened()) {
                    this.die();
                } else {
                    hole.stepOn(this);
                }
            }
        }

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
    public StepResult visit(Switch lever, Direction dir, int force) {

        StepResult result = StepResult.SUCCESS;
        if (lever.isEmpty()) {
            if(force > 0) {
                actCell.stepOff();
                lever.stepOn(this);
            } else{
                result = StepResult.FAIL;
            }
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
     * A Visitor patternt megvalósító függvény.
     * A játékos ellenőrzi, hogy tud-e lépni a célra.
     * @param target A cél, amit a játékos visitel.
     * @param dir Az irány, amelyről a játékos van.
     * @return A lépés sikeressége.
     */
    @Override
    public StepResult visit(Target target, Direction dir, int force) {

        StepResult result = StepResult.SUCCESS;
        if (target.isEmpty()) {
            if(force > 0) {
                actCell.stepOff();
                target.stepOn(this);
            } else {
                result = StepResult.FAIL;
            }
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
     * A játékost lehet vele odébbtolni a megadott irányba.
     * @param dir Az irány, amelyre a játékost tolni akarják.
     * @param force A tolashoz hasznalt ero
     * @return A tolás sikeressége.
     */
    public StepResult push(Direction dir, int force) {

        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir, force);


        return result;
    }

    /**
     * A játékost lehet vele odébbtolni
     * @param actor A játékos objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a játékost.
     * @param force A tolashoz hasznalt ero
     * @return Mindig hamis, mert játékos nem tolhat játékost.
     */
    public StepResult push(Player actor, Direction dir, int force) {
        return StepResult.FAIL;
    }

    /**
     * A játékost lehet vele odébbtolni
     * @param actor A láda objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a játékost
     * @param force A tolashoz hasznalt ero
     * @return A tolás sikeressége.
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
     * A játékos pontjait növeli eggyel.
     */
    public void addOnePoint() {
        points++;
    }

    /**
     * A játékost ezzel mozgathatja a felhasználó.
     * @param dir Az irány, amelyre mozgatni akarják a játékost.
     * @return A mozgatás sikeressége.
     */
    public StepResult move(Direction dir) {

        StepResult result = push(dir, force);

        return result;
    }

    /**
     * A jatekosnal levo slime-ot lehelyezi arra a mezore, amin eppen all
     */
    public void putSlime(){
        actCell.putSlime(this.slime);
    }

    /**
     * A jatekosnak ad egy slimeot
     * @param slime ezt a slimeot adja a jatekosnak
     */
    public void giveSlime(Slime slime){
        this.slime = slime;
        this.slime = slime;
    }

    @Override
    public void draw() {
        System.out.print(id);
    }


}
