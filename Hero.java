package shootgame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hero extends Flyer {
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    private int life = 1;

    @Override
    public void boom(int x, int y, Hero hero) {

    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    private int Score;
    public Hero() {
        super(Shootgame.background.getWidth()/2-Shootgame.hero1.getWidth()/2,
                Shootgame.background.getHeight()/2+Shootgame.hero1.getHeight(),Shootgame.hero1,1);
    }


    public void boom(int x,int y) {

    }

    public Hero(int x, int y, BufferedImage image) {
        super(Shootgame.background.getWidth()/2-Shootgame.hero1.getWidth()/2,
                Shootgame.background.getHeight()/2+Shootgame.hero1.getHeight(),Shootgame.hero1,1);

    }

    @Override
    public void move() {
        System.out.println("用鼠标移动");
    }

    public Bullet[] shoot(){
            Bullet[] bulle = new Bullet[1];
            bulle[0] = new Bullet(this.getX()+this.getWidth()/2,this.getY());
            return bulle;
    }

    private int count = 0;

    public void swag(){
        count++;
        if(count % 5 == 0 ){
            this.setImage(Shootgame.hero1);
        }else {
            this.setImage(Shootgame.hero2);
        }

    }
}
