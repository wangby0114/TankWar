package com.wangby.tank;

import java.awt.*;

public class Explode extends GameObject {
    public boolean living = true;

    public static int WIDTH = ResourceMgr.explods[0].getWidth();
    public static int HEIGHT = ResourceMgr.explods[0].getHeight();
    private int step = 0;

    GameModel INSTANCE = GameModel.getInstance();

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explods[step++], x, y, null);
        if (step >= ResourceMgr.explods.length) {
            step = 0;
            INSTANCE.objects.remove(this);
        }

        new Thread(() -> new Audio("audio/explode.wav").play());
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }
}
