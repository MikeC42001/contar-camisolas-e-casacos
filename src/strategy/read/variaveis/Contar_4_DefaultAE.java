package strategy.read.variaveis;

import config.MyConfiguration;
import contar.app.facade.classes.Tabela;
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
    public void contarVariaveis(MyConfiguration config, String readFileName) {

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

            itr.next();

            itr.forEachRemaining(row -> {

                setVariables(row.getCell(6).getStringCellValue(), row.getCell(7).getStringCellValue(), row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue());
                adicionaRoupa(config);
            });

            /*while(itr.hasNext()){
                Row row = itr.next();

                setVariables(row.getCell(6).getStringCellValue(), row.getCell(7).getStringCellValue(), row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue());
                adicionaRoupa(config);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("WTF ESTAS CONTAR MAL Contar_4_DefaultAE.java");

        }
    }

    private void setVariables(String roupa, String cor, String tamanho, String curso) {
        this.roupa = roupa;
        this.cor = cor;
        this.tamanho = tamanho;
        this.curso = curso;

        System.out.println(getRoupa() + " " + getCor() + " " + getTamanho() + " " + getCurso());

    }

    public String getRoupa() {
        return roupa;
    }

    public String getCor() {
        return cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getCurso() {
        return curso;
    }

    private void adicionaRoupa(MyConfiguration config) { // Todas as variáveis são utilizadas independentemente entre threads
        if ((getRoupa() != null && getCurso() != null && getTamanho() != null && getCor() != null)) {

            // Adiciona tabela template
                new Thread(() -> {
                    String[] roupaECor = new String[]{getRoupa(), getCor()};

                    Tabela table = config.getBaseDeDados().getTabela(roupaECor); // get table with current cor e roupa (adds table and color in function if they dont exist)
                    table.addTamanho(getTamanho()); // add tamanho de não houver nesta tabela
                    table.addCurso(getCurso()); // add curso se não houver nesta tabela

                }).start();

            // Adiciona +1 na contagem
            new Thread(() -> config.getBaseDeDados().addCountPlus1(new String[]{getRoupa(), getCor(), getTamanho(), getCurso()})).start();

        }
    }

    @Override
    public void construirTemplate(MyConfiguration config, String writeFileName) {

    }
}
