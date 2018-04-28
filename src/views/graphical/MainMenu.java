package views.graphical;

import controller.ControllerEventListener;
import controller.Game;

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
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class MainMenu extends JPanel implements ActionListener, KeyListener {

    ControllerEventListener listener;

    private List<Square> squares;

    public MainMenu(ControllerEventListener listener) {
        setDoubleBuffered(true);
        this.addKeyListener(this);
        this.setFocusable(true);

        this.listener = listener;

        Timer t = new Timer(40, this);
        t.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(new Color(40, 35, 53));
        if (squares == null) {
            squares = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                squares.add(new Square(this.getSize()));
            }
        }
        drawSquares(g);
        drawTitle(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private void drawSquares(Graphics g) {
        Graphics2D squaresG = (Graphics2D) g;

        for (int i = 0; i < squares.size(); i++) {
            Square actSquare = squares.get(i);
            squaresG.setColor(new Color(255, 255, 255));
            squaresG.drawRect(actSquare.getX(), actSquare.getY(), actSquare.getSize(), actSquare.getSize());
            squaresG.setColor(new Color(255, 255, 255, 20));
            squaresG.fillRect(actSquare.getX(), actSquare.getY(), actSquare.getSize(), actSquare.getSize());

            actSquare.move();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    boolean secondActivated = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 83) {
            if (secondActivated) {
                if (listener != null) {
                    listener.userRemoved();
                    secondActivated = !secondActivated;
                }
            } else {
                if(listener != null) {
                    listener.userAdded();
                    secondActivated = !secondActivated;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public class Square{
        private Random r;

        private int x;
        private int y;
        private int rotate;
        private int size;

        int dirX, dirY;

        Dimension dimension;

        public Square(Dimension dimension){
            r = new Random();
            this.dimension = dimension;
            this.x = r.nextInt(dimension.width - 40)+20;
            this.y = r.nextInt(dimension.height-40)+20;
            this.rotate = r.nextInt(180);
            this.size = r.nextInt(30)+10;

            this.dirX = r.nextBoolean() ? 1 : -1;
            this.dirY = r.nextBoolean() ? 1 : -1;
        }

        public int getSize(){
            return size;
        }

        public int getX(){
            return x;
        }

        public int getRotate(){
            return rotate;
        }

        public int getY(){
            return y;
        }

        public void move(){
            this.x += dirX;
            this.y += dirY;

            if(this.x > dimension.width - this.size || this.x < 0)
                this.dirX = -this.dirX;

                if(this.y > dimension.height - this.size||this.y < 0)
                this.dirY = -this.dirY;
        }
    }


    private void drawTitle(Graphics g){

        Graphics2D title = (Graphics2D) g;
        title.setColor(new Color(255, 255, 255));
        title.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Font titleFont = null;
        try {
            InputStream is = new FileInputStream("res/fonts/NEON.TTF");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(70f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        title.setColor(new Color(255,255,0));
        title.setFont(titleFont.deriveFont(70f));

        title.drawString("Sokoban", getWidth() / 2 - title.getFontMetrics().stringWidth("Sokoban") / 2 , 150);
    }
}
