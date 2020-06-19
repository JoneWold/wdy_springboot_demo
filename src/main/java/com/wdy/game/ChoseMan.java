package com.wdy.game;


import com.wdy.game.engin.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * 人物选择
 *
 * @author Administrator
 */
public class ChoseMan {
    private BufferedImage cmimage0 = null, cmimage1 = null;
    private BufferedImage cjimage0 = null, cjimage1 = null;
    private Sound sd = new Sound("music/go.mp3");
    Sound ch;
    private int ctime = 0;

    /**
     * 初始化
     */
    public ChoseMan() {
        try {
            //人物
            cmimage0 = ImageIO.read(UserRobot.class.getResource("imgs/choseman01.gif"));
            cmimage1 = ImageIO.read(UserRobot.class.getResource("imgs/choseman02.gif"));

            //机体
            cjimage0 = ImageIO.read(UserRobot.class.getResource("imgs/lwj.gif"));
            cjimage1 = ImageIO.read(UserRobot.class.getResource("imgs/freedom.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sd.playloop();
    }

    /**
     * 绘制全部
     */
    public void drawAll(Graphics g) {
        //按钮
        ctime++;
        g.setFont(new Font("幼圆", Font.BOLD + Font.ITALIC, 24));
        g.setColor(Color.yellow);
        if (ctime <= 10) {
            g.drawString("Press [Enter]>>", 50, 550);
        } else if (ctime <= 20) {
            g.drawString("Press [Enter] >>", 50, 550);
        } else if (ctime <= 30) {
            g.drawString("Press [Enter]  >>", 50, 550);
        } else if (ctime <= 40) {
            g.drawString("Press [Enter]   >>", 50, 550);
        } else if (ctime <= 50) {
            ctime = 0;
            g.drawString("Press [Enter]>    >", 50, 550);
        }

        if (GameStartApp.choseman == 0) {
            g.setFont(new Font("幼圆", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString("<真实系>", 160, 100);
            g.drawString("基拉大和", 160, 450);
            g.drawImage(cmimage1, 50, 120, null);
            g.drawImage(cjimage1, 550, 60, null);

            g.setFont(new Font("宋体", 0, 18));
            //***********机体介绍
            g.drawString("『自由高达』", 450, 350);
            g.drawString("    高：18.03米，重量：71.5吨 ", 450, 380);
            g.drawString("    本机是ZAFT军在得到地球联合军的高达数据资料后开发的高达中 ", 450, 400);
            g.drawString("的一架，其性能在强袭高达的4倍之上。机体两肩各设一门M100型等离", 450, 420);
            g.drawString("子收束加农炮，腰部还装有两门MMI-M15型电磁加农炮，加上MA-M20型 ", 450, 440);
            g.drawString("光束步枪，使本机具有相当强劲的火力。由于搭载了反中子干扰装置， ", 450, 460);
            g.drawString("使本机可以使用核引擎，这就基本上避免了相转移装甲在战斗中因能量", 450, 480);
            g.drawString("不足而失效的问题。机体背部的翼共有5对。另外，本机可搭载流星系", 450, 500);
            g.drawString("统，可作为具有强大火力的MA使用。", 450, 520);
            //************************
        } else {
            g.setFont(new Font("幼圆", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString("<超级系>", 160, 100);
            g.drawString("水羽楠叶", 160, 450);
            g.drawImage(cmimage0, 50, 120, null);
            g.drawImage(cjimage0, 500, 60, null);

            g.setFont(new Font("宋体", 0, 18));
            //***********机体介绍
            g.drawString("『龙虎王』", 450, 350);
            g.drawString("    高：49.9米，重量：158吨", 450, 380);
            g.drawString("    在中国山东地区的蚩尤塚挖掘出来的超机人。原本是古代人类为了", 450, 400);
            g.drawString("和称为「百邪」的恶魔妖怪战斗而制造的半生物兵器。带有自律型思考", 450, 420);
            g.drawString("回路，虽然能够单独行动，但是如果不是由它承认的念动力者来驾驶就", 450, 440);
            g.drawString("不能发挥本来的力量。与另外一个形态虎龙王相比，它擅长空中/水中", 450, 460);
            g.drawString("的战斗和远距离的法术攻击，有极大的攻击力，驾驶员是被龙虎王选中", 450, 480);
            g.drawString("的水羽楠叶。第一次登场于PS的「超级机器人大战α」，在OG系列中首", 450, 500);
            g.drawString("次出场。", 450, 520);
            //************************
        }
    }

    /**
     * 按键信息
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_J) {
            GameStartApp.gamestatic = 1;
        }

        boolean cm = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                cm = true;
                ch = new Sound("music/chose.mp3");
                ch.play();
                break; //左
            case KeyEvent.VK_D:
                cm = true;
                ch = new Sound("music/chose.mp3");
                ch.play();
                break; //右
            case KeyEvent.VK_LEFT:
                cm = true;
                ch = new Sound("music/chose.mp3");
                ch.play();
                break; //左
            case KeyEvent.VK_RIGHT:
                cm = true;
                ch = new Sound("music/chose.mp3");
                ch.play();
                break; //右
            default:
                break;
        }

        if (cm == true) {
            if (GameStartApp.choseman == 0) {
                GameStartApp.choseman = 1;
            } else {
                GameStartApp.choseman = 0;
            }
        }
    }

    /**
     * 关闭方法
     */
    public void close() {
        sd.stop();
        sd = null;
    }
}
