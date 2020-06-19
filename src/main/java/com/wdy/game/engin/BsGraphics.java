package com.wdy.game.engin;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Administrator
 */
public class BsGraphics {
    /**
     * ������
     */
    private Image bufferface = null;
    /**
     * �������ϵı�
     */
    private Graphics bufferpen = null;

    /**
     * ����ͼƬ��ʼ��
     */
    public Image[] picInit(String[] picPath) {
        Image[] im = new Image[picPath.length];
        Toolkit tk = Toolkit.getDefaultToolkit();
        for (int i = 0; i < picPath.length; i++) {
            im[i] = tk.getImage(picPath[i]);
        }
        return im;
    }

    /**
     * ����ͼƬ��ʼ��
     */
    public Image picInit(String picPath) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        return tk.getImage(picPath);
    }

    /**
     * ���������ʴ���
     */
    public void creatBufferFace(int width, int height, Container cont) {
        bufferface = cont.createImage(width, height);
        bufferpen = bufferface.getGraphics();
    }

    /**
     * �������ϻ�ͼ
     */
    public void drawImage(Image image, int x, int y) {
        bufferpen.drawImage(image, x, y, null);
    }

    /**
     * ���ƻ��嵽��Ļ
     */
    public void copyBufferToScreen(Graphics g) {
        if (bufferface != null) {
            g.drawImage(bufferface, 0, 0, null);
        }

    }

    /**
     * ��תͼƬΪָ���Ƕ�
     *
     * @param img    Ŀ��ͼ��
     * @param degree ��ת�Ƕ�
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage img,
                                            final int degree) {
        int w = img.getWidth();
        int h = img.getHeight();

        int type = img.getColorModel().getTransparency();
        BufferedImage toImg;
        Graphics2D graphics2d;
        (graphics2d = (toImg = new BufferedImage(w, h, type)).createGraphics())
                .setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(img, 0, 0, null);
        graphics2d.dispose();
        return toImg;
    }

    /**
     * ���ͼ��Ϊָ����С
     *
     * @param img Ŀ��ͼ��
     * @param w   ��
     * @param h   ��
     */
    public static BufferedImage resizeImage(final BufferedImage img,
                                            final int w, final int h) {
        int type = img.getColorModel().getTransparency();
        BufferedImage toImg;
        Graphics2D graphics2d;
        (graphics2d = (toImg = new BufferedImage(w, h, type)).createGraphics())
                .setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.drawImage(img, 0, 0, w, h, 0, 0,
                img.getWidth(), img.getHeight(), null);
        graphics2d.dispose();
        return toImg;
    }

    /**
     * ˮƽ��תͼ��
     *
     * @param img Ŀ��ͼ��
     * @return
     */
    public static BufferedImage flipImage(final BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage toImg;
        Graphics2D graphics2d;
        (graphics2d = (toImg = new BufferedImage(w, h, img
                .getColorModel().getTransparency())).createGraphics())
                .drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        graphics2d.dispose();
        return toImg;
    }
}
