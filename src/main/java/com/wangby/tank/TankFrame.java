package com.wangby.tank;

import com.sun.javafx.sg.prism.web.NGWebView;
import jdk.nashorn.internal.ir.IfNode;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    static final int GAME_WIDTH = 800, GAME_HEIGTH = 600;

    Tank myTank = new Tank(100, 100, Dir.VK_DOWN);;
    Bullet bullet = new Bullet(200, 200, Dir.VK_DOWN);

    public TankFrame() throws HeadlessException {
        this.setSize(GAME_WIDTH, GAME_HEIGTH);
        this.setTitle("tank war");
        this.setResizable(false);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new MyKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        myTank.tankPaint(g);
        bullet.paint(g);
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;

                default:
                    break;
            }

            setMainTankDir(myTank);
        }

        void setMainTankDir(Tank myTank) {
            if (bL && bU && bR && bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.VK_LEFT);
                if (bU) myTank.setDir(Dir.VK_UP);
                if (bR) myTank.setDir(Dir.VK_RIGHT);
                if (bD) myTank.setDir(Dir.VK_DOWN);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.setMoving(false);
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;

                default:
                    break;
            }
        }
    }

}
