package com.compare_file.model;

public class Difference {
    private String sheetName;
    private int row;
    private int column;
    private String value1;
    private String value2;

    public Difference(String sheetName, int row, int column, String value1, String value2) {
        this.sheetName = sheetName;
        this.row = row;
        this.column = column;
        this.value1 = value1;
        this.value2 = value2;
    }

    public String getSheetName() { return sheetName; }
    public int getRow() { return row; }
    public int getColumn() { return column; }
    public String getValue1() { return value1; }
    public String getValue2() { return value2; }

}
