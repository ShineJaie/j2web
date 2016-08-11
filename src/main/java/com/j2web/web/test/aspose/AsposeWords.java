package com.j2web.web.test.aspose;


import com.aspose.words.Document;
import com.aspose.words.ReportingEngine;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * aspose.words generate word example
 * Created by wxj on 2016/8/10.
 */
public class AsposeWords {

    public static void main(String[] args) throws Exception {

        // The path to the documents directory.
        File dir = new File(System.getProperty("user.dir"));
        dir = new File(dir, "src");
        dir = new File(dir, "main");
        dir = new File(dir, "resources");
        dir = new File(dir, "doc");
        String dataDir = dir.toString() + File.separator;
        String fileName = "测试模板.docx";
        System.out.println("This template for report: " + dataDir + fileName);

        // Load the document from disk.
        Document doc = new Document(dataDir + fileName);

        // Create an instance of sender class to set it's properties.
        DocBean bean = new DocBean();
        bean.setName("我是谁");
        bean.message = "关于数据填充的测试";

        // 当前时间
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currDateStr = sdf.format(currDate);
        bean.currDateStr = currDateStr;

        DocUser user1 = new DocUser("小红", 18, 50);
        DocUser user2 = new DocUser("小绿", 19, 20);
        DocUser user3 = new DocUser("小蓝", 20, 30);
        bean.list.add(user1);
        bean.list.add(user2);
        bean.list.add(user3);

        // open image
        String imageName = "dog.png";
        System.out.println(dataDir + imageName);
        File imgFile = new File(dataDir + imageName);
        bean.photo = readContentIntoByteArray(imgFile);

        // Create a Reporting Engine.
        ReportingEngine engine = new ReportingEngine();

        // Execute the build report.
        engine.buildReport(doc, bean, "bean");

        // Save the finished document to disk.
        doc.save(dataDir + "output.doc");
        System.out.println("Document loaded and saved successfully.");
    }

    private static byte[] readContentIntoByteArray(File file) {
        FileInputStream fileInputStream = null;
        byte[] bFile = new byte[(int) file.length()];
        try {
            //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            for (int i = 0; i < bFile.length; i++) {
                //System.out.print((char) bFile[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bFile;
    }

}
