package com.yw.springbootdemo.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * @author yangwei
 * @date 2019/6/18 10:27
 */
public class test {
    public static void main(String[] args) throws IOException {
//        try (OutputStream out = new FileOutputStream("withoutHead.xlsx");) {
//            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
//            Sheet sheet1 = new Sheet(1, 0);
//            sheet1.setSheetName("sheet1");
//            List<List<String>> data = new ArrayList<>();
//            for (int i = 0; i < 100; i++) {
//                List<String> item = new ArrayList<>();
//                item.add("item0" + i);
//                item.add("item1" + i);
//                item.add("item2" + i);
//                data.add(item);
//            }
//            writer.write0(data, sheet1);
//            writer.finish();
//        }
        long start = System.currentTimeMillis();
//        Thumbnails.of("C:\\Users\\yangwei\\Desktop\\IMG_1210.JPG")
//                .size(160, 160)
//                .toFile("thumbnail.jpg");
        Thumbnails.of("C:\\Users\\yangwei\\Desktop\\新建文件夹\\IMG_1210.JPG")
                .scale(0.25f)
                .toFile("C:\\Users\\yangwei\\Desktop\\新建文件夹\\IMG_1210.JPG");
        System.out.println("it consumes " + (System.currentTimeMillis() - start) / 1000 + "s");
    }
}
