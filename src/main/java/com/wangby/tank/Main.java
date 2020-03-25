package com.wangby.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();


        //初始化敌方坦克
        PropertyMgr props = PropertyMgr.getSingleton();
        int tankCount = Integer.parseInt(props.get("initTankCount"));
        for (int i = 0; i < tankCount; i++) {
            tf.tanks.add(new Tank(50 + i*50, 200, Group.BAD, Dir.VK_DOWN, tf));
        }

        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(50);
            tf.repaint();

        }
    }
}