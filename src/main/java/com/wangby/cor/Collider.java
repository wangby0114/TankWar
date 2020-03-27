package com.wangby.cor;

import com.wangby.tank.GameObject;

public interface Collider {
    public boolean collide(GameObject o1, GameObject o2);
}
