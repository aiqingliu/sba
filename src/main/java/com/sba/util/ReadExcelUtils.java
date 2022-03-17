package com.sba.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sba.model.excel.ColumnModel;
import com.sba.model.excel.ExcelModel;
import com.sba.model.excel.SheetModel;
import com.sba.model.excel.XPEData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 读取Excel数据
 */
public class ReadExcelUtils {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void main(String[] args) {
        ReadExcelUtils readExcelUtils = new ReadExcelUtils();
        // Excel路径
        File file = new File("E:/Users/LAQ/IdeaProjects/SpringBoot_A/src/main/resources/files/excels/readExcel.xlsx");
        List excelList = readExcelUtils.readExcel(file);
        System.out.println("list中的数据打印出来");
        for (int i = 0; i < excelList.size(); i++) {
            List list = (List) excelList.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j));
            }
            System.out.println();
        }
    }

    public static void testR() {
        ReadExcelUtils readExcelUtils = new ReadExcelUtils();
        // Excel路径
        File file = new File("E:/Users/LAQ/IdeaProjects/SpringBoot_A/src/main/resources/files/excels/readExcel.xlsx");
        List excelList = readExcelUtils.readExcel(file);
        System.out.println("list中的数据打印出来");
        for (int i = 0; i < excelList.size(); i++) {
            List list = (List) excelList.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j));
            }
            System.out.println();
        }
    }

    /**
     * 去读Excel的方法readExcel,该方法的入口参数为一个File对象
     * @param file
     * @return
     */
    public List readExcel(File file) {
//        try {
//            // 创建输入流，读取Excel
//            InputStream is = new FileInputStream(file.getAbsolutePath());
//            // jxl提供的Workbook类
//            Workbook wb = getWorkbok(file);
//            // Excel的页签数量
//            int sheet_size = wb.getNumberOfSheets();
//            // 遍历sheet页
//            for (int index = 0; index < sheet_size; index++) {
//                List<List> outerList = new ArrayList<List>();
//                // 每个页签创建一个Sheet对象
//                Sheet sheet = wb.getSheetAt(index);
//                // 列数
//                // 指定表头行,获取列数
//                // sheet.getRows()返回该页的总行数
//                for (int i = 0; i < sheet.getLastRowNum(); i++) {
//                    List innerList = new ArrayList();
//                    // sheet.getColumns()返回该页的总列数
//                    for (int j = 0; j < sheet.getColumns(); j++) {
//                        String cellinfo = sheet.getCell(j, i).getContents();
//                        if(cellinfo.isEmpty()){
//                            continue;
//                        }
//                        innerList.add(cellinfo);
//                        System.out.print(cellinfo);
//                    }
//                    outerList.add(i, innerList);
//                    System.out.println();
//                }
//                return outerList;
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (BiffException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    /**
     * 读取Excel内容
     * @param excelModel
     * @return
     */
    public static Map<String, List<XPEData>> readExcel(ExcelModel excelModel) {

        Map<String, List<XPEData>> dataListMap = new LinkedHashMap<>();
        List<XPEData> dataList = new ArrayList<>();// 返回值
        String finalXlsxPath = null;// 文件位置
        File file = null;// 文件
        InputStream is = null;// 流
        Workbook wb = null;// 格式文件
        String fieldName = null;// 字段英文名
        try {
            if (excelModel == null || excelModel.getSheetMap() == null) {
                System.out.println("数据内容不存在");
            } else {
                // 读取Excel路径
                finalXlsxPath = excelModel.getPath_a_r();
                // 文件
                file = new File(finalXlsxPath);
//                // 创建输入流,读取Excel
//                is = new FileInputStream(file.getAbsolutePath());
                // jxl提供的Workbook类
                wb = getWorkbok(file);
                // Excel的页签数量
                int sheet_size = wb.getNumberOfSheets();
                // 遍历文件的sheet页
                for (int index = 0; index < sheet_size; index++) {
                    dataList = new ArrayList<>();
                    // 1 匹配sheet
                    // 每个页签创建一个Sheet对象
                    Sheet sheetEach = wb.getSheetAt(index);
                    // 通过sheet页名判断sheet是否配置
                    String sheetName = sheetEach.getSheetName();
                    if (excelModel.getSheetMap().get(sheetName) == null) {
                        continue;
                    }
                    SheetModel sheetModelEach = excelModel.getSheetMap().get(sheetName);
                    // 列数 指定表头行,获取列数
                    Row headRow = sheetEach.getRow(sheetModelEach.getHead_r());
                    // 数据列数
                    sheetModelEach.setC(headRow.getLastCellNum());

                    System.out.println("sheet页:" + sheetName + "共" + sheetEach.getLastRowNum() + "行,表头共" + headRow.getLastCellNum() + "列");

                    // 2 匹配sheet中的column
                    for (int i = headRow.getFirstCellNum(); i < headRow.getLastCellNum(); i++) {
                        // 获取该列是否在配置中(列内容与key值相对)
                        ColumnModel columnModelEach = sheetModelEach.getColumnMap().get(getCellValue(headRow.getCell(i)));
                        if (columnModelEach != null) {
                            // 设置列对应位置
                            columnModelEach.setC(i);
                            sheetModelEach.getColumnMap().put(i+"", columnModelEach);
                        } else {
                            continue;
                        }
                    }
                    // 3 读取数据
                    // 从数据行开始读取到最后一行
                    for (int i = sheetModelEach.getData_r(); i <= sheetEach.getLastRowNum(); i++) {
                        XPEData XPEDataEach = new XPEData();// 当前行存储位置
                        Row rowEach = sheetEach.getRow(i);// 当前行
                        if (rowEach == null) {
                            continue;
                        }
                        // 从第一列读到最后一列
                        for (int j = 0; j < rowEach.getLastCellNum(); j++) {
//                            System.out.println("第" + i + "行第" + j + "列");
                            String cellStr = getCellValue(rowEach.getCell(j));
                            if (cellStr == null) {
                                continue;
                            }
                            fieldName = null;
                            ColumnModel columnModelEach = sheetModelEach.getColumnMap().get(j + "");
                            if (columnModelEach != null) {
                                // 反射注入
                                fieldName = columnModelEach.getName_en();
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
                                // 设置值
                                field.set(XPEDataEach, cellStr);
                            } else {
                                continue;
                            }
                        }
                        dataList.add(XPEDataEach);
                    }
                    dataListMap.put(sheetName, dataList);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataListMap;
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        InputStream is = new FileInputStream(file);
        // 判断文件后缀,创建处理实现
        if(file.getName().endsWith(EXCEL_XLS)){// Excel 2003
            wb = new HSSFWorkbook(is);
        }else if(file.getName().endsWith(EXCEL_XLSX)){// Excel 2007/2010
            wb = new XSSFWorkbook(is);
        }
        return wb;
    }

    /**
     * 根据单元格获取内容
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        if (cell != null) {
//            if (cell != null) {
//                cell.setCellType(xssfRow.CELL_TYPE_STRING);
//            }
            // 如果是布尔类型
            if (cell.getCellType() == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            }
            // 数值类型
            else if (cell.getCellType() == CellType.NUMERIC) {
                String result = "";
                if (cell.getCellStyle().getDataFormat() == 22) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                }
                return result;
            }
            // 字符串类型
            else {
                return String.valueOf(cell.getStringCellValue());
            }
        } else {
            return null;
        }
    }

}
