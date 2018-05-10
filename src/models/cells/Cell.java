package models.cells;

import models.ViewData;
import models.pushables.Pushable;
import models.slimes.Slime;
import push_enums.Direction;
import visitor_pattern.Visitable;

import java.util.HashMap;
import java.util.Map;

/**
 * A jatekban talalhato cellak ososztolya.
 * Tartalmazza a szomszedjait es a rajta levo aktualis pushable objektumot
 */

public abstract class Cell implements Visitable, ViewData {
    /**
     * A models.pushables.Pushable, ami eppen a mezon all.
     */
    protected Pushable actPushable;

    /**
     * A mezo szomszedos mezoi.
     */
    private Map<Direction, Cell> neighbors;

    /**
     * A models.slimes.Slime, ami a mezon van.
     */
    protected Slime slime;

    /**
     * A konstruktor inicializalja a szomszedokat.
     */
    public Cell(){
        this.actPushable = null;

        neighbors = new HashMap<>();
        neighbors.put(Direction.LEFT, null);
        neighbors.put(Direction.RIGHT, null);
        neighbors.put(Direction.DOWN, null);
        neighbors.put(Direction.UP, null);

        slime = null;
    }

    /**
     * Ezzel a fuggvennyel lekerhetok a szomszedok.
     * @param dir az adott irany
     * @return a dir iranyban levo szomszed referenciaja
     */
    public Cell getNext(Direction dir){
        return neighbors.get(dir);
    }

    /**
     * Ezzel a fuggvennyel lehet ralepni a cellara
     * @param pushable a pushable, ami ralep a cellara
     * @return
     */
    public boolean stepOn(Pushable pushable){
        if (actPushable == null) {
            actPushable = pushable;
            actPushable.setActCell(this);
            return true;
        }

        return false;
    }

    /**
     * Ezzel a fuggvennyel adhato meg egy cella adott iranyban
     * levo szomszedja
     * @param dir irany
     * @param nextCell a dir-en levo szomszed
     */
    public void setNeighbor(Direction dir, Cell nextCell){
        neighbors.put(dir, nextCell);
    }

    /**
     * Ezzel a fuggvennyel lelephetunk a cellarol
     */
    public void stepOff()
    {
        actPushable = null;
    }

    /**
     * Ezzel a fuggvennyel lekerdezheto a mezon talalhato
     * aktualis pushable objektum
     * @return a mezon levo aktualis pushable objektum
     */
    public Pushable getActPushable() {
        return actPushable;
    }

    /**
     * Ezzel a fuggvennyel lekerdezheto, hogy az
     * a cellan van-e pushable objektum
     * @return Ures-e a mezo.
     */
    public boolean isEmpty() {
        return actPushable == null;
    }

    /**
     * Lehelyezi a parameterben megadott Slimeot a mezore.
     *
     * @param slime A kenoanyag, amit le szeretnenek helyezni.
     */
    public void putSlime(Slime slime) {
        this.slime = slime;
    }

    /**
     * Eltunteti a Slimeot a mezorol.
     */
    public void clearSlime() {
        this.slime = null;
    }

    /**
     * Visszaadja, hogy mennyi a tapadasi surlodasi egyutthatoja a mezonek.
     * @return A tapadasi surlodasi egyutthato.
     */
    public int getFriction() {
        int friction = 0;

        if (slime != null) {
            friction += slime.getFriction();
        }

        if (actPushable != null) {
            friction += actPushable.getFriction();
        }

        return friction;
    }

    protected String getData(char cellChar) {
        String res = "";

        boolean slimeBool = slime != null;
        boolean pushBool = actPushable != null;

        if (slimeBool || pushBool) {
            res += "{";
        }

        res += cellChar;

        if (slimeBool) {
            res += slime.getData();
        }

        if (pushBool) {
            res += actPushable.getData();
        }

        if (slimeBool || pushBool) {
            res += "}";
        }

        return res;
    }
}
