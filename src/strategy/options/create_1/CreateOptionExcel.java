package strategy.options.create_1;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import strategy.options.CreateOptionAbstract;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class CreateOptionExcel extends CreateOptionAbstract {
    @Override
    public void doAction(int nVariable, String readFileName, String writeFileName) {
        try {
            //File file = new File("C:\\demo\\employee.xlsx");   //creating a new file instance

            //obtaining bytes from the file
            FileInputStream fis = new FileInputStream(new File("/home/miguel-linux/Desktop/MiguelL/My_Own_PlusProj/ExcelFiles/Encomenda 20_21 (Respostas).xlsx"));

            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file


        } catch (Exception e) {

        }
    }
}
