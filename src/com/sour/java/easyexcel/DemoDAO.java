package com.sour.java.easyexcel;

import java.util.List;

public class DemoDAO {
    public void save(List<DemoData> list) {
        for (DemoData demoData : list) {
            System.out.println(demoData);
        }
    }
}
