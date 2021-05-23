package com.sour.java.poi.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 读取
 *
 * @author xgl
 * @date 2021/4/17 21:35
 **/
public class ReadDemo03 {
    public static void main(String[] args) throws IOException {
        // 1, 读文件
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("D:\\MyProject\\demo-java\\src\\com\\sour\\java\\poi\\file\\读取的对象800行.xlsx"));

        // 2, 第一个工作表
        XSSFSheet sheet = workbook.getSheetAt(0);

        // 3, 读取内容
//        read01(sheet);
        read02(sheet);
    }

    /**
     * 完整读取
     *
     * @author xgl
     * @date 2021/4/17 21:45
     **/
    private static void read01(XSSFSheet sheet) {
        Row row;
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                System.out.print(row.getCell(j));
                System.out.print(" , ");
            }
            System.out.println();
        }
    }

    /**
     * 有类型地拿取
     *
     * @author xgl
     * @date 2021/4/17 21:47
     **/
    private static void read02(XSSFSheet sheet) {
        Row row;
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
//            double cell01 = row.getCell(1).getNumericCellValue();
            String cell02 = row.getCell(2).getStringCellValue();
        }
    }
}
