package com.wdy.game;


import com.wdy.game.constant.Direction;
import com.wdy.game.engin.Sound;

import java.awt.*;

/**
 * 敌方子弹
 *
 * @author wgch
 * @date 2020/6/19
 */
public class EnemyBullet extends Bullet {

    EnemyBullet(Point position, boolean life, String[] BulletImage, Direction bulletDirection,
                int speed, boolean fanzhuan, int power, int p1x, int p1y, int p2x, int p2y) {
        super(position, life, BulletImage, bulletDirection, speed, fanzhuan, power, p1x, p1y, p2x, p2y);
    }

    /**
     * 子弹碰撞方法
     */
    public void collision(UserRobot ur) {
        if (this.life && ur.life > 0 && this.getMeRect().intersects(ur.getRect())) {
            //被击中
            ur.life = ur.life - this.power;
            this.life = false;

            if (ur.life <= 0) {
                //爆炸声
                Sound fireSound = new Sound("music/Explode.mp3");
                fireSound.play();
            }
        }
    }

}
