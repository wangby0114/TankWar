package com.wangby.tank;

import com.wangby.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    //私有构造方法
    private GameModel() {}
    //类的实例化
    public static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }



    Tank myTank;
    //初始化变量
    private void init () {
        //初始化主战坦克
        myTank = new Tank(100, 100, Group.GOOD, Dir.VK_DOWN);
        //初始化敌方坦克
        int tankCount = Integer.parseInt(props.get("initTankCount"));
        {
            for (int i = 0; i < tankCount; i++) {
                this.add(new Tank(50 + i * 60, 200, Group.BAD, Dir.VK_DOWN));
            }
        }

        //初始化墙壁
        this.add(new Wall( 185,90,30,120));
        this.add(new Wall(585,90,30,120));
        this.add(new Wall(385,270,30,120));
        this.add(new Wall(185,390,30,120));
        this.add(new Wall(585,390,30,120));
    }

    PropertyMgr props = PropertyMgr.getSingleton();
    int GAME_WIDTH = Integer.parseInt(props.get("gameWidth"));
    int GAME_HEIGHT = Integer.parseInt(props.get("gemeHeight"));

//    //敌方坦克列表
//    List<Tank> tanks = new ArrayList<Tank>();
//    //爆炸列表
//    List<Explode> explodsList = new ArrayList<Explode>();
//    //子弹列表
//    public List<Bullet> bullets = new ArrayList<Bullet>();

    public List<GameObject> objects = new ArrayList<>();
    
    //子弹坦克碰撞器
//    Collider bulletTankCol = new BulletTankCollider();
//    Collider tankTankCol = new TankTankCollider();

    ColliderChain colliderChain = new ColliderChain();

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.white);
//        g.drawString("子弹数量：" + bullets.size(), 10, 40);
//        g.drawString("敌人数量：" + tanks.size(), 10, 55);
//        g.drawString("爆炸数量：" + explodsList.size(), 10, 70);
//        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //碰撞测试
        for (int i = 0; i < objects.size(); i++) {
            boolean signal = true;
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                signal = colliderChain.collide(o1, o2);
                if (!signal) break;
            }
            if (!signal) break;

        }

//        //画出爆炸效果
//        for (int i = 0; i < explodsList.size(); i++) {
//            explodsList.get(i).paint(g);
//        }
//
//        //画出敌方坦克
//        for (int i = 0; i < tanks.size(); i++) {
//            tanks.get(i).paint(g);
//        }
//
//        //画出子弹(包括主战坦克，和敌方坦克的子弹)
//        for (int i = 0; i < bullets.size(); i++) {
//            bullets.get(i).paint(g);
//        }

//        for (int i = 0; i < bullets.size(); i++) {
//            for (int j = 0; j < tanks.size(); j++) {
//                bullets.get(i).collideWith(tanks.get(j));
//            }
//        }
    }
}
