package shootgame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy1 extends Flyer {
    private Random random = new Random();
    private int life;


    public void boom(int x,int y,Hero hero) {
        hero.setScore(hero.getScore()+5);
    }

    public Enemy1() {
        super((int)(Math.random()*(Shootgame.background.getWidth()-Shootgame.enemy1.getWidth())),-Shootgame.enemy1.getHeight(),Shootgame.enemy1,5);
    }

    public Enemy1(int x, int y, BufferedImage image, int life) {
        super(x, y, image,3);
        this.life = life;
    }

    @Override
    public void move() {
        this.y++;
    }

}
