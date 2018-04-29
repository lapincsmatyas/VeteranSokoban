package views;

import controller.ControllerEventListener;

public interface View {
    void init(ControllerEventListener listener);
    void view();
}
