public class Hole extends Cell {
    private boolean opened = false;

    public Hole() {
        Logger.getInstance().logWithDec("Hole", "Hole()");
    }

    public void open() {
        Logger.getInstance().logWithDec("Hole", "open()");
        opened = true;
    }

    public void close() {
        Logger.getInstance().logWithDec("Hole", "close()");
        opened = false;
    }

    public void change() {
        Logger.getInstance().logWithDec("Hole", "change()");
        opened = !opened;
    }

    public boolean isOpened() {
        Logger.getInstance().logWithDec("Hole", "isOpened()");
        return opened;
    }

    @Override
    public StepResult accept(Visitor visitor, Direction dir) {
        Logger.getInstance().log("Hole", "accept(Visitor, Direction)");

        StepResult result = visitor.visit(this, dir);

        Logger.getInstance().decIndentDepth();
        return result;
    }
}
