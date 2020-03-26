package com.wangby.tank;

import com.wangby.abstractFactory.BaseExplode;

import java.awt.*;

public class RecExplode extends BaseExplode {

    private int x, y;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private TankFrame tf;
    private int step = 0;

    public RecExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x, y, 10*step, 10*step);
        step++;

        if (step >= 5) {
            tf.explodsList.remove(this);
        }

        g.setColor(c);
    }
}
