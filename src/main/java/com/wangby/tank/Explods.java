package com.wangby.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explods {

    private int x, y;
    private boolean living = true;

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
        }
    }
}
