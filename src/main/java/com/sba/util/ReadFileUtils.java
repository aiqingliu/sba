package com.sba.util;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFileUtils {

    /**
     * 默认的文件路径
     */
    private String filepath = "classpath:files/";

    /**
     * 读取路径文件
     * 形如"classpath:files/"
     * 形如"classpath:files/***.*"
     * 形如"classpath:filespackage/***.*"
     * 返回文件中读取的字符串
     * @param filepackagepath
     * @return List<String>
     */
    public List<String> findFilepackage(String filepackagepath) {
        List<String> listStr = new ArrayList<>();

        try {
            // 获取文件路径,不存在则设置默认位置
            if (filepackagepath == null) {
                if (this.filepath != null) {
                    filepackagepath = this.filepath;
                } else {
                    filepackagepath = "classpath:files/";
                }
            }
            // 获取文件路径下所有文件
            File filepackage = ResourceUtils.getFile(filepackagepath);
            List listFileLocal = this.getFile(filepackage);

//            List<String> filesmentlist = new ArrayList<>();
//            for (int i = 0; i < listFileLocal.size(); i++) {
//                System.out.println(listFileLocal.get(i).toString());
//            }
            // 读取文件中的内容
            for (int i = 0; i < listFileLocal.size(); i++) {
                System.out.println(listFileLocal.get(i).toString());
                BufferedReader bufferedReaderEach = new BufferedReader(new FileReader(listFileLocal.get(i).toString()));
                StringBuffer filementEach = new StringBuffer();
                String seach = null;
                while ((seach = bufferedReaderEach.readLine()) != null) {
                    filementEach.append(seach.trim());
                }
                listStr.add(filementEach.toString());
            }

//            for (int i = 0; i < filepackage.listFiles().length; i++) {
//                System.out.println(filepackage.listFiles()[i]);
//
//                BufferedReader bufferedReaderEach = new BufferedReader(new FileReader(filepackage.listFiles()[i]));
//                StringBuffer filementEach = new StringBuffer();
//                String seach = null;
//                while ((seach = bufferedReaderEach.readLine()) != null) {
//                    filementEach.append(seach.trim());
//                }
//                filesmentlist.add(filementEach.toString());
//            }
////            System.out.println(filesmentlist);

//            for (File fileEach : filepackage.listFiles()) {
//                BufferedReader bufferedReaderEach = new BufferedReader(new FileReader(fileEach));
//                StringBuffer filementEach = new StringBuffer();
//                String seach = null;
//                while ((seach = bufferedReaderEach.readLine()) != null) {
//                    filementEach.append(seach.trim());
//                }
//                listStr.add(filementEach.toString());
//            }
//            System.out.println(listStr);

//            for (int i = 0; i < listStr.size(); i++) {
//                System.out.println(listStr.get(i));
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStr;
    }

    /**
     * 递归读取文件夹下所有文件名
     * @param file
     * @return
     */
    public List getFile(File file) {
        List listFileLocal = new ArrayList<>();
        if (file != null) {
            // 获取文件数组,遍历
            File[] f = file.listFiles();
            if (f != null) {
                for (int i = 0; i < f.length; i++) {
//                    getFile(f[i]);
//                    listFileLocal.add(f[i]);
                    // 判断文件的类型,文件夹或文件
                    if (f[i].isDirectory()) {
                        // 文件夹则递归调用
                        listFileLocal.addAll(getFile(f[i]));
                    } else if (f[i].isFile()){
                        // 文件则添加到文件list
                        listFileLocal.add(f[i]);
                    }
                }
            } else {
                // 空文件处理
                // System.out.println(file);
            }
        }
        return listFileLocal;
    }
}
