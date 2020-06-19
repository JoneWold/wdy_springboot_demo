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
 * @author Administrator
 */
public class Boss extends Enemy {
    private Point Exp_point = null; //��ըλ��
    BufferedImage[] Bossimages = new BufferedImage[24]; //Boss����ͼ
    private int overHitTime = 0; //��ɱ��
    private Point overHitPosition = null; //��ɱ��ײ��λ��
    private Dimension overHitsize = null; //��ɱ��ײ�ķ�Χ
    private String[] bossBulletimg = {"imgs/bullet_03/00.gif", "imgs/bullet_03/01.gif", "imgs/bullet_03/02.gif", "imgs/bullet_03/03.gif"};

    public Boss(Point position, int speed, int life, BufferedImage EnemyImage,
                Direction enemyDirection) {
        super(position, speed, life, EnemyImage, enemyDirection);

        //��ʼ������ͼ
        for (int i = 0; i < Bossimages.length; i++) {
            String str = null;
            if (String.valueOf(i + 1).length() < 2) {
                str = "0" + (i + 1);
            } else {
                str = String.valueOf(i + 1);
            }
            try {
                Bossimages[i] = ImageIO.read(UserRobot.class.getResource("imgs/Boss/" + str + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MaxLife = life;
        Exp_point = new Point(this.position.x + 360, this.position.y + 130);
        exp = new Explosion(explosionimage, Exp_point, true);
        this.bodyPower = 1000; //�����˺�

        Boss = true;
        overHitPosition = new Point(this.position.x + 360, this.position.y + 130);//��ɱ��ײ��λ��
        overHitsize = new Dimension(150, 145);//��ɱ��ײ�ķ�Χ


    }

    //�����Լ�
    @Override
    public void drawMe(Graphics g, UserRobot ur) {
        //����Ѫ��
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

        //��ը��λ
        exp.position.x = this.position.x + 360;
        exp.position.y = this.position.y + 130;
        explosionMe(g);

        //��ʱ�����ӵ�
        timer++;
        overHitTime++; //��ɱ��
        if (timer >= 80) {
            timer = 0;
            EnemyBullet[] bt = fire();
            for (int i = 0; i < bt.length; i++) {
                enemybullets.add(bt[i]);
            }
        }
        //��ɱ
        if (overHitTime >= 800) {
            if ((overHitTime - 800) / 4 < 24) {
                g.drawImage(Bossimages[(overHitTime - 800) / 4], position.x, position.y, null);
            } else if (this.position.x >= -750) {
                //��ײ��Χ
                overHitPosition.x = this.position.x + 50;
                overHitPosition.y = this.position.y + 155;
                overHitsize.width = 460;
                overHitsize.height = 60;

                this.position.x -= Speed * 9;
                g.drawImage(Bossimages[23], position.x, position.y, null);
            } else {
                this.position.x = 700;
                //this.enemyDirection = Direction.DLeft;
                //��ͨ��Χ
                overHitPosition.x = this.position.x + 360;
                overHitPosition.y = this.position.y + 130;
                overHitsize.width = 150;
                overHitsize.height = 145;

                overHitTime = 0; //��ձ�ɱ
            }

        } else {
            if (this.life > 0) { //���žͻ����Լ�
                //��ͨ��Χ
                overHitPosition.x = this.position.x + 360;
                overHitPosition.y = this.position.y + 130;
                overHitsize.width = 150;
                overHitsize.height = 145;

                g.drawImage(Bossimages[0], position.x, position.y, null);
                move();
            }
        }


        //�����ӵ�
        if (enemybullets.size() > 0) {
            for (int i = 0; i < enemybullets.size(); i++) {
                if (enemybullets.get(i).life) {
                    //�ӵ�����
                    enemybullets.get(i).GraphicsBullet(g);
                    enemybullets.get(i).collision(ur);
                } else {
                    //�ӵ�����
                    enemybullets.remove(i);
                }
            }
        }
    }

    //��д�ƶ�����
    @Override
    public void move() {
        if (this.position.x >= 330) { //�볡���ƶ�
            this.position.x -= Speed;
            this.enemyDirection = Direction.DUp;
        } else {//��Ϊ�����ƶ�
            if (this.position.y < -90) {
                this.enemyDirection = Direction.DDown; //ȷ������
                this.position.y += Speed;
            } else if (this.position.y > 330) {
                this.enemyDirection = Direction.DUp; //ȷ������
                this.position.y -= Speed;
            } else if (this.enemyDirection == Direction.DDown) {
                this.position.y += Speed; //��������
            } else if (this.enemyDirection == Direction.DUp) {
                this.position.y -= Speed; //��������
            }

        }
    }

    //�õ��Լ��ľ�������
    @Override
    public Rectangle getMeRect() {
        Rectangle rectme = new Rectangle(overHitPosition, overHitsize);
        return rectme;
    }

    //����
    @Override
    public EnemyBullet[] fire() {
        Sound fireSound = new Sound("music/Cannon.mp3"); //��������
        fireSound.play();

        EnemyBullet[] bt = new EnemyBullet[3];
        //bt[0] = new EnemyBullet(new Point(position.x + 360,position.y + 35+ 130),true,"imgs/bullet_02/00.gif",Direction.DAuto,4,true,5,Chapter_01.userrobot.position.x,Chapter_01.userrobot.position.y,this.position.x,this.position.y);
        bt[0] = new EnemyBullet(new Point(position.x + 360, position.y + 35 + 130), true, bossBulletimg, Direction.DUpLeft, 4, true, 10, 0, 0, 0, 0);
        bt[1] = new EnemyBullet(new Point(position.x + 360, position.y + 35 + 130), true, bossBulletimg, Direction.DDownLeft, 4, true, 10, 0, 0, 0, 0);
        bt[2] = new EnemyBullet(new Point(position.x + 360, position.y + 35 + 130), true, bossBulletimg, Direction.DLeft, 4, true, 10, 0, 0, 0, 0);
        return bt;
    }
}
