package com.sour.java.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TestWrite {

    public final static String PATH = "D:\\MyProject\\demo-java\\src\\com\\sour\\java\\easyexcel\\";

    public static void main(String[] args) {
        simpleWrite();
    }

    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


    /**
     * 最简单的写
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 直接写即可
     */
    public static void simpleWrite() {
        // 写法1
        String fileName = PATH + "easyExcel01.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    }
}
