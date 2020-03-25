package com.wangby.tank;

public class DefaultFireStrategy implements FireStragety {
    @Override
    public void fire(Tank tank) {
        int bx = tank.x + tank.TANK_WIDTH /2 - Bullet.BULLET_WIDTH /2;
        int by = tank.y + tank.TANK_HEIGHT /2 - Bullet.BULLET_HTIGHT /2;

        new Bullet(bx, by, tank.getGroup(), tank.dir, tank.tf);
        if (tank.getGroup() == Group.GOOD){
            new Thread(() -> {new Audio("audio/tank_fire.wav").play();}).start();
        }
    }
}
