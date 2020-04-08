package com.wangby.threadLocal;

public class ThreadBean01 extends Thread {

    private ThreadLocalTest te;
    public ThreadBean01(String name, ThreadLocalTest te) {
        super(name);
        this.te = te;
    }
    @Override
    public void run() {
        while (te.getNextNum().getId() < 100) {
            te.getNextNum().setId(te.getNextNum().getId() + 1);
            System.out.println(this.getName() + "数字： " + te.getNextNum().getId());
        }
    }
    public ThreadLocalTest getTe() {
        return te;
    }
    public void setTe(ThreadLocalTest te) {
        this.te = te;
    }

    public static void main(String[] args) {

//        ThreadLocalTest local = new ThreadLocalTest();
//        ThreadBean01 thread01 = new ThreadBean01("线程一",local);
//        ThreadBean01 thread02 = new ThreadBean01("线程二",local);
//        thread01.start();
//        thread02.start();

        int i = 8;
//        i = i++;
        i = ++i;

        System.out.println(i);

    }
}
