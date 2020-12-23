package shootgame;

import java.awt.image.BufferedImage;

public class Bullet extends Flyer {
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private int speed;
    public Bullet(){

    }


    public void boom(int x,int y,Hero hero) {

    }

    public Bullet(int x, int y) {
        super(x,y,Shootgame.bullet1,1);
        speed = 10;
    }

    @Override
    public void move() {
        y -= speed;
        this.setY(this.getY() - speed);
    }
}
