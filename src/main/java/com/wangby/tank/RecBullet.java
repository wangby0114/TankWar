package com.wangby.tank;

import com.wangby.abstractFactory.BaseBullet;

import java.awt.*;

public class RecBullet extends BaseBullet {
    PropertyMgr props = PropertyMgr.getSingleton();
    private final int SPEED = Integer.parseInt(props.get("bulletSpeed"));
    private int x, y;
    private Dir dir = Dir.VK_DOWN;

    private boolean living = true;
    private TankFrame tf;

    public static int BULLET_WIDTH = ResourceMgr.bulletL.getWidth();
    public static int BULLET_HTIGHT = ResourceMgr.bulletL.getHeight();

    private Group group = Group.BAD;

    private Rectangle rec = new Rectangle();

    public RecBullet(int x, int y, Group group, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rec.x = this.x;
        rec.y = this.y;
        rec.width = BULLET_WIDTH;
        rec.height = BULLET_HTIGHT;

        tf.bullets.add(this);

    }

    @Override
    public void paint(Graphics g) {
        if (!this.living) {
            tf.bullets.remove(this);
        } else {
            Color c = g.getColor();
            g.setColor(Color.red);
            g.fillRect(x, y, 10, 10);
            g.setColor(c);

            move();
        }
    }

    private void move() {
        switch (this.dir) {
            case VK_LEFT:
                x -= SPEED;
                break;
            case VK_UP:
                y -= SPEED;
                break;
            case VK_RIGHT:
                x += SPEED;
                break;
            case VK_DOWN:
                y += SPEED;
                break;

            default:
                break;
        }

        rec.x = this.x;
        rec.y = this.y;

        if (x < 0 || y < 0 || x > tf.getSize().width || y > tf.getSize().height) {
            tf.bullets.remove(this);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;

        //TODO:可以分别在tanke类和bullet类里生成一个Rectangle，记录tanke和bullet的位置，在此碰撞
        Rectangle rec1 = new Rectangle(x, y, BULLET_WIDTH, BULLET_HTIGHT);
        Rectangle rec2 = new Rectangle(tank.getX(), tank.getY(), tank.TANK_WIDTH, tank.TANK_HEIGHT);
        if (rec1.intersects(rec2)) {
            tank.die();
            this.die();

            int bx = tank.getX() + tank.TANK_WIDTH/2 - Explode.WIDTH/2;
            int by = tank.getY() + tank.TANK_HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodsList.add(tf.gf.createExplode(bx, by, tf));

        }
    }

    private void die() {
        this.living = false;
    }
}
