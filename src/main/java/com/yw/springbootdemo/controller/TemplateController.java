package com.yw.springbootdemo.controller;

import com.ssi.common.utils.FileUtils;
import com.yw.springbootdemo.common.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Locale;

/**
 * @author yangwei
 * @date 2019/6/4 10:33
 */
@RestController
public class TemplateController {

    @Value("${driver.import.template}")
    private String driverImportTemplateFile;

    @GetMapping(value = "/downloadImportTemplate")
    public void downloadImportTemplate(HttpServletRequest request,
                                       HttpServletResponse response)
            throws IOException {
        OutputStream outputStream = null;
        try {
            String filePath = request.getServletContext().getRealPath(driverImportTemplateFile);
            File file = new File(filePath);
            byte[] data = FileUtils.readFileToByteArray(file);
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/vnd.ms-excel");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
    /*
    public ResponseEntity<InputStreamResource> downloadImportTemplate(HttpServletRequest request)
            throws IOException {
        String filePath = request.getServletContext().getRealPath(driverImportTemplateFile);
//        String filePath = getClass().getResource(driverImportTemplateFile).getPath();
//        String filePath = System.getProperty("user.dir") + driverImportTemplateFile;
//        filePath = new String(filePath.getBytes(Constants.CHARSET), Constants.UTF8);
        FileSystemResource file = new FileSystemResource(filePath);
        if (!file.exists()) {
//            throw new Exception("");
        }
        String fileName = file.getFilename();
        fileName = URLEncoder.encode(fileName,"UTF-8");
//        String userAgentString = request.getHeader("User-Agent");
//        if (userAgentString != null) {
//            fileName = getFilenameWithBrower(userAgentString.toLowerCase(Locale.CHINA), fileName);
//        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setPragma("no-cache");
        headers.setExpires(0);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

    private static String getFilenameWithBrower(String userAgentString, String fileName) throws
            UnsupportedEncodingException {
        String resultFname;
        if (userAgentString.indexOf("msie") != -1 || userAgentString.indexOf("trident") != -1) {
            resultFname = URLEncoder.encode(fileName, Constants.UTF8);
        } else {
            resultFname = new String(fileName.getBytes(Constants.UTF8), Constants.CHARSET);
        }
        return resultFname;
    }*/
}
