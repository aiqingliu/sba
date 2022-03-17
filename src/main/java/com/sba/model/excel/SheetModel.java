package com.sba.model.excel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SheetModel {

    // 标识
    private String id;

    // 页英文名
    private String name_en;
    // 页中文名
    private String name_cn;
    // 页类型
    private String sheet_type;
    // 页序号
    private int sheet_index;

    // 头位置
    private int head_r;// 行位置
    private int head_c;// 列位置
    // 数据位置
    private int data_r;// 行位置
    private int data_c;// 列位置

    // 数据量
    private int r;// 行数
    private int c;// 列数

    // 列属性
    private List<ColumnModel> columnList = new ArrayList<ColumnModel>();

    // 页>列属性
    private Map<String, ColumnModel> columnMap = new LinkedHashMap<String, ColumnModel>();
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

    public String getSheet_type() {
        return sheet_type;
    }

    public void setSheet_type(String sheet_type) {
        this.sheet_type = sheet_type;
    }

    public int getSheet_index() {
        return sheet_index;
    }

    public void setSheet_index(int sheet_index) {
        this.sheet_index = sheet_index;
    }

    public int getHead_r() {
        return head_r;
    }

    public void setHead_r(int head_r) {
        this.head_r = head_r;
    }

    public int getHead_c() {
        return head_c;
    }

    public void setHead_c(int head_c) {
        this.head_c = head_c;
    }

    public int getData_r() {
        return data_r;
    }

    public void setData_r(int data_r) {
        this.data_r = data_r;
    }

    public int getData_c() {
        return data_c;
    }

    public void setData_c(int data_c) {
        this.data_c = data_c;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public List<ColumnModel> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnModel> columnList) {
        this.columnList = columnList;
    }

    public Map<String, ColumnModel> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, ColumnModel> columnMap) {
        this.columnMap = columnMap;
    }

    public Map<String, List<ColumnModel>> getSheetColumnMap() {
        return sheetColumnMap;
    }

    public void setSheetColumnMap(Map<String, List<ColumnModel>> sheetColumnMap) {
        this.sheetColumnMap = sheetColumnMap;
    }
}
