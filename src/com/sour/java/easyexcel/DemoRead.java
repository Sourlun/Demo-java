package com.sour.java.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.io.File;

/**
 * 读
 *
 * @author xgl
 * @date 2021/5/29 10:21
 **/
public class DemoRead {

    public final static String PATH = "D:\\MyProject\\demo-java\\src\\com\\sour\\java\\easyexcel\\";

    public static void main(String[] args) {
        String fileName = PATH + File.separator + "easyExcel01.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }

}
