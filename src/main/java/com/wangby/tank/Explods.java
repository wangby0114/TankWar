package com.wangby.tank;

import java.awt.*;

public class Explods {

    private int x, y;
    public boolean living = true;

    public static int WIDTH = ResourceMgr.explods[0].getWidth();
    public static int HEIGHT = ResourceMgr.explods[0].getHeight();
    private TankFrame tf;
    private int step = 0;

    public Explods(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }
    
    public void pint(Graphics g) {
        g.drawImage(ResourceMgr.explods[step++], x, y, null);
        if (step >= ResourceMgr.explods.length) {
            step = 0;
            tf.explodsList.remove(this);
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
