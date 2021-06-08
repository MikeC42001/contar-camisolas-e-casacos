package strategy.read.variaveis;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import strategy.read.IContar_N_VariaveisStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class Contar_4_DefaultAE implements IContar_N_VariaveisStrategy {
    private String roupa;
    private String cor;
    private String tamanho;
    private String curso;

    @Override
    public void contarVariaveis(String readFileName) {

        // ONDE ESTÂO AS VARIAVEIS!
        // 6-Hoodie/Casaco
        // 7-Cor
        // 8-Tamanho
        // 9-Curso

        try {
            FileInputStream fis = new FileInputStream(new File(readFileName));

            XSSFWorkbook wb = new XSSFWorkbook(fis); // Fui buscar o excel
            XSSFSheet sheet = wb.getSheetAt(0);    //fui buscar  a primeira folha
            Iterator<Row> itr = sheet.iterator();    // tenho um iterador

            Row initialRow = itr.next();

            itr.forEachRemaining(row -> {
                setVariables(row.getCell(6).getStringCellValue(), row.getCell(7).getStringCellValue(), row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue());
                adicionaRoupa();
            });

        } catch (Exception e) {

        }
    }

    private void setVariables(String roupa, String cor, String tamanho, String curso){
        this.roupa = roupa;
        this.cor = cor;
        this.tamanho = tamanho;
        this.curso = curso;

    }

    private void adicionaRoupa() {

        new Thread(() -> {
            config

        }).start();

        new Thread(() -> {

        }).start();
    }

    @Override
    public void construirTemplate() {

    }
}
