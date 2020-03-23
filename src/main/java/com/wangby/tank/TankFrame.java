package com.wangby.tank;

import com.sun.javafx.sg.prism.web.NGWebView;
import jdk.nashorn.internal.ir.IfNode;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    Tank myTank = new Tank(100, 100, Dir.VK_DOWN, this);
    Bullet bullet = new Bullet(200, 200, Dir.VK_DOWN, this);

    public List<Bullet> bulletList = new ArrayList<Bullet>();

    public TankFrame() throws HeadlessException {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹数量：" + bulletList.size(), 50,50);
        g.setColor(c);

        myTank.tankPaint(g);

        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

//        Iterator<Bullet> iterator = bulletList.iterator();
//        while (iterator.hasNext()) {
//            Bullet b = iterator.next();
//            b.paint(g);
//        }
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
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
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
