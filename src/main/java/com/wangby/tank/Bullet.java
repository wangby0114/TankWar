package com.wangby.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;
    private int x, y;
    private Dir dir = Dir.VK_DOWN;

    private boolean living = true;
    private TankFrame tf;

    public static int BULLET_WIDTH = ResourceMgr.bulletL.getWidth();
    public static int BULLET_HTIGHT = ResourceMgr.bulletL.getHeight();

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (!this.living) {
            tf.bullets.remove(this);
        } else {
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
            tf.bullets.remove(this);
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

    public void collideWith(Tank tank) {
        Rectangle rec1 = new Rectangle(x, y, BULLET_WIDTH, BULLET_HTIGHT);
        Rectangle rec2 = new Rectangle(tank.getX(), tank.getY(), tank.TANK_WIDTH, tank.TANK_HEIGHT);
        if (rec1.intersects(rec2)) {
            tank.die();
            this.die();
        }

    }

    private void die() {
        this.living = false;
    }
}
