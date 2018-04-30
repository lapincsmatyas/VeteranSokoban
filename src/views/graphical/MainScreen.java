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

    ControllerEventListener listener;
    int selectedMenu;
    Dimension size;
    private List<Square> squares;
    Font titleFont = null;
    Font menuFont = null;

    String[] menus;

    public MainScreen(ControllerEventListener listener, Dimension size){
        selectedMenu = 0;
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

        menus = new String[]{"start","exit"};
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

        title.setFont(menuFont.deriveFont(30f));
        for(int i = 0; i< menus.length; i++) {
            title.setColor(new Color(selectedMenu == i ? 0 : 255, 255, 0));
            title.drawString(menus[i], size.width / 2 - title.getFontMetrics().stringWidth(menus[i]) / 2, size.height / 2 + i*40);
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        switch (keyCode){
            case KeyEvent.VK_ENTER:
                if(selectedMenu == 0)
                    listener.loadLevel(99);
                break;
            case KeyEvent.VK_DOWN:
                selectedMenu++;
                if(selectedMenu > menus.length-1)
                    selectedMenu = menus.length-1;
                break;
            case KeyEvent.VK_UP:
                selectedMenu--;
                if(selectedMenu < 0)
                    selectedMenu = 0;
        }
    }
}
