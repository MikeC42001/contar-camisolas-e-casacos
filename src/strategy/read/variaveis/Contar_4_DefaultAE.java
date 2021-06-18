package strategy.read.variaveis;

import config.MyConfiguration;
import contar.app.facade.classes.Tabela;
import contar.app.tuplo.ImmutableTuple;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import strategy.adapter.excel.ExcelAdapter;
import strategy.read.IContar_N_VariaveisStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;


public class Contar_4_DefaultAE implements IContar_N_VariaveisStrategy {
    private String roupaBuffer;
    private String corBuffer;
    private String tamanhoBuffer;
    private String cursoBuffer;

    //private final static int START_N_CELL = 1;

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

            Row initialRow = itr.next(); // primeira linha

            itr.next(); // elimina a segunda linha, indice 2 no excel, se for tudo null

            boolean isFisrt = true;

            itr.forEachRemaining(row -> {

                setVariables(getStringCell(row, 6), getStringCell(row, 7), getStringCell(row, 8), getStringCell(row, 9));
                try {
                    adicionaRoupa(config, isFisrt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Joins in Threads went wrong");
                }
            });

            // HASHMAP PRINT in console sys.out.prtln TESTE
            config.getBaseDeDados().counterToStringSystemOutPut();

            System.out.println("Quantidade de peças de roupa contadas = " + config.getBaseDeDados().sumCounter());

            config.getBaseDeDados().sortTamanhosDeTabelas(); // sort tamanhos
            config.getBaseDeDados().sortListaDeCores(); // sort list cores
            config.getBaseDeDados().sortRoupaDeTabelas(); // sort list roupa

            // TABELAS PRINT in console sys.out.prtln TESTE
            config.getBaseDeDados().tabelasToStringSystemOutPut();

            // CORES PRINT in console sys.out.prtln TESTE
            config.getBaseDeDados().coresToStringSystemOutPut();

            wb.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("WTF ESTAS CONTAR MAL Contar_4_DefaultAE.java");

        }
    }

    private String getStringCell(Row row, int i) {
        return ((row.getCell(i).getCellType().equals(CellType.BLANK)) ? null : row.getCell(i).getStringCellValue()); //TODO Pôr em EXCEL ADAPTER
    }

    private void setVariables(String roupa, String cor, String tamanho, String curso) {
        this.roupaBuffer = roupa;
        this.corBuffer = cor;
        this.tamanhoBuffer = tamanho;
        this.cursoBuffer = curso;

        System.out.println(getRoupaBuffer() + " " + getCorBuffer() + " " + getTamanhoBuffer() + " " + getCursoBuffer());

    }

    private String getRoupaBuffer() {
        return roupaBuffer;
    }

    private String getCorBuffer() {
        return corBuffer;
    }

    private String getTamanhoBuffer() {
        return tamanhoBuffer;
    }

    private String getCursoBuffer() {
        return cursoBuffer;
    }

    private void adicionaRoupa(MyConfiguration config, boolean isFisrt) throws InterruptedException { // Todas as variáveis são utilizadas independentemente entre threads
        if ((getRoupaBuffer() != null && getCursoBuffer() != null && getTamanhoBuffer() != null && getCorBuffer() != null)) {

            // Adiciona tabela template
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    ImmutableTuple<String> roupaECor = new ImmutableTuple<>(new String[]{getRoupaBuffer(), getCorBuffer()});

                    Tabela table = config.getBaseDeDados().getTabela(roupaECor); // get table with current cor e roupa (adds table and color in function if they dont exist)
                    table.addTamanho(getTamanhoBuffer()); // add tamanho de não houver nesta tabela
                    table.addCurso(getCursoBuffer()); // add curso se não houver nesta tabela

                }
            };
            t1.start();

            if (isFisrt) {
                t1.join();
                isFisrt = false;
            }


            // Adiciona +1 na contagem
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    config.getBaseDeDados().addCountPlus1(new ImmutableTuple<>(new String[]{getRoupaBuffer(), getCorBuffer(), getTamanhoBuffer(), getCursoBuffer()}));
                }
            };

            t2.start();

            if (t1.isAlive()) {
                t1.join();
            }
            t2.join();
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

                ExcelAdapter.createLine(sheet, nRow++, 0, cor);   //linha 1 Cor

                for (String roupa : config.getBaseDeDados().getRoupas()) { // TODO config.getBaseDeDados().getRoupas()
                    Tabela tabela = config.getBaseDeDados().getTabela(new ImmutableTuple<>(new String[]{roupa, cor}));

                    if (config.getBaseDeDados().getRoupas().get(0).equals(roupa)) // Cor estar merge com o de cima
                        corColTo = tabela.getTamanhos().size() + 1;

                    roupaRowFromAndTo = nRow;
                    roupaColTo = tabela.getTamanhos().size();   // Roupa estar merge com os tamanhos

                    ExcelAdapter.createLine(sheet, nRow++, 0, roupa);  //linha 2 Roupa

                    ExcelAdapter.createLine(sheet, nRow++, tabela.getTamanhos());  //linha 3 tamanhos index + 1

                    for (String curso : tabela.getCursos()) { // Base com os números todos e cursos
                        ExcelAdapter.createLine(sheet, nRow++, 0, curso);       //linha(s) 4 Cursos, na Vertival

                        ExcelAdapter.createLineContagemDeCurso(config.getBaseDeDados().getCounter(), sheet.getRow(nRow-1), roupa, cor, curso, tabela.getTamanhos());    // Pôr os números nas tabelas

                    }

                    ExcelAdapter.createSumTotalLine(sheet, nRow++, tabela.getTamanhos().size(), tabela.getCursos().size()); // linha 5 As somas finais + TOTAL

                    sheet.addMergedRegion(new CellRangeAddress(roupaRowFromAndTo, roupaRowFromAndTo, 0, roupaColTo)); //int RoupaColFrom = 0;   //(rowFrom,rowTo,colFrom,colTo) // Roupa
                }

                ///TESTE DE COR

                Row rrrr = sheet.createRow(nRow++);

                XSSFCellStyle style = workbook.createCellStyle();
                style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
                /* int quantidadeColunas = sheet.getRow(0).getPhysicalNumberOfCells();
 for(int i = 0; i < quantidadeColunas; i++ ) {
      sheet.autoSizeColumn(i);
 }*/
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


}
