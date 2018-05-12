package views.graphical;

import controller.ControllerEventListener;
import controller.Game;
import views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SokobanGraphView extends JFrame implements View, KeyListener, MouseListener, MouseMotionListener{

    ControllerEventListener listener;

    Canvas canvas;

    MainScreen menu;
    int numOfLevels;

    Font titleFont;
    Font menuFont;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                if(actScreen != menu) {
                    gameState = State.MENU;
                    actScreen = menu;
                } else{
                    System.exit(0);
                }
                break;
            default:
                actScreen.keyPressed(e.getKeyCode());
                break;
        }
    }

    Point startClick;

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        startClick = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = e.getX()-startClick.x;
        int deltaY = e.getY()-startClick.y;

        setLocation(getLocation().x + deltaX, getLocation().y + deltaY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    enum State{
        MENU,
        GAME,
        GAME_OVER
    }

    State gameState;
    Screen actScreen;

    @Override
    public void init(ControllerEventListener listener, int numOfLevels) {
        this.listener = listener;
        gameState = State.MENU;
        canvas = new Canvas();
        this.numOfLevels = numOfLevels;

        setSize(1920*5/7,1080*5/7);
        setResizable(false);
        setUndecorated(true);
        setTitle("Sokoban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);

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

        add(canvas);
        menu = new MainScreen(listener, getSize(), numOfLevels, titleFont,menuFont);
        actScreen = menu;
    }

    @Override
    public void view() {
        setVisible(true);
    }

    @Override
    public void levelLoaded(List<List<String>> data) {
        gameState = State.GAME;
        actScreen = new GameScreen(listener, this.getSize(),data, menu.getSquares(), menuFont);
    }

    @Override
    public void levelUpdated(List<List<String>> data) {
        if(gameState == State.GAME)
            ((GameScreen)actScreen).updateMap(data);
    }

    @Override
    public void gameOver(int winnerId, int point, Game.GameOverType cause) {
        gameState = State.GAME_OVER;
        actScreen = new GameOverScreen(winnerId, point, this.getSize(), menu.getSquares(), menuFont, cause);
    }

    class Canvas extends JPanel implements ActionListener{

        public Canvas(){
            Timer t = new Timer(40, this);
            t.start();
        }

        @Override
        public void paintComponent(Graphics g){
            if(actScreen != null) {
                actScreen.render(g);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(actScreen != null) {
                actScreen.update();
                repaint();
            }
        }
    }
}
