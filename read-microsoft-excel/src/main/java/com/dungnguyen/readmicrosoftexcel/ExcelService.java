package com.dungnguyen.readmicrosoftexcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ExcelService {
  @Value("${app-config.excel-path}")
  private String fileLocation = "G:\\JAVA\\TO_DO_PROJECT\\read-microsoft-excel\\file\\";

  public Map<Long, People> readAllPeopleData() throws IOException {
    FileInputStream file = new FileInputStream(fileLocation + "demo.xlsx");
    Workbook workbook = new XSSFWorkbook(file);

    // read from first sheet
    Sheet sheet = workbook.getSheetAt(0);

    Map<Long, People> data = new HashMap<>();
    for (Row row : sheet) {
      People people = new People();
      people.setId((long) row.getCell(0).getNumericCellValue());
      people.setName(row.getCell(1).getStringCellValue());
      people.setAge((int) row.getCell(2).getNumericCellValue());
      people.setAddress(row.getCell(3).getStringCellValue());
      people.setDegree(Degree.getDegreeFromString(row.getCell(4).getStringCellValue()));

      data.put(people.getId(), people);
    }
    return data;
  }



  public void createSheetAndSaveData(String fileName, String sheetName, List<People> data)
      throws IOException {

    XSSFWorkbook workbook = new XSSFWorkbook();

    Sheet sheet = workbook.createSheet(sheetName);
    sheet.setColumnWidth(0, 6000);
    sheet.setColumnWidth(1, 4000);

    // create header for the sheet
    Row header = sheet.createRow(0);

    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    XSSFFont font = workbook.createFont();
    font.setFontName("Arial");
    font.setFontHeightInPoints((short) 16);
    font.setBold(true);
    headerStyle.setFont(font);

    Cell headerCell = header.createCell(0);
    headerCell.setCellValue("STT");
    headerCell.setCellStyle(headerStyle);

    headerCell = header.createCell(1);
    headerCell.setCellValue("Name");
    headerCell.setCellStyle(headerStyle);

    CellStyle style = workbook.createCellStyle();
    style.setWrapText(true);

    // insert data to the sheet
    AtomicLong rowIndex = new AtomicLong(2);
    data.forEach(
        people -> {
          Row row = sheet.createRow((int) rowIndex.getAndIncrement());

          Cell cell = row.createCell(0);
          cell.setCellValue(people.getId());
          cell.setCellStyle(style);

          cell = row.createCell(1);
          cell.setCellValue(people.getName());
          cell.setCellStyle(style);
        });

    // write content to excel file


    FileOutputStream outputStream = new FileOutputStream(fileLocation + fileName);
    workbook.write(outputStream);
    workbook.close();

  }
}
