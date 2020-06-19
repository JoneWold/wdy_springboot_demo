package com.wdy.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.wdy.game.constant.CommonConstant.screen_size;

/**
 * @author wgch
 * @date 2020/6/18
 */
public class GameStartApp extends Frame {
    /**
     * 累加时间。用于计算FPS
     */
    private Long cumulativeTime;
    /**
     * 实时时间，用于锁定FPS
     */
    private Long realTime;
    private int fps = 0;
    private int showFPS = 0;
    private Chapter01 chapter01;
    /**
     * 游戏状态。1为第1关，99为过关，0为死亡,-1为选择人物
     */
    static int gamestatic = -1;
    /**
     * 0为基拉，1为蓝叶
     */
    static int choseman = 0;
    /**
     * 选择人物
     */
    private ChoseMan cman = new ChoseMan();

    public static void main(String[] args) {
        new GameStartApp().showScreen();
    }

    /**
     * 游戏窗口显示
     */
    private void showScreen() {
        //窗口初始化
        this.setSize(screen_size);
        this.setTitle("LOVO机战  Ver 1.0");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point pcenter = ge.getCenterPoint();
        this.setLocation(pcenter.x - screen_size.width / 2, pcenter.y - screen_size.height / 2);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //开启主循环线程
        MainLoop ml = new MainLoop();
        ml.start();

        //添加键盘事件
        this.addKeyListener(new GameJoy());

//        try { //读取读取画面
//            completeimage = ImageIO.read(UserRobot.class.getResource("imgs/complete.jpg"));
//            gameoverimage = ImageIO.read(UserRobot.class.getResource("imgs/Gameover.jpg"));
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
        this.setVisible(true);
    }


    /**
     * 主循环线程
     */
    class MainLoop extends Thread {
        @Override
        public void run() {
            cumulativeTime = System.currentTimeMillis();
            realTime = System.currentTimeMillis();
            while (true) {
                //限制FPS
                try {
                    Thread.sleep(16 - (System.currentTimeMillis() - realTime));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //获取并显示FPS
                fps++;
                if ((System.currentTimeMillis() - cumulativeTime) >= 1000L) {
                    cumulativeTime = System.currentTimeMillis();
                    showFPS = fps;
                    fps = 0;
                }

                repaint();
                realTime = System.currentTimeMillis();
            }
        }
    }

    /**
     * 键盘监听
     */
    class GameJoy extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                // Java消息提示框
                int msgIndex = JOptionPane.showConfirmDialog(null, "你确定要退出游戏吗？", "消息框", 0);
                if (msgIndex == 0) {
                    System.exit(0);
                }
            }

            if (chapter01 != null) {
                chapter01.keyReleased(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (chapter01 != null) {
                chapter01.keyPressed(e);
            }
            if (cman != null) {
                cman.keyPressed(e);
            }
        }
    }
}
