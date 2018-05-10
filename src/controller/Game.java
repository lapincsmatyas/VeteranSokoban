package controller;

import loader.*;
import models.cells.*;
import models.pushables.Crate;
import models.pushables.Player;
import models.slimes.Honey;
import models.slimes.Oil;
import models.slimes.Slime;
import push_enums.*;
import views.graphical.SokobanGraphView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class Game implements ControllerEventListener {
    private List<Cell> cells;
    private List<Crate> crates;
    private List<Player> players;
    private int numOfPlayers;

    private LevelLoader levelLoader;

    SokobanGraphView view;

    public Game(){
        cells = new ArrayList<>();
        players = new ArrayList<>();
        crates = new ArrayList<>();

        numOfPlayers = 0;
    }

    public void init(){
        levelLoader = new LevelLoader();

        view = new SokobanGraphView();
        view.init(this, getNumOfLevels());
        view.view();
    }

    private int getNumOfLevels(){
        File file = Paths.get("res\\Levels\\").toFile();
        return file.listFiles().length;
    }

    /**
     * A parameterben megadott azonostioju palyaval indit egy jatekot
     * @param id loader.Level azonosito
     */
    public void start(int id){
        Level level = null;
        try {
            level = levelLoader.loadMap(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildLevel(level);
        view.levelLoaded(getMapData());

    }

    public List<List<String>> getMapData() {
        List<List<String>> list = new ArrayList<>();

        boolean nomore = false;
        Cell cell = cells.get(0);
        while (!nomore) {
            List<String> line = new ArrayList<>();
            while (cell.getNext(Direction.RIGHT) != null) {
                String str = cell.getData();
                line.add(str);
                cell = cell.getNext(Direction.RIGHT);
            }

            String str = cell.getData();
            line.add(str);

            cell = cell.getNext(Direction.DOWN);

            if (cell != null) {
                while (cell.getNext(Direction.LEFT) != null) {
                    cell = cell.getNext(Direction.LEFT);
                }
            } else {
                nomore = true;
            }

            list.add(line);
        }

        return list;
    }

    public void movePlayer(int playerId, Direction dir) {
        for (Player p : players) {
            if (p.getId() == playerId) {
                StepResult result = p.move(dir);
                if (result == StepResult.SUCCESS_POINT) {
                    p.addOnePoint();

                    Random r = new Random();

                    if (r.nextInt(2) == 0) {
                        p.giveSlime(new Oil());
                    } else {
                        p.giveSlime(new Honey());
                    }
                }
            }
        }
    }

    public void putSlime(int playerId) {
        for (Player p : players) {
            if (p.getId() == playerId) {
                p.putSlime();
            }
        }
    }

    public void giveSlime(int playerId, Slime slime) {
        for (Player p : players) {
            if (p.getId() == playerId) {
                p.giveSlime(slime);
            }
        }
    }

    private void buildLevel(Level level){
        cells = new ArrayList<>();
        crates = new ArrayList<>();
        players = new ArrayList<>();

        List<Hole> holes = new ArrayList<>();
        List<Switch> levers = new ArrayList<>();

        for(int i = -1; i <= level.getHeight(); i++){
            for(int j = -1; j <= level.getWidth(); j++){
                char act;
                if (i == -1 || j == -1 || i == level.getHeight() || j == level.getWidth()) {
                    act = 'p';
                } else {
                    act = level.getCellAt(i,j);
                }
                switch (act){
                    case '.':
                        cells.add(new Field());
                        break;
                    case 'p':
                        cells.add(new Pillar());
                        break;
                    case 's':
                        Switch lever = new Switch();
                        levers.add(lever);
                        cells.add(lever);
                        break;
                    case 't':
                        cells.add(new Target());
                        break;
                    case '*':
                    {
                        Hole hole = new Hole();
                        holes.add(hole);
                        cells.add(hole);
                        break;
                    }
                    case 'O':
                        Hole hole = new Hole();
                        hole.open();
                        holes.add(hole);
                        cells.add(hole);
                        break;
                }
            }
        }

        for (Switch lever: levers) {
            lever.addHoles(holes);
        }

        connectNeighbors(level.getWidth() + 2, level.getHeight() + 2);
        placeEntities(level, levers);
    }

    private void placeEntities(Level level, List<Switch> levers) {
        for (Point p : level.getOil()) {
            int x = (int) p.getX();
            int y = (int) p.getY();

            Cell c = cells.get(y * (level.getWidth() + 2) + x);
            c.putSlime(new Oil());
        }

        for (Point p : level.getHoney()) {
            int x = (int) p.getX();
            int y = (int) p.getY();

            Cell c = cells.get(y * (level.getWidth() + 2) + x);
            c.putSlime(new Honey());
        }

        for (Point p : level.getCrates()) {
            int friction = (int) p.getX();
            int x = (int) p.getY();
            int y = (int) p.getZ();

            Cell c = cells.get(y * (level.getWidth() + 2) + x);
            for (Switch lever: levers) {
                if(lever == c){
                    lever.change();
                }
            }
            crates.add(new Crate(c, friction));
        }

        addPlayer();
        for(int i = 0; i < numOfPlayers; i++){
            addPlayer();
        }
    }

    private void connectNeighbors(int width, int height){
        //Elso sor
        cells.get(0).setNeighbor(Direction.RIGHT, cells.get(1));
        cells.get(0).setNeighbor(Direction.DOWN, cells.get(0 + width));
        for(int i = 1; i < width-1; i++){
            cells.get(i).setNeighbor(Direction.LEFT, cells.get(i-1));
            cells.get(i).setNeighbor(Direction.RIGHT, cells.get(i+1));
            cells.get(i).setNeighbor(Direction.DOWN, cells.get(i + width));
        }
        cells.get(width-1).setNeighbor(Direction.LEFT, cells.get(width-2));
        cells.get(width-1).setNeighbor(Direction.DOWN, cells.get(width - 1 + width));

        for(int i = 1; i < height-1; i++){
            for(int j = 0; j < width; j++){
                int actPos = i*width + j;
                if(j == 0){
                    cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos-width));
                    cells.get(actPos).setNeighbor(Direction.RIGHT, cells.get(actPos+1));
                    cells.get(actPos).setNeighbor(Direction.DOWN, cells.get(actPos+width));
                } else if(j == width -1){
                    cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos-width));
                    cells.get(actPos).setNeighbor(Direction.LEFT, cells.get(actPos-1));
                    cells.get(actPos).setNeighbor(Direction.DOWN, cells.get(actPos+width));
                } else{
                    cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos-width));
                    cells.get(actPos).setNeighbor(Direction.RIGHT, cells.get(actPos+1));
                    cells.get(actPos).setNeighbor(Direction.DOWN, cells.get(actPos+width));
                    cells.get(actPos).setNeighbor(Direction.LEFT, cells.get(actPos-1));
                }
            }
        }

        //Utolso sor

        int actPos = (height-1)*width;

        cells.get(actPos).setNeighbor(Direction.RIGHT, cells.get(actPos+1));
        cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos - width));
        actPos++;

        for(; actPos < height*width-1; actPos++){
            cells.get(actPos).setNeighbor(Direction.LEFT, cells.get(actPos - 1));
            cells.get(actPos).setNeighbor(Direction.RIGHT, cells.get(actPos + 1));
            cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos - width));
        }
        cells.get(actPos).setNeighbor(Direction.LEFT, cells.get(actPos - 1));
        cells.get(actPos).setNeighbor(Direction.UP, cells.get(actPos - width));
    }

    public void addPlayer() {
        Player p1 = new Player(null, 10, players.size()+1);
        p1.giveSlime(new Oil());
        while (!getRandomCell().stepOn(p1));
        players.add(p1);

        System.out.println("Player added");
    }

    private Cell getRandomCell() {
        int size = cells.size();
        Random rand = new Random();
        int n = rand.nextInt(size);

        return cells.get(n);
    }

    public void removePlayer(){
        if(players.size() > 1)
            players.remove(players.get(players.size()-1));
    }

    @Override
    public void userAdded() {
        numOfPlayers++;
    }

    @Override
    public void userRemoved() {
        numOfPlayers--;
    }

    @Override
    public void loadLevel(int id) {
        start(id);
    }

    @Override
    public void userStepped(int id, Direction direction) {
        if(players.size() >=  id && players.get(id-1) != null) {
            players.get(id - 1).move(direction);
            view.levelUpdated(getMapData());
        }
    }

    @Override
    public void userDroppedSlime(int id) {
        players.get(id-1).putSlime();
        view.levelUpdated(getMapData());
    }
}