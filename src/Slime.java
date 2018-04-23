<<<<<<< .merge_file_a10224
/**
 * A Slime osztaly, ami a kenoanyag rendszerhez kell.
 * Rendelkezik tapadasi surlodasi egyutthatoval.
 * Le lehet helyezni mezore.
 */
public abstract class Slime implements Drawable {
    /**
     * A Slime tapadasi surlodasi egyutthatoja.
     */
    private int friction;

    /**
     * Az osztaly default konstruktora.
     *
     * @param friction A tapadasi surlodasi egyutthato.
     */
    public Slime(int friction) {
        this.friction = friction;
    }

    /**
     * Visszaadja a tapadasi surlodasi egyutthato erteket.
     * @return A tapadasi surlodasi egyutthato.
     */
    public int getFriction() {
=======
public abstract class Slime {
    protected int friction;

    public int getFriction(){
>>>>>>> .merge_file_a14156
        return friction;
    }
}
