package views.graphical;

import controller.ControllerEventListener;

import java.awt.*;
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
        this.map = map;
        int width = map.get(0).size();
        int height = map.size();
        System.out.println(width + " " + height);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

        g.setColor(new Color(40,35,53));
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

        g.setColor(new Color(40,35,53));
        g.fillRect(x,y, width*squareSize, height*squareSize);

        g.setColor(new Color(255,255,255));
        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.get(0).size(); j++){
                /*
                String actString = map.get(i).get(j);
                for(int k = 0; k < actString.length(); k++){
                    switch (actString.charAt(i)){
                        case '.':
                            g.drawRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
                            break;
                        case 'c':
                            break;
                    }
                }
                */

                g.drawRect(x + j * squareSize,y +i*squareSize,squareSize, squareSize);
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

    private void drawMenu(Graphics g) {
        Graphics2D title = (Graphics2D) g;
        title.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if(selectedMenu == 0) {
            title.setColor(new Color(0, 255, 0));
        } else
            title.setColor(new Color(255, 255, 0));
        title.setFont(menuFont.deriveFont(30f));

        title.drawString("start", size.width / 2 - title.getFontMetrics().stringWidth("start") / 2 , size.height / 2);
    }

    @Override
    public void keyPressed(int keyCode) {

    }
}
