package shootgame;

import java.awt.image.BufferedImage;

public class Ember {
    private BufferedImage[] images = {};
    private int index;
    private int i;
    private BufferedImage image;
    private int intevel = 10;
    private int x,y;

    public Ember(Flyer flyer){
        this.x = flyer.getX();
        this.y = flyer.getY();
    }
}
