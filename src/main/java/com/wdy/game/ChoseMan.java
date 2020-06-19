package com.wdy.game;


import com.wdy.game.engin.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * ����ѡ��
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
     * ��ʼ��
     */
    public ChoseMan() {
        try {
            //����
            cmimage0 = ImageIO.read(UserRobot.class.getResource("imgs/choseman01.gif"));
            cmimage1 = ImageIO.read(UserRobot.class.getResource("imgs/choseman02.gif"));

            //����
            cjimage0 = ImageIO.read(UserRobot.class.getResource("imgs/lwj.gif"));
            cjimage1 = ImageIO.read(UserRobot.class.getResource("imgs/freedom.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sd.playloop();
    }

    /**
     * ����ȫ��
     */
    public void drawAll(Graphics g) {
        //��ť
        ctime++;
        g.setFont(new Font("��Բ", Font.BOLD + Font.ITALIC, 24));
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
            g.setFont(new Font("��Բ", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString("<��ʵϵ>", 160, 100);
            g.drawString("�������", 160, 450);
            g.drawImage(cmimage1, 50, 120, null);
            g.drawImage(cjimage1, 550, 60, null);

            g.setFont(new Font("����", 0, 18));
            //***********�������
            g.drawString("�����ɸߴ", 450, 350);
            g.drawString("    �ߣ�18.03�ף�������71.5�� ", 450, 380);
            g.drawString("    ������ZAFT���ڵõ��������Ͼ��ĸߴ��������Ϻ󿪷��ĸߴ��� ", 450, 400);
            g.drawString("��һ�ܣ���������ǿϮ�ߴ��4��֮�ϡ������������һ��M100�͵���", 450, 420);
            g.drawString("��������ũ�ڣ�������װ������MMI-M15�͵�ż�ũ�ڣ�����MA-M20�� ", 450, 440);
            g.drawString("������ǹ��ʹ���������൱ǿ���Ļ��������ڴ����˷����Ӹ���װ�ã� ", 450, 460);
            g.drawString("ʹ��������ʹ�ú����棬��ͻ����ϱ�������ת��װ����ս����������", 450, 480);
            g.drawString("�����ʧЧ�����⡣���屳��������5�ԡ����⣬�����ɴ�������ϵ", 450, 500);
            g.drawString("ͳ������Ϊ����ǿ�������MAʹ�á�", 450, 520);
            //************************
        } else {
            g.setFont(new Font("��Բ", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString("<����ϵ>", 160, 100);
            g.drawString("ˮ���Ҷ", 160, 450);
            g.drawImage(cmimage0, 50, 120, null);
            g.drawImage(cjimage0, 500, 60, null);

            g.setFont(new Font("����", 0, 18));
            //***********�������
            g.drawString("����������", 450, 350);
            g.drawString("    �ߣ�49.9�ף�������158��", 450, 380);
            g.drawString("    ���й�ɽ����������ȉV�ھ�����ĳ����ˡ�ԭ���ǹŴ�����Ϊ��", 450, 400);
            g.drawString("�ͳ�Ϊ����а���Ķ�ħ����ս��������İ��������������������˼��", 450, 420);
            g.drawString("��·����Ȼ�ܹ������ж���������������������ϵ����������ʻ��", 450, 440);
            g.drawString("���ܷ��ӱ�����������������һ����̬��������ȣ����ó�����/ˮ��", 450, 460);
            g.drawString("��ս����Զ����ķ����������м���Ĺ���������ʻԱ�Ǳ�������ѡ��", 450, 480);
            g.drawString("��ˮ���Ҷ����һ�εǳ���PS�ġ����������˴�ս��������OGϵ������", 450, 500);
            g.drawString("�γ�����", 450, 520);
            //************************
        }
    }

    /**
     * ������Ϣ
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
                break; //��
            case KeyEvent.VK_D:
                cm = true;
                ch = new Sound("music/chose.mp3");
                ch.play();
                break; //��
            case KeyEvent.VK_LEFT:
                cm = true;
                ch = new Sound("music/chose.mp3");
                ch.play();
                break; //��
            case KeyEvent.VK_RIGHT:
                cm = true;
                ch = new Sound("music/chose.mp3");
                ch.play();
                break; //��
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
     * �رշ���
     */
    public void close() {
        sd.stop();
        sd = null;
    }
}
