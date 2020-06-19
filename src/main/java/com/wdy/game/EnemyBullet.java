package com.wdy.game;


import com.wdy.game.constant.Direction;
import com.wdy.game.engin.Sound;

import java.awt.*;

/**
 * �з��ӵ�
 *
 * @author Administrator
 */
public class EnemyBullet extends Bullet {

    EnemyBullet(Point position, boolean life, String[] BulletImage, Direction bulletDirection,
                int speed, boolean fanzhuan, int power, int p1x, int p1y, int p2x, int p2y) {
        super(position, life, BulletImage, bulletDirection, speed, fanzhuan, power, p1x, p1y, p2x, p2y);
    }

    /**
     * �ӵ���ײ����
     */
    public void collision(UserRobot ur) {
        if (this.life && ur.life > 0 && this.getMeRect().intersects(ur.getRect())) {
            //������
            ur.life = ur.life - this.power;
            this.life = false;

            if (ur.life <= 0) {
                //��ը��
                Sound fireSound = new Sound("music/Explode.mp3");
                fireSound.play();
            }
        }
    }

}
