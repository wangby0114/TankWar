package com.wangby.tank;

import java.awt.*;
import java.nio.channels.NonReadableChannelException;

public class Tank {

    private int x, y;
    private Dir dir = Dir.VK_DOWN;
    private static final int SPEED = 10;

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void tankPaint(Graphics g) {
        g.fillRect(x, y, 50, 50);
        switch (this.dir) {
            case VK_LEFT:
                x -= SPEED;
                break;
            case VK_UP:
                y += SPEED;
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
