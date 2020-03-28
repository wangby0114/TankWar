package com.wangby.cor;

import com.wangby.tank.GameObject;
import com.wangby.tank.Tank;

public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if ((o1 instanceof Tank) && (o2 instanceof Tank)) {
            Tank t1 = (Tank)o1;
            Tank t2 = (Tank)o2;

            if (t1.getRec().intersects(t2.getRec())) {
                //TODO 坦克和坦克碰撞之后，调转方向处理
                t1.back();
                t2.back();
                //坦克和坦克相撞，没有消失，所以不用永远返回true
                //return false;
            }
        }
        return true;
    }
}
