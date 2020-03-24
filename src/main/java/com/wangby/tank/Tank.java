package com.wangby.tank;

import java.awt.*;
import java.security.Signature;
import java.util.Random;

public class Tank {

    private int x, y;
    private Dir dir = Dir.VK_DOWN;
    private static final int SPEED = 5;
    private boolean moving = true;

    private boolean living = true;

    public static int TANK_WIDTH = ResourceMgr.tankL.getWidth();
    public static int TANK_HEIGHT = ResourceMgr.tankL.getHeight();

    private TankFrame tf;
    private Random random = new Random();

    private Group group = Group.BAD;

    public Tank(int x, int y, Group group, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public void tankPaint(Graphics g) {
        if (!this.living) {
            tf.tanks.remove(this);
        } else {
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

        if (random.nextInt(10) > 8) {
            this.fire();
        }

    }

    public void fire() {
//        Bullet b = new Bullet(this.x, this.y, this.dir);
//        tf.bullet = b;
        int bx = this.x + this.TANK_WIDTH /2 - Bullet.BULLET_WIDTH /2;
        int by = this.y + this.TANK_HEIGHT /2 - Bullet.BULLET_HTIGHT /2;
        tf.bullets.add(new Bullet(bx, by, Group.BAD, this.dir, tf));

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

    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
