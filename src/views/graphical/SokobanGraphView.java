package views;

import javax.swing.*;
import java.awt.*;

public class SokobanGraphView extends JFrame implements View {
    public SokobanGraphView(){
        super();
        Init();
    }

    @Override
    public void Init() {
        setSize(500,500);
        setTitle("Sokoban");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JPanel());
    }

    @Override
    public void Show() {
        setVisible(true);
    }
}
