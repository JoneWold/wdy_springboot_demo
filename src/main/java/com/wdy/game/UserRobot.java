package com.wdy.game;


import com.wdy.game.constant.Direction;
import com.wdy.game.engin.BsGraphics;
import com.wdy.game.engin.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static com.wdy.game.constant.CommonConstant.SCREEN_SIZE;


/**
 * 玩家机体类
 *
 * @author wgch
 * @date 2020/6/19
 */
public class UserRobot {
    //尺寸
    private Dimension size = null;
    //位置
    public Point position = null;
    //速度
    private int speed = 3;
    //生命
    public int life = 0;
    //射击延迟
    private int bulletTime = 0;
    //身体冲撞威力
    public int bodyPower = 100;
    private int maxLife = 0;
    //机体图
    private BufferedImage robotimage01 = null, robotimage02 = null, robotimage03 = null, robotimage04 = null;
    //子弹图
    private BufferedImage bulletimage01 = null, bulletimage02 = null, bulletimage03 = null, bulletimage04 = null, bulletimage05 = null;
    //图片
    public BufferedImage robotImage = null;
    //子弹序列
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    //机器人方向
    private Direction robotDirection = Direction.DStop;
    //判断按键
    private boolean pUp = false, pDown = false, pLeft = false, pRight = false, pStop = false;
    private final String[] bulletImg = {"/game/imgs/bullet_01/00.gif"};
    //爆炸
    public Explosion exp = null;
    //死亡时说的话
    private int overspeakindex;
    private BufferedImage overspeakimg = null;
    private BufferedImage headimage = null;

    /**
     * 初始化
     */
    public UserRobot(Point position, int life, Direction robotDirection) {
        this.position = position;
        this.life = life;
        this.maxLife = life;
        this.robotDirection = robotDirection;
        //初始化图片
        try {
            robotimage01 = ImageIO.read(UserRobot.class.getResource("/game/imgs/Freedom_0001.gif"));
            robotimage02 = ImageIO.read(UserRobot.class.getResource("/game/imgs/Freedom_0002.gif"));
            robotimage03 = ImageIO.read(UserRobot.class.getResource("/game/imgs/Freedom_0003.gif"));
            robotimage04 = ImageIO.read(UserRobot.class.getResource("/game/imgs/Freedom_0004.gif"));

            bulletimage01 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("/game/imgs/bullet_01/01.gif")));
            bulletimage02 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("/game/imgs/bullet_01/02.gif")));
            bulletimage03 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("/game/imgs/bullet_01/03.gif")));
            bulletimage04 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("/game/imgs/bullet_01/04.gif")));
            bulletimage05 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("/game/imgs/bullet_01/05.gif")));

            //头像
            headimage = ImageIO.read(UserRobot.class.getResource("/game/imgs/jlhead.gif"));
            //拉克丝
            overspeakimg = ImageIO.read(UserRobot.class.getResource("/game/imgs/speak/lks2.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.robotImage = robotimage01;
        //从图片返回尺寸
        this.size = new Dimension(this.robotImage.getWidth(null), this.robotImage.getHeight(null));

        //初始化爆炸图
        String[] expImg = new String[6];
        for (int i = 1; i < 7; i++) {
            expImg[i - 1] = "/game/imgs/Explosion/0" + i + ".gif";
        }
        exp = new Explosion(expImg, position, true);

    }

    /**
     * 按键事件
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                pUp = true;
                break;
            case KeyEvent.VK_S:
                pDown = true;
                break;
            case KeyEvent.VK_A:
                pLeft = true;
                break;
            case KeyEvent.VK_D:
                pRight = true;
                break;

            //开火
            case KeyEvent.VK_J:
                if (bulletTime == 0 && life > 0) {
                    bulletTime = 15;
                }
                break;
            default:
                break;
        }

    }

    /**
     * 释放按键
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                pUp = false;
                break;
            case KeyEvent.VK_S:
                pDown = false;
                break;
            case KeyEvent.VK_A:
                pLeft = false;
                break;
            case KeyEvent.VK_D:
                pRight = false;
                break;
            default:
                break;
        }
    }

    /**
     * 自己爆炸
     */
    public void explosionMe(Graphics g) {
        if (this.life <= 0) {
            exp.showMe(g);
        }
    }

    /**
     * 获得机器人方向
     */
    public void getDirection() {
        //上
        if (pUp && !pDown && !pLeft && !pRight) {
            robotDirection = Direction.DUp;
        }
        //下
        if (!pUp && pDown && !pLeft && !pRight) {
            robotDirection = Direction.DDown;
        }
        //左
        if (!pUp && !pDown && pLeft && !pRight) {
            robotDirection = Direction.DLeft;
        }
        //右
        if (!pUp && !pDown && !pLeft && pRight) {
            robotDirection = Direction.DRight;
        }
        //停止
        if (!pUp && !pDown && !pLeft && !pRight) {
            robotDirection = Direction.DStop;
        }

        //左上
        if (pUp && !pDown && pLeft && !pRight) {
            robotDirection = Direction.DUpLeft;
        }
        //右上
        if (pUp && !pDown && !pLeft && pRight) {
            robotDirection = Direction.DUpright;
        }
        //左下
        if (!pUp && pDown && pLeft && !pRight) {
            robotDirection = Direction.DDownLeft;
        }
        //右下
        if (!pUp && pDown && !pLeft && pRight) {
            robotDirection = Direction.DDownRight;
        }
    }

    /**
     * 移动
     */
    public void robotMove() {
        int img = 1;
        switch (robotDirection) {
            case DUp:
                position.y -= speed;
                break; //上
            case DDown:
                position.y += speed;
                break; //下
            case DLeft:
                //向后飞的图片
                img = 3;
                position.x -= speed;
                break; //左
            case DRight:
                position.x += speed;
                break; //右

            case DUpLeft:
                //向后飞的图片
                img = 3;
                position.y -= speed;
                position.x -= speed;
                break; //上左
            case DUpright:
                position.y -= speed;
                position.x += speed;
                break; //上右
            case DDownLeft:
                //向后飞的图片
                img = 3;
                position.y += speed;
                position.x -= speed;
                break; //下左
            case DDownRight:
                position.y += speed;
                position.x += speed;
                break; //下右
            default:
                break;
        }

        //限制不移出屏幕
        if (position.y <= 25) {
            position.y = 25;
        }
        if (position.x <= 0) {
            position.x = 0;
        }
        if (position.x >= SCREEN_SIZE.width - size.width) {
            position.x = SCREEN_SIZE.width - size.width;
        }
        if (position.y >= SCREEN_SIZE.height - size.height) {
            position.y = SCREEN_SIZE.height - size.height;
        }

        //换机体图
        switch (img) {
            case 1:
                robotImage = robotimage01;
                break;
            //case 2:robotImage = robotimage02;break;
            case 3:
                robotImage = robotimage04;
                break;
            default:
                break;
        }
    }

    public void drawGunFire(int bullettime, Graphics g) {
        if (bullettime > 12 && bullettime <= 15) {
            g.drawImage(bulletimage01, position.x + 118, position.y + 3, null);
        } else if (bullettime > 9 && bullettime <= 12) {
            g.drawImage(bulletimage02, position.x + 118, position.y + 3, null);
        } else if (bullettime > 6 && bullettime <= 9) {
            g.drawImage(bulletimage03, position.x + 118, position.y + 3, null);
        } else if (bullettime > 3 && bullettime <= 6) {
            g.drawImage(bulletimage04, position.x + 118, position.y + 3, null);
        }
    }

    /**
     * 绘制自己
     */
    public void graphicsRobot(Graphics g, ArrayList<Enemy> em) {
        //延迟射击处理
        if (bulletTime > 0) {
            bulletTime--;
        }
        if (bulletTime > 10 && bulletTime <= 15) {
            this.robotImage = robotimage03;
            this.size.width = robotImage.getWidth();
            this.size.height = robotImage.getHeight();
        } else if (bulletTime > 5 && bulletTime <= 10) {
            this.robotImage = robotimage02;
            this.size.width = robotImage.getWidth();
            this.size.height = robotImage.getHeight();
        } else if (bulletTime > 1 && bulletTime <= 5) {
            this.robotImage = robotimage03;
            this.size.width = robotImage.getWidth();
            this.size.height = robotImage.getHeight();

            if (bulletTime == 2) {
                bullets.add(fire());
            }
        }

        drawGunFire(bulletTime, g);

        //处理子弹绘制
        if (bullets.size() > 0) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).life) {
                    //子弹尚在
                    bullets.get(i).GraphicsBullet(g);
                    //和所有敌军碰撞
                    for (int i2 = 0; i2 < em.size(); i2++) {
                        bullets.get(i).collision(em.get(i2));
                    }
                } else {
                    //子弹消亡
                    bullets.remove(i);
                }
            }
        }

        //处理死亡
        if (life > 0) {
            // 活着才绘制自己
            exp.i = 0;
            g.drawImage(BsGraphics.flipImage(robotImage), position.x, position.y, null);
        } else {
            explosionMe(g);
            overspeakindex++;
            if (overspeakindex < 400) {
                g.drawImage(overspeakimg, 200, 330, null);

                g.setFont(new Font("宋体", Font.BOLD, 16));
                g.setColor(Color.white);
                g.drawString("拉克丝", 420, 495);
                g.drawString("『基拉......』", 400, 520);
            } else {
                GameStartApp.gameStatic = 0;
            }
        }

        //绘制血条
        g.drawImage(headimage, 15, 500, null);
        int xt_length = this.life * 150 / this.maxLife;
        if (xt_length < 0) {
            xt_length = 0;
        }
        Color xt_color = Color.yellow;
        if (xt_length < 80) {
            xt_color = Color.orange;
        }
        if (xt_length < 40) {
            xt_color = Color.red;
        }
        g.setColor(xt_color);
        g.fillRect(130, 550, xt_length, 20);
        g.setColor(Color.BLACK);
        g.drawRect(130, 550, 150, 20);

        //处理机器人移动
        getDirection();
        robotMove();
    }

    /**
     * 获得机体矩形区域
     */
    public Rectangle getRect() {
        Rectangle rect = new Rectangle(this.position.x + 6, this.position.y + 6, this.size.width - 12, this.size.height - 12);
        return rect;
    }

    /**
     * 机体碰装机体
     */
    public void collision(Enemy em) {
        if (this.life > 0 && em.life > 0 && this.getRect().intersects(em.getMeRect())) {
            //被击中
            em.life = em.life - this.bodyPower;
            this.life = this.life - em.bodyPower;

            if (em.life <= 0) {
                //爆炸声
                Sound fireSound = new Sound("music/Explode.mp3");
                fireSound.play();
            }
        }
    }

    /**
     * 开火
     */
    public Bullet fire() {
        //开火声音
        Sound fireSound = new Sound("music/Beam.mp3");
        fireSound.play();
        Bullet bt = new Bullet(new Point(position.x + 120, position.y + 35), true,
                bulletImg, Direction.DRight, 8, true, 5, 0, 0, 0, 0);
        return bt;
    }
}
