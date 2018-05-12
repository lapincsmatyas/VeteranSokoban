package views.graphical;

import controller.ControllerEventListener;
import push_enums.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    ControllerEventListener listener;
    int selectedMenu;
    Dimension size;
    private List<Square> squares;
    Font pointFont = null;
    List<Integer> playerPoints;
    private List<List<String>> map;

    public GameScreen(ControllerEventListener listener, Dimension size,List<List<String>> map, List<Square> squares, Font pointFont){
        selectedMenu = 0;
        this.size = size;
        this.listener = listener;

        this.squares = squares;
        this.pointFont = pointFont;

        updateMap(map);
    }

    public void updateMap(List<List<String>> map) {
        playerPoints = new ArrayList<>();
        for (int i = 0; i < map.get(map.size() - 1).size(); i++)
            playerPoints.add(Integer.parseInt(map.get(map.size() - 1).get(i)));

        this.map = map;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

        g.setColor(new Color(25, 42, 86));
        g.fillRect(0,0, size.width, size.height);

        drawSquares(g);
        drawMap(g);
        drawPoints(g);
        drawBackText(g);
    }

    private void drawPoints(Graphics g) {
        g.setFont(pointFont.deriveFont(20f));
        g.setColor(new Color(255, 255, 0));
        for(int i = 0; i < playerPoints.size(); i++){
            g.drawString("Player " + (i+1) + " points: " + playerPoints.get(i), 20+(i*size.width / playerPoints.size()), 30);
        }

    }

    private void drawMap(Graphics g){

        int width = map.get(0).size();
        int height = map.size();

        int windowMax = size.width < size.height ? size.width : size.height;
        int squareMax = width < height ? height : width;

        int squareSize = (windowMax / (squareMax)) - 5;

        int x = size.width / 2 - (width*squareSize/2)-10;
        int y = size.height / 2 - (height*squareSize/2)-10;

        g.setColor(new Color(27,30,63));
        g.fillRect(x+squareSize,y+squareSize, (width-2)*squareSize, (height-3)*squareSize);

        boolean isPrevPlayer = false;

        for(int i = 0; i < map.size() - 1; i++){
            for(int j = 0; j < map.get(0).size(); j++){

                String actString = map.get(i).get(j);
                for(int k = 0; k < actString.length(); k++){

                    switch (actString.charAt(k)){
                        case '.':
                            g.setColor(new Color(255,255,255));
                            g.drawRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
                            g.setColor(new Color(255, 255, 255, 20));
                            g.fillRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
                            isPrevPlayer = false;
                            break;
                        case '*':
                            g.setColor(new Color(255,255,255));
                            g.drawRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
                            g.setColor(new Color(255, 255, 255, 20));
                            g.fillRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
                            isPrevPlayer = false;
                            break;
                        case 'c':
                            g.drawRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
                            g.drawRect(x+5 + j * squareSize,y + 5 +i*squareSize,squareSize-10, squareSize-10);
                            g.setColor(new Color(151,83,0));
                            g.fillRect(x+5 + j * squareSize,y + 5 +i*squareSize,squareSize-10, squareSize-10);
                            isPrevPlayer = false;
                            break;
                        case 't':
                            g.drawRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
                            g.setColor(new Color(0,255,0, 100));
                            g.fillRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
                            isPrevPlayer = false;
                            break;
                        case 'o':
                            if(isPrevPlayer){
                                g.setColor(new Color(172,172,172));
                                g.fillOval(x + j * squareSize + squareSize/2, y + i * squareSize + squareSize/2, squareSize/3, squareSize/3);
                            } else {
                                g.drawRect(x + j * squareSize, y + i * squareSize, squareSize, squareSize);
                                g.setColor(new Color(172, 172, 172));
                                g.fillRect(x + j * squareSize, y + i * squareSize, squareSize, squareSize);
                                isPrevPlayer = false;
                            }
                            break;
                        case 'h':
                            if(isPrevPlayer){
                                g.setColor(new Color(254, 133, 0));
                                g.fillOval(x + j * squareSize + squareSize/2, y + i * squareSize + squareSize/2, squareSize/3, squareSize/3);
                            } else {
                                g.setColor(new Color(254, 133, 0));
                                g.fillRect(x + j * squareSize, y + i * squareSize, squareSize, squareSize);
                                g.setColor(new Color(255, 255, 255));
                                g.drawRect(x + j * squareSize, y + i * squareSize, squareSize, squareSize);
                            }
                            isPrevPlayer = false;
                            break;
                        case 'O':
                            g.setColor(new Color(0,0,0));
                            g.fillRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
                            g.setColor(new Color(255,255,255));
                            g.drawRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);

                            break;
                        default:
                            int temp = squareSize / 4;
                            if(actString.charAt(k) == '1') {
                                g.setColor(new Color(255, 0, 255));
                                g.fillOval(x + temp + j * squareSize, y + temp + i * squareSize, squareSize-2*temp, squareSize - 2*temp);
                            }
                            else if(actString.charAt(k) == '2'){
                                g.setColor(new Color(0, 255, 255));
                                g.fillOval(x + temp + j * squareSize, y + temp + i * squareSize, squareSize-2*temp, squareSize - 2*temp);
                            }
                            isPrevPlayer = true;
                            break;
                    }
                }



            }
        }

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
            case KeyEvent.VK_DOWN:
                listener.userStepped(1, Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                listener.userStepped(1, Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                listener.userStepped(1, Direction.LEFT);
                break;
            case KeyEvent.VK_UP:
                listener.userStepped(1, Direction.UP);
                break;
            case KeyEvent.VK_NUMPAD0:
                listener.userDroppedSlime(1);
                break;
            case KeyEvent.VK_S:
                listener.userStepped(2, Direction.DOWN);
                break;
            case KeyEvent.VK_D:
                listener.userStepped(2, Direction.RIGHT);
                break;
            case KeyEvent.VK_A:
                listener.userStepped(2, Direction.LEFT);
                break;
            case KeyEvent.VK_W:
                listener.userStepped(2, Direction.UP);
                break;
            case KeyEvent.VK_0:
                listener.userDroppedSlime(2);
                break;
        }
    }

    private void drawBackText(Graphics g) {
        g.setFont(pointFont.deriveFont(20f));
        g.setColor(new Color(255, 0, 0));
        String playerAddString = "Press ESC to go back to main menu!";
        g.drawString(playerAddString, size.width / 2 - g.getFontMetrics().stringWidth(playerAddString) / 2, size.height - 50);
    }
}
