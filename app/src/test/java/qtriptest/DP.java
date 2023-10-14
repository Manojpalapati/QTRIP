package qtriptest;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;


public class DP {

    @DataProvider(name = "TestData")
    public static Object[][] getTestData(Method m) {
        String Excelsheetname = m.getName();
        File f = new File("/home/crio-user/workspace/manojpalapati-ME_QTRIP_QA/app/src/test/resources/DatasetsforQTrip.xlsx");
        FileInputStream fis;
        Workbook wb;
        try {
            fis = new FileInputStream(f);
            wb = new XSSFWorkbook(fis);
            Sheet sheetname = wb.getSheet(Excelsheetname);

            int totalRows = sheetname.getLastRowNum();
            System.out.print(totalRows);
            Row rowcells = sheetname.getRow(0);
            int totalCols = rowcells.getLastCellNum();
            System.out.print(totalCols);

            DataFormatter dataFormatter = new DataFormatter();

            String getTestData[][] = new String[totalRows][totalCols - 1];
            for (int i = 1; i <= totalRows; i++) {
                for (int j = 1; j < totalCols; j++) {
                    getTestData[i - 1][j - 1] = dataFormatter.formatCellValue(sheetname.getRow(i).getCell(j));
                    System.out.print(getTestData[i - 1][j - 1]);
                }
            }
            return getTestData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
