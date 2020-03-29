package com.wangby.fireobserver;

import com.wangby.decorator.RecDecorator;
import com.wangby.decorator.TailDecorator;
import com.wangby.tank.Bullet;
import com.wangby.tank.GameModel;
import com.wangby.tank.GameObject;
import com.wangby.tank.Tank;

public class FireListerer1 implements FireListener {
    @Override
    public void executeFire(Tank tank) {
        int bx = tank.x + tank.WIDTH /2 - Bullet.WIDTH /2;
        int by = tank.y + tank.HEIGHE /2 - Bullet.HEIGHT /2;
        ////用装饰者模式给子弹添加外壳
        //GameModel.getInstance().objects.add(new RecDecorator(new TailDecorator(new Bullet(bx, by, tank.getGroup(), tank.dir))));
        GameModel.getInstance().objects.add(new Bullet(bx, by, tank.getGroup(), tank.dir));
    }
}
