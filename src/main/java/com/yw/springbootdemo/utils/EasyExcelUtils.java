package com.yw.springbootdemo.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.yw.springbootdemo.beans.UserExportItem;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangwei
 * @date 2020-01-09 9:42
 */
public class EasyExcelUtils {

    public void test() {
        try(OutputStream out = new FileOutputStream("test.xlsx")) {

            WriteSheet writeSheet = EasyExcel.writerSheet("test").build();
            List<UserExportItem> resultList = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                BufferedImage originalImage = ImageIO
                        .read(new File("C:\\Users\\yangwei\\Desktop\\新建文件夹\\IMG_1210.JPG"));
                BufferedImage thumbnail = Thumbnails.of(originalImage)
                        .scale(0.1f)
                        .asBufferedImage();
                ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
                ImageIO.write(thumbnail, "jpg", byteArrayOut);
                byte[] data = byteArrayOut.toByteArray();
                UserExportItem item = new UserExportItem();
                item.setName("阿姨" + i);
//                item.setPath("C:\\Users\\yangwei\\Desktop\\IMG_1210.JPG");
                item.setData(data);
                resultList.add(item);
            }
            ExcelWriter writer = EasyExcel.write(out, UserExportItem.class).build();
//            for (int i = 0; i < 100; i++) {
//                writer.write(resultList, writeSheet);
//            }
            writer.write(resultList, writeSheet);
            writer.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        EasyExcelUtils easyExcelUtils = new EasyExcelUtils();
        System.out.println(" ~~~ start ~~~ ");
        System.out.println(new Date());
        long start = System.currentTimeMillis();
        System.out.println();
        easyExcelUtils.test();
        System.out.println("it consumes " + (System.currentTimeMillis() - start) / 1000 + "s");
        System.out.println(" ~~~ end ~~~");
    }
}
