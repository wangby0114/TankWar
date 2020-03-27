package com.wangby.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    PropertyMgr props = PropertyMgr.getSingleton();
    int GAME_WIDTH = Integer.parseInt(props.get("gameWidth"));
    int GAME_HEIGHT = Integer.parseInt(props.get("gemeHeight"));

    //敌方坦克列表
    List<Tank> tanks = new ArrayList<Tank>();
    //爆炸列表
    List<Explods> explodsList = new ArrayList<Explods>();
    //子弹列表
    public List<Bullet> bullets = new ArrayList<Bullet>();

    Tank myTank = new Tank(100, 100, Group.GOOD, Dir.VK_DOWN, this);

    //初始化敌方坦克
    int tankCount = Integer.parseInt(props.get("initTankCount"));
    {
        for (int i = 0; i < tankCount; i++) {
            this.tanks.add(new Tank(50 + i * 50, 200, Group.BAD, Dir.VK_DOWN, this));
        }
    }
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹数量：" + bullets.size(), 10, 40);
        g.drawString("敌人数量：" + tanks.size(), 10, 55);
        g.drawString("爆炸数量：" + explodsList.size(), 10, 70);
        g.setColor(c);

        myTank.tankPaint(g);

        for (int i = 0; i < explodsList.size(); i++) {
            explodsList.get(i).pint(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).tankPaint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }
}
