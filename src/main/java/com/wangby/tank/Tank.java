package com.wangby.tank;

import com.wangby.decorator.RecDecorator;
import com.wangby.decorator.TailDecorator;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{
    public Dir dir = Dir.VK_DOWN;
    PropertyMgr props = PropertyMgr.getSingleton();
    private final int SPEED = Integer.parseInt(props.get("tankSpeed"));
    public boolean moving = true;

    public boolean living = true;

    public int oldx, oldy;

    public static int WIDTH = ResourceMgr.badTankL.getWidth();
    public static int HEIGHE = ResourceMgr.badTankL.getHeight();

    private Random random = new Random();

    public Group group = Group.BAD;

    Rectangle rec = new Rectangle();

    GameModel INSTANCE = GameModel.getInstance();

    public Tank(int x, int y, Group group, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rec.x = this.x;
        rec.y = this.y;
        rec.width = WIDTH;
        rec.height = HEIGHE;
    }

    @Override
    public void paint(Graphics g) {
        if (!this.living) {
            INSTANCE.objects.remove(this);
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
        oldx = x;
        oldy = y;

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
        if (x<= 0
                || y <= 0
                || x >= INSTANCE.GAME_WIDTH - WIDTH
                || y >= INSTANCE.GAME_HEIGHT - HEIGHE) {
            //boundsCheck();
            this.back();
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

    public void back() {
        x = oldx;
        y = oldy;
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
        int bx = this.x + this.WIDTH /2 - Bullet.WIDTH /2;
        int by = this.y + this.HEIGHE /2 - Bullet.HEIGHT /2;
        INSTANCE.objects.add(new RecDecorator(new TailDecorator(new Bullet(bx, by, this.getGroup(), this.dir))));
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHE;
    }
}
