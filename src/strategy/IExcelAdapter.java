package strategy;

import java.util.List;

public interface IExcelAdapter<T> {

    public T readCell(int row, int column);

    public T readRow(int row);

    public void writeCell(int row, int column, T input); //TODO add color

    public void writeRow(int row, int startColumn, List<T> input); //TODO add color

    public void setCellColor(int row, int column/*, IndexedColors color*/);
}
