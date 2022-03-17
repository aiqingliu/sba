package com.sba.model.excel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "excelmodel")
//@PropertySource(value = {"applicationexcel.yml"})
public class ExcelModel {
    // 标识
    private String id;
    // 表英文名
    private String name_en;
    // 表中文名
    private String name_cn;
    // 表类型
    private String excel_type;
    // 表序号
    private int excel_index;
    // 路径relative
    private String path_r;
    private String path_r_w;
    private String path_r_r;
    // 路径absolute
    private String path_a;
    private String path_a_w;
    private String path_a_r;

    // 页属性
    private List<SheetModel> sheetList = new ArrayList<SheetModel>();
    // 列属性
    private List<ColumnModel> columnList = new ArrayList<ColumnModel>();
    // 表>页属性
    private Map<String, SheetModel> sheetMap = new LinkedHashMap<String, SheetModel>();
    private Map<String, List<SheetModel>> excelSheetMap = new LinkedHashMap<String, List<SheetModel>>();

    // 页>列属性
    private Map<String, List<ColumnModel>> sheetColumnMap = new LinkedHashMap<String, List<ColumnModel>>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_cn() {
        return name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
    }

    public String getExcel_type() {
        return excel_type;
    }

    public void setExcel_type(String excel_type) {
        this.excel_type = excel_type;
    }

    public int getExcel_index() {
        return excel_index;
    }

    public void setExcel_index(int excel_index) {
        this.excel_index = excel_index;
    }

    public String getPath_r() {
        return path_r;
    }

    public void setPath_r(String path_r) {
        this.path_r = path_r;
    }

    public String getPath_r_w() {
        return path_r_w;
    }

    public void setPath_r_w(String path_r_w) {
        this.path_r_w = path_r_w;
    }

    public String getPath_r_r() {
        return path_r_r;
    }

    public void setPath_r_r(String path_r_r) {
        this.path_r_r = path_r_r;
    }

    public String getPath_a() {
        return path_a;
    }

    public void setPath_a(String path_a) {
        this.path_a = path_a;
    }

    public String getPath_a_w() {
        return path_a_w;
    }

    public void setPath_a_w(String path_a_w) {
        this.path_a_w = path_a_w;
    }

    public String getPath_a_r() {
        return path_a_r;
    }

    public void setPath_a_r(String path_a_r) {
        this.path_a_r = path_a_r;
    }

    public List<SheetModel> getSheetList() {
        return sheetList;
    }

    public void setSheetList(List<SheetModel> sheetList) {
        this.sheetList = sheetList;
    }

    public List<ColumnModel> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnModel> columnList) {
        this.columnList = columnList;
    }

    public Map<String, SheetModel> getSheetMap() {
        return sheetMap;
    }

    public void setSheetMap(Map<String, SheetModel> sheetMap) {
        this.sheetMap = sheetMap;
    }

    public Map<String, List<SheetModel>> getExcelSheetMap() {
        return excelSheetMap;
    }

    public void setExcelSheetMap(Map<String, List<SheetModel>> excelSheetMap) {
        this.excelSheetMap = excelSheetMap;
    }

    public Map<String, List<ColumnModel>> getSheetColumnMap() {
        return sheetColumnMap;
    }

    public void setSheetColumnMap(Map<String, List<ColumnModel>> sheetColumnMap) {
        this.sheetColumnMap = sheetColumnMap;
    }
}
