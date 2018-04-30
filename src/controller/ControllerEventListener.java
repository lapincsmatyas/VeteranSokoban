package controller;

import push_enums.Direction;

public interface ControllerEventListener {
    void userAdded();
    void userRemoved();
    void loadLevel(int id);
    void userStepped(int id, Direction direction);
    void userDroppedSlime(int id);
}
