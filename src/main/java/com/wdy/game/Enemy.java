package com.wdy.game;


import com.wdy.game.constant.Direction;
import com.wdy.game.engin.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static com.wdy.game.constant.CommonConstant.screen_size;

/**
 * 敌机
 *
 * @author wgch
 * @date 2020/6/19
 */
public class Enemy {
    /**
     * 是否BOSS
     */
    boolean Boss = false;
    public int MaxLife = 0;
    // 尺寸
    private Dimension size = null;
    // 位置
    protected Point position = null;
    /**
     * 速度
     */
    protected int Speed = 3;
    /**
     * 生命值
     */
    int life = 0;
    protected BufferedImage EnemyImage = null;
    /**
     * 移动方向
     */
    protected Direction enemyDirection = Direction.DStop;
    /**
     * 爆炸图片
     */
    protected String[] explosionimage = new String[6];
    // 身体冲撞威力
    int bodyPower = 10;
    /**
     * 随机开火
     */
    Random random = new Random();
    /**
     * 敌军发射的子弹
     */
    protected ArrayList<EnemyBullet> enemybullets = new ArrayList<EnemyBullet>();

    public Explosion exp = null;
    protected String[] bulletimg = {"imgs/bullet_02/00.gif"};
    protected int timer;

    /**
     * 初始化敌军
     */
    public Enemy(Point position, int speed, int life, BufferedImage EnemyImage,
                 Direction enemyDirection) {
        this.position = position;
        Speed = speed;
        this.life = life;
        this.enemyDirection = enemyDirection;
        this.EnemyImage = EnemyImage;

        // 为BOSS判断
        if (EnemyImage != null) {
            // 设置自己的尺寸
            this.size = new Dimension(this.EnemyImage.getWidth(),
                    this.EnemyImage.getHeight());
        }
        // 初始化爆炸图
        for (int i = 1; i < 7; i++) {
            explosionimage[i - 1] = "imgs/Explosion/0" + i + ".gif";
        }
        // 不是BOSS
        Boss = false;
        exp = new Explosion(explosionimage, position, true);
    }

    /**
     * 绘制自己
     *
     * @param g
     * @param ur
     */
    public void drawMe(Graphics g, UserRobot ur) {
        if (this.life > 0) {
            g.drawImage(this.EnemyImage, position.x, position.y, null);
            move();
        }
        explosionMe(g);

        // 定时发射子弹
        timer++;
        if (timer >= 80) {
            timer = 0;
            EnemyBullet[] bt = fire();
            for (int i = 0; i < bt.length; i++) {
                enemybullets.add(bt[i]);
            }
        }

        // 绘制子弹
        if (enemybullets.size() > 0) {
            for (int i = 0; i < enemybullets.size(); i++) {
                if (enemybullets.get(i).life) {
                    // 子弹尚在
                    enemybullets.get(i).GraphicsBullet(g);
                    enemybullets.get(i).collision(ur);
                } else {
                    // 子弹消亡
                    enemybullets.remove(i);
                }
            }
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
     * 得到自己的矩形区域
     */
    public Rectangle getMeRect() {
        Rectangle rectme = new Rectangle(this.position, this.size);
        return rectme;
    }

    /**
     * 移动
     */
    public void move() {
        switch (enemyDirection) {
            case DUp:
                position.y -= Speed;
                break; // 上
            case DDown:
                position.y += Speed;
                break; // 下
            case DLeft:
                position.x -= Speed;
                break; // 左
            case DRight:
                position.x += Speed;
                break; // 右
            case DUpLeft:
                position.y -= Speed;
                position.x -= Speed;
                break; // 上左
            case DUpright:
                position.y -= Speed;
                position.x += Speed;
                break; // 上右
            case DDownLeft:
                position.y += Speed;
                position.x -= Speed;
                break; // 下左
            case DDownRight:
                position.y += Speed;
                position.x += Speed;
                break; // 下右
            default:
                break;
        }

        // 移出边界的NPC
        if (position.x + size.width <= 0 || position.y + size.height <= 0
                || position.y - size.height >= screen_size.height) {
            life = 0;
        }
    }

    /**
     * 开火
     */
    public EnemyBullet[] fire() {
        // 开火声音
        Sound fireSound = new Sound("music/Cannon.mp3");
        fireSound.play();
        EnemyBullet[] bt = new EnemyBullet[1];
        bt[0] = new EnemyBullet(new Point(position.x, position.y + 35), true,
                bulletimg, Direction.DAuto, 4, true, 5,
                Chapter01.userrobot.position.x,
                Chapter01.userrobot.position.y, this.position.x,
                this.position.y);
        return bt;
    }
}
