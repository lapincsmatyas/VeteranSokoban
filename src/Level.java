import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {
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

    public void addPlayer(int id, int height, int width) {
        players.put(id, new Point(width, height));
    }

    public void addCrate(int height, int width) {
        crates.add(new Point(width, height));
    }

    public void addHoney(int height, int width) {
        honeys.add(new Point(width, height));
    }

    public void addOil(int width, int height) {
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
    }
}
