package com.j2web.web.test.aspose;


import com.aspose.words.Document;
import com.aspose.words.ReportingEngine;

import java.io.File;

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
        String fileName = "Test File (doc).docx";
        System.out.println("This template for report: " + dataDir + fileName);

        // Load the document from disk.
        Document doc = new Document(dataDir + fileName);

        // Create an instance of sender class to set it's properties.
        DocBean bean = new DocBean();
        bean.setName("吴仙杰");
        bean.message = "关于数据填充的测试";

        // Create a Reporting Engine.
        ReportingEngine engine = new ReportingEngine();

        // Execute the build report.
        engine.buildReport(doc, bean, "bean");

        // Save the finished document to disk.
        doc.save(dataDir + "output.doc");
        System.out.println("Document loaded and saved successfully.");
    }

}
