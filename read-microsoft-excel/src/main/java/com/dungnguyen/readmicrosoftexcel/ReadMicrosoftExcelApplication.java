package com.dungnguyen.readmicrosoftexcel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ReadMicrosoftExcelApplication implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(ReadMicrosoftExcelApplication.class);

  public static void main(String[] args) {
    logger.info("STARTING APPLICATION");
    SpringApplication.run(ReadMicrosoftExcelApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    logger.info("STARTING COMMAND LINE RUNNER");
    ExcelService excelService = new ExcelService();
    Map<Long, People> data = excelService.readAllPeopleData();
    for (Long key : data.keySet()) {
      logger.info("DATA ", data.get(key).toString());
    }

    List<People> dataInsert = new ArrayList<>(data.values());
    String sheetName = "people_" + Instant.now().getEpochSecond();
    excelService.createSheetAndSaveData("output.xlsx", sheetName, dataInsert);
  }
}
