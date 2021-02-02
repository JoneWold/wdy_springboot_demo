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
 * 子弹
 *
 * @author wgch
 * @date 2020/6/19
 */
public class Bullet {
    //尺寸
    private Dimension size = null;
    //位置
    private Point position = null;
    //速度
    private int speed = 0;
    //生命
    public boolean life;
    //图片
    public BufferedImage[] BulletImage = null;
    //威力
    public int power = 0;
    //子弹方向
    private Direction bulletDirection = Direction.DStop;

    private boolean autob = true;
    // 玩家坐标
    private int p1x;
    private int p1y;
    // 子弹坐标
    private int p2x;
    private int p2y;
    // 子弹角度
    private Direction bulletangle = Direction.DLeft;
    private int bulletindex = 0;

    //初始化子弹
    public Bullet(Point position, boolean life, String[] BulletImage, Direction bulletDirection,
                  int speed, boolean fanzhuan, int power, int p1x, int p1y, int p2x, int p2y) {
        this.position = position;
        this.life = life;
        this.power = power;
        this.p1x = p1x;
        this.p1y = p1y;
        this.p2x = p2x;
        this.p2y = p2y;

        //初始化子弹图
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

        //从图片返回尺寸
        this.size = new Dimension(this.BulletImage[0].getWidth(null), this.BulletImage[0].getHeight(null));
    }

    //绘制自己
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
                break; //上
            case DDown:
                position.y += speed;
                break; //下
            case DLeft:
                position.x -= speed;
                break; //左
            case DRight:
                position.x += speed;
                break; //右

            case DUpLeft:
                position.y -= speed;
                position.x -= speed;
                break; //上左
            case DUpright:
                position.y -= speed;
                position.x += speed;
                break; //上右
            case DDownLeft:
                position.y += speed;
                position.x -= speed;
                break; //下左
            case DDownRight:
                position.y += speed;
                position.x += speed;
                break; //下右

            //跟踪子弹
            case DAuto:
                if (autob) {
                    try {
                        if (p2x - p1x >= 0) {
                            //前面
                            if ((p2x - p1x) / (p1y - p2y) > 2 || (p2x - p1x) / (p2y - p1y) > 2) {
                                bulletangle = Direction.DLeft;//左
                            } else if ((p2y - p1y) > ((p2x - p1x) * 2)) {
                                bulletangle = Direction.DUp;//上
                            } else if ((p1y - p2y) > ((p2x - p1x) * 2)) {
                                bulletangle = Direction.DDown;//下
                            } else if (p2y - p1y > 0) {
                                bulletangle = Direction.DUpLeft;//左上
                            } else {
                                bulletangle = Direction.DDownLeft; //左下
                            }
                        } else {
                            //后面
                            if ((p1x - p2x) / (p1y - p2y) > 2 || (p1x - p2x) / (p2y - p1y) > 2) {
                                bulletangle = Direction.DRight;//右
                            } else if ((p2y - p1y) > ((p1x - p2x) * 2)) {
                                bulletangle = Direction.DUp;//上
                            } else if ((p1y - p2y) > ((p1x - p2x) * 2)) {
                                bulletangle = Direction.DDown;//下
                            } else if (p2y - p1y > 0) {
                                bulletangle = Direction.DUpright;//右上
                            } else {
                                bulletangle = Direction.DDownRight; //右下
                            }
                        }
                    } catch (Exception e) {
                        //除法0问题
                    }
                    autob = false;
                }

                switch (bulletangle) {
                    case DUp:
                        position.y -= speed;
                        break; //上
                    case DDown:
                        position.y += speed;
                        break; //下
                    case DLeft:
                        position.x -= speed;
                        break; //左
                    case DRight:
                        position.x += speed;
                        break; //右

                    case DUpLeft:
                        position.y -= speed;
                        position.x -= speed;
                        break; //上左
                    case DUpright:
                        position.y -= speed;
                        position.x += speed;
                        break; //上右
                    case DDownLeft:
                        position.y += speed;
                        position.x -= speed;
                        break; //下左
                    case DDownRight:
                        position.y += speed;
                        position.x += speed;
                        break; //下右
                    default:
                        position.x -= speed;
                        break;
                }

                break;
            default:
                break;
        }

        //超出屏幕
        if (position.y <= 25 || position.x <= 0 || position.x >= screen_size.width || position.y >= screen_size.height) {
            this.life = false;
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
     * 子弹碰撞方法
     */
    public void collision(Enemy em) {
        if (this.life && em.life > 0 && this.getMeRect().intersects(em.getMeRect())) {
            //被击中
            em.life = em.life - this.power;
            this.life = false;

            if (em.life <= 0) {
                //爆炸声
                Sound fireSound = new Sound("music/Explode.mp3");
                fireSound.play();
            }
        }
    }

}
