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
 * @author wgch
 * @date 2020/6/19
 */
public class Chapter01 {
    public BufferedImage backgroundImage01 = null;
    public BufferedImage backgroundImage02 = null;
    public int x1 = 0;
    public int x2 = 1536;
    /**
     * 关卡信息
     */
    private ArrayList<String[]> chapterList = new ArrayList<String[]>();
    /**
     * 背景音乐
     */
    private Sound sd = new Sound("music/BGM_0001.mp3");
    /**
     * BOSS音乐
     */
    private Sound bossSd = new Sound("music/zengjia.mp3");
    /**
     * 敌军列表
     */
    public ArrayList<Enemy> enemies = new ArrayList<>();
    /**
     * 初始化用户
     */
    public static UserRobot userrobot = new UserRobot(new Point(100, 250), 100, Direction.DStop);
    /**
     * 生命图
     */
    private BufferedImage lifeImage = null;
    /**
     * 敌人image
     */
    private BufferedImage[] enemyImage = new BufferedImage[6];
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
    private int bossLife;
    /**
     * 歌姬号数据
     */
    private BufferedImage gjhImage = null;
    private boolean hsGjh = false;
    private Point gjhPosition = new Point(-800, -200);
    private int completeTime = 0;

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
            lifeImage = ImageIO.read(UserRobot.class.getResource("imgs/life.gif"));
            backgroundImage01 = BsGraphics.resizeImage(ImageIO.read(UserRobot.class.getResource("imgs/Chapter_01/02.gif")), 1536, 600);
            gjhImage = BsGraphics.resizeImage(ImageIO.read(UserRobot.class.getResource("imgs/gjh.gif")), 800, 800); //加载歌姬号
            speak01 = ImageIO.read(UserRobot.class.getResource("imgs/speak/lks.gif")); //拉克丝
            speak02 = ImageIO.read(UserRobot.class.getResource("imgs/speak/jl.gif")); //基拉大和
            speak03 = ImageIO.read(UserRobot.class.getResource("imgs/speak/zj2.gif")); //增加
            speak04 = ImageIO.read(UserRobot.class.getResource("imgs/speak/zj.gif")); //增加遗言
            backgroundImage02 = backgroundImage01;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //敌机载入内存
        for (int i = 0; i < enemyImage.length; i++) {
            try {
                enemyImage[i] = ImageIO.read(UserRobot.class.getResource("imgs/NPC_000" + (i + 1) + ".gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sd.playLoop(); //播放背景音乐
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
                    chapterList.add(temstr);
                    //System.out.println(temstr[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //歌姬号出现
    public void drawGJH(Graphics g) {
        if (gjhPosition.x <= -200 && !hsGjh) {
            g.drawImage(gjhImage, gjhPosition.x++, gjhPosition.y, null);

            if (gjhPosition.x >= -750 && gjhPosition.x <= -350) {
                g.drawImage(speak01, 200, 330, null);

                //对话内容
                g.setFont(new Font("宋体", Font.BOLD, 16));
                g.setColor(Color.white);
                g.drawString("拉克丝", 420, 495);

                g.drawString("『发现敌机，全员各就各位，机动战士准备出击！』", 400, 520);
            } else if (gjhPosition.x > -350) {
                g.drawImage(speak02, 200, 330, null);

                g.setFont(new Font("宋体", Font.BOLD, 16));
                g.setColor(Color.white);
                g.drawString("基拉", 420, 495);
                g.drawString("『收到，FreeDom 出击！』", 400, 520);
            }
        } else {
            hsGjh = true;
            g.drawImage(gjhImage, gjhPosition.x--, gjhPosition.y, null);

            userrobot.graphicsRobot(g, enemies); //活着才绘制玩家角色

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
            this.bossSd.playLoop();
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
        for (int i = 0; i < enemies.size(); i++) {
            userrobot.collision(enemies.get(i));
        }

        for (int i = 0; i < enemies.size(); i++) {
            //获得BOSS生命
            if (enemies.get(i).Boss) {
                bossLife = enemies.get(i).life;
                if (bossLife <= 0) {
                    //过关
                    completeTime++;
                    if (completeTime <= 500) {
                        g.drawImage(speak04, 200, 330, null);

                        //对话内容
                        g.setFont(new Font("宋体", Font.BOLD, 16));
                        g.setColor(Color.white);
                        g.drawString("曾伽", 420, 495);
                        g.drawString("『我果然老了，该是把世界让给年轻人了......』", 400, 520);
                    } else {
                        GameStartApp.gameStatic = 99;
                    }

                }
            }

            if (enemies.get(i).life > 0) {
                enemies.get(i).exp.i = 0;
                enemies.get(i).drawMe(g, userrobot); //活着的敌军
            } else if (enemies.get(i).life <= 0 && enemies.get(i).exp.life) {
                enemies.get(i).drawMe(g, userrobot); //绘制爆炸
            } else if (enemies.get(i).life <= 0 && !enemies.get(i).exp.life) {
                if (!enemies.get(i).Boss) {
                    enemies.remove(i); //不是BOSS死掉就删除
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
        g.drawString("敌军数量为" + String.valueOf(enemies.size()), 280, 50);
    }

    //绘制背景方法
    public void drawBackGround(Graphics g) {
        x1 = x1 - 5;
        x2 = x2 - 5;
        g.drawImage(backgroundImage01, x1, 0, null);
        g.drawImage(backgroundImage02, x2, 0, null);
        if (x1 <= -1536) {
            x1 = x2 + 1536;
        } else if (x2 <= -1536) {
            x2 = x1 + 1536;
        }
    }

    //*********************关卡NPC分配*************************************************
    public void initNPC() {
        if (chapterList.size() > 0) {
            for (int i = 0; i < chapterList.size(); i++) {
                String[] npcs = chapterList.get(i);
                if (chapterTime == Integer.parseInt(npcs[0])) { //时间到了出场
                    BufferedImage loadimg = null;
                    Direction dir = Direction.DLeft;
                    if (npcs[1].equals("true")) {//BOSS
                        if (npcs[7].equals("left")) {
                            dir = Direction.DLeft;
                        }
                        if (npcs[7].equals("right")) {
                            dir = Direction.DRight;
                        }
                        if (npcs[7].equals("up")) {
                            dir = Direction.DUp;
                        }
                        if (npcs[7].equals("down")) {
                            dir = Direction.DDown;
                        }
                        enemies.add(new Boss(new Point(Integer.parseInt(npcs[2]), Integer.parseInt(npcs[3])), Integer.parseInt(npcs[4]), Integer.parseInt(npcs[5]), loadimg, dir));
                    } else {//杂兵
                        loadimg = enemyImage[Integer.parseInt(npcs[6])];
                        if (npcs[7].equals("left")) {
                            dir = Direction.DLeft;
                        }
                        if (npcs[7].equals("right")) {
                            dir = Direction.DRight;
                        }
                        if (npcs[7].equals("up")) {
                            dir = Direction.DUp;
                        }
                        if (npcs[7].equals("down")) {
                            dir = Direction.DDown;
                        }
                        enemies.add(new Enemy(new Point(Integer.parseInt(npcs[2]), Integer.parseInt(npcs[3])), Integer.parseInt(npcs[4]), Integer.parseInt(npcs[5]), loadimg, dir));
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
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).life -= 100;
                if (enemies.get(i).life <= 0) {
                    Sound Explodesd = new Sound("music/Explode.mp3"); //爆炸声音
                    Explodesd.play();
                }
            }
        }

        userrobot.keyPressed(e);
    }

    public void close() {
        sd.stop();
        bossSd.stop();
        sd = null;
        bossSd = null;
    }
}
