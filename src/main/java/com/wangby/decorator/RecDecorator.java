package com.wangby.decorator;

import com.wangby.tank.GameObject;

import java.awt.*;

public class RecDecorator extends GODecorator{
    public RecDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);

        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawRect(go.x, go.y, go.getWidth() + 10, go.getHeight() + 10);
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
