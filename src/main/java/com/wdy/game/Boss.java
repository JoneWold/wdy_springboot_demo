package com.wdy.game;

import com.wdy.game.constant.Direction;
import com.wdy.game.engin.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * boss
 *
 * @author wgch
 * @date 2020/6/19
 */
public class Boss extends Enemy {
    //爆炸位置
    private Point Exp_point = null;
    //Boss机体图
    BufferedImage[] Bossimages = new BufferedImage[24];
    //必杀技
    private int overHitTime = 0;
    //必杀冲撞的位置
    private Point overHitPosition = null;
    //必杀冲撞的范围
    private Dimension overHitsize = null;
    private String[] bossBulletimg = {"/game/imgs/bullet_03/00.gif", "/game/imgs/bullet_03/01.gif", "/game/imgs/bullet_03/02.gif", "/game/imgs/bullet_03/03.gif"};

    public Boss(Point position, int speed, int life, BufferedImage EnemyImage,
                Direction enemyDirection) {
        super(position, speed, life, EnemyImage, enemyDirection);

        //初始化机体图
        for (int i = 0; i < Bossimages.length; i++) {
            String str = null;
            if (String.valueOf(i + 1).length() < 2) {
                str = "0" + (i + 1);
            } else {
                str = String.valueOf(i + 1);
            }
            try {
                Bossimages[i] = ImageIO.read(UserRobot.class.getResource("/game/imgs/Boss/" + str + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MaxLife = life;
        Exp_point = new Point(this.position.x + 360, this.position.y + 130);
        exp = new Explosion(explosionimage, Exp_point, true);
        //身体伤害
        this.bodyPower = 1000;

        Boss = true;
        //必杀冲撞的位置
        overHitPosition = new Point(this.position.x + 360, this.position.y + 130);
        //必杀冲撞的范围
        overHitsize = new Dimension(150, 145);


    }

    /**
     * 绘制自己
     */
    @Override
    public void drawMe(Graphics g, UserRobot ur) {
        //绘制血条
        int xt_length = this.life * 150 / this.MaxLife;
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
        g.fillRect(this.position.x + 350, this.position.y + 90, xt_length, 10);
        g.setColor(Color.BLACK);
        g.drawRect(this.position.x + 350, this.position.y + 90, 150, 10);

        //爆炸定位
        exp.position.x = this.position.x + 360;
        exp.position.y = this.position.y + 130;
        explosionMe(g);

        //定时发射子弹
        timer++;
        //必杀技
        overHitTime++;
        if (timer >= 80) {
            timer = 0;
            EnemyBullet[] bt = fire();
            for (int i = 0; i < bt.length; i++) {
                enemybullets.add(bt[i]);
            }
        }
        //必杀
        if (overHitTime >= 800) {
            if ((overHitTime - 800) / 4 < 24) {
                g.drawImage(Bossimages[(overHitTime - 800) / 4], position.x, position.y, null);
            } else if (this.position.x >= -750) {
                //冲撞范围
                overHitPosition.x = this.position.x + 50;
                overHitPosition.y = this.position.y + 155;
                overHitsize.width = 460;
                overHitsize.height = 60;

                this.position.x -= Speed * 9;
                g.drawImage(Bossimages[23], position.x, position.y, null);
            } else {
                this.position.x = 700;
                //this.enemyDirection = Direction.DLeft;
                //普通范围
                overHitPosition.x = this.position.x + 360;
                overHitPosition.y = this.position.y + 130;
                overHitsize.width = 150;
                overHitsize.height = 145;
                //清空必杀
                overHitTime = 0;
            }

        } else {
            //活着就绘制自己
            if (this.life > 0) {
                //普通范围
                overHitPosition.x = this.position.x + 360;
                overHitPosition.y = this.position.y + 130;
                overHitsize.width = 150;
                overHitsize.height = 145;

                g.drawImage(Bossimages[0], position.x, position.y, null);
                move();
            }
        }


        //绘制子弹
        if (enemybullets.size() > 0) {
            for (int i = 0; i < enemybullets.size(); i++) {
                if (enemybullets.get(i).life) {
                    //子弹尚在
                    enemybullets.get(i).GraphicsBullet(g);
                    enemybullets.get(i).collision(ur);
                } else {
                    //子弹消亡
                    enemybullets.remove(i);
                }
            }
        }
    }

    /**
     * 重写移动方法
     */
    @Override
    public void move() {
        //入场的移动
        if (this.position.x >= 330) {
            this.position.x -= Speed;
            this.enemyDirection = Direction.DUp;
            //变为上下移动
        } else {
            if (this.position.y < -90) {
                //确定下移
                this.enemyDirection = Direction.DDown;
                this.position.y += Speed;
            } else if (this.position.y > 330) {
                //确定上移
                this.enemyDirection = Direction.DUp;
                this.position.y -= Speed;
            } else if (this.enemyDirection == Direction.DDown) {
                //持续下移
                this.position.y += Speed;
            } else if (this.enemyDirection == Direction.DUp) {
                //持续上移
                this.position.y -= Speed;
            }

        }
    }

    /**
     * 得到自己的矩形区域
     */
    @Override
    public Rectangle getMeRect() {
        Rectangle rectme = new Rectangle(overHitPosition, overHitsize);
        return rectme;
    }

    /**
     * 开火
     */
    @Override
    public EnemyBullet[] fire() {
        //开火声音
        Sound fireSound = new Sound("music/Cannon.mp3");
        fireSound.play();

        EnemyBullet[] bt = new EnemyBullet[3];
        //bt[0] = new EnemyBullet(new Point(position.x + 360,position.y + 35+ 130),true,"imgs/bullet_02/00.gif",Direction.DAuto,4,true,5,Chapter_01.userrobot.position.x,Chapter_01.userrobot.position.y,this.position.x,this.position.y);
        bt[0] = new EnemyBullet(new Point(position.x + 360, position.y + 35 + 130), true, bossBulletimg, Direction.DUpLeft, 4, true, 10, 0, 0, 0, 0);
        bt[1] = new EnemyBullet(new Point(position.x + 360, position.y + 35 + 130), true, bossBulletimg, Direction.DDownLeft, 4, true, 10, 0, 0, 0, 0);
        bt[2] = new EnemyBullet(new Point(position.x + 360, position.y + 35 + 130), true, bossBulletimg, Direction.DLeft, 4, true, 10, 0, 0, 0, 0);
        return bt;
    }
}
