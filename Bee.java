package shootgame;

import java.awt.image.BufferedImage;

public class Bee extends Flyer {
    private int speed_x =1;
    private int speed_y =1;


    public void boom(int x,int y,Hero hero) {
         hero.setLife(hero.getLife()+1);
    }

    public Bee() {
        super((int)(Math.random()*(Shootgame.background.getWidth()-Shootgame.bee.getWidth())),-Shootgame.bee.getHeight(),Shootgame.bee,1);
    }

    @Override
    public void move() {
       this.x += speed_x;
       this.y += speed_y;
       if (this.x == Shootgame.background.getWidth() - Shootgame.bee.getWidth()){
           speed_x = -1;
       }
       if (this.x == 0){
           speed_x = 1;
       }
    }
}
