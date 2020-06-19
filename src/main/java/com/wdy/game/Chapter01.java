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
 * 关卡1
 *
 * @author Administrator
 */
public class Chapter01 {
    public BufferedImage BackgroundImage01 = null;
    public BufferedImage BackgroundImage02 = null;
    public int x1 = 0;
    public int x2 = 1536;
    /**
     * 关卡信息
     */
    private ArrayList<String[]> chapterlist = new ArrayList<String[]>();
    /**
     * 背景音乐
     */
    private Sound sd = new Sound("music/BGM_0001.mp3");
    /**
     * BOSS音乐
     */
    private Sound Boss_sd = new Sound("music/zengjia.mp3");
    /**
     * 敌军列表
     */
    public ArrayList<Enemy> enemys = new ArrayList<>();
    /**
     * 初始化用户
     */
    public static UserRobot userrobot = new UserRobot(new Point(100, 250), 100, Direction.DStop);
    // 生命图
    private BufferedImage lifeimage = null;
    /**
     * 敌人image
     */
    private BufferedImage[] enemyimage = new BufferedImage[6];
    private BufferedImage speak01 = null;
    private BufferedImage speak02 = null;
    private BufferedImage speak03 = null;
    private BufferedImage speak04 = null;
    /**
     * 关卡计时器
     */
    private int chapterTime;
    /**
     * Boss生命
     */
    private int Bosslife;
    //歌姬号数据
    private BufferedImage gjhimage = null;
    private boolean hs_gjh = false;
    private Point gjh_position = new Point(-800, -200);
    private int completetime = 0;

    //BOSS说话
    private int speak_start_time;
    private int speak_end_time;
    private String speaker;
    private String speak_about;

    /**
     * 初始化关卡信息
     */
    public Chapter01() {
        //读取关卡配置单位
        readTxt();
        try {
            lifeimage = ImageIO.read(UserRobot.class.getResource("imgs/life.gif"));
            BackgroundImage01 = BsGraphics.resizeImage(ImageIO.read(UserRobot.class.getResource("imgs/Chapter_01/02.gif")), 1536, 600);
            gjhimage = BsGraphics.resizeImage(ImageIO.read(UserRobot.class.getResource("imgs/gjh.gif")), 800, 800); //加载歌姬号
            speak01 = ImageIO.read(UserRobot.class.getResource("imgs/speak/lks.gif")); //拉克丝
            speak02 = ImageIO.read(UserRobot.class.getResource("imgs/speak/jl.gif")); //基拉大和
            speak03 = ImageIO.read(UserRobot.class.getResource("imgs/speak/zj2.gif")); //增加
            speak04 = ImageIO.read(UserRobot.class.getResource("imgs/speak/zj.gif")); //增加遗言
            BackgroundImage02 = BackgroundImage01;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //敌机载入内存
        for (int i = 0; i < enemyimage.length; i++) {
            try {
                enemyimage[i] = ImageIO.read(UserRobot.class.getResource("imgs/NPC_000" + (i + 1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sd.playloop(); //播放背景音乐
    }

    /**
     * 读取文本中的关卡信息
     */
    public void readTxt() {
        String[] temstr = null;
        InputStream ist = this.getClass().getResourceAsStream("imgs/C1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(ist));
        String lineStr = null;
        try {
            while ((lineStr = br.readLine()) != null) {
                // 去掉注释
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

    //歌姬号出现
    public void drawGJH(Graphics g) {
        if (gjh_position.x <= -200 && !hs_gjh) {
            g.drawImage(gjhimage, gjh_position.x++, gjh_position.y, null);

            if (gjh_position.x >= -750 && gjh_position.x <= -350) {
                g.drawImage(speak01, 200, 330, null);

                //对话内容
                g.setFont(new Font("宋体", Font.BOLD, 16));
                g.setColor(Color.white);
                g.drawString("拉克丝", 420, 495);

                g.drawString("『发现敌机，全员各就各位，机动战士准备出击！』", 400, 520);
            } else if (gjh_position.x > -350) {
                g.drawImage(speak02, 200, 330, null);

                g.setFont(new Font("宋体", Font.BOLD, 16));
                g.setColor(Color.white);
                g.drawString("基拉", 420, 495);
                g.drawString("『收到，FreeDom 出击！』", 400, 520);
            }
        } else {
            hs_gjh = true;
            g.drawImage(gjhimage, gjh_position.x--, gjh_position.y, null);

            userrobot.graphicsRobot(g, enemys); //活着才绘制玩家角色

        }
    }

    //主绘制方法
    public void drawAll(Graphics g) {
        chapterTime++;
        drawBackGround(g); //绘制背景
        initNPC(); //制造批量敌军
//		/增加说话
        if (chapterTime == 1500) {
            this.sd.stop(); //BOSS音乐
            this.Boss_sd.playloop();
        }
        if (chapterTime >= 1500 && chapterTime <= 1700) {


            g.drawImage(speak03, 200, 330, null);

            g.setFont(new Font("宋体", Font.BOLD, 16));
            g.setColor(Color.white);
            g.drawString("曾伽", 420, 495);
            g.drawString("『让我来告诉你武者精神吧，曾伽参上！』", 400, 520);
        }
        drawGJH(g); //歌姬号
        //机体碰撞
        for (int i = 0; i < enemys.size(); i++) {
            userrobot.collision(enemys.get(i));
        }

        for (int i = 0; i < enemys.size(); i++) {
            //获得BOSS生命
            if (enemys.get(i).Boss) {
                Bosslife = enemys.get(i).life;
                if (Bosslife <= 0) {
                    //过关
                    completetime++;
                    if (completetime <= 500) {
                        g.drawImage(speak04, 200, 330, null);

                        //对话内容
                        g.setFont(new Font("宋体", Font.BOLD, 16));
                        g.setColor(Color.white);
                        g.drawString("曾伽", 420, 495);
                        g.drawString("『我果然老了，该是把世界让给年轻人了......』", 400, 520);
                    } else {
                        GameStartApp.gamestatic = 99;
                    }

                }
            }

            if (enemys.get(i).life > 0) {
                enemys.get(i).exp.i = 0;
                enemys.get(i).drawMe(g, userrobot); //活着的敌军
            } else if (enemys.get(i).life <= 0 && enemys.get(i).exp.life) {
                enemys.get(i).drawMe(g, userrobot); //绘制爆炸
            } else if (enemys.get(i).life <= 0 && !enemys.get(i).exp.life) {
                if (!enemys.get(i).Boss) {
                    enemys.remove(i); //不是BOSS死掉就删除
                }
            }
        }


        //*******************************显示信息类********************************************
        //显示子弹
        g.setFont(new Font("宋体", 0, 14));
        g.setColor(Color.orange);
        g.drawString("我军发射的子弹为：" + String.valueOf(userrobot.bullets.size()), 110, 50); //显示子弹

        //绘制敌军数量
        g.setColor(Color.white);
        g.drawString("敌军数量为" + String.valueOf(enemys.size()), 280, 50);
    }

    //绘制背景方法
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

    //*********************关卡NPC分配*************************************************
    public void initNPC() {
        if (chapterlist.size() > 0) {
            for (int i = 0; i < chapterlist.size(); i++) {
                String[] npcs = chapterlist.get(i);
                if (chapterTime == Integer.parseInt(npcs[0])) { //时间到了出场
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
                    } else {//杂兵
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
    //键盘信息
    public void keyReleased(KeyEvent e) {
        userrobot.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        //必杀
        if (e.getKeyCode() == KeyEvent.VK_K) {
            for (int i = 0; i < enemys.size(); i++) {
                enemys.get(i).life -= 100;
                if (enemys.get(i).life <= 0) {
                    Sound Explodesd = new Sound("music/Explode.mp3"); //爆炸声音
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
