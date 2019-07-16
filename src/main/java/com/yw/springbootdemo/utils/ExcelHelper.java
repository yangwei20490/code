package com.yw.springbootdemo.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangwei
 * @date 2019/6/4 17:10
 */
public final class ExcelHelper {

    private ExcelHelper() {
    }

    public static List<List<String>> readExcelToList(InputStream inputStream, int startRow,
                                                     int... excludeCols) {
        try (Workbook wb = WorkbookFactory.create(inputStream)) {
            return readExcel(wb, startRow, excludeCols);
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<List<String>> readExcel(Workbook wb, int startRow, int[] excludeCols) {
        List<List<String>> rowList = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        int rows = 0;
        for (int i = firstRowNum; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (rows++ < startRow) {
                continue;
            }
            //int firstCellNum = row.getFirstCellNum();
            int firstCellNum = 0;
            int lastCellNum = row.getLastCellNum();
            List<String> values = new ArrayList<>();
            int cols = 0;
            int blankCount = 0;
            for (int j = firstCellNum; j < lastCellNum; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (ArrayUtils.contains(excludeCols, cols++)) {
                    continue;
                }
                String value = getCellValue(cell);
                if (StringUtils.isBlank(value)) {
                    blankCount++;
                }
                values.add(value);
            }
            if (blankCount < values.size()) {
                rowList.add(values);
            }
        }
        return rowList;
    }

    private static String getCellValue(Cell cell) {
        String value = "";
        if (cell == null) {
            return value;
        }
        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getRichStringCellValue().getString().trim();
                break;
            case NUMERIC:
                value = readNumberValue(cell);
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case BLANK:
            case ERROR:
                break;
            default:
        }
        if (value != null) {
            value = value.trim();
        }
        return value;
    }

    private static String readNumberValue(Cell cell) {
        if (cell.getCellTypeEnum() != CellType.NUMERIC) {
            throw new IllegalArgumentException("Not number type of the cell value");
        }
        String value;
        if (DateUtil.isCellDateFormatted(cell)) {
            Date dateValue = cell.getDateCellValue();
            String dateFmt = cell.getCellStyle().getDataFormatString();
            value = new CellDateFormatter(dateFmt).format(dateValue);
        } else {
            double doubValue = cell.getNumericCellValue();
            value = Double.toString(doubValue);
            String cellValueInString = BigDecimal.valueOf(doubValue).toPlainString();
            String longString = Long.toString((long) doubValue);
            if (cellValueInString.equals(longString)) {
                value = longString;
            }
        }
        return parseDecimalString(value);
    }

    private static String parseDecimalString(String num) {
        if (num == null) {
            return num;
        }
        if (num.endsWith(".0")) {
            int lasDotPos = num.lastIndexOf('.');
            return num.substring(0, lasDotPos);
        }
        return num;
    }
}
