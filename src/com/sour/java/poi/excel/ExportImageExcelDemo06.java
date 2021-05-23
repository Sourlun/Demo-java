package com.sour.java.poi.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 导出excel (带图片)
 *
 * @author xgl
 * @date 2021/4/17 21:50
 **/
public class ExportImageExcelDemo06 {
    public static void main(String[] args) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 读取图片
        BufferedImage image = ImageIO.read(new File("D:\\MyProject\\demo-java\\src\\com\\sour\\java\\poi\\file\\嵌入图片.jpg"));
        // 把图片写入到了字节输出流中
        ImageIO.write(image, "JPG", byteArrayOutputStream);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("随机数据");
        // 固定的标题
        String[] titles = new String[]{"编号", "数字一", "数字二", "数字三", "和", "合并单元格1", "合并单元格2"};
        XSSFRow title = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            title.createCell(i).setCellValue(titles[i]);
        }

        XSSFCellStyle cellStyle01 = workbook.createCellStyle();
        cellStyle01.setBorderBottom(BorderStyle.THIN); // 下边框变细
        cellStyle01.setAlignment(HorizontalAlignment.LEFT); // 水平: 靠左

        XSSFCellStyle cellStyle02 = workbook.createCellStyle();
        cellStyle02.setBorderLeft(BorderStyle.THIN); // 下边框变细
        cellStyle02.setAlignment(HorizontalAlignment.CENTER); // 水平: 居中

        // Patriarch 控制图片的写入
        XSSFDrawing drawing = sheet.createDrawingPatriarch();

        // 指定图片的位置
        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, 7, 1, 9, 3 );
        // 开始把图片写入到指定的位置
        drawing.createPicture(anchor, workbook.addPicture(byteArrayOutputStream.toByteArray(), Workbook.PICTURE_TYPE_JPEG));
        // 处理图片的结束


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
        workbook.write(new FileOutputStream("D:\\MyProject\\demo-java\\src\\com\\sour\\java\\poi\\file\\Demo06带图片导出.xlsx"));


    }
}
