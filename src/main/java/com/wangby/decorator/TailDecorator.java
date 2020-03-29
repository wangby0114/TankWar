package com.wangby.decorator;

import com.wangby.tank.GameObject;

import java.awt.*;

public class TailDecorator extends GODecorator{
    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.white);
        //g.drawRect(go.x + 2, go.y +2, go.getWidth() + 2, go.getHeight() + 2);
        g.drawLine(go.x, go.y, go.x + 10, go.y + 10);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
