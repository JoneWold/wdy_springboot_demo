package com.wdy.game;

import com.wdy.game.engin.Sound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static com.wdy.game.constant.CommonConstant.SCREEN_SIZE;

/**
 * @author wgch
 * @date 2020/6/18
 */
public class GameStartApp extends Frame {
    private int fps = 0;
    private int showFPS = 0;
    private Chapter01 chapter01;
    /**
     * 游戏状态。1为第1关，99为过关，0为死亡,-1为选择人物
     */
    static int gameStatic = -1;
    /**
     * 0为基拉，1为蓝叶
     */
    static int manNum = 0;
    /**
     * 选择人物
     */
    private ChoseMan choseMan = new ChoseMan();
    /**
     * 过关图
     */
    private BufferedImage completeImage = null;
    /**
     * GameOver图
     */
    private BufferedImage gameOverImage = null;
    /**
     * 缓冲层
     */
    private Image bufferImage = null;
    private final Sound completeSound = new Sound("music/complete.mp3");
    private final Sound gameOverSound = new Sound("music/gameover.mp3");

    public static void main(String[] args) {
        new GameStartApp().showScreen();
    }

    /**
     * 游戏窗口显示
     */
    private void showScreen() {
        //窗口初始化
        this.setSize(SCREEN_SIZE);
        this.setTitle("LoVo机战  Ver 1.0");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point pCenter = ge.getCenterPoint();
        this.setLocation(pCenter.x - SCREEN_SIZE.width / 2, pCenter.y - SCREEN_SIZE.height / 2);
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

        try { //读取读取画面
            completeImage = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("game/imgs/complete.jpg")));
            gameOverImage = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("game/imgs/Gameover.jpg")));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.setVisible(true);
    }

    /**
     * 绘制缓冲层
     */
    @Override
    public void update(Graphics g) {
        //创建缓冲层
        if (bufferImage == null) {
            bufferImage = this.createImage(SCREEN_SIZE.width, SCREEN_SIZE.height);
        }
        //获得缓冲层笔
        Graphics bufferPen = bufferImage.getGraphics();
        //设置刷新层颜色
        bufferPen.setColor(this.getBackground());
        //绘制刷新层
        bufferPen.fillRect(0, 0, SCREEN_SIZE.width, SCREEN_SIZE.height);
        paint(bufferPen);
        g.drawImage(bufferImage, 0, 0, null);
    }

    /**
     * 绘制元件
     */
    @Override
    public void paint(Graphics g) {
        switch (gameStatic) {
            //读取状态
            case -1:
                choseMan.drawAll(g);
                break;
            //第一关开始
            case 1:
                if (chapter01 == null) {
                    choseMan.close();
                    choseMan = null;
                    chapter01 = new Chapter01();
                }
                chapter01.drawAll(g);
                break;
            //过关
            case 99:
                if (chapter01 != null) {
                    chapter01.close();
                    chapter01 = null;
                    completeSound.playLoop();
                }
                g.drawImage(completeImage, 0, 0, null);
                break;
            //GAME OVER
            case 0:
                if (chapter01 != null) {
                    chapter01.close();
                    chapter01 = null;
                    gameOverSound.playLoop();
                }
                g.drawImage(gameOverImage, 0, 0, null);
                break;
            default:
                break;
        }

        drawFPS(g);
    }

    /**
     * 显示FPS
     */
    public void drawFPS(Graphics g) {
        g.setFont(new Font("宋体", Font.BOLD, 24));
        g.setColor(Color.RED);
        g.drawString(String.valueOf(showFPS) + " FPS", 20, 50);
    }

    /**
     * 主循环线程
     */
    class MainLoop extends Thread {
        @Override
        public void run() {
            // 累加时间。用于计算FPS
            long cumulativeTime = System.currentTimeMillis();
            // 实时时间，用于锁定FPS
            long realTime = System.currentTimeMillis();
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
                int msgIndex = JOptionPane.showConfirmDialog(null, "你确定要退出游戏吗？", "消息框", JOptionPane.YES_NO_OPTION);
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
            if (choseMan != null) {
                choseMan.keyPressed(e);
            }
        }
    }
}
