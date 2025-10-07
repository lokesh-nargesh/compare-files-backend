package com.compare_file.service;

import com.compare_file.model.Difference;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelCompareService {

    public List<Difference> compare(MultipartFile file1, MultipartFile file2) {
        List<Difference> differences = new ArrayList<>();

        try (InputStream is1 = file1.getInputStream();
             InputStream is2 = file2.getInputStream();
             Workbook wb1 = WorkbookFactory.create(is1);
             Workbook wb2 = WorkbookFactory.create(is2)) {

            int sheetCount = Math.min(wb1.getNumberOfSheets(), wb2.getNumberOfSheets());

            for (int i = 0; i < sheetCount; i++) {
                Sheet sheet1 = wb1.getSheetAt(i);
                Sheet sheet2 = wb2.getSheetAt(i);
                String sheetName = sheet1.getSheetName();

                Iterator<Row> it1 = sheet1.iterator();
                Iterator<Row> it2 = sheet2.iterator();

                int rowNum = 0;
                while (it1.hasNext() && it2.hasNext()) {
                    Row row1 = it1.next();
                    Row row2 = it2.next();
                    rowNum++;

                    int maxCell = Math.max(row1.getLastCellNum(), row2.getLastCellNum());
                    for (int c = 0; c < maxCell; c++) {
                        Cell cell1 = row1.getCell(c);
                        Cell cell2 = row2.getCell(c);

                        String val1 = getCellValue(cell1);
                        String val2 = getCellValue(cell2);

                        if (!val1.equals(val2)) {
                            differences.add(new Difference(sheetName, rowNum, c + 1, val1, val2));
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return differences;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }
}


