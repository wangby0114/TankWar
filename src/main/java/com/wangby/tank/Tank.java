package com.wangby.tank;

import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;
import com.sun.xml.internal.ws.server.sei.TieHandler;
import com.wangby.abstractFactory.BaseTank;
import jdk.nashorn.internal.ir.IfNode;
import sun.tools.tree.ShiftLeftExpression;

import java.awt.*;
import java.security.Signature;
import java.util.Random;

public class Tank extends BaseTank {

    int x, y;
    Dir dir = Dir.VK_DOWN;
    PropertyMgr props = PropertyMgr.getSingleton();
    private final int SPEED = Integer.parseInt(props.get("tankSpeed"));
    private boolean moving = true;

    private boolean living = true;

    public static int TANK_WIDTH = ResourceMgr.badTankL.getWidth();
    public static int TANK_HEIGHT = ResourceMgr.badTankL.getHeight();

    TankFrame tf;
    private Random random = new Random();

    private Group group = Group.BAD;

    Rectangle rec = new Rectangle();

    DefaultFireStrategy defaultFire = new DefaultFireStrategy();
    FourDirFireStrategy fourFire = new FourDirFireStrategy();

    public Tank(int x, int y, Group group, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rec.x = this.x;
        rec.y = this.y;
        rec.width = TANK_WIDTH;
        rec.height = TANK_HEIGHT;
    }

    @Override
    public void tankPaint(Graphics g) {
        if (!this.living) {
            tf.tanks.remove(this);
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
        if (x <= 0
                || y <= 0
                || x >= tf.getSize().getWidth() - TANK_WIDTH
                || y >= tf.getSize().getHeight() - TANK_HEIGHT) {
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

        if ((this.group == Group.BAD) && random.nextInt(10) > 8) {
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
        } else if (this.dir == Dir.VK_RIGHT) {
            this.dir = Dir.VK_LEFT;
            return;
        } else if (this.dir == Dir.VK_DOWN) {
            this.dir = Dir.VK_UP;
            return;
        }
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        if (this.group == Group.GOOD) {
            fourFire.fire(this);
        } else {
            defaultFire.fire(this);
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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
}
