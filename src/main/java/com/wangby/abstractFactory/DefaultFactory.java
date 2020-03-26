package com.wangby.abstractFactory;

import com.wangby.tank.*;

public class DefaultFactory extends GameFactory {
    @Override
    public BaseTank createTanke(int x, int y, Group group, Dir dir, TankFrame tf) {
        return new Tank(x, y, group, dir, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Group group, Dir dir, TankFrame tf) {
        return null;
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }
}
