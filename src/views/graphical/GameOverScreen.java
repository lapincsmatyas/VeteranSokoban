package views.graphical;

import controller.ControllerEventListener;
import controller.Game;
import push_enums.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class GameOverScreen implements Screen {

    ControllerEventListener listener;
    Dimension size;
    private List<Square> squares;
    Font gameOverFont = null;
    Point winnerData;
    Game.GameOverType cause;
    Point winnerPosition;

    public GameOverScreen(int winnerId, int point, Dimension size, List<Square> squares, Font gameOverFont, Game.GameOverType cause){
        this.squares = squares;
        this.gameOverFont = gameOverFont;
        this.size = size;
        winnerData = new Point(winnerId, point);
        winnerPosition = new Point(size.width/2-size.width/10, size.height-200-size.width/10);
        this.cause = cause;
    }

    Color background = new Color(25, 42, 86);

    float speed = -70f;
    float gravity = 9.8f;

    @Override
    public void update() {
        int newRed, newGreen,newBlue;
        if(cause == Game.GameOverType.WIN){
            newRed = background.getRed() - 1;
            if (newRed < 0) newRed = 0;
            newGreen = background.getGreen() + 1;
            if (newGreen > 150) newGreen = 150;
            newBlue = background.getBlue() - 1;
            if (newBlue < 0) newBlue = 0;
        } else {
            newRed = background.getRed() - 1;
            if (newRed < 0) newRed = 0;
            newGreen = background.getGreen() - 1;
            if (newGreen < 0) newGreen = 0;
            newBlue = background.getBlue() - 1;
            if (newBlue < 0) newBlue = 0;
        }
        background = new Color(newRed,newGreen,newBlue);

        speed+=gravity;
        winnerPosition.y+=speed;
        if(winnerPosition.y > size.height-200-size.width/10) {
            winnerPosition.y = size.height-200-size.width/10;
            speed = -70;
        }
    }


    private void jump(){

    }

    @Override
    public void render(Graphics g) {

        g.setColor(background);
        g.fillRect(0,0, size.width, size.height);

        drawSquares(g);
        drawWinner(g);
        drawBackText(g);
    }

    private void drawBackText(Graphics g) {
        g.setFont(gameOverFont.deriveFont(20f));
        g.setColor(new Color(255, 0, 0));
        String playerAddString = "Press ESC to go back to main menu!";
        g.drawString(playerAddString, size.width / 2 - g.getFontMetrics().stringWidth(playerAddString) / 2, size.height - 50);
    }

    private void drawWinner(Graphics g) {
        g.setFont(gameOverFont.deriveFont(60f));
        g.setColor(new Color(255, 255, 0));

        String titleText= "";
        String descriptionText = "";

        if(cause != Game.GameOverType.WIN){
            titleText = "Game Over!";
            if(cause == Game.GameOverType.DEATH){
                descriptionText = "Everybody died";
            } else{
                descriptionText = "Nothing else to move";
            }
        } else{
            titleText = "Player " + winnerData.x + " won!";
            if(winnerData.x == 1){
                g.setColor(new Color(255, 0, 255));
                g.fillOval(winnerPosition.x, winnerPosition.y, size.width / 5, size.width / 5);
                g.setColor(new Color(0, 255, 255));
                g.fillOval(winnerPosition.x + 2*size.width/10 , size.height-200, size.width / 10, size.width / 10);
                g.setColor(new Color(255, 0, 255));
            } else{
                g.setColor(new Color(0, 255, 255));
                g.fillOval(winnerPosition.x, winnerPosition.y, size.width / 5, size.width / 5);
                g.setColor(new Color(255, 0, 255));
                g.fillOval(winnerPosition.x + 2*size.width/10, size.height-200, size.width / 10, size.width / 10);
                g.setColor(new Color(0, 255, 255));
            }
        }
        g.setColor(new Color(255, 255, 0));
        g.drawString(titleText, size.width / 2 - g.getFontMetrics().stringWidth(titleText) / 2, 150);

        g.setFont(gameOverFont.deriveFont(30f));
        g.drawString(descriptionText, size.width / 2 - g.getFontMetrics().stringWidth(descriptionText) / 2, 200);
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

    @Override
    public void keyPressed(int keyCode) {
        switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("JUMP");
                jump();
                break;
        }
    }
}
