package com.j2web.web.test.aspose;

import java.util.ArrayList;
import java.util.List;

/**
 * the java bean for aspose.words
 * Created by wxj on 2016/8/10.
 */
public class DocBean {

    private String name;
    public String message;
    public String currDateStr;
    public byte[] photo;
    public List<DocUser> list = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
