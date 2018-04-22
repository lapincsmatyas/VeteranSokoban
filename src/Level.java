import java.util.ArrayList;
import java.util.List;

public class Level {
    private int id;
    private int width;
    private int height;

    List<Character> map;

    public Level(){
        map = new ArrayList<>();
        height = 0;
    }

    public void addLine(String line){
        width = line.length();
        height++;

        for(int i = 0; i < line.length(); i++){
            map.add(line.charAt(i));
        }
    }


}
