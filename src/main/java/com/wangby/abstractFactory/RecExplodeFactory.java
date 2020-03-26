package com.wangby.abstractFactory;

import com.wangby.tank.*;

public class RecExplodeFactory extends GameFactory {
    @Override
    public BaseTank createTanke(int x, int y, Group group, Dir dir, TankFrame tf) {
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, Group group, Dir dir, TankFrame tf) {
        return new RecBullet(x, y, group, dir, tf);
    }

    @Override
    public RecExplode createExplode(int x, int y, TankFrame tf) {
        return new RecExplode(x, y, tf);
    }
}
