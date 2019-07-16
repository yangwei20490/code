package com.yw.springbootdemo.service.impl;

import com.yw.springbootdemo.beans.UserImportItem;
import com.yw.springbootdemo.common.Constants;
import com.yw.springbootdemo.service.IUploadService;
import com.yw.springbootdemo.service.IUserService;
import com.yw.springbootdemo.utils.ExcelHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangwei
 * @date 2019/6/4 16:56
 */
@Service
public class UploadService implements IUploadService {

    private static final Logger LOGGER = LogManager.getLogger(UploadService.class);

    private ExecutorService uploadUsersThreadPool = Executors.newFixedThreadPool(1);

    @Autowired
    private IUserService userService;

    @Override
    public void importUsers(MultipartFile file) {
        uploadUsersThreadPool.submit(() -> {
            List<List<String>> rowList = parseUserImportFile(file);
            System.out.println(rowList);
            importUsers(rowList);
        });
    }

    private List<List<String>> parseUserImportFile(MultipartFile file) {
        List<List<String>> rowList;
        try {
            rowList = ExcelHelper.readExcelToList(file.getInputStream(),1, null);
        } catch (Exception ex) {
            LOGGER.error(Constants.EXCEL_FORMAT_ERROR + ": " + ex.getMessage(), ex);
            throw new RuntimeException(Constants.EXCEL_FORMAT_ERROR);
        }

        Iterator<List<String>> rowIterator = rowList.iterator();
        if (!rowIterator.hasNext()) {
            throw new RuntimeException(Constants.EXCEL_FORMAT_ERROR);
        }

//        String enterpriseName = getEnterpriseName(rowIterator.next());
//        userImportInfo.setEnterpriseName(enterpriseName);

        List<String> headers = rowIterator.next();
        checkExcelFormat(headers);
        return rowList.subList(1, rowList.size());
    }

    private static void checkExcelFormat(List<String> headers) {
        headers.forEach((String n) -> {
            if (!ArrayUtils.contains(Constants.USER_IMPORT_EXCEL_HEADERS, n)) {
                throw new RuntimeException(Constants.EXCEL_FORMAT_ERROR);
            }
        });
    }

    private void importUsers(List<List<String>> userInfoList) {
        Iterator<List<String>> rowIterator = userInfoList.iterator();
        //遍历所有用户记录，进行导入
        while (rowIterator.hasNext()) {
            UserImportItem item = getUserImportItem(rowIterator);
            userService.importUsers(item);
        }
    }

    private UserImportItem getUserImportItem(Iterator<List<String>> rowIterator) {
        List<String> cells = rowIterator.next();
        Iterator<String> cellIt = cells.iterator();
        UserImportItem item = new UserImportItem();
        if (cellIt.hasNext()) {
            item.setName(cellIt.next());
        }
//        if (cellIt.hasNext()) {
//            item.setAge(cellIt.next());
//        }
        if (cellIt.hasNext()) {
            item.setEmail(cellIt.next());
        }
//        if (cellIt.hasNext()) {
//            item.setCertificateNo(cellIt.next());
//        }
//        if (cellIt.hasNext()) {
//            item.setDepartmentName(cellIt.next());
//        }
//        if (cellIt.hasNext()) {
//            item.setPosition(cellIt.next());
//        }
//        if (cellIt.hasNext()) {
//            item.setEmail(cellIt.next());
//        }
//        if (cellIt.hasNext()) {
//            item.setRoles(cellIt.next());
//        }
        return item;
    }
}
