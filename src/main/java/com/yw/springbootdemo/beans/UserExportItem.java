package com.yw.springbootdemo.beans;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;

/**
 * @author yangwei
 * @date 2020-01-09 15:48
 */
@ColumnWidth(15)
@ContentRowHeight(100)
public class UserExportItem {

    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    private byte[]  data;
    /**
     * 如果string类型 必须指定转换器，string默认转换成string
     */
//    @ExcelProperty(value = "照片", index = 1, converter = StringImageConverter.class)
//    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
