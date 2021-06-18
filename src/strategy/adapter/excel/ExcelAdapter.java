package strategy.adapter.excel;

import contar.app.tuplo.ImmutableTuple;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.List;

public class ExcelAdapter /*implements IExcelAdapter<String>*/ {

    private final static int START_N_CELL = 1;

    public void setStyle() {

    }

    private XSSFCellStyle createStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();

        style.setAlignment(HorizontalAlignment.CENTER);

        return style;
    }

    public void setCellColor(int row, int column/*, IndexedColors color*/) {
        /*XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        row.getCell(0).setCellStyle(style);*/
    }

    public static short getExcelCorPrincipal(String cor) {
        switch (cor) {

            case "Azul Bebé Claro":
                return IndexedColors.BLUE1.getIndex();

            case "Azul Marinho":
                return IndexedColors.BLUE_GREY.getIndex();

            case "Bordeaux":
                return IndexedColors.RED.getIndex();

            case "Cinza Mescla":
                return IndexedColors.GREY_25_PERCENT.getIndex();

            case "Preto":
                return IndexedColors.BLACK.getIndex();

            case "Rosa Bebé":
                return IndexedColors.ROSE.getIndex();

            case "Verde Garrafa":
                return IndexedColors.GREEN.getIndex();

            default:
                return IndexedColors.BLACK1.getIndex();
        }
    }

    private static String getLetterColumn(int nColumn) {
        String name = "";
        while (nColumn > 0) {
            nColumn--;
            name = (char) ('A' + nColumn % 26) + name;
            nColumn /= 26;
        }
        return name;
    }

    public static void createSumTotalLine(XSSFSheet sheet, int nRow, int sizeHorizontal, int sizeVertical) {
        Row row = sheet.createRow(nRow);
        createCellString(row, 0, "TOTAL");
        for (int i = START_N_CELL; i <= sizeHorizontal + 1; i++) {
            createVertivalSumEquationCell(row, i, nRow, sizeVertical);
        }

        createHorizontalSumEquationCell(row, nRow, sizeHorizontal);

    }

    private static void createHorizontalSumEquationCell(Row row, int nRow, int sizeHorizontal) {
        int sumColumn = (sizeHorizontal + 1);

        Cell cell = row.createCell(sumColumn);

        String columnStart = getLetterColumn(2);
        String columnEnd = getLetterColumn(sumColumn);

        String range = columnStart + (nRow + 1) + ":" + columnEnd + (nRow + 1);
        cell.setCellFormula("SUM(" + range + ")");
    }

    private static void createVertivalSumEquationCell(Row row, int nColumn, int nRow, int sizeVertical) {
        Cell cell = row.createCell(nColumn);
        String column = getLetterColumn(++nColumn);
        String range = column + (nRow - sizeVertical) + ":" + column + nRow;
        cell.setCellFormula("SUM(" + range + ")");
    }

    public static void createLineContagemDeCurso(HashMap<ImmutableTuple<String>, Integer> counterData, Row row, String roupa, String cor, String curso, List<String> tamanhos) {

        for (int i = START_N_CELL; i <= tamanhos.size(); i++) {
            ImmutableTuple<String> roupaCorTamanhoCursoBuff = new ImmutableTuple<>(new String[]{roupa, cor, tamanhos.get(i - 1), curso});

            if (counterData.containsKey(roupaCorTamanhoCursoBuff)) {
                createCellInt(row, i, counterData.get(roupaCorTamanhoCursoBuff));
            }
        }
    }

    public static void createLine(XSSFSheet sheet, int nRow, int nColumn, String value) {
        Row row = sheet.createRow(nRow);
        createCellString(row, nColumn, value);
    }

    public static void createLine(XSSFSheet sheet, int nRow, List<String> values) {
        Row row = sheet.createRow(nRow);
        for (int i = START_N_CELL; i <= values.size(); i++) {
            createCellString(row, i, values.get(i - 1));
        }
    }

    private static void createCellInt(Row row, int nColumn, Integer integer) {
        Cell cell = row.createCell(nColumn);
        cell.setCellValue(integer);             // CRIA MESMO A CÉLULA!
    }

    private static void createCellString(Row row, int nColumn, String value) {
        Cell cell = row.createCell(nColumn);
        cell.setCellValue(value);       // CRIA MESMO A CÉLULA!
    }
}
