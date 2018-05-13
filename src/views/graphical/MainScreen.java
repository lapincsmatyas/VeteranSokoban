package views.graphical;

import controller.ControllerEventListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainScreen implements Screen {

    private ControllerEventListener listener;
    private MenuData selectedMenu;
    private int selectedLevel = 0;
    private boolean isTwoPlayer = false;
    private Dimension size;
    private List<Square> squares;
    private Font titleFont = null;
    private Font menuFont = null;
    private int numOfLevels;
    private List<MenuData> menus;
    private List<PlayerAvatar> players;
    private Random r;
    private float gravity = 3.8f;

    class PlayerAvatar{
        int x;
        int y;
        int dir;
        float speed;

        public PlayerAvatar(int x, int y, int dir, float speed){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.speed = speed;
        }
    }

    public MainScreen(ControllerEventListener listener, Dimension size, int numOfLevels, Font titleFont, Font menuFont) {

        this.size = size;
        this.listener = listener;
        this.numOfLevels = numOfLevels;
        players = new ArrayList<>();

        if (squares == null) {
            squares = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                squares.add(new Square(size));
            }
        }

        this.titleFont = titleFont;
        this.menuFont = menuFont;

        menus = new ArrayList<>();
        menus.add(new MenuData("start", 30f, 40f));
        menus.add(new MenuData("<level " + (selectedLevel + 1) + ">", 20f, 20f));
        menus.add(new MenuData("exit", 30f, 30f));

        menus.get(0).setPrev(menus.get(0));
        menus.get(0).setNext(menus.get(2));
        menus.get(2).setNext(menus.get(2));
        menus.get(2).setPrev(menus.get(0));

        selectedMenu = menus.get(0);
        r = new Random();
        players.add(new PlayerAvatar(r.nextInt(size.width), size.height-20, r.nextBoolean() ? r.nextInt(0 + 1 + 30) - 30 :  r.nextInt(30 + 1 - 0) + 0, -50f));
    }

    class MenuData {
        private String text;
        private float size;
        private float padding;

        private MenuData next;
        private MenuData prev;

        public MenuData(String text, float size, float padding) {
            this.text = text;
            this.size = size;
            this.padding = padding;
        }


        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public float getSize() {
            return size;
        }

        public float getPadding() {
            return padding;
        }

        public MenuData getNext() {
            return next;
        }

        public void setNext(MenuData next) {
            this.next = next;
        }

        public MenuData getPrev() {
            return prev;
        }

        public void setPrev(MenuData prev) {
            this.prev = prev;
        }
    }

    public List<Square> getSquares() {
        return squares;
    }



    @Override
    public void update() {
        for(int i = 0; i < players.size(); i++){
            PlayerAvatar actPlayer = players.get(i);
            actPlayer.x = actPlayer.x + actPlayer.dir;
            if(actPlayer.x < 0)
                actPlayer.dir = r.nextInt(30 + 1 - 0) + 0;
            if(actPlayer.x > size.width)
                actPlayer.dir = r.nextInt(0 + 1 + 30) - 30;

            actPlayer.speed +=gravity;
            actPlayer.y+=actPlayer.speed;
            if(actPlayer.y > size.height-20) {
                actPlayer.y = size.height-20;
                actPlayer.speed = -50f;
            }
        }
    }


    @Override
    public void render(Graphics g) {

        g.setColor(new Color(25, 42, 86));
        g.fillRect(0, 0, size.width, size.height);

        drawSquares(g);
        drawTitle(g);
        drawMenu(g);
        drawPlayerAdd(g);
        drawPlayers(g);
    }

    private void drawPlayers(Graphics g) {
        for(int i = 0;  i< players.size(); i++){
            if(i == 0) {
                g.setColor(new Color(255, 0, 255));
                g.fillOval(players.get(i).x, players.get(i).y, 20,20);
            }
            else {
                g.setColor(new Color(0, 255, 255));
                g.fillOval(players.get(i).x, players.get(i).y, 20,20);
            }
        }
    }

    private void drawPlayerAdd(Graphics g) {
        g.setFont(menuFont.deriveFont(20f));
        g.setColor(new Color(255, 0, 0));
        String playerAddString = isTwoPlayer ? "Press \'S' two remove second player" : "Press 'S' two add a second player";
        g.drawString(playerAddString, size.width / 2 - g.getFontMetrics().stringWidth(playerAddString) / 2, size.height - 50);
    }

    private void drawTitle(Graphics g) {
        Graphics2D title = (Graphics2D) g;
        title.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        title.setColor(new Color(255, 255, 0));
        title.setFont(titleFont.deriveFont(70f));

        title.drawString("Sokoban", size.width / 2 - title.getFontMetrics().stringWidth("Sokoban") / 2, 150);
        title.setFont(menuFont.deriveFont(30f));
        title.setColor(new Color(255, 0, 0));

        Graphics2D titleG = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        transform.rotate(50, size.width / 2 - title.getFontMetrics().stringWidth("Sokoban") -title.getFontMetrics().stringWidth("killer")/2,100);
        AffineTransform old = titleG.getTransform();
        titleG.transform(transform);
        title.drawString("killer", size.width / 2 - title.getFontMetrics().stringWidth("Sokoban") -title.getFontMetrics().stringWidth("killer") , 100);
        titleG.setTransform(old);
    }

    private void drawSquares(Graphics g) {
        Graphics2D squaresG = (Graphics2D) g;

        for (int i = 0; i < squares.size(); i++) {
            Square actSquare = squares.get(i);
            squaresG.setColor(new Color(255, 255, 255));

            AffineTransform transform = new AffineTransform();
            transform.rotate(actSquare.getRotate(), actSquare.getX() + actSquare.getSize() / 2, actSquare.getY() + actSquare.getSize() / 2);
            AffineTransform old = squaresG.getTransform();
            squaresG.transform(transform);

            squaresG.drawRect(actSquare.getX(), actSquare.getY(), actSquare.getSize(), actSquare.getSize());
            squaresG.setColor(new Color(255, 255, 255, 20));
            squaresG.fillRect(actSquare.getX(), actSquare.getY(), actSquare.getSize(), actSquare.getSize());

            squaresG.setTransform(old);

            actSquare.move();
        }
    }

    private void drawMenu(Graphics g) {
        Graphics2D title = (Graphics2D) g;
        title.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        for (int i = 0; i < menus.size(); i++) {
            title.setFont(menuFont.deriveFont(menus.get(i).getSize()));
            title.setColor(selectedMenu == menus.get(i) ? new Color(0, 255, 0) : new Color(255, 255, 0));
            title.drawString(menus.get(i).getText(), size.width / 2 - title.getFontMetrics().stringWidth(menus.get(i).getText()) / 2, size.height / 2 + i * menus.get(i).getPadding());
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                if (selectedMenu == menus.get(0))
                    listener.loadLevel(selectedLevel);
                else if (selectedMenu == menus.get(2))
                    System.exit(0);
                break;
            case KeyEvent.VK_S:
                if(isTwoPlayer){
                    listener.userRemoved();
                    players.remove(players.size()-1);
                } else {
                    listener.userAdded();
                    players.add(new PlayerAvatar(r.nextInt(size.width), size.height-20, r.nextBoolean() ? r.nextInt(0 + 1 + 30) - 30 :  r.nextInt(30 + 1 - 0) + 0, -50f));
                }
                isTwoPlayer = !isTwoPlayer;
                break;
            case KeyEvent.VK_DOWN:
                selectedMenu = selectedMenu.getNext();
                break;
            case KeyEvent.VK_UP:
                selectedMenu = selectedMenu.getPrev();
                break;
            case KeyEvent.VK_RIGHT:
                if (selectedMenu == menus.get(0)) {
                    selectedLevel = (selectedLevel + 1) % numOfLevels;
                    menus.get(1).setText("<level " + (selectedLevel + 1) + ">");
                }
                break;
            case KeyEvent.VK_LEFT:
                if (selectedMenu == menus.get(0)) {
                    selectedLevel--;
                    if (selectedLevel < 0)
                        selectedLevel = numOfLevels - 1;
                    menus.get(1).setText("<level " + (selectedLevel + 1) + ">");
                }
                break;
        }
    }
}
