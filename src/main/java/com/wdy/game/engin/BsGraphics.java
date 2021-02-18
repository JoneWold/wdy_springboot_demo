package com.wdy.game.engin;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author wgch
 * @date 2020/6/19
 */
public class BsGraphics {
    /**
     * 缓冲面
     */
    private Image bufferFace = null;
    /**
     * 缓冲面上的笔
     */
    private Graphics bufferPen = null;

    /**
     * 批量图片初始化
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
     * 单幅图片初始化
     */
    public Image picInit(String picPath) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        return tk.getImage(picPath);
    }

    /**
     * 缓冲区及笔创建
     */
    public void creatBufferFace(int width, int height, Container cont) {
        bufferFace = cont.createImage(width, height);
        bufferPen = bufferFace.getGraphics();
    }

    /**
     * 缓冲区上绘图
     */
    public void drawImage(Image image, int x, int y) {
        bufferPen.drawImage(image, x, y, null);
    }

    /**
     * 复制缓冲到屏幕
     */
    public void copyBufferToScreen(Graphics g) {
        if (bufferFace != null) {
            g.drawImage(bufferFace, 0, 0, null);
        }

    }

    /**
     * 旋转图片为指定角度
     *
     * @param img    目标图像
     * @param degree 旋转角度
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
     * 变更图像为指定大小
     *
     * @param img 目标图像
     * @param w   宽
     * @param h   高
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
     * 水平翻转图像
     *
     * @param img 目标图像
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
