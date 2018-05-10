package views;

import controller.ControllerEventListener;
import controller.Game;

import java.util.List;

public interface View {
    void init(ControllerEventListener listener, int numOfLevels);
    void view();
    void levelLoaded(List<List<String>> data);
    void levelUpdated(List<List<String>> data);
}
