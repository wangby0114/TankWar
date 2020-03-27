package com.wangby.tank;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{

    public int x, y;
    public Dir dir = Dir.VK_DOWN;
    PropertyMgr props = PropertyMgr.getSingleton();
    private final int SPEED = Integer.parseInt(props.get("tankSpeed"));
    public boolean moving = true;

    public boolean living = true;

    public static int TANK_WIDTH = ResourceMgr.badTankL.getWidth();
    public static int TANK_HEIGHT = ResourceMgr.badTankL.getHeight();

    private GameModel gm;
    private Random random = new Random();

    public Group group = Group.BAD;

    Rectangle rec = new Rectangle();

    public Tank(int x, int y, Group group, Dir dir, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;

        rec.x = this.x;
        rec.y = this.y;
        rec.width = TANK_WIDTH;
        rec.height = TANK_HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        if (!this.living) {
            gm.objects.remove(this);
        } else {
            switch (this.dir) {
                case VK_LEFT:
                    g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                    break;
                case VK_UP:
                    g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                    break;
                case VK_RIGHT:
                    g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                    break;
                case VK_DOWN:
                    g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                    break;
                default:
                    break;
            }
            if (moving) {
                move();
            }
        }
    }

    private void move() {
        if (x<= 0
                || y <= 0
                || x >= gm.GAME_WIDTH - TANK_WIDTH
                || y >= gm.GAME_HEIGHT - TANK_HEIGHT) {
            boundsCheck();
        }
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

        if ((this.group == Group.BAD)  && random.nextInt(10) > 8) {
            this.fire();
        }

        if ((this.group == Group.BAD) && random.nextInt(100) > 95) {
            randomDir();
        }
    }

    private void boundsCheck() {
        if (this.dir == Dir.VK_LEFT) {
            this.dir = Dir.VK_RIGHT;
            return;
        } else if (this.dir == Dir.VK_UP) {
            this.dir = Dir.VK_DOWN;
            return;
        }
         else if (this.dir == Dir.VK_RIGHT) {
            this.dir = Dir.VK_LEFT;
            return;
        }
         else if (this.dir == Dir.VK_DOWN) {
            this.dir = Dir.VK_UP;
            return;
        }
    }

    private void randomDir() {
         this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx = this.x + this.TANK_WIDTH /2 - Bullet.BULLET_WIDTH /2;
        int by = this.y + this.TANK_HEIGHT /2 - Bullet.BULLET_HTIGHT /2;
        gm.objects.add(new Bullet(bx, by, this.getGroup(), this.dir, gm));

    }

    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRec() {
        return rec;
    }

    public void stop() {
        moving = false;
    }
}
