package com.wangby.decorator;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wangby.tank.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {
    GameObject go;
    public GODecorator(GameObject go) {
        this.go = go;
    }

    public abstract void paint(Graphics g);

//    @Override
//    public void paint(Graphics g) {
//         go.paint(g);
//    }
}
