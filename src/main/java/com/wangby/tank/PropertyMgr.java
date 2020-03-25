package com.wangby.tank;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.awt.*;
import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private PropertyMgr() {}

    static class PropertyMgrHolder{
        private static final PropertyMgr singleton = new PropertyMgr();
    }

    public static PropertyMgr getSingleton() {
        return PropertyMgrHolder.singleton;
    }

    Properties props = new Properties();
    {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.property"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String get(String key) {
        if (props == null) return null;
        return (String)props.get(key);
    }

    public static void main(String[] args) {
        String str = (String) PropertyMgr.getSingleton().get("gameWidth");
        System.out.println(str);
    }
}
