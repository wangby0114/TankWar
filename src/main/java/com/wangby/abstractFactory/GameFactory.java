package com.wangby.abstractFactory;

import com.wangby.tank.Dir;
import com.wangby.tank.Group;
import com.wangby.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTanke(int x, int y, Group group, Dir dir, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Group group, Dir dir, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}
