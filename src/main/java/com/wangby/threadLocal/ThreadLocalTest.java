package com.wangby.threadLocal;

public class ThreadLocalTest {

    // 定义匿名子类创建ThreadLocal的变量为什么是静态的，不管你new多少个这个类，ThreadLocal只有一个
    private static ThreadLocal<NumberBean> seqNum = new ThreadLocal<NumberBean>() {
        // 覆盖初始化方法,泛型指定什么类型就返回什么类型，方法名不变自动调用。
        public NumberBean initialValue() {
            System.out.println("初始化了");
            return new NumberBean();
        }
    };

    // 或者这线程的数据
    public NumberBean  getNextNum() {
        return seqNum.get();
    }
}
