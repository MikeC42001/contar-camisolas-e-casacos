package strategy.adapter.excel;

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
}
