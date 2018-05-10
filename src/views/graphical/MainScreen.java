package views.graphical;

import controller.ControllerEventListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainScreen implements Screen {

    private ControllerEventListener listener;
    private MenuData selectedMenu;
    private int selectedLevel = 0;
    private Dimension size;
    private List<Square> squares;
    private Font titleFont = null;
    private Font menuFont = null;

    private List<MenuData> menus;

    public MainScreen(ControllerEventListener listener, Dimension size){

        this.size = size;
        this.listener = listener;

        if (squares == null) {
            squares = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                squares.add(new Square(size));
            }
        }

        try {
            InputStream is = new FileInputStream("res/fonts/NEON.TTF");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, is);

            is = new FileInputStream("res/fonts/NEON_80.TTF");
            menuFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        menus = new ArrayList<>();
        menus.add(new MenuData("start", 30f, 40f ));
        menus.add(new MenuData("<level " + selectedLevel + ">", 20f, 20f));
        menus.add(new MenuData("exit", 30f, 30f ));

        menus.get(0).setPrev(menus.get(0));
        menus.get(0).setNext(menus.get(2));
        menus.get(2).setNext(menus.get(2));
        menus.get(2).setPrev(menus.get(0));

        selectedMenu = menus.get(0);
    }

    class MenuData{
        private String text;
        private float size;
        private float padding;

        private MenuData next;
        private MenuData prev;

        public MenuData(String text, float size, float padding){
            this.text = text;
            this.size = size;
            this.padding = padding;
        }


        public String getText() {
            return text;
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

    }

    @Override
    public void render(Graphics g) {

        g.setColor(new Color(27,30,63));
        g.fillRect(0,0, size.width, size.height);

        drawSquares(g);
        drawTitle(g);
        drawMenu(g);
    }

    private void drawTitle(Graphics g){
        Graphics2D title = (Graphics2D) g;
        title.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        title.setColor(new Color(255,255,0));
        title.setFont(titleFont.deriveFont(70f));

        title.drawString("Sokoban", size.width / 2 - title.getFontMetrics().stringWidth("Sokoban") / 2 , 150);
    }

    private void drawSquares(Graphics g) {
        Graphics2D squaresG = (Graphics2D) g;

        for (int i = 0; i < squares.size(); i++) {
            Square actSquare = squares.get(i);
            squaresG.setColor(new Color(255, 255, 255));

            AffineTransform transform = new AffineTransform();
            transform.rotate(actSquare.getRotate(), actSquare.getX() + actSquare.getSize()/2, actSquare.getY() + actSquare.getSize()/2);
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


        for(int i = 0; i< menus.size(); i++) {
            title.setFont(menuFont.deriveFont(menus.get(i).getSize()));
            title.setColor(selectedMenu == menus.get(i) ? new Color(0, 255, 0):new Color(255, 255, 0));
            title.drawString(menus.get(i).getText(), size.width / 2 - title.getFontMetrics().stringWidth(menus.get(i).getText()) / 2, size.height / 2 + i*menus.get(i).getPadding());
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        switch (keyCode){
            case KeyEvent.VK_ENTER:
                if(selectedMenu == menus.get(0))
                    listener.loadLevel(99);
                break;
            case KeyEvent.VK_DOWN:
                selectedMenu = selectedMenu.getNext();
                break;
            case KeyEvent.VK_UP:
                selectedMenu = selectedMenu.getPrev();
        }
    }
}
