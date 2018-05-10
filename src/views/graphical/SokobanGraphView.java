package views.graphical;

import controller.ControllerEventListener;
import views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SokobanGraphView extends JFrame implements View, KeyListener{

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
                gameState = State.MENU;
                actScreen = menu;
                break;
            default:
                actScreen.keyPressed(e.getKeyCode());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    enum State{
        MENU,
        GAME
    }

    State gameState;
    Screen actScreen;

    @Override
    public void init(ControllerEventListener listener, int numOfLevels) {
        this.listener = listener;
        gameState = State.MENU;
        canvas = new Canvas();
        this.numOfLevels = numOfLevels;

        setSize(600,600);
        setTitle("Sokoban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
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
