package com.wangby.tank;

import com.sun.scenario.effect.impl.prism.PrImage;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;
    private int x, y;
    private int WIDTH = 10;
    private int HEIGHT = 10;
    private Dir dir = Dir.VK_DOWN;

    private boolean buLiving = true;
    private TankFrame tf;

    public static int bulletWdth = ResourceMgr.bulletL.getWidth();
    public static int bulletHeight = ResourceMgr.bulletL.getHeight();

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        switch (this.dir) {
            case VK_LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case VK_UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case VK_RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case VK_DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        switch (this.dir) {
            case VK_LEFT:
                x -= SPEED;
                break;
            case VK_UP:
                y -= SPEED;
                break;
            case VK_RIGHT:
                x += SPEED;
                break;
            case VK_DOWN:
                y += SPEED;
                break;

            default:
                break;
        }

        if (x < 0 || y < 0 || x > tf.getSize().width || y > tf.getSize().height) {
            tf.bulletList.remove(this);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}
