/**
 * A palyak betolteseert felel
 */
public class Level {
    public Level() {
        Logger.getInstance().logWithDec("Level", "Level()");
    }

    /**
     * A parameterben megadott azonositoju
     * palya adatait tolti be
     * @param id a betoltendo palya azonositoja
     */
    public void loadMap(int id) {
        Logger.getInstance().logWithDec("Level", "loadMap(int)");
    }

    /**
     * Megadja a betoltott palya adatait.
     * @return a betoltott palya adatai
     */
    public String getLevelData() {
        Logger.getInstance().logWithDec("Level", "getLevelData()");
        return null;
    }
}
