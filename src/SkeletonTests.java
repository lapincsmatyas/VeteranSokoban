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

    public static void playerMovesToEmptyFieldTest() {
        System.out.println("2.1 Játékos üres mezőre mozog");

        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);

        Player player = new Player(cell1);
        cell1.setActPushable(player);

        Logger.getInstance().reset();
        player.move(Direction.RIGHT);
    }

    public static void playerMovesToPillarTest() {
        System.out.println("2.2 Játékos falra mozog");

        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Pillar();
        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);

        Player player = new Player(cell1);
        cell1.setActPushable(player);

        Logger.getInstance().reset();
        player.move(Direction.RIGHT);
    }

    public static void playerGetsPointTest() {
        Logger.getInstance().deactivate();
        Cell cell1 = new Field();
        Cell cell2 = new Field();
        Cell cell3 = new Target();

        cell1.setNeighbor(Direction.RIGHT, cell2);
        cell2.setNeighbor(Direction.LEFT, cell1);
        cell2.setNeighbor(Direction.RIGHT, cell3);
        cell3.setNeighbor(Direction.LEFT, cell2);

        Player player = new Player(cell1);
        cell1.setActPushable(player);

        Crate crate = new Crate(cell2);
        cell2.setActPushable(crate);

        Logger.getInstance().reset();
        player.move(Direction.RIGHT);
    }
}
