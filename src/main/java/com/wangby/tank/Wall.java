package com.wangby.tank;

import java.awt.*;

public class Wall extends GameObject {
    int x, y, width, height;
    public Rectangle rec = new Rectangle();

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rec.x= x;
        rec.y= y;
        rec.width = width;
        rec.height= height;

    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
