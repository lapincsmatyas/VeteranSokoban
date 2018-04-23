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
    private int force;
    private Slime slime;
    private int id;

    /**
     * A Slime, amit a jatekos lerakhat.
     */
<<<<<<< .merge_file_a09636
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
=======
    public Player(int id, Cell actCell, int force) {
        super(actCell, force);
        this.id = id;
        points = 0;
        this.force = force;
        this.slime = null;
>>>>>>> .merge_file_a06020
    }

    /**
     * A Visitor patternt megvalosito fuggveny.
     * A jatekos ellenorzi, hogy tud-e lepni a mezore.
     * @param field A mezo, amit a jatekos visitel.
     * @param dir Az irany, amelyrol a játékos van.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Field field, Direction dir, int force) {
<<<<<<< .merge_file_a09636
=======

>>>>>>> .merge_file_a06020
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
     * A Visitor patternt megvalosito fuggveny.
     * A jatekos ellenorzi, hogy tud-e lepni a lyukra.
     * Ha a lyuk nyitva van, akkor a jatekos meghal, ha ralep.
     * @param hole A lyuk, amit a játekos visitel.
     * @param dir Az irany, amelyrol a játékos van.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Hole hole, Direction dir, int force) {
<<<<<<< .merge_file_a09636
=======

>>>>>>> .merge_file_a06020
        StepResult result = StepResult.SUCCESS;


        if (hole.isEmpty()) {
<<<<<<< .merge_file_a09636
            actCell.stepOff();
            hole.stepOn(this);
=======
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
>>>>>>> .merge_file_a06020
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
     * A Visitor patternt megvalosito fuggveny.
     * A jatekos ellenorzi, hogy tud-e lepni a kapcsolora.
     * @param lever A kapcsolo, amit a jatekos visitel.
     * @param dir Az irany, amelyrol a játékos van.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Switch lever, Direction dir, int force) {
<<<<<<< .merge_file_a09636
=======

>>>>>>> .merge_file_a06020
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
     * A Visitor patternt megvalosito fuggveny.
     * A jatekos ellenorzi, hogy tud-e lepni a celra.
     * @param target A cel, amit a jatekos visitel.
     * @param dir Az irany, amelyrol a játékos van.
     * @param force A tolas ereje.
     * @return A lepes sikeressege.
     */
    @Override
    public StepResult visit(Target target, Direction dir, int force) {
<<<<<<< .merge_file_a09636
=======

>>>>>>> .merge_file_a06020
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
<<<<<<< .merge_file_a09636
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
=======
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
>>>>>>> .merge_file_a06020
     */
    public StepResult push(Player actor, Direction dir, int force) {
        return StepResult.FAIL;
    }

    /**
<<<<<<< .merge_file_a09636
     * A jatekost lehet vele odebbtolni a megadott iranyba.
     * @param actor A lada objektum, ami kezdemenyezte a tolast.
     * @param dir Az irany, amelyre a jatekost tolni akarjak.
     * @param force A tolas ereje.
     * @return A tolas sikeressege.
     */
    @Override
    public StepResult push(Crate actor, Direction dir, int force) {
=======
     * A játékost lehet vele odébbtolni
     * @param actor A láda objektum, ami kezdeményezte a tolást.
     * @param dir Az irány, amelybe tolni akarják a játékost
     * @param force A tolashoz hasznalt ero
     * @return A tolás sikeressége.
     */
    @Override
    public StepResult push(Crate actor, Direction dir, int force) {

>>>>>>> .merge_file_a06020
        Cell nextCell = actCell.getNext(dir);
        StepResult result = nextCell.accept(this, dir, force);

        if (result == StepResult.FAIL) {
            this.die();
        }
<<<<<<< .merge_file_a09636

=======
>>>>>>> .merge_file_a06020
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
<<<<<<< .merge_file_a09636
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
     * @param slime A Slime, amit a jatekos kap.
     */
    public void giveSlime(Slime slime) {
        this.slime = slime;
    }

    public int getId() {
        return id;
    }
=======

        StepResult result = push(dir, force);
>>>>>>> .merge_file_a06020

    @Override
    public void draw() {
        System.out.print(id);
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
