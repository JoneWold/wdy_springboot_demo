package com.wdy.game;


import com.wdy.game.constant.Direction;
import com.wdy.game.engin.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static com.wdy.game.constant.CommonConstant.screen_size;

/**
 * �л�
 *
 * @author Administrator
 */
public class Enemy {
    /**
     * �Ƿ�BOSS
     */
    boolean Boss = false;
    public int MaxLife = 0;
    // �ߴ�
    private Dimension size = null;
    // λ��
    protected Point position = null;
    /**
     * �ٶ�
     */
    protected int Speed = 3;
    /**
     * ����ֵ
     */
    int life = 0;
    protected BufferedImage EnemyImage = null;
    /**
     * �ƶ�����
     */
    protected Direction enemyDirection = Direction.DStop;
    /**
     * ��ըͼƬ
     */
    protected String[] explosionimage = new String[6];
    // �����ײ����
    int bodyPower = 10;
    /**
     * �������
     */
    Random random = new Random();
    /**
     * �о�������ӵ�
     */
    protected ArrayList<EnemyBullet> enemybullets = new ArrayList<EnemyBullet>();

    public Explosion exp = null;
    protected String[] bulletimg = {"imgs/bullet_02/00.gif"};
    protected int timer;

    /**
     * ��ʼ���о�
     */
    public Enemy(Point position, int speed, int life, BufferedImage EnemyImage,
                 Direction enemyDirection) {
        this.position = position;
        Speed = speed;
        this.life = life;
        this.enemyDirection = enemyDirection;
        this.EnemyImage = EnemyImage;

        // ΪBOSS�ж�
        if (EnemyImage != null) {
            // �����Լ��ĳߴ�
            this.size = new Dimension(this.EnemyImage.getWidth(),
                    this.EnemyImage.getHeight());
        }
        // ��ʼ����ըͼ
        for (int i = 1; i < 7; i++) {
            explosionimage[i - 1] = "imgs/Explosion/0" + i + ".gif";
        }
        // ����BOSS
        Boss = false;
        exp = new Explosion(explosionimage, position, true);
    }

    /**
     * �����Լ�
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

        // ��ʱ�����ӵ�
        timer++;
        if (timer >= 80) {
            timer = 0;
            EnemyBullet[] bt = fire();
            for (int i = 0; i < bt.length; i++) {
                enemybullets.add(bt[i]);
            }
        }

        // �����ӵ�
        if (enemybullets.size() > 0) {
            for (int i = 0; i < enemybullets.size(); i++) {
                if (enemybullets.get(i).life) {
                    // �ӵ�����
                    enemybullets.get(i).GraphicsBullet(g);
                    enemybullets.get(i).collision(ur);
                } else {
                    // �ӵ�����
                    enemybullets.remove(i);
                }
            }
        }
    }

    /**
     * �Լ���ը
     */
    public void explosionMe(Graphics g) {
        if (this.life <= 0) {
            exp.showMe(g);
        }
    }

    /**
     * �õ��Լ��ľ�������
     */
    public Rectangle getMeRect() {
        Rectangle rectme = new Rectangle(this.position, this.size);
        return rectme;
    }

    /**
     * �ƶ�
     */
    public void move() {
        switch (enemyDirection) {
            case DUp:
                position.y -= Speed;
                break; // ��
            case DDown:
                position.y += Speed;
                break; // ��
            case DLeft:
                position.x -= Speed;
                break; // ��
            case Dright:
                position.x += Speed;
                break; // ��
            case DUpLeft:
                position.y -= Speed;
                position.x -= Speed;
                break; // ����
            case DUpright:
                position.y -= Speed;
                position.x += Speed;
                break; // ����
            case DDownLeft:
                position.y += Speed;
                position.x -= Speed;
                break; // ����
            case DDownRight:
                position.y += Speed;
                position.x += Speed;
                break; // ����
            default:
                break;
        }

        // �Ƴ��߽��NPC
        if (position.x + size.width <= 0 || position.y + size.height <= 0
                || position.y - size.height >= screen_size.height) {
            life = 0;
        }
    }

    /**
     * ����
     */
    public EnemyBullet[] fire() {
        // ��������
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
