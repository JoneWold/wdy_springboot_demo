package com.wdy.game.engin;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.InputStream;

/**
 * ��Ƶ�ļ�
 *
 * @author Administrator
 */
public class Sound {
    private AdvancedPlayer ap = null;
    private boolean tLoop = false;
    private Musicl ml = new Musicl();
    private String mp3path = null;

    /**
     * ��ʼ��
     */
    public Sound(String mp3path) {
        this.mp3path = "game/" + mp3path;
    }

    /**
     * ����
     */
    public void play() {
        tLoop = false;
        ml.start();
    }

    /**
     * ֹͣ
     */
    @SuppressWarnings("deprecation")
    public void stop() {
        tLoop = false;
        ml.stop();
    }

    /**
     * ѭ������
     */
    public void playloop() {
        tLoop = true;
        ml.start();
    }

    class Musicl extends Thread {
        @Override
        public void run() {
            do {
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
