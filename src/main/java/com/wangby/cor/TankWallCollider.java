package com.wangby.cor;

import com.wangby.tank.GameObject;
import com.wangby.tank.Tank;
import com.wangby.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if ((o1 instanceof Tank) && (o2 instanceof Wall)) {
            Tank tank = (Tank)o1;
            Wall wall = (Wall) o2;
            if (tank.getRec().intersects(wall.rec)) {
                tank.back();
            }
        } else if ((o1 instanceof Wall) && (o2 instanceof Tank)) {
            collide(o2, o1);
        }

        return true;
    }
}
