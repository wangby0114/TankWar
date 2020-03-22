package com.wangby.tank;

import java.awt.*;
import java.nio.channels.NonReadableChannelException;

public class Tank {

    private int x, y;
    private Dir dir = Dir.VK_DOWN;
    private static final int SPEED = 5;
    private boolean moving = false;

    private int WIDTH = 50;
    private int HEIGHT = 50;

    private TankFrame tf;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void tankPaint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(c);
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
        tf.bullet = new Bullet(this.x, this.y, this.dir);
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
