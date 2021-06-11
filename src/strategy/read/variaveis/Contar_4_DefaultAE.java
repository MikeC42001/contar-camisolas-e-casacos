package strategy.read.variaveis;

import config.MyConfiguration;
import contar.app.facade.classes.Tabela;
import contar.app.tuplo.ImmutableTuple;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import strategy.read.IContar_N_VariaveisStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class Contar_4_DefaultAE implements IContar_N_VariaveisStrategy {
    private String roupaBuffer;
    private String corBuffer;
    private String tamanhoBuffer;
    private String cursoBuffer;

    private final static int START_N_CELL = 1;

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

            itr.next(); // elimina a segunda linha, indice 2 no excel, se for tudo null

            itr.forEachRemaining(row -> {

                setVariables(getStringCell(row, 6), getStringCell(row, 7), getStringCell(row, 8), getStringCell(row, 9));
                //try {
                adicionaRoupa(config);
                /*} catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Joins in Threads went wrong");
                }*/
            });

            // HASHMAP TOSTRING in console sys.out.prtln TESTE
            config.getBaseDeDados().counterToStringSystemOutPut();

            System.out.println("Quantidade de peças de roupa contadas = " + config.getBaseDeDados().sumCounter());

            config.getBaseDeDados().sortTamanhosDeTabelas(); // sort tamanhos
            config.getBaseDeDados().sortListaDeCores(); // sort list cores
            config.getBaseDeDados().sortRoupaDeTabelas(); // sort list roupa

            // TABELAS TOSTRING in console sys.out.prtln TESTE
            config.getBaseDeDados().tabelasToStringSystemOutPut();

            // CORES TOSTRING in console sys.out.prtln TESTE
            config.getBaseDeDados().coresToStringSystemOutPut();

            /*while(itr.hasNext()){
                Row row = itr.next();

                setVariables(row.getCell(6).getStringCellValue(), row.getCell(7).getStringCellValue(), row.getCell(8).getStringCellValue(), row.getCell(9).getStringCellValue());
                adicionaRoupa(config);
            }*/

            wb.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("WTF ESTAS CONTAR MAL Contar_4_DefaultAE.java");

        }
    }

    private String getStringCell(Row row, int i) {
        return ((row.getCell(i).getCellType().equals(CellType.BLANK)) ? null : row.getCell(i).getStringCellValue());
    }

    private void setVariables(String roupa, String cor, String tamanho, String curso) {
        this.roupaBuffer = roupa;
        this.corBuffer = cor;
        this.tamanhoBuffer = tamanho;
        this.cursoBuffer = curso;

        System.out.println(getRoupaBuffer() + " " + getCorBuffer() + " " + getTamanhoBuffer() + " " + getCursoBuffer());

    }

    public String getRoupaBuffer() {
        return roupaBuffer;
    }

    public String getCorBuffer() {
        return corBuffer;
    }

    public String getTamanhoBuffer() {
        return tamanhoBuffer;
    }

    public String getCursoBuffer() {
        return cursoBuffer;
    }

    private void adicionaRoupa(MyConfiguration config) /*throws InterruptedException*/ { // Todas as variáveis são utilizadas independentemente entre threads
        if ((getRoupaBuffer() != null && getCursoBuffer() != null && getTamanhoBuffer() != null && getCorBuffer() != null)) {

            // Adiciona tabela template
            /*Thread t1 = new Thread() {
                public void run() {*/
            ImmutableTuple<String> roupaECor = new ImmutableTuple<>(new String[]{getRoupaBuffer(), getCorBuffer()});

            Tabela table = config.getBaseDeDados().getTabela(roupaECor); // get table with current cor e roupa (adds table and color in function if they dont exist)
            table.addTamanho(getTamanhoBuffer()); // add tamanho de não houver nesta tabela
            table.addCurso(getCursoBuffer()); // add curso se não houver nesta tabela

           /*     }
            };*/

            // Adiciona +1 na contagem
            /*Thread t2 = new Thread() {
                public void run() {*/
            config.getBaseDeDados().addCountPlus1(new ImmutableTuple<>(new String[]{getRoupaBuffer(), getCorBuffer(), getTamanhoBuffer(), getCursoBuffer()}));
                /*}
            };

            t1.start();
            t2.start();

            t2.join();
            t1.join();*/
            // TODO ver se dá para alterar para a forma anterior, sem fzr join
        }
    }

    @Override
    public void construirTemplate(MyConfiguration config, String writeFileName) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Contagem");

            int nRow = 0;

            for (String cor : config.getBaseDeDados().getCores()) {

                int corRowFromAndTo = nRow;
                int corColTo = 0;

                int roupaRowFromAndTo = 0;
                int roupaColTo = 0;

                createLine(sheet, nRow++, 0, cor);   //linha 1 Cor

                for (String roupa : config.getBaseDeDados().getRoupas()) { // TODO config.getBaseDeDados().getRoupas()
                    Tabela tabela = config.getBaseDeDados().getTabela(new ImmutableTuple<>(new String[]{roupa, cor}));

                    if (config.getBaseDeDados().getRoupas().get(0).equals(roupa)) // Cor estar merge com o de cima
                        corColTo = tabela.getTamanhos().size() + 1;

                    roupaRowFromAndTo = nRow;
                    roupaColTo = tabela.getTamanhos().size();   // Roupa estar merge com os tamanhos

                    createLine(sheet, nRow++, 0, roupa);  //linha 2 Roupa

                    createLine(sheet, nRow++, tabela.getTamanhos());  //linha 3 tamanhos index + 1

                    for (String curso : tabela.getCursos()) { // Base com os números todos e cursos
                        Row row = sheet.createRow(nRow++);
                        createCellString(row, 0, curso);        //linha(s) 4 Cursos, na Vertival

                        createLineContagemDeCurso(config, row, roupa, cor, curso, tabela.getTamanhos());

                    }

                    createSumTotalLine(sheet, nRow++, tabela.getTamanhos().size(), tabela.getCursos().size()); // linha 5 As somas finais + TOTAL

                    sheet.addMergedRegion(new CellRangeAddress(roupaRowFromAndTo, roupaRowFromAndTo, 0, roupaColTo)); //int RoupaColFrom = 0;   //(rowFrom,rowTo,colFrom,colTo) // Roupa
                }

                ///TESTE DE COR

                Row rrrr = sheet.createRow(nRow++);

                XSSFCellStyle style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                //rrrr.getCell(0).setCellStyle(style);

                for (int i = 0; i < 8; i++) {
                    rrrr.createCell(i).setCellStyle(style);
                }

                //rrrr.forEach(cell -> cell.setCellStyle(style));

                sheet.addMergedRegion(new CellRangeAddress(corRowFromAndTo, corRowFromAndTo, 0, corColTo)); //int CorColFrom = 0;   //(rowFrom,rowTo,colFrom,colTo) // Cor
            }


            try (FileOutputStream outputStream = new FileOutputStream(writeFileName)) {
                workbook.write(outputStream);
            }
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MERDA ESTAS CRIAR MAL A TEMPLATE Contar_4_DefaultAE.java");
        }
    }

    private void createSumTotalLine(XSSFSheet sheet, int nRow, int sizeHorizontal, int sizeVertical) {
        Row row = sheet.createRow(nRow);
        createCellString(row, 0, "TOTAL");
        for (int i = START_N_CELL; i <= sizeHorizontal + 1; i++) {
            createVertivalSumEquationCell(row, i, nRow, sizeVertical);
        }

        createHorizontalSumEquationCell(row, nRow, sizeHorizontal);

    }

    private void createHorizontalSumEquationCell(Row row, int nRow, int sizeHorizontal) {
        int sumColumn = (sizeHorizontal + 1);

        Cell cell = row.createCell(sumColumn);

        String columnStart = getLetterColumn(2);
        String columnEnd = getLetterColumn(sumColumn);

        String range = columnStart + (nRow + 1) + ":" + columnEnd + (nRow + 1);
        cell.setCellFormula("SUM(" + range + ")");
    }

    private void createVertivalSumEquationCell(Row row, int nColumn, int nRow, int sizeVertical) {
        Cell cell = row.createCell(nColumn);
        String column = getLetterColumn(++nColumn);
        String range = column + (nRow - sizeVertical) + ":" + column + nRow;
        cell.setCellFormula("SUM(" + range + ")");
    }

    private String getLetterColumn(int nColumn) {
        String name = "";
        while (nColumn > 0) {
            nColumn--;
            name = (char) ('A' + nColumn % 26) + name;
            nColumn /= 26;
        }
        return name;
    }

    private void createLineContagemDeCurso(MyConfiguration config, Row row, String roupa, String cor, String curso, List<String> tamanhos) {

        HashMap<ImmutableTuple<String>, Integer> counterData = config.getBaseDeDados().getCounter();

        for (int i = START_N_CELL; i <= tamanhos.size(); i++) {
            ImmutableTuple<String> RoupaCorTamanhoCursoBuff = new ImmutableTuple<>(new String[]{roupa, cor, tamanhos.get(i - 1), curso});

            if (counterData.containsKey(RoupaCorTamanhoCursoBuff)) {
                createCellInt(row, i, counterData.get(RoupaCorTamanhoCursoBuff));
            }
        }
    }

    private void createCellInt(Row row, int nColumn, Integer integer) {
        Cell cell = row.createCell(nColumn);
        cell.setCellValue(integer);             // CRIA MESMO A CÉLULA!
    }

    private void createLine(XSSFSheet sheet, int nRow, int nColumn, String value) {
        Row row = sheet.createRow(nRow);
        createCellString(row, nColumn, value);
    }

    private void createLine(XSSFSheet sheet, int nRow, List<String> values) {
        Row row = sheet.createRow(nRow);
        for (int i = START_N_CELL; i <= values.size(); i++) {
            createCellString(row, i, values.get(i - 1));
        }
    }

    private void createCellString(Row row, int nColumn, String value) {
        Cell cell = row.createCell(nColumn);
        cell.setCellValue(value);       // CRIA MESMO A CÉLULA!
    }
}
