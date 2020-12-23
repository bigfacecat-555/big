package shootgame;

import java.awt.image.BufferedImage;

public  abstract class Flyer {
    protected int x;
    protected int y;
    protected BufferedImage image;
    protected int width;
    protected   int height;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    protected int life;

    public Flyer(int x, int y, BufferedImage image,int life) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.life = life;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Flyer(){
    }
    public abstract void boom(int x,int y,Hero hero);

    public abstract void move();

    public static void GetFlyer(){
        Enemy enemy = new Enemy();
        Hero hero = new Hero();
        Enemy1 enemy_1 = new Enemy1();
        Bee bee_1 = new Bee();
    }
}
