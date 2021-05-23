package com.sour.java.poi.excel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 导出excel
 *
 * @author xgl
 * @date 2021/4/17 21:50
 **/
public class ExportExcelDemo04 {
    public static void main(String[] args) throws IOException {
        // 创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 创建sheet
        XSSFSheet sheet = workbook.createSheet("随机数据");

        // 固定的标题
        String[] titles = new String[]{"编号", "数字一", "数字二", "数字三", "和"};
        XSSFRow title = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            title.createCell(i).setCellValue(titles[i]);
        }

        // 数据
        for (int i = 1; i <= 100; i++) {
            XSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(i);
            int cell1 = (int)(Math.random() * 100);
            int cell2 = (int)(Math.random() * 100);
            int cell3 = (int)(Math.random() * 100);
            row.createCell(1).setCellValue(cell1);
            row.createCell(2).setCellValue(cell2);
            row.createCell(3).setCellValue(cell3);
            row.createCell(4).setCellValue(cell1+cell2+cell3);
        }

        // 导出
        workbook.write(new FileOutputStream("D:\\MyProject\\demo-java\\src\\com\\sour\\java\\poi\\file\\Demo03导出.xlsx"));


    }
}
