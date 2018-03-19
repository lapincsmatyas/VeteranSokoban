public class Logger {
    private static Logger ourInstance = new Logger();

    public static Logger getInstance() {
        return ourInstance;
    }

    //A behúzás mélysége.
    private static int indentDepth;

    /* Aktív-e a logger. Csak akkor tud a Logger logolni, ha ez true.
     * Azért van rá szükség, hogy a tesztek inicializálása ne legyen benne a logolásokban.
     */
    private static boolean active;

    //Azért -1-től kezdődik, mert minden log hívásnál inkrementálja, úgyhogy alapból 0.
    private Logger() {
        indentDepth = -1;
        active = true;
    }

    public void log(String namespace, String method) {
        if (active) {
            indentDepth++;

            for (int i = 0; i < indentDepth; i++) {
                System.out.print("\t");
            }

            System.out.println(namespace + "::" + method);
        }
    }

    public void logStepResult(StepResult result){
        System.out.print("The step was a: ");
        switch (result){
            case FAIL:
                System.out.println("fail.");
                break;
            case SUCCESS:
                System.out.println("success.");
                break;
            case SUCCESS_POINT:
                System.out.println("new point.");
                break;
        }
    }

    //Ha a függvényben nincs több függvényhívás, ez is hívható.
    public void logWithDec(String namespace, String method) {
        log(namespace, method);
        decIndentDepth();
    }

    //Tesztesetek elején kell meghívni, hogy ne az előző állapotokat mutassa.
    //Aktiválja a loggert és a behúzás mélységét visszaállítja.
    public void reset() {
        indentDepth = -1;
        active = true;
    }

    //Dekrementálja a behúzás számlálót.
    //Minden függvény legeslegvégén meghívandó, kivéve ha a logWithDec-et meghívták előtte.
    public void decIndentDepth() {
        if (active) {
            indentDepth--;
        }
    }

    public void deactivate() {
        active = false;
    }
}
