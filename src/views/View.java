package views;

import controller.ControllerEventListener;
import controller.Game;

import java.util.List;

public interface View {
    public void init(ControllerEventListener listener);
    public void view();
    public void levelLoaded(List<List<String>> data);
}
