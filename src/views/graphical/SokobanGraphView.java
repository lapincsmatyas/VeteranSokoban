package views.graphical;

import controller.ControllerEventListener;
import views.View;

import javax.swing.*;

public class SokobanGraphView extends JFrame implements View{
    private ControllerEventListener listener;

    public SokobanGraphView(){
        super();
    }

    @Override
    public void init(ControllerEventListener listener) {
        setSize(500,500);
        setTitle("Sokoban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.listener = listener;
        add(new MainMenu(this.listener));
    }

    @Override
    public void view() {
        setVisible(true);
    }
}
