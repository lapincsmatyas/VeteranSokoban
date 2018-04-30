package views.graphical;

import java.awt.*;

public interface Screen {
    void update();
    void render(Graphics g);
    public void keyPressed(int keyCode);
}
