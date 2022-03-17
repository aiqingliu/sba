package com.sba.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

import com.sba.model.excel.ColumnModel;
import com.sba.model.excel.ExcelModel;
import com.sba.model.excel.SheetModel;
import com.sba.model.excel.XPEData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 写入Excel数据
 */
public class WriteExcelUtils {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static ExcelModel e = new ExcelModel();
    public static List<XPEData> xList = new ArrayList<XPEData>();
    public static Map<String, List<XPEData>> xlm = new HashMap<>();

    /**
     * 测试一个初始数据
     */
    public static void initXPEData() {
        xlm = new HashMap<>();
        for (int j = 0; j < 6; j++) {
            xList = new ArrayList<XPEData>();
            for (int i = 0; i < 6; i++) {
                XPEData xEach = new XPEData();
                xEach.setField1(1 + "" + j + i);
                xEach.setField2(new Date().toString());
                xEach.setField3(3 + "##" + j + i);
//                xEach.setField4(4 + "##" + j * i);
                xEach.setField5(5 + "##" + j * i);
                xEach.setField6(6 + "##" + j * i);
                xEach.setField7(7 + "##" + j * i);
                xList.add(xEach);
            }
            xlm.put(j+"", xList);
        }
    }

    /**
     * 测试一个格式
     */
    public static void initExcel() {
        e = new ExcelModel();
        Random r = new Random();

        e.setPath_a("E:/Users/LAQ/IdeaProjects/SpringBoot_A/src/main/resources/files/excels/writeExcel.xlsx");
        // sheet
        for (int i = 1; i <= 3; i++) {
            SheetModel s = new SheetModel();
            s.setId(i+"");
            s.setName_en("Sheet"+i);
            s.setName_cn("中文Sheet"+i);
            s.setData_c(1);
            // column
            for (int j = 1; j <= 4; j++) {
                r = new Random(j*10);
                ColumnModel c = new ColumnModel();
                c.setId(r.nextInt() + "");
                c.setCol_index(j);
                c.setName_en("field"+j);
                c.setName_cn("中文field"+j);
                // sheet加入column的Map
                s.getColumnMap().put(i+"", c);
                // sheet加入column的List
                s.getColumnList().add(c);
            }
            // 表加入sheet
            e.getSheetList().add(s);
        }
    }

    public static void main(String[] args) {

        Map<String, String> dataMap=new HashMap<String, String>();
        dataMap.put("BankName", "BankName");
        dataMap.put("Addr", "Addr");
        dataMap.put("Phone", "Phone");
        List<Map> list=new ArrayList<Map>();
        list.add(dataMap);
        writeExcel(list, 3, "classpath:files/excels/writeExcel.xlsx");
    }

    public static void testM() {
        Map<String, String> dataMap=new HashMap<String, String>();
        dataMap.put("BankName", "BankName");
        dataMap.put("Addr", "Addr");
        dataMap.put("Phone", "Phone");
        List<Map> list=new ArrayList<Map>();
        list.add(dataMap);
        writeExcel(list, 3, "E:/Users/LAQ/IdeaProjects/SpringBoot_A/src/main/resources/files/excels/writeExcel.xlsx");
    }

    public static void testW() {
        Map<String, String> dataMap=new HashMap<String, String>();
        dataMap.put("BankName", "BankName");
        dataMap.put("Addr", "Addr");
        dataMap.put("Phone", "Phone");
        List<Map> list=new ArrayList<Map>();
        list.add(dataMap);
        writeExcel(list, 3, "classpath:files/excels/writeExcel.xlsx");
    }

    /**
     * writeExcel 写出excel文档
     * @param dataList 待输出数据
     * @param cloumnCount 列数
     * @param finalXlsxPath 文件路径
     */
    public static void writeExcel(List<Map> dataList, int cloumnCount, String finalXlsxPath){
        OutputStream out = null;
        Workbook workBook = null;
        File finalXlsxFile = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            finalXlsxFile = new File(finalXlsxPath);
            // 文件不存在则创建一个
            if (!finalXlsxFile.exists()) {
                finalXlsxFile.createNewFile();

            }
            workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
//            Sheet sheet = workBook.getSheetAt(0);
            Sheet sheet = workBook.getSheet("Sheet2");
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                String name = dataMap.get("BankName").toString();
                String address = dataMap.get("Addr").toString();
                String phone = dataMap.get("Phone").toString();
                // 根据数据条数创建excel行
                for (int k = 0; k <= columnNumCount; k++) {
                    // 在一行内循环
                    Cell first = row.createCell(0);
                    first.setCellValue(name);

                    Cell second = row.createCell(1);
                    second.setCellValue(address);

                    Cell third = row.createCell(2);
                    third.setCellValue(phone);
                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            System.out.println("数据导出成功");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据导出异常");
        } finally{
            try {
                if(out != null){
                    workBook.close();
                    System.out.println("excel关闭");
                    out.flush();
                    out.close();
                    System.out.println("流关闭");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * writeExcel 写入excel文档
     * @param excelModel
     * @description excel>sheet>column
     */
    public static void writeExcel(ExcelModel excelModel){
        OutputStream out = null;// 流
        Workbook workBook = null;// 文件接口
        File finalXlsxFile = null;// 文件
        String finalXlsxPath = null;// 文件位置

        String fieldName = null;// 字段名
        String fieldValue = null;// 字段内容
        List<XPEData> dataList = new ArrayList<XPEData>();
        dataList = xList;
        try {
            if (excelModel == null) {
                System.out.println("数据内容不存在");
            } else {
                finalXlsxPath = excelModel.getPath_a_w();
                // 读取Excel文档
                finalXlsxFile = new File(finalXlsxPath);
                // 文件不存在则创建一个
                if (!finalXlsxFile.exists()) {
                    finalXlsxFile.createNewFile();
                }
                // 获取
                workBook = getWorkbok(finalXlsxFile);
                // 存在sheet进行遍历
                if (excelModel.getSheetList() != null) {
                    for (SheetModel sheetModelEach : excelModel.getSheetList()) {
                        // 先根据英文名找,没有再按照中文名找
                        //Sheet sheet = workBook.getSheetAt(sheetModelEach.getSheet_index());
                        Sheet sheet = workBook.getSheet(sheetModelEach.getName_en());
                        if (sheet == null) {
                            sheet = workBook.getSheet(sheetModelEach.getName_cn());
                        }
                        // 存在有效sheet
                        if (sheet == null) {
                            continue;
                        }
                        // 获取总列数
                        int columnNumCount = sheetModelEach.getColumnList().size();
                        // 删除原有数据，除了属性列
                        int rowNumber = sheet.getLastRowNum();// 第一行从0开始算
                        if (sheetModelEach.getData_r() != 0) {
                            rowNumber = sheetModelEach.getData_r();
                        }
                        System.out.println("原始数据总行数,除属性行(数据的条数)：" + rowNumber);
                        for (int i = 1; i <= rowNumber; i++) {
                            Row row = sheet.getRow(i);
                            sheet.removeRow(row);
                        }
                        // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
                        out =  new FileOutputStream(finalXlsxPath);
                        workBook.write(out);
                        // 往Excel的sheet中写新数据(从指定列开始,跳过属性列)
                        dataList = xlm.get(sheetModelEach.getId());
                        for (int j = 0; j < dataList.size(); j++) {
                            // 创建一行(sheetModelEach.getData_c()设置起始位置)
                            Row row = sheet.createRow(j + sheetModelEach.getData_c());
                            // 循环列数
                            for (int k = 0; k < columnNumCount; k++) {
                                // 创建第k个cell
                                Cell cellEach = row.createCell(k);
                                // 当前sheet的第k个列属性名
                                fieldName = sheetModelEach.getColumnList().get(k).getName_en();
                                if (fieldName == null) {
                                    continue;
                                }
                                Field field = XPEData.class.getDeclaredField(fieldName);
                                // 当字段存在,则取得内容
                                if (field == null) {
                                    continue;
                                }
                                // 打开私有访问
                                field.setAccessible(true);
                                fieldValue = (String)field.get(dataList.get(j));
                                // 放入第k个cell的值
                                cellEach.setCellValue(fieldValue);

                                fieldName = null;
                                fieldValue = null;
                            }

                        }
                    }
                }
            }

            // sheet 对应一个工作页
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            System.out.println("数据导出成功");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据导出异常");
        } finally{
            try {
                if(out != null){
                    workBook.close();
                    System.out.println("excel关闭");
                    out.flush();
                    out.close();
                    System.out.println("流关闭");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        // 判断文件后缀,创建处理实现
        if(file.getName().endsWith(EXCEL_XLS)){// Excel 2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){// Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }
}
