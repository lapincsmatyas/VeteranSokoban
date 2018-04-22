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
    private List<Point> honey;
    private List<Point> oil;

    public Level(int id){
        map = new ArrayList<>();
        players = new HashMap<>();
        crates = new ArrayList<>();
        honey = new ArrayList<>();
        oil = new ArrayList<>();
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
        honey.add(new Point(width, height));
    }

    public void addOil(int width, int height) {
        oil.add(new Point(width, height));
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
        return oil;
    }

    public List<Point> getHoney() {
        return honey;
    }

    public int getId() {
        return id;
    }
}
