package com.wdy.game;


import com.wdy.game.constant.Direction;
import com.wdy.game.engin.BsGraphics;
import com.wdy.game.engin.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * �ؿ�1
 *
 * @author Administrator
 */
public class Chapter01 {
    public BufferedImage BackgroundImage01 = null;
    public BufferedImage BackgroundImage02 = null;
    public int x1 = 0;
    public int x2 = 1536;
    /**
     * �ؿ���Ϣ
     */
    private ArrayList<String[]> chapterlist = new ArrayList<String[]>();
    /**
     * ��������
     */
    private Sound sd = new Sound("music/BGM_0001.mp3");
    /**
     * BOSS����
     */
    private Sound Boss_sd = new Sound("music/zengjia.mp3");
    /**
     * �о��б�
     */
    public ArrayList<Enemy> enemys = new ArrayList<>();
    /**
     * ��ʼ���û�
     */
    public static UserRobot userrobot = new UserRobot(new Point(100, 250), 100, Direction.DStop);
    // ����ͼ
    private BufferedImage lifeimage = null;
    /**
     * ����image
     */
    private BufferedImage[] enemyimage = new BufferedImage[6];
    private BufferedImage speak01 = null;
    private BufferedImage speak02 = null;
    private BufferedImage speak03 = null;
    private BufferedImage speak04 = null;
    /**
     * �ؿ���ʱ��
     */
    private int chapterTime;
    /**
     * Boss����
     */
    private int Bosslife;
    //�輧������
    private BufferedImage gjhimage = null;
    private boolean hs_gjh = false;
    private Point gjh_position = new Point(-800, -200);
    private int completetime = 0;

    //BOSS˵��
    private int speak_start_time;
    private int speak_end_time;
    private String speaker;
    private String speak_about;

    /**
     * ��ʼ���ؿ���Ϣ
     */
    public Chapter01() {
        //��ȡ�ؿ����õ�λ
        readTxt();
        try {
            lifeimage = ImageIO.read(UserRobot.class.getResource("imgs/life.gif"));
            BackgroundImage01 = BsGraphics.resizeImage(ImageIO.read(UserRobot.class.getResource("imgs/Chapter_01/02.gif")), 1536, 600);
            gjhimage = BsGraphics.resizeImage(ImageIO.read(UserRobot.class.getResource("imgs/gjh.gif")), 800, 800); //���ظ輧��
            speak01 = ImageIO.read(UserRobot.class.getResource("imgs/speak/lks.gif")); //����˿
            speak02 = ImageIO.read(UserRobot.class.getResource("imgs/speak/jl.gif")); //�������
            speak03 = ImageIO.read(UserRobot.class.getResource("imgs/speak/zj2.gif")); //����
            speak04 = ImageIO.read(UserRobot.class.getResource("imgs/speak/zj.gif")); //��������
            BackgroundImage02 = BackgroundImage01;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //�л������ڴ�
        for (int i = 0; i < enemyimage.length; i++) {
            try {
                enemyimage[i] = ImageIO.read(UserRobot.class.getResource("imgs/NPC_000" + (i + 1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sd.playloop(); //���ű�������
    }

    /**
     * ��ȡ�ı��еĹؿ���Ϣ
     */
    public void readTxt() {
        String[] temstr = null;
        InputStream ist = this.getClass().getResourceAsStream("imgs/C1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(ist));
        String lineStr = null;
        try {
            while ((lineStr = br.readLine()) != null) {
                // ȥ��ע��
                if (lineStr.indexOf("//") != 0) {
                    temstr = lineStr.split(",");
                    if (temstr[0].equals("speak")) {
                        speak_start_time = Integer.parseInt(temstr[1]);
                        speak_end_time = Integer.parseInt(temstr[2]);
                        speaker = temstr[3];
                        speak_about = temstr[4];
                        continue;
                    }
                    chapterlist.add(temstr);
                    //System.out.println(temstr[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //�輧�ų���
    public void drawGJH(Graphics g) {
        if (gjh_position.x <= -200 && !hs_gjh) {
            g.drawImage(gjhimage, gjh_position.x++, gjh_position.y, null);

            if (gjh_position.x >= -750 && gjh_position.x <= -350) {
                g.drawImage(speak01, 200, 330, null);

                //�Ի�����
                g.setFont(new Font("����", Font.BOLD, 16));
                g.setColor(Color.white);
                g.drawString("����˿", 420, 495);

                g.drawString("�����ֵл���ȫԱ���͸�λ������սʿ׼����������", 400, 520);
            } else if (gjh_position.x > -350) {
                g.drawImage(speak02, 200, 330, null);

                g.setFont(new Font("����", Font.BOLD, 16));
                g.setColor(Color.white);
                g.drawString("����", 420, 495);
                g.drawString("���յ���FreeDom ��������", 400, 520);
            }
        } else {
            hs_gjh = true;
            g.drawImage(gjhimage, gjh_position.x--, gjh_position.y, null);

            userrobot.graphicsRobot(g, enemys); //���ŲŻ�����ҽ�ɫ

        }
    }

    //�����Ʒ���
    public void drawAll(Graphics g) {
        chapterTime++;
        drawBackGround(g); //���Ʊ���
        initNPC(); //���������о�
//		/����˵��
        if (chapterTime == 1500) {
            this.sd.stop(); //BOSS����
            this.Boss_sd.playloop();
        }
        if (chapterTime >= 1500 && chapterTime <= 1700) {


            g.drawImage(speak03, 200, 330, null);

            g.setFont(new Font("����", Font.BOLD, 16));
            g.setColor(Color.white);
            g.drawString("��٤", 420, 495);
            g.drawString("�����������������߾���ɣ���٤���ϣ���", 400, 520);
        }
        drawGJH(g); //�輧��
        //������ײ
        for (int i = 0; i < enemys.size(); i++) {
            userrobot.collision(enemys.get(i));
        }

        for (int i = 0; i < enemys.size(); i++) {
            //���BOSS����
            if (enemys.get(i).Boss) {
                Bosslife = enemys.get(i).life;
                if (Bosslife <= 0) {
                    //����
                    completetime++;
                    if (completetime <= 500) {
                        g.drawImage(speak04, 200, 330, null);

                        //�Ի�����
                        g.setFont(new Font("����", Font.BOLD, 16));
                        g.setColor(Color.white);
                        g.drawString("��٤", 420, 495);
                        g.drawString("���ҹ�Ȼ���ˣ����ǰ������ø���������......��", 400, 520);
                    } else {
                        GameStartApp.gamestatic = 99;
                    }

                }
            }

            if (enemys.get(i).life > 0) {
                enemys.get(i).exp.i = 0;
                enemys.get(i).drawMe(g, userrobot); //���ŵĵо�
            } else if (enemys.get(i).life <= 0 && enemys.get(i).exp.life) {
                enemys.get(i).drawMe(g, userrobot); //���Ʊ�ը
            } else if (enemys.get(i).life <= 0 && !enemys.get(i).exp.life) {
                if (!enemys.get(i).Boss) {
                    enemys.remove(i); //����BOSS������ɾ��
                }
            }
        }


        //*******************************��ʾ��Ϣ��********************************************
        //��ʾ�ӵ�
        g.setFont(new Font("����", 0, 14));
        g.setColor(Color.orange);
        g.drawString("�Ҿ�������ӵ�Ϊ��" + String.valueOf(userrobot.bullets.size()), 110, 50); //��ʾ�ӵ�

        //���Ƶо�����
        g.setColor(Color.white);
        g.drawString("�о�����Ϊ" + String.valueOf(enemys.size()), 280, 50);
    }

    //���Ʊ�������
    public void drawBackGround(Graphics g) {
        x1 = x1 - 5;
        x2 = x2 - 5;
        g.drawImage(BackgroundImage01, x1, 0, null);
        g.drawImage(BackgroundImage02, x2, 0, null);
        if (x1 <= -1536) {
            x1 = x2 + 1536;
        } else if (x2 <= -1536) {
            x2 = x1 + 1536;
        }
    }

    //*********************�ؿ�NPC����*************************************************
    public void initNPC() {
        if (chapterlist.size() > 0) {
            for (int i = 0; i < chapterlist.size(); i++) {
                String[] npcs = chapterlist.get(i);
                if (chapterTime == Integer.parseInt(npcs[0])) { //ʱ�䵽�˳���
                    BufferedImage loadimg = null;
                    Direction dir = Direction.DLeft;
                    if (npcs[1].equals("true")) {//BOSS
                        if (npcs[7].equals("left")) {
                            dir = Direction.DLeft;
                        }
                        if (npcs[7].equals("right")) {
                            dir = Direction.Dright;
                        }
                        if (npcs[7].equals("up")) {
                            dir = Direction.DUp;
                        }
                        if (npcs[7].equals("down")) {
                            dir = Direction.DDown;
                        }
                        enemys.add(new Boss(new Point(Integer.parseInt(npcs[2]), Integer.parseInt(npcs[3])), Integer.parseInt(npcs[4]), Integer.parseInt(npcs[5]), loadimg, dir));
                    } else {//�ӱ�
                        loadimg = enemyimage[Integer.parseInt(npcs[6])];
                        if (npcs[7].equals("left")) {
                            dir = Direction.DLeft;
                        }
                        if (npcs[7].equals("right")) {
                            dir = Direction.Dright;
                        }
                        if (npcs[7].equals("up")) {
                            dir = Direction.DUp;
                        }
                        if (npcs[7].equals("down")) {
                            dir = Direction.DDown;
                        }
                        enemys.add(new Enemy(new Point(Integer.parseInt(npcs[2]), Integer.parseInt(npcs[3])), Integer.parseInt(npcs[4]), Integer.parseInt(npcs[5]), loadimg, dir));
                    }
                }
            }
        }
    }

    //**************************************************************************************
    //������Ϣ
    public void keyReleased(KeyEvent e) {
        userrobot.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        //��ɱ
        if (e.getKeyCode() == KeyEvent.VK_K) {
            for (int i = 0; i < enemys.size(); i++) {
                enemys.get(i).life -= 100;
                if (enemys.get(i).life <= 0) {
                    Sound Explodesd = new Sound("music/Explode.mp3"); //��ը����
                    Explodesd.play();
                }
            }
        }

        userrobot.keyPressed(e);
    }

    public void close() {
        sd.stop();
        Boss_sd.stop();
        sd = null;
        Boss_sd = null;
    }
}
