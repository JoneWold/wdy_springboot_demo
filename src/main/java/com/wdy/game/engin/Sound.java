package com.wdy.game.engin;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.InputStream;

/**
 * 音频文件
 *
 * @author wgch
 * @date 2020/6/19
 */
public class Sound {
    private AdvancedPlayer ap = null;
    private boolean tLoop = false;
    private final MusicThread ml = new MusicThread();
    private String mp3path = null;

    /**
     * 初始化
     */
    public Sound(String mp3path) {
        this.mp3path = "game/" + mp3path;
//        this.mp3path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("game/" + mp3path)).getPath();
    }

    /**
     * 播放
     */
    public void play() {
        tLoop = false;
        ml.start();
    }

    /**
     * 停止
     */
    @SuppressWarnings("deprecation")
    public void stop() {
        tLoop = false;
        ml.stop();
    }

    /**
     * 循环播放
     */
    public void playLoop() {
        tLoop = true;
        ml.start();
    }

    class MusicThread extends Thread {
        @Override
        public void run() {
            do {
                // /D:/wdy/wdy_springboot_demo/target/classes/game/music/go.mp3
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(mp3path);
                try {
                    ap = new AdvancedPlayer(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    ap.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            } while (tLoop);
        }
    }
}
