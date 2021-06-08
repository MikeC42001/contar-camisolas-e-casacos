package strategy.options.create_1;

import config.MyConfiguration;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import strategy.options.CreateOptionAbstract;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class CreateOptionExcel extends CreateOptionAbstract {
    @Override
    public void doAction(MyConfiguration config, String readFileName, String writeFileName) {
        try {//TODO

            config.getContarNStrategy().contarVariaveis(readFileName);


        } catch (Exception e) {

        }
    }
}
