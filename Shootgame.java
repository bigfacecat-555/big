package shootgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.TimerTask;

public class Shootgame extends JPanel{
    public static BufferedImage background;
    public static BufferedImage hero1;
    public static BufferedImage hero2;
    public static BufferedImage enemy0;
    public static BufferedImage enemy1;
    public static BufferedImage enemy2;
    public static BufferedImage bullet1;
    public static BufferedImage bee;
    public static BufferedImage start;
    public static BufferedImage pause;
    public static BufferedImage gameover;
    public static BufferedImage enemy0_down1;
    public static BufferedImage enemy0_down2;
    public static BufferedImage enemy0_down3;
    public static BufferedImage enemy0_down4;
    private final int START = 0;
    private final int RUNNING = 1;
    private final int PAUSE = 2;
    private final  int GAMEOVER = 3;
    private int state = START;
    ArrayList<Flyer> flyerArrayList = new ArrayList<>();
    ArrayList<Bullet> bulletArrayList = new ArrayList<>();
    ArrayList<Flyer> asheArrayList = new ArrayList<>();
    //加载图片
    static {
        try {
            background = ImageIO.read(Shootgame.class.getResource("images/background.png"));
            hero1 = ImageIO.read(Shootgame.class.getResource("images/hero1.png"));
            hero2 = ImageIO.read(Shootgame.class.getResource("images/hero2.png"));
            enemy0 = ImageIO.read(Shootgame.class.getResource("images/enemy0.png"));
            enemy1 = ImageIO.read(Shootgame.class.getResource("images/enemy1.png"));
            enemy2 = ImageIO.read(Shootgame.class.getResource("images/enemy2.png"));
            bee = ImageIO.read(Shootgame.class.getResource("images/bee.png"));
            bullet1 = ImageIO.read(Shootgame.class.getResource("images/bullet1.png"));
            start = ImageIO.read(Shootgame.class.getResource("images/name.png"));
            pause = ImageIO.read(Shootgame.class.getResource("images/pause.png"));
            gameover = ImageIO.read(Shootgame.class.getResource("images/gameover.png"));
            enemy0_down1 = ImageIO.read(Shootgame.class.getResource("images/enemy0_down1.png"));
            enemy0_down2 = ImageIO.read(Shootgame.class.getResource("images/enemy0_down2.png"));
            enemy0_down3 = ImageIO.read(Shootgame.class.getResource("images/enemy0_down3.png"));
            enemy0_down4 = ImageIO.read(Shootgame.class.getResource("images/enemy0_down4.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Hero hero = new Hero();
    @Override
    //画图
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background,0,0,this);
        g.drawImage(hero.getImage(),hero.getX(),hero.getY(),this);
        paintBullet(g);
        paintflyobject(g);
        paintstate(g);
        paintScoreAndLife(g);
        paintAshe(g);
        repaint();
    }
    //画灰烬
    private void paintAshe(Graphics g) {
        for (int i = 0; i < asheArrayList.size(); i++) {
            g.drawImage(enemy0_down1,asheArrayList.get(i).getX(),asheArrayList.get(i).getY(),this);
        }
    }
    //画分数和英雄机生命
    private void paintScoreAndLife(Graphics g) {
        g.drawString("分数: " + hero.getScore(),5,20);
        g.drawString("生命: " + hero.getLife(),5,40);
    }
    //画状态
    private void paintstate(Graphics g) {
        if (state == PAUSE){
            g.drawImage(pause,0,0,background.getWidth(),background.getHeight(),this);
        }else if (state == START){
            g.drawImage(start,(background.getWidth()-start.getWidth())/2,100,this);
        }else if (state == GAMEOVER){
            g.drawImage(gameover,0,0,background.getWidth(),background.getHeight(),this);
        }
        repaint();
    }

    //画飞行物
    private void paintflyobject(Graphics g){
        for (int i = 0; i < flyerArrayList.size(); i++) {
            g.drawImage(flyerArrayList.get(i).getImage(),flyerArrayList.get(i).getX(),flyerArrayList.get(i).getY(),this);
        }
    }
    //画子弹
    private void paintBullet(Graphics g){
        for (int i = 0; i < bulletArrayList.size(); i++) {
            g.drawImage(bulletArrayList.get(i).getImage(),bulletArrayList.get(i).getX(),bulletArrayList.get(i).getY(),this);
        }
    }



    private java.util.Timer timer = new java.util.Timer();

    //创建飞行物
    int flyIndex = 0;

    private void creatFlyArrayList(){
        Flyer flyer;
        flyIndex++;
        if (flyIndex % 20 == 0){
            int q = (int)(Math.random()*20);
            if (q == 0){
                flyer = new Bee();
            }
            else if (q>0&&q<3){
                flyer = new Enemy1();
            }else {
                flyer = new Enemy();
            }
            flyerArrayList.add(flyer);
        }
    }

    //飞行物移动
    private void moveFlyObject(){
        for (int i = 0; i < flyerArrayList.size(); i++) {
            flyerArrayList.get(i).move();
        }
    }

    //飞行物移除
    private void removeFlyObject(){
        for (int i = 0; i < flyerArrayList.size(); i++) {
            if (flyerArrayList.get(i).getY() >= background.getHeight()){
                flyerArrayList.remove(i);
                i--;
            }
        }
    }

    //子弹移动
    private void moveBullets(){
        for (int i = 0; i < bulletArrayList.size(); i++) {
            bulletArrayList.get(i).move();
        }
    }

    //子弹移除
    private void removeBullets(){
        for (int i = 0; i < bulletArrayList.size(); i++) {
            if (bulletArrayList.get(i).getY() >= background.getHeight()){
                bulletArrayList.remove(i);
                i--;
            }
        }
    }

    //创建子弹集合
    private void shootAction() {
        if (flyIndex %10 ==0){
            Bullet[] bs = hero.shoot();
            for (int i = 0; i < bs.length; i++) {
                bulletArrayList.add(bs[i]);
            }
        }
    }
    //击毁飞行物
    private void flyBoom() {
        Bullet bullet;
        Flyer flyer;
        for (int i = 0; i < bulletArrayList.size(); i++) {
            for (int j = 0; j < flyerArrayList.size(); j++) {
                bullet = bulletArrayList.get(i);
                flyer = flyerArrayList.get(j);
                if (bullet.getX() <= flyer.getX() + flyer.getWidth() && bullet.getX()+bullet.getWidth() >= flyer.getX() &&
                    bullet.getY() <= flyer.getY()+flyer.getHeight() && bullet.getY()+bullet.getHeight() >= flyer.getHeight()){
                    bulletArrayList.remove(i);
                    i--;
                    flyer.life--;
                    if (flyer.life == 0){
                        asheArrayList.add(flyer);
                        flyerArrayList.remove(j);
                        flyer.boom(flyer.x,flyer.y,hero);
                        j--;
                    }

                }
            }
        }
        repaint();
    }

    private void heroBoom() {
        Flyer flyer;
        for (int i = 0; i < flyerArrayList.size(); i++) {
            flyer = flyerArrayList.get(i);
            if (hero.getX() <= flyer.getX() +flyer.getWidth() && hero.getX() + hero.getWidth() >= flyer.getX()&&
                hero.getY() <= flyer.getY() + flyer.getHeight() && hero.getY() + hero.getHeight() >= flyer.getY()){
                hero.setLife(hero.getLife()-1);
                flyerArrayList.remove(i);
                flyer.boom(flyer.x,flyer.y,hero);
                if (hero.getLife() == 0){
                    state = GAMEOVER;
                }
            }
        }
    }


    //计时器
    Enemy enemy =new Enemy();
    public void action(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNNING){
                    creatFlyArrayList();
                    moveFlyObject();
                    removeFlyObject();
                    removeBullets();
                    shootAction();
                    moveBullets();
                    flyBoom();
                    heroBoom();
                    hero.swag();

                    repaint();
                }
            }
        }, 0, 30);
        //添加监听器
        MouseAdapter mouseAdapter = new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e) {
                //e可以获取鼠标的x和y坐标
                if (state == RUNNING){
                    hero.setY(e.getY()-hero.getHeight()/2);
                    hero.setX(e.getX()-hero.getWidth()/2);

                    repaint();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (state == PAUSE){
                    state = RUNNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (state == RUNNING){
                    state = PAUSE;
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (state == START){
                    state = RUNNING;
                }
            }
        };
        this.addMouseMotionListener(mouseAdapter);
        this.addMouseListener(mouseAdapter);
    }


    public static void main(String[] args) {
        JFrame window = new JFrame();
        Shootgame shootgame = new Shootgame();
        window.setSize(background.getWidth()+17,background.getHeight());
        window.setDefaultCloseOperation(3);
        window.setLocationRelativeTo(null);
        window.add(shootgame);
        window.setVisible(true);
        shootgame.action();
    }
}
