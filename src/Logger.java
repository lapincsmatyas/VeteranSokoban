public class Logger {
    private static Logger ourInstance = new Logger();

    public static Logger getInstance() {
        return ourInstance;
    }

    private static int indentDepth;

    //Azért -1-től kezdődik, mert minden log hívásnál inkrementálja, úgyhogy alapból 0.
    private Logger() {
        indentDepth = -1;
    }

    public void log(String msg) {
        indentDepth++;

        for (int i = 0; i < indentDepth; i++) {
            System.out.print("\t");
        }

        System.out.println(msg);
    }

    //Tesztesetek elején kell meghívni, hogy ne az előző állapotokat mutassa.
    //Valszeg felesleges, mert úgyis visszatér a dekrementálásokkal -1-re.
    public void resetIndentDepth() {
        indentDepth = -1;
    }

    //Dekrementálja a behúzás számlálót.
    //Minden függvény legeslegvégén meghívandó.
    public void decIndentDepth() {
        indentDepth--;
    }
}
