package views.graphical;

import java.awt.*;
import java.util.Random;

public class Square{
    private Random r;

    private int x;
    private int y;
    private double rotate;
    private int size;

    int dirX, dirY;
    int dirRot;

    Dimension dimension;

    public Square(Dimension dimension){
        r = new Random();
        this.dimension = dimension;
        this.x = r.nextInt(dimension.width - 40)+20;
        this.y = r.nextInt(dimension.height-40)+20;
        this.dirRot = r.nextBoolean() ? 1 : -1;
        this.size = r.nextInt(30)+10;

        this.dirX = r.nextBoolean() ? 1 : -1;
        this.dirY = r.nextBoolean() ? 1 : -1;
    }

    public int getSize(){
        return size;
    }

    public int getX(){
        return x;
    }

    public double getRotate(){
        return rotate;
    }

    public int getY(){
        return y;
    }

    public void move(){
        this.x += dirX;
        this.y += dirY;
        this.rotate+= dirRot*0.05;

        if(this.x > dimension.width - this.size || this.x < 0)
            this.dirX = -this.dirX;

        if(this.y > dimension.height - this.size||this.y < 0)
            this.dirY = -this.dirY;


    }
}
