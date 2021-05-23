package com.sour.java.poi.excel;

import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectExcel {

    public static void main(String[] args) throws Exception {

        String content = getTemplateContent();
        content = content.replace("|", "&").replace("NULL", "");
        String[] totalRow = content.split("\n");
        List<Company> companies = new ArrayList<>();

        Map<String, List<Company>> map = new HashMap<>();

        for (int i = 0; i < totalRow.length; i++) {
            String row = totalRow[i];
            String[] rowArray = row.replace(" ", "").split("&");
            Company company = new Company();
            company.setGoodsName(rowArray[1]);
            company.setCompanyName(rowArray[2]);
            company.setAddress(rowArray[3]);
            company.setContactPeople(rowArray[4]);
            company.setTelephone(rowArray[5]);
            company.setBusinessType(rowArray[6]);
            company.setTotalScore(rowArray[7]);
            companies.add(company);
            System.out.println(rowArray);
        }

        for (Company company : companies) {
            String goodsName = company.getGoodsName();
            List<Company> companies1;
            if ( map.containsKey(goodsName) ) {
                companies1 = map.get(goodsName);
            } else {
                companies1 = new ArrayList<>();
            }
            companies1.add(company);
            map.put(goodsName, companies1);
        }

        // 创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 固定的标题
        String[] titles = new String[]{"编号", "产品品种中类", "企业名称", "地址", "联系人", "联系电话", "企业类型", "计分值", "记分等级"};

        for (String goodsName : map.keySet()) {
            // 创建sheet
            XSSFSheet sheet = workbook.createSheet(goodsName);
            XSSFRow title = sheet.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                title.createCell(i).setCellValue(titles[i]);
            }
            // 数据
            List<Company> companies1 = map.get(goodsName);
            for (int i = 0; i < companies1.size(); i++) {
                XSSFRow row = sheet.createRow(i+1);
                Company company = companies1.get(i);
                row.createCell(0).setCellValue(i);
                row.createCell(1).setCellValue(company.getGoodsName());
                row.createCell(2).setCellValue(company.getCompanyName());
                row.createCell(3).setCellValue(company.getAddress());
                row.createCell(4).setCellValue(company.contactPeople);
                row.createCell(5).setCellValue(aesDecrypt(company.getTelephone()));
                row.createCell(6).setCellValue(company.getBusinessType());
                row.createCell(7).setCellValue(company.getTotalScore());
                row.createCell(8).setCellValue(getRankByScore(Integer.valueOf(company.getTotalScore())));
            }
        }

        // 导出
        workbook.write(new FileOutputStream("D:\\项目文件\\市场监督局\\资料\\备份\\2021-05-17\\导出.xlsx"));

    }



    private static String getTemplateContent() throws Exception {
        File file = new File("D:\\项目文件\\市场监督局\\资料\\备份\\2021-05-17\\text.txt");
        if (!file.exists()) {
            return null;
        }
        FileInputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte bytes[] = new byte[length];
        inputStream.read(bytes);
        inputStream.close();
        String str = new String(bytes, StandardCharsets.UTF_8);
        return str;
    }

    /**
     * 企业信息　－　失信等级： 根据分数获取失信等级
     */
    public static String getRankByScore( Integer score ) {

        if ( null == score ) {
            return "";
        } else if ( score >= 12 ) {
            return "A";
        } else if ( score > 6 && score < 12 ) {
            return "B";
        } else if ( score > 0 && score <= 6 ) {
            return "C";
        } else {
            return "D";
        }
    }


    private static String AES_KEY = "izmNRXQ9Cw38tTiD";
    private static String AES_IV = "ZJPIsLH4JBp2mUDw";

    /**
     * AES解密
     *
     * @param data 需解密字符串
     * @return
     */
    public static String aesDecrypt(String data) {
        if (data == null) return null;
        return aesDecrypt(data, AES_KEY, AES_IV);
    }

    public static String aesDecrypt(String data, String keyStr, String ivStr) {
        try {
            Base64 base64 = new Base64();
            byte[] encrypted = base64.decode(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(keyStr.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(ivStr.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original);
            return originalString.trim();
        } catch (Exception e) {
            e.printStackTrace();
            // 考虑到之前有些数据是没有经过加密的,所以这里报错的话,把源数据返回
            return data;
        }
    }

    @Data
    static class Company {
        private String goodsName;
        private String companyName;
        private String address;
        private String contactPeople;
        private String telephone;
        private String businessType;
        private String totalScore;
    }
}
