package controller;

import push_enums.Direction;

public interface ControllerEventListener {
    void userAdded();
    void userRemoved();
    void userStepped(int id, Direction direction);
    void userDroppedSlime(int id);
}
