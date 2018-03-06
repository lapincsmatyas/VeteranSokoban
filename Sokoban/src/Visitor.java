public interface Visitor {
    boolean visit(Field field, int dir);
    boolean visit(Pillar pillar, int dir);
    boolean visit(Hole hole, int dir);
}
