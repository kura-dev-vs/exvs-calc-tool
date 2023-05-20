package com.example.demo;

import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
// excelを読み込むのに必要なクラス
final public class CellUtils {
    public static String getCellValue(Cell cell) {
        Objects.requireNonNull(cell, "cell is null");
        Object obj;
        CellType cellType = cell.getCellType();
        if (cellType == CellType.BLANK) {
            //cell.setCellValue(" ");
            return " ";
        } else if (cellType == CellType.BOOLEAN) {
            //System.out.println("booloea");
            //obj = cell.getBooleanCellValue();
            return "";
        } else if (cellType == CellType.ERROR) {
            throw new RuntimeException("Error cell is unsupported");
        } else if (cellType == CellType.FORMULA) {
            throw new RuntimeException("Formula cell is unsupported");
        } else if (cellType == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                obj = cell.getDateCellValue();
                return obj.toString();
            } else {
                obj = cell.getNumericCellValue();
                return obj.toString();
            }
        } else if (cellType == CellType.STRING) {
            obj = cell.getStringCellValue();
            return obj.toString();
        } else {
            throw new RuntimeException("Unknow type cell");
        }
    }
}
