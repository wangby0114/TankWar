package com.wangby.observer2;

import com.wangby.tank.Tank;

public class TankFireHandle implements TankFireObserver {
    @Override
    public void actionOnFire(TankFireEvent e) {
        Tank tank = e.getSource();
        tank.fire();
    }
}
