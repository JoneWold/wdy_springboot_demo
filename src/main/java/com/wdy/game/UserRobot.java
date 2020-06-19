package com.wdy.game;


import com.wdy.game.constant.Direction;
import com.wdy.game.engin.BsGraphics;
import com.wdy.game.engin.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static com.wdy.game.constant.CommonConstant.screen_size;


/**
 * ��һ�����
 *
 * @author Administrator
 */
public class UserRobot {

    private Dimension size = null; //�ߴ�
    public Point position = null; //λ��
    private int Speed = 3; //�ٶ�
    public int life = 0; //����
    private int bullettime = 0; //����ӳ�
    public int bodyPower = 100; //�����ײ����
    private int MaxLife = 0;
    //
    private BufferedImage robotimage01 = null, robotimage02 = null, robotimage03 = null, robotimage04 = null; //����ͼ
    private BufferedImage bulletimage01 = null, bulletimage02 = null, bulletimage03 = null, bulletimage04 = null, bulletimage05 = null; //�ӵ�ͼ
    //
    public BufferedImage robotImage = null; //ͼƬ
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>(); //�ӵ�����

    private Direction robotDirection = Direction.DStop; //�����˷���
    private boolean pUp = false, pDown = false, pLeft = false, pRight = false, pStop = false; //�жϰ���
    private String[] bulletimg = {"imgs/bullet_01/00.gif"};

    public Explosion exp = null; //��ը
    private String[] exp_img = null;
    private int overspeakindex; //����ʱ˵�Ļ�
    private BufferedImage overspeakimg = null;
    private BufferedImage headimage = null;

    //��ʼ��
    public UserRobot(Point position, int life, Direction robotDirection) {
        this.position = position;
        this.life = life;
        this.MaxLife = life;
        this.robotDirection = robotDirection;
        //��ʼ��ͼƬ
        try {
            robotimage01 = ImageIO.read(UserRobot.class.getResource("imgs/Freedom_0001.gif"));
            robotimage02 = ImageIO.read(UserRobot.class.getResource("imgs/Freedom_0002.gif"));
            robotimage03 = ImageIO.read(UserRobot.class.getResource("imgs/Freedom_0003.gif"));
            robotimage04 = ImageIO.read(UserRobot.class.getResource("imgs/Freedom_0004.gif"));

            bulletimage01 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("imgs/bullet_01/01.gif")));
            bulletimage02 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("imgs/bullet_01/02.gif")));
            bulletimage03 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("imgs/bullet_01/03.gif")));
            bulletimage04 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("imgs/bullet_01/04.gif")));
            bulletimage05 = BsGraphics.flipImage(ImageIO.read(UserRobot.class.getResource("imgs/bullet_01/05.gif")));

            headimage = ImageIO.read(UserRobot.class.getResource("imgs/jlhead.gif")); //ͷ��
            overspeakimg = ImageIO.read(UserRobot.class.getResource("imgs/speak/lks2.gif")); //����˿
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.robotImage = robotimage01;
        //��ͼƬ���سߴ�
        this.size = new Dimension(this.robotImage.getWidth(null), this.robotImage.getHeight(null));

        //��ʼ����ըͼ
        exp_img = new String[6];
        for (int i = 1; i < 7; i++) {
            exp_img[i - 1] = "imgs/Explosion/0" + i + ".gif";
        }
        exp = new Explosion(exp_img, position, true);

    }

    //�����¼�
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                pUp = true;
                break;
            case KeyEvent.VK_S:
                pDown = true;
                break;
            case KeyEvent.VK_A:
                pLeft = true;
                break;
            case KeyEvent.VK_D:
                pRight = true;
                break;

            //����
            case KeyEvent.VK_J:
                if (bullettime == 0 && life > 0) {
                    bullettime = 15;
                }
                break;
            default:
                break;
        }

    }

    //�ͷŰ���
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                pUp = false;
                break;
            case KeyEvent.VK_S:
                pDown = false;
                break;
            case KeyEvent.VK_A:
                pLeft = false;
                break;
            case KeyEvent.VK_D:
                pRight = false;
                break;
            default:
                break;
        }
    }

    //�Լ���ը
    public void explosionMe(Graphics g) {
        if (this.life <= 0) {
            exp.showMe(g);
        }
    }

    //��û����˷���
    public void getDirection() {
        //��
        if (pUp && !pDown && !pLeft && !pRight) {
            robotDirection = Direction.DUp;
        }
//		��
        if (!pUp && pDown && !pLeft && !pRight) {
            robotDirection = Direction.DDown;
        }
//		��
        if (!pUp && !pDown && pLeft && !pRight) {
            robotDirection = Direction.DLeft;
        }
//		��
        if (!pUp && !pDown && !pLeft && pRight) {
            robotDirection = Direction.Dright;
        }
//		ֹͣ
        if (!pUp && !pDown && !pLeft && !pRight) {
            robotDirection = Direction.DStop;
        }

        //����
        if (pUp && !pDown && pLeft && !pRight) {
            robotDirection = Direction.DUpLeft;
        }
//		����
        if (pUp && !pDown && !pLeft && pRight) {
            robotDirection = Direction.DUpright;
        }
//		����
        if (!pUp && pDown && pLeft && !pRight) {
            robotDirection = Direction.DDownLeft;
        }
//		����
        if (!pUp && pDown && !pLeft && pRight) {
            robotDirection = Direction.DDownRight;
        }
    }

    //�ƶ�
    public void robotMove() {
        int img = 1;
        switch (robotDirection) {
            case DUp:
                position.y -= Speed;
                break; //��
            case DDown:
                position.y += Speed;
                break; //��
            case DLeft:
                img = 3; //���ɵ�ͼƬ
                position.x -= Speed;
                break; //��
            case Dright:
                position.x += Speed;
                break; //��

            case DUpLeft:
                img = 3; //���ɵ�ͼƬ
                position.y -= Speed;
                position.x -= Speed;
                break; //����
            case DUpright:
                position.y -= Speed;
                position.x += Speed;
                break; //����
            case DDownLeft:
                img = 3; //���ɵ�ͼƬ
                position.y += Speed;
                position.x -= Speed;
                break; //����
            case DDownRight:
                position.y += Speed;
                position.x += Speed;
                break; //����
            default:
                break;
        }

        //���Ʋ��Ƴ���Ļ
        if (position.y <= 25) {
            position.y = 25;
        }
        if (position.x <= 0) {
            position.x = 0;
        }
        if (position.x >= screen_size.width - size.width) {
            position.x = screen_size.width - size.width;
        }
        if (position.y >= screen_size.height - size.height) {
            position.y = screen_size.height - size.height;
        }

        //������ͼ
        switch (img) {
            case 1:
                robotImage = robotimage01;
                break;
            //case 2:robotImage = robotimage02;break;
            case 3:
                robotImage = robotimage04;
                break;
            default:
                break;
        }
    }

    public void drawGunFire(int bullettime, Graphics g) {
        if (bullettime > 12 && bullettime <= 15) {
            g.drawImage(bulletimage01, position.x + 118, position.y + 3, null);
        } else if (bullettime > 9 && bullettime <= 12) {
            g.drawImage(bulletimage02, position.x + 118, position.y + 3, null);
        } else if (bullettime > 6 && bullettime <= 9) {
            g.drawImage(bulletimage03, position.x + 118, position.y + 3, null);
        } else if (bullettime > 3 && bullettime <= 6) {
            g.drawImage(bulletimage04, position.x + 118, position.y + 3, null);
        }
    }

    //�����Լ�
    public void graphicsRobot(Graphics g, ArrayList<Enemy> em) {
        //�ӳ��������
        if (bullettime > 0) {
            bullettime--;
        }
        if (bullettime > 10 && bullettime <= 15) {
            this.robotImage = robotimage03;
            this.size.width = robotImage.getWidth();
            this.size.height = robotImage.getHeight();
        } else if (bullettime > 5 && bullettime <= 10) {
            this.robotImage = robotimage02;
            this.size.width = robotImage.getWidth();
            this.size.height = robotImage.getHeight();
        } else if (bullettime > 1 && bullettime <= 5) {
            this.robotImage = robotimage03;
            this.size.width = robotImage.getWidth();
            this.size.height = robotImage.getHeight();

            if (bullettime == 2) {
                bullets.add(fire());
            }
        }

        drawGunFire(bullettime, g);

        //�����ӵ�����
        if (bullets.size() > 0) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).life) {
                    //�ӵ�����
                    bullets.get(i).GraphicsBullet(g);
                    //�����ео���ײ
                    for (int i2 = 0; i2 < em.size(); i2++) {
                        bullets.get(i).collision(em.get(i2));
                    }
                } else {
                    //�ӵ�����
                    bullets.remove(i);
                }
            }
        }

        //��������
        if (life > 0) {
            // ���ŲŻ����Լ�
            exp.i = 0;
            g.drawImage(BsGraphics.flipImage(robotImage), position.x, position.y, null);
        } else {
            explosionMe(g);
            overspeakindex++;
            if (overspeakindex < 400) {
                g.drawImage(overspeakimg, 200, 330, null);

                g.setFont(new Font("����", Font.BOLD, 16));
                g.setColor(Color.white);
                g.drawString("����˿", 420, 495);
                g.drawString("������......��", 400, 520);
            } else {
                GameStartApp.gamestatic = 0;
            }
        }

        //����Ѫ��
        g.drawImage(headimage, 15, 500, null);
        int xt_length = this.life * 150 / this.MaxLife;
        if (xt_length < 0) {
            xt_length = 0;
        }
        Color xt_color = Color.yellow;
        if (xt_length < 80) {
            xt_color = Color.orange;
        }
        if (xt_length < 40) {
            xt_color = Color.red;
        }
        g.setColor(xt_color);
        g.fillRect(130, 550, xt_length, 20);
        g.setColor(Color.BLACK);
        g.drawRect(130, 550, 150, 20);

        //����������ƶ�
        getDirection();
        robotMove();
    }

    /**
     * ��û����������
     */
    public Rectangle getRect() {
        Rectangle rect = new Rectangle(this.position.x + 6, this.position.y + 6, this.size.width - 12, this.size.height - 12);
        return rect;
    }

    /**
     * ������װ����
     */
    public void collision(Enemy em) {
        if (this.life > 0 && em.life > 0 && this.getRect().intersects(em.getMeRect())) {
            //������
            em.life = em.life - this.bodyPower;
            this.life = this.life - em.bodyPower;

            if (em.life <= 0) {
                //��ը��
                Sound fireSound = new Sound("music/Explode.mp3");
                fireSound.play();
            }
        }
    }

    /**
     * ����
     */
    public Bullet fire() {
        //��������
        Sound fireSound = new Sound("music/Beam.mp3");
        fireSound.play();
        Bullet bt = new Bullet(new Point(position.x + 120, position.y + 35), true,
                bulletimg, Direction.Dright, 8, true, 5, 0, 0, 0, 0);
        return bt;
    }
}
