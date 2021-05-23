package com.sour.java.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 低版本的excel
 *
 * @author xgl
 * @date 2021/4/17 21:24
 **/
public class LowVersionDemo01 {

    public static void main(String[] args) throws IOException {

        // 1, 创建一个全新的工作簿
        Workbook workbook = new HSSFWorkbook();

        // 2, 在工作簿中创建新的工作表
        Sheet sheet01 = workbook.createSheet("sheet01");

        // 3, 工作区中创建行
        Row sheet01Row = sheet01.createRow(0);

        // 4, 在单元格中写入内容
        Cell cell01 = sheet01Row.createCell(0);
        cell01.setCellValue("单元格一");

        // 5, 输出
        workbook.write(new FileOutputStream("D:\\MyProject\\demo-java\\src\\com\\sour\\java\\poi\\file\\LowVersionDemo01.xls"));
    }
}
