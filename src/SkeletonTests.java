public class SkeletonTests {
    public static void initTest() {
        Logger.getInstance().reset();
        Game g = new Game();
        g.init();
    }

    public static void playerMovesToEmptyFieldTest() {
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
}
