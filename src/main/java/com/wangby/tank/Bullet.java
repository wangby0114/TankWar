package com.wangby.tank;

import java.awt.*;

public class Bullet extends GameObject{
    PropertyMgr props = PropertyMgr.getSingleton();
    private final int SPEED = Integer.parseInt(props.get("bulletSpeed"));
    public int x, y;
    public Dir dir = Dir.VK_DOWN;

    public boolean living = true;
    public GameModel gm;

    public static int BULLET_WIDTH = ResourceMgr.bulletL.getWidth();
    public static int BULLET_HTIGHT = ResourceMgr.bulletL.getHeight();

    public Group group = Group.BAD;

    private Rectangle rec = new Rectangle();

    public Bullet(int x, int y, Group group, Dir dir, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;

        rec.x = this.x;
        rec.y = this.y;
        rec.width = BULLET_WIDTH;
        rec.height = BULLET_HTIGHT;
    }

    @Override
    public void paint(Graphics g) {
        if (!this.living) {
            gm.objects.remove(this);
        } else {
            switch (this.dir) {
                case VK_LEFT:
                    g.drawImage(ResourceMgr.bulletL, x, y, null);
                    break;
                case VK_UP:
                    g.drawImage(ResourceMgr.bulletU, x, y, null);
                    break;
                case VK_RIGHT:
                    g.drawImage(ResourceMgr.bulletR, x, y, null);
                    break;
                case VK_DOWN:
                    g.drawImage(ResourceMgr.bulletD, x, y, null);
                    break;
                default:
                    break;
            }
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

        if (x < 0 || y < 0 || x > gm.GAME_WIDTH || y > gm.GAME_HEIGHT) {
            gm.objects.remove(this);
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

//    public void collideWith(Tank tank) {
//        if (this.group == tank.getGroup()) return;
//
//        //TODO:可以分别在tanke类和bullet类里生成一个Rectangle，记录tanke和bullet的位置，在此碰撞
//        Rectangle rec1 = new Rectangle(x, y, BULLET_WIDTH, BULLET_HTIGHT);
//        Rectangle rec2 = new Rectangle(tank.getX(), tank.getY(), tank.TANK_WIDTH, tank.TANK_HEIGHT);
//        if (rec1.intersects(rec2)) {
//            tank.die();
//            this.die();
//
//            gm.objects.add(new Explode(tank.getX() + tank.TANK_WIDTH/2 - Explode.WIDTH/2, tank.getY() + tank.TANK_HEIGHT/2 - Explode.HEIGHT/2, gm));
//        }
//    }

    public void die() {
        this.living = false;
    }
}
