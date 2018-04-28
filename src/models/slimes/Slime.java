package models.slimes;

/**
 * A models.slimes.Slime osztaly, ami a kenoanyag rendszerhez kell.
 * Rendelkezik tapadasi surlodasi egyutthatoval.
 * Le lehet helyezni mezore.
 */
public abstract class Slime {
    /**
     * A models.slimes.Slime tapadasi surlodasi egyutthatoja.
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
        return friction;
    }
}
