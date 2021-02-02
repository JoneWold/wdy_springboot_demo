package com.wdy.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 爆炸类
 *
 * @author wgch
 * @date 2020/6/19
 */
public class Explosion {

    private BufferedImage[] explosionImage = new BufferedImage[6];
    //位置
    public Point position = null;
    //生命
    boolean life = true;
    int i = 0;

    public Explosion(String[] explosionImage, Point position, boolean life) {
        for (int i = 0; i < explosionImage.length; i++) {
            try {
                this.explosionImage[i] = ImageIO.read(Explosion.class.getResource(explosionImage[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.position = position;
        this.life = life;
    }

    public void showMe(Graphics g) {
        if (i / 5 >= explosionImage.length - 1) {
            this.life = false;
            return;
        }
        g.drawImage(explosionImage[i / 5], position.x, position.y, null);
        i += 5;
    }
}
