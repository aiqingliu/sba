package com.sba.controller;

import com.sba.util.ReadFileUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/readfiles")
public class ReadFilesController {

    @RequestMapping("readFilesOne")
    public String readFilesOne(){
        System.out.println("readFilesOne");
        try {
//            File file = ResourceUtils.getFile("classpath:files/one_proc.txt");
//            System.out.println(file);
//            String filename = file.getName();
//            System.out.println(filename);
//            InputStream inputStream = new FileInputStream(file);
//            System.out.println(inputStream);
//            System.out.println(inputStream.read());
//            byte[] buffer = new byte[inputStream.available()];
//            System.out.println(buffer);
//            System.out.println(inputStream.read(buffer));
//            System.out.println(inputStream);
//            inputStream.close();
//            System.out.println(inputStream);

            File file = ResourceUtils.getFile("classpath:files/one_proc.txt");
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = bufferedReader.readLine())!=null){//使用readLine方法，一次读一行
                stringBuffer.append(s.trim());
            }
            System.out.println(stringBuffer.toString());
            // 关闭
            bufferedReader.close();

//            File files = ResourceUtils.("classpath:files/one_proc.txt");
            String path = "classpath:files/";
//            File filepackage = new File(path);
            File filepackage = ResourceUtils.getFile("classpath:files/sql/");

            List listLocal = new ArrayList<>();
//            if (filepackage != null) {
//                File[] f = filepackage.listFiles();
//                if (f != null) {
//                    for (int i = 0; i < f.length; i++) {
////                        递归调用
////                        getFile(f[i]);
//                        if (f[i].isDirectory()) {
//
//                        } else if (f[i].isFile()){
//                            listLocal.add(f[i]);
//                        }
//                    }
//                } else {
//                    // System.out.println(file);
//                }
//            }

            ReadFileUtils readFileUtils = new ReadFileUtils();
//            listLocal = readFileUtils.getFile(filepackage);

            List<String> filesmentlist = new ArrayList<>();

//            for (int i = 0; i < listLocal.size(); i++) {
//                System.out.println(listLocal.get(i).toString());
//                BufferedReader bufferedReaderEach = new BufferedReader(new FileReader(listLocal.get(i).toString()));
//                StringBuffer filementEach = new StringBuffer();
//                String seach = null;
//                while ((seach = bufferedReaderEach.readLine()) != null) {
//                    filementEach.append(seach.trim());
//                }
//                filesmentlist.add(filementEach.toString());
//            }
//            System.out.println(filesmentlist);
//
//            filesmentlist = new ArrayList<>();
//            for (int i = 0; i < filepackage.listFiles().length; i++) {
//                System.out.println(filepackage.listFiles()[i]);
//
//                if (filepackage.listFiles()[i].isDirectory()) {
//                    continue;
//                }
//                BufferedReader bufferedReaderEach = new BufferedReader(new FileReader(filepackage.listFiles()[i]));
//                StringBuffer filementEach = new StringBuffer();
//                String seach = null;
//                while ((seach = bufferedReaderEach.readLine()) != null) {
//                    filementEach.append(seach.trim());
//                }
//                filesmentlist.add(filementEach.toString());
//            }
//            System.out.println(filesmentlist);
//
//            filesmentlist = new ArrayList<>();
//            for (File fileEach : filepackage.listFiles()) {
//                if (fileEach.isDirectory()) {
//                    continue;
//                }
//                BufferedReader bufferedReaderEach = new BufferedReader(new FileReader(fileEach));
//                StringBuffer filementEach = new StringBuffer();
//                String seach = null;
//                while ((seach = bufferedReaderEach.readLine()) != null) {
//                    filementEach.append(seach.trim());
//                }
//                filesmentlist.add(filementEach.toString());
//            }
//            System.out.println(filesmentlist);
//
//            for (int i = 0; i < filesmentlist.size(); i++) {
//                System.out.println(filesmentlist.get(i));
//            }

            filesmentlist = readFileUtils.findFilepackage("classpath:files/sql/");
            for (int i = 0; i < filesmentlist.size(); i++) {
                System.out.println(filesmentlist.get(i));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "readFilesOne";
    }

}
