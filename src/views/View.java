package views;

import controller.ControllerEventListener;
import controller.Game;

public interface View {
    public void init(ControllerEventListener listener);
    public void view();
}
