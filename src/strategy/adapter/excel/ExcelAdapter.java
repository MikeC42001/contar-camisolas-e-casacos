package strategy.adapter.excel;

import org.apache.poi.ss.usermodel.IndexedColors;
import strategy.IExcelAdapter;

import java.util.List;

public class ExcelAdapter implements IExcelAdapter<String> {

    @Override
    public String readCell(int row, int column) {
        return null;
    }

    @Override
    public String readRow(int row) {
        return null;
    }

    @Override
    public void writeCell(int row, int column, String input) {

    }

    @Override
    public void writeRow(int row, int startColumn, List<String> input) {

    }

    @Override
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
}
