
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {
<<<<<<< .merge_file_a13764
    private int id;
    private int width;
    private int height;

    private List<Character> map;
    private Map<Integer, Point> players;
    private List<Point> crates;
    private List<Point> honeys;
    private List<Point> oils;

    public Level(int id){
        map = new ArrayList<>();
        players = new HashMap<>();
        crates = new ArrayList<>();
        honeys = new ArrayList<>();
        oils = new ArrayList<>();
        width = 0;
        height = 0;

        this.id = id;
    }

    public void addLine(String line){
        width = line.length();
        height++;

        for(int i = 0; i < line.length(); i++){
            map.add(line.charAt(i));
        }
    }

    public void addPlayer(int id, int force, int height, int width) {
        players.put(id, new Point(force, width, height));
    }

    public void addCrate(int friction, int height, int width) {
        crates.add(new Point(friction, width, height));
    }

    public void addHoney(int height, int width) {
        honeys.add(new Point(width, height));
    }

    public void addOil(int height, int width) {
        oils.add(new Point(width, height));
    }

    public char getCellAt(int x, int y){
        return map.get(x*width + y);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Map<Integer, Point> getPlayers() {
        return players;
    }

    public List<Point> getCrates() {
        return crates;
    }

    public List<Point> getOil() {
        return oils;
    }

    public List<Point> getHoney() {
        return honeys;
    }

    public int getId() {
        return id;
=======
    public Level() {

    }

    /**
     * A parameterben megadott azonositoju
     * palya adatait tolti be
     * @param id a betoltendo palya azonositoja
     */
    public void loadMap(int id) {

    }

    /**
     * Megadja a betoltott palya adatait.
     * @return a betoltott palya adatai
     */
    public String getLevelData() {
        return null;
>>>>>>> .merge_file_a12612
    }
}
