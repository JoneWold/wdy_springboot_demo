package com.wdy.game;

import com.wdy.game.constant.Direction;
import com.wdy.game.engin.BsGraphics;
import com.wdy.game.engin.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.wdy.game.constant.CommonConstant.screen_size;

/**
 * �ӵ�
 *
 * @author Administrator
 */
public class Bullet {
    //�ߴ�
    private Dimension size = null;
    //λ��
    private Point position = null;
    //�ٶ�
    private int speed = 0;
    //����
    public boolean life;
    //ͼƬ
    public BufferedImage[] BulletImage = null;
    //����
    public int power = 0;
    //�ӵ�����
    private Direction bulletDirection = Direction.DStop;

    private boolean autob = true;
    // �������
    private int p1x;
    private int p1y;
    // �ӵ�����
    private int p2x;
    private int p2y;
    // �ӵ��Ƕ�
    private Direction bulletangle = Direction.DLeft;
    private int bulletindex = 0;

    //��ʼ���ӵ�
    public Bullet(Point position, boolean life, String[] BulletImage, Direction bulletDirection,
                  int speed, boolean fanzhuan, int power, int p1x, int p1y, int p2x, int p2y) {
        this.position = position;
        this.life = life;
        this.power = power;
        this.p1x = p1x;
        this.p1y = p1y;
        this.p2x = p2x;
        this.p2y = p2y;

        //��ʼ���ӵ�ͼ
        this.BulletImage = new BufferedImage[BulletImage.length];
        try {
            for (int i = 0; i < BulletImage.length; i++) {
                this.BulletImage[i] = ImageIO.read(Bullet.class.getResource(BulletImage[i]));
                if (fanzhuan) {
                    this.BulletImage[i] = BsGraphics.flipImage(this.BulletImage[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
        this.bulletDirection = bulletDirection;
        this.speed = speed;

        //��ͼƬ���سߴ�
        this.size = new Dimension(this.BulletImage[0].getWidth(null), this.BulletImage[0].getHeight(null));
    }

    //�����Լ�
    public void GraphicsBullet(Graphics g) {
        bulletindex++;
        if (bulletindex / 3 >= BulletImage.length) {
            bulletindex = 0;
        }
        g.drawImage(BulletImage[bulletindex / 3], position.x, position.y, null);
        robotMove();
    }

    public void robotMove() {
        switch (bulletDirection) {
            case DUp:
                position.y -= speed;
                break; //��
            case DDown:
                position.y += speed;
                break; //��
            case DLeft:
                position.x -= speed;
                break; //��
            case Dright:
                position.x += speed;
                break; //��

            case DUpLeft:
                position.y -= speed;
                position.x -= speed;
                break; //����
            case DUpright:
                position.y -= speed;
                position.x += speed;
                break; //����
            case DDownLeft:
                position.y += speed;
                position.x -= speed;
                break; //����
            case DDownRight:
                position.y += speed;
                position.x += speed;
                break; //����

            //�����ӵ�
            case DAuto:
                if (autob) {
                    try {
                        if (p2x - p1x >= 0) {
                            //ǰ��
                            if ((p2x - p1x) / (p1y - p2y) > 2 || (p2x - p1x) / (p2y - p1y) > 2) {
                                bulletangle = Direction.DLeft;//��
                            } else if ((p2y - p1y) > ((p2x - p1x) * 2)) {
                                bulletangle = Direction.DUp;//��
                            } else if ((p1y - p2y) > ((p2x - p1x) * 2)) {
                                bulletangle = Direction.DDown;//��
                            } else if (p2y - p1y > 0) {
                                bulletangle = Direction.DUpLeft;//����
                            } else {
                                bulletangle = Direction.DDownLeft; //����
                            }
                        } else {
                            //����
                            if ((p1x - p2x) / (p1y - p2y) > 2 || (p1x - p2x) / (p2y - p1y) > 2) {
                                bulletangle = Direction.Dright;//��
                            } else if ((p2y - p1y) > ((p1x - p2x) * 2)) {
                                bulletangle = Direction.DUp;//��
                            } else if ((p1y - p2y) > ((p1x - p2x) * 2)) {
                                bulletangle = Direction.DDown;//��
                            } else if (p2y - p1y > 0) {
                                bulletangle = Direction.DUpright;//����
                            } else {
                                bulletangle = Direction.DDownRight; //����
                            }
                        }
                    } catch (Exception e) {
                        //����0����
                    }
                    autob = false;
                }

                switch (bulletangle) {
                    case DUp:
                        position.y -= speed;
                        break; //��
                    case DDown:
                        position.y += speed;
                        break; //��
                    case DLeft:
                        position.x -= speed;
                        break; //��
                    case Dright:
                        position.x += speed;
                        break; //��

                    case DUpLeft:
                        position.y -= speed;
                        position.x -= speed;
                        break; //����
                    case DUpright:
                        position.y -= speed;
                        position.x += speed;
                        break; //����
                    case DDownLeft:
                        position.y += speed;
                        position.x -= speed;
                        break; //����
                    case DDownRight:
                        position.y += speed;
                        position.x += speed;
                        break; //����
                    default:
                        position.x -= speed;
                        break;
                }

                break;
            default:
                break;
        }

        //������Ļ
        if (position.y <= 25 || position.x <= 0 || position.x >= screen_size.width || position.y >= screen_size.height) {
            this.life = false;
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
     * �ӵ���ײ����
     */
    public void collision(Enemy em) {
        if (this.life && em.life > 0 && this.getMeRect().intersects(em.getMeRect())) {
            //������
            em.life = em.life - this.power;
            this.life = false;

            if (em.life <= 0) {
                //��ը��
                Sound fireSound = new Sound("music/Explode.mp3");
                fireSound.play();
            }
        }
    }

}
