package com.wangby.cor;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.wangby.tank.*;

import java.awt.*;

public class BulletTankCollider implements Collider {
    //返回true表示继续执行
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if ((o1 instanceof Bullet) && (o2 instanceof Tank)) {
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            boolean explode = collideWith(b, t);
            if (explode) {
                return false;
            }
        } else if ((o1 instanceof Tank) && (o2 instanceof Bullet)){
            return collide(o2, o1);

        }
        return true;
    }

    //返回true表示撞上了
    public boolean collideWith(Bullet o1, Tank o2) {
        if (o1.group == o2.group) return false;
        //TODO:可以分别在tanke类和bullet类里生成一个Rectangle，记录tanke和bullet的位置，在此碰撞
        Rectangle rec1 = new Rectangle(o1.x, o1.y, o1.BULLET_WIDTH, o1.BULLET_HTIGHT);
        Rectangle rec2 = new Rectangle(o2.x, o2.y, o2.TANK_WIDTH, o2.TANK_HEIGHT);
        if (rec1.intersects(rec2)) {
            o1.die();
            o2.die();
            GameModel.getInstance().objects.add(new Explode(o2.x + o2.TANK_WIDTH/2 - Explode.WIDTH/2, o2.y + o2.TANK_HEIGHT/2 - Explode.HEIGHT/2));
            return true;
        }
        return false;
    }

}
