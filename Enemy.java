package shootgame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Flyer {
    private Random random1 = new Random();
    private int life;
    int count = 0;

    public void boom(int x,int y,Hero hero) {
        hero.setScore(hero.getScore()+1);
    }

    public Enemy() {
        super((int)(Math.random()*(Shootgame.background.getWidth()-Shootgame.enemy0.getWidth())),-Shootgame.enemy0.getHeight(),Shootgame.enemy0,1);
    }

    public Enemy(int x, int y, BufferedImage image) {
        super(x, y, image,1);
        this.life = life;
    }
    @Override
    public void move() {
        this.y += 5;
    }
}
