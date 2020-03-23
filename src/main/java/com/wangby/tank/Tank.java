package com.wangby.tank;

import java.awt.*;
import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.List;

public class Tank {

    private int x, y;
    private Dir dir = Dir.VK_DOWN;
    private static final int SPEED = 5;
    private boolean moving = false;

    private int WIDTH = 50;
    private int HEIGHT = 50;

    public int tankWdth = ResourceMgr.tankL.getWidth();
    public int tankHeight = ResourceMgr.tankL.getHeight();

    private TankFrame tf;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void tankPaint(Graphics g) {
        switch (this.dir) {
            case VK_LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case VK_UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case VK_RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case VK_DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            default:
                break;
        }
        if (moving) {
            move();
        }
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
    }

    public void fire() {
//        Bullet b = new Bullet(this.x, this.y, this.dir);
//        tf.bullet = b;
        int bx = this.x + this.tankWdth/2 - Bullet.bulletWdth/2;
        int by = this.y + this.tankHeight/2 - Bullet.bulletHeight/2;
        tf.bulletList.add(new Bullet(bx, by, this.dir, tf));

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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
