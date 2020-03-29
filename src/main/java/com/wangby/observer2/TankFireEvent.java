package com.wangby.observer2;

import com.wangby.tank.Tank;

public class TankFireEvent {
    Tank tank;

    public Tank getSource() {
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
