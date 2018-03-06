public interface Visitor {
    boolean visit(Field field, Direction dir);
    boolean visit(Pillar pillar, Direction dir);
    boolean visit(Hole hole, Direction dir);
    boolean visit(Switch lever, Direction dir);
}
