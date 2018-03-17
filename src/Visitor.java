public interface Visitor {
    public boolean visit(Field field, Direction dir);

    public boolean visit(Pillar pillar, Direction dir);

    public boolean visit(Hole hole, Direction dir);

    public boolean visit(Switch lever, Direction dir);

    public boolean visit(Target target, Direction dir);
}
