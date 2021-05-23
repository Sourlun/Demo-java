package com.sour.java.poi.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 导出excel (带样式)
 *
 * @author xgl
 * @date 2021/4/17 21:50
 **/
public class ExportStyleExcelDemo05 {
    public static void main(String[] args) throws IOException {
        // 创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 创建sheet
        XSSFSheet sheet = workbook.createSheet("随机数据");

        // 固定的标题
        String[] titles = new String[]{"编号", "数字一", "数字二", "数字三", "和", "合并单元格1", "合并单元格2"};
        XSSFRow title = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            title.createCell(i).setCellValue(titles[i]);
        }

        // 样式设置
        XSSFCellStyle cellStyle01 = workbook.createCellStyle();
        cellStyle01.setBorderBottom(BorderStyle.THIN); // 下边框变细
        cellStyle01.setAlignment(HorizontalAlignment.LEFT); // 水平: 靠左

        // 样式设置
        XSSFCellStyle cellStyle02 = workbook.createCellStyle();
        cellStyle02.setBorderLeft(BorderStyle.THIN); // 下边框变细
        cellStyle02.setAlignment(HorizontalAlignment.CENTER); // 水平: 居中

        // 数据
        for (int i = 1; i <= 100; i++) {
            XSSFRow row = sheet.createRow(i);

            // 设置 行高
            row.setHeightInPoints(50);

            row.createCell(0).setCellValue(i);
            int cell1 = (int)(Math.random() * 100);
            int cell2 = (int)(Math.random() * 100);
            int cell3 = (int)(Math.random() * 100);
            XSSFCell cell01 = row.createCell(1);
            XSSFCell cell02 = row.createCell(2);
            XSSFCell cell03 = row.createCell(3);
            XSSFCell cell04 = row.createCell(4);

            XSSFCell cell05 = row.createCell(5);
            XSSFCell cell06 = row.createCell(6);
            cell05.setCellStyle(cellStyle02);
            cell06.setCellStyle(cellStyle02);


            cell01.setCellStyle(cellStyle01);

            cell01.setCellValue(cell1);
            cell02.setCellValue(cell2);
            cell03.setCellValue(cell3);
            cell04.setCellValue(cell1+cell2+cell3);

            // 合并单元格 双数才合并
            if ( i % 2 == 0 ) {
                cell05.setCellValue("合并");
                sheet.addMergedRegion(new CellRangeAddress(i, i, 5, 6));
            } else {
                cell05.setCellValue("不合并");
                cell06.setCellValue("不合并");
            }
        }

        // 导出
        workbook.write(new FileOutputStream("D:\\MyProject\\demo-java\\src\\com\\sour\\java\\poi\\file\\Demo04带样式导出.xlsx"));


    }
}
