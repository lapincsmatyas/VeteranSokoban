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
    Font titleFont = null;
    Font menuFont = null;
    private List<List<String>> map;

    public GameScreen(ControllerEventListener listener, Dimension size,List<List<String>> map, List<Square> squares){
        selectedMenu = 0;
        this.size = size;
        this.listener = listener;

        this.squares = squares;
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

        updateMap(map);
    }

    public void updateMap(List<List<String>> map) {
        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.get(i).size(); j++){
                System.out.print(map.get(i).get(j));
            }
            System.out.println();
        }

        this.map = map;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

        g.setColor(new Color(27,30,63));
        g.fillRect(0,0, size.width, size.height);

        drawSquares(g);
        drawMap(g);
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
        g.fillRect(x,y, width*squareSize, height*squareSize);

        boolean isPrevPlayer = false;

        for(int i = 0; i < map.size(); i++){
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
}
