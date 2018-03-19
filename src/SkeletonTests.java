public class SkeletonTests {
    /*
     * A felépítése a fontos, így fog rendesen logolni a Logger.
     * Egyébként felesleges tesztesetek.
     */

    public static void initTest() {
        System.out.println("1 Init");

        Logger.getInstance().reset();
        Game g = new Game();
        g.init();
    }

    public static void playerMovesToEmpty() {
        System.out.println("2.1 Játékos üres mezőre mozog");

        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);

        Player player = new Player(cell1);

        Logger.getInstance().reset();

        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void playerMovesToPillar() {
        System.out.println("2.2 Játékos falra mozog");

        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Pillar();
        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);

        Player player = new Player(cell1);
        cell1.setActPushable(player);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void playerMovesToTarget() {
        System.out.println("2.3 Játékos célra lép");

        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Target();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);

        Player player = new Player(cell1);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void playerMovesToSwitch() {
        System.out.println("2.4 Játékos kapcsolóra lép");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Switch();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);

        Player player = new Player(cell1);
        cell1.setActPushable(player);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void playerMovesToHole() {
        System.out.println("2.5 Játékos lyukra lép");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Hole();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);

        Player player = new Player(cell1);
        cell1.setActPushable(player);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void playerMovesACrate() {
        System.out.println("2.6 Játékos ládára lép");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Field();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);

        Player player = new Player(cell1);
        Crate crate = new Crate(cell2);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void playerMovesToPlayer() {
        System.out.println("2.7 Játékos játékosra lép");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);

        Player player1 = new Player(cell1);
        Player player2 = new Player(cell2);


        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player1.move(Direction.RIGHT));
    }

    public static void crateMovesToEmpty() {
        System.out.println("3.1 Ládát üresre tolják");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Field();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);

        Player player = new Player(cell1);
        Crate crate = new Crate(cell2);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void crateMovesToPillar() {
        System.out.println("3.2 Ládát oszlopra tolják");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Pillar();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);

        Player player = new Player(cell1);
        Crate crate = new Crate(cell2);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void crateMovesToTarget() {
        System.out.println("3.3 Ládát célra tolják");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Target();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);

        Player player = new Player(cell1);
        Crate crate = new Crate(cell2);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void crateMovesToSwitch() {
        System.out.println("3.4 Ládát kapcsolóra tolják");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Switch();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);

        Player player = new Player(cell1);
        Crate crate = new Crate(cell2);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void crateMovesToHole() {
        System.out.println("3.5 Ládát lyukra tolják");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Hole();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);

        Player player = new Player(cell1);
        Crate crate = new Crate(cell2);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void crateMovesToCrate() {
        System.out.println("3.6 Láda ládát tol");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Field();
        Cell cell4 = new Field();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);
        cell3.setNeighbor(Direction.RIGHT, cell4);
        cell4.setNeighbor(Direction.LEFT, cell3);

        Player player = new Player(cell1);
        Crate crate1 = new Crate(cell2);
        Crate crate2 = new Crate(cell3);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player.move(Direction.RIGHT));
    }

    public static void crateMovesToUserEmpty() {
        System.out.println("3.7.1 Láda játékost tol üres mezőre");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Field();
        Cell cell4 = new Field();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);
        cell3.setNeighbor(Direction.RIGHT, cell4);
        cell4.setNeighbor(Direction.LEFT, cell3);

        Player player1 = new Player(cell1);
        Player player2 = new Player(cell3);
        Crate crate = new Crate(cell2);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player1.move(Direction.RIGHT));
    }

    public static void crateMovesToUserWall() {
        System.out.println("3.7.2 Láda játékost tol falhoz, ezért a játékos meghal");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Field();
        Cell cell4 = new Pillar();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);
        cell3.setNeighbor(Direction.RIGHT, cell4);
        cell4.setNeighbor(Direction.LEFT, cell3);

        Player player1 = new Player(cell1);
        Player player2 = new Player(cell3);
        Crate crate = new Crate(cell2);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player1.move(Direction.RIGHT));
    }

    public static void crateMovesToPlayerCrate() {
        System.out.println("3.7.3 Láda játékost tol ládára, ami odébbtolódik");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Field();
        Cell cell4 = new Field();
        Cell cell5 = new Field();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);
        cell3.setNeighbor(Direction.RIGHT, cell4);
        cell4.setNeighbor(Direction.LEFT, cell3);
        cell4.setNeighbor(Direction.RIGHT, cell5);
        cell5.setNeighbor(Direction.LEFT, cell4);

        Player player1 = new Player(cell1);
        Player player2 = new Player(cell3);
        Crate crate1 = new Crate(cell2);
        Crate crate2 = new Crate(cell4);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player1.move(Direction.RIGHT));
    }

    public static void crateMovesToCratePlayerWall() {
        System.out.println("3.7.4 Láda játékost tol ládára, ami fal mellett van, ezért a játékos meghal");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Field();
        Cell cell4 = new Field();
        Cell cell5 = new Pillar();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);
        cell3.setNeighbor(Direction.RIGHT, cell4);
        cell4.setNeighbor(Direction.LEFT, cell3);
        cell4.setNeighbor(Direction.RIGHT, cell5);
        cell5.setNeighbor(Direction.LEFT, cell4);

        Player player1 = new Player(cell1);
        Player player2 = new Player(cell3);
        Crate crate1 = new Crate(cell2);
        Crate crate2 = new Crate(cell4);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player1.move(Direction.RIGHT));
    }

    public static void MLMMLLMF() {
        System.out.println("4.1 MLMMLLMF");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Field();
        Cell cell4 = new Field();
        Cell cell5 = new Field();
        Cell cell6 = new Field();
        Cell cell7 = new Field();
        Cell cell8 = new Field();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);
        cell3.setNeighbor(Direction.RIGHT, cell4);
        cell4.setNeighbor(Direction.LEFT, cell3);
        cell4.setNeighbor(Direction.RIGHT, cell5);
        cell5.setNeighbor(Direction.LEFT, cell4);
        cell5.setNeighbor(Direction.RIGHT, cell6);
        cell6.setNeighbor(Direction.LEFT, cell5);
        cell6.setNeighbor(Direction.RIGHT, cell7);
        cell7.setNeighbor(Direction.LEFT, cell6);
        cell7.setNeighbor(Direction.RIGHT, cell8);
        cell8.setNeighbor(Direction.LEFT, cell7);

        Player player1 = new Player(cell1);
        Player player2 = new Player(cell3);
        Player player3 = new Player(cell4);
        Player player4 = new Player(cell7);


        Crate crate1 = new Crate(cell2);
        Crate crate2 = new Crate(cell5);
        Crate crate3 = new Crate(cell6);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player1.move(Direction.RIGHT));
    }

    public static void MLMMF() {
        System.out.println("4.2 MLMMF");
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Field();
        Cell cell4 = new Field();
        Cell cell5 = new Pillar();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);
        cell3.setNeighbor(Direction.RIGHT, cell4);
        cell4.setNeighbor(Direction.LEFT, cell3);
        cell4.setNeighbor(Direction.RIGHT, cell5);
        cell5.setNeighbor(Direction.LEFT, cell4);

        Player player1 = new Player(cell1);
        Player player2 = new Player(cell3);
        Player player3 = new Player(cell4);
        Crate crate1 = new Crate(cell2);

        Logger.getInstance().reset();
        Logger.getInstance().logStepResult(player1.move(Direction.RIGHT));
    }
}
