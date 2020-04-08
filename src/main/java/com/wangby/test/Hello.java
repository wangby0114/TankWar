package com.wangby.test;

import java.util.LinkedList;
import java.util.List;

public class Hello {

    public static void main(String[] args) {
        System.out.println("HelloGc");
        List list = new LinkedList();
        for(;;) {
            byte[] b = new byte[1024*1024];
            list.add(b);
        }



    }
}
