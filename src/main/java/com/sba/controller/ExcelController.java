package com.sba.controller;

import com.alibaba.fastjson.JSON;
import com.sba.model.excel.ExcelModel;
import com.sba.model.excel.XPEData;
import com.sba.util.ReadExcelUtils;
import com.sba.util.WriteExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excels")
public class ExcelController {

    @Autowired
    private ExcelModel xm;

    WriteExcelUtils writeExcelUtils = new WriteExcelUtils();

    @RequestMapping("/testWrite")
    public String testWrite() {
        System.out.println("writeFilesOne");
        try {
            WriteExcelUtils.initExcel();
            System.out.println(JSON.toJSONString(WriteExcelUtils.e));

            WriteExcelUtils.initXPEData();
            System.out.println(JSON.toJSONString(WriteExcelUtils.xList));

//            WriteExcelUtils.writeExcel(WriteExcelUtils.e);
            System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");

            WriteExcelUtils.writeExcel(xm);
            System.out.println("xmxmxmxmxmxmxmxmxmxmxmxmxmxmxmxmxm");

//            WriteExcelUtils.testM();
//            WriteExcelUtils.testw();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return "testWrite";
    }

    @RequestMapping("/testRead")
    public String testRead() {
        System.out.println("readFilesOne");
        try {

            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
            Map<String, List<XPEData>> dataListMap = ReadExcelUtils.readExcel(xm);
            System.out.println("xmxmxmxmxmxmxmxmxmxmxmxmxmxmxmxmxm");
            System.out.println(JSON.toJSONString(dataListMap));

//            ReadExcelUtils.testR();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return "testRead";
    }
}
