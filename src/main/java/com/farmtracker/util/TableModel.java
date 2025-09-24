package com.farmtracker.util;

import com.farmtracker.tracker.FarmTrackerData;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class TableModel extends AbstractTableModel {
    private final List<FarmTrackerData> data; // Example data storage
    private final String[] columnNames; // Example column names

    public TableModel(List<FarmTrackerData> data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }
    public TableModel(String[] columnNames) {
        this.data = new ArrayList<>();
        this.columnNames = columnNames;
    }

    public void addRow(FarmTrackerData data) {
        this.data.add(data);
    }
    private String getFormatInt(int number) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        return nf.format(number);
    }
    private int getUnformatInt(String numberStr) {
        String numberReplace = numberStr.replaceAll(",", "");
        return Integer.parseInt(numberReplace);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FarmTrackerData obj = data.get(rowIndex);
        switch (columnIndex) {
            case 0: return obj.getRunNumber();
            case 1: return obj.getItemName();
            case 2: return obj.getQuantity();
            case 3: return getFormatInt(obj.getPrice());
            case 4: return getFormatInt(obj.getTotal());
            case 5: return obj.getDiseased();
            case 6: return obj.getDead();
            case 7: return obj.getTime();
            case 8: return obj.getDate();
            case 9: return obj.getSeedsUsed();
            case 10: return obj.getGESeedsNumber();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 4;
    }
    //String[] Columns = {"Run #", "Item", "Quantity", "Price", "Total", "Diseased", "Dead", "Time", "Date", "Used", "Seed #"};

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        FarmTrackerData obj = data.get(rowIndex);
        switch (columnIndex) {
            case 0: obj.setRunNumber((Integer) aValue); break;
            case 1: obj.setItemName((String) aValue); break;
            case 2: obj.setQuantity((Integer) aValue); break;
            case 3: obj.setPrice(getUnformatInt((String) aValue)); break;
            case 4: break;
            case 5: obj.setDiseased((Integer) aValue); break;
            case 6: obj.setDead((Integer) aValue); break;
            case 7: obj.setTime((String) aValue); break;
            case 8: obj.setDate((String) aValue); break;
            case 9: obj.setSeedsUsed((Integer) aValue); break;
            case 10: obj.setGESeedsNumber((Integer) aValue); break;
        }
        fireTableCellUpdated(rowIndex, columnIndex); // Notify JTable of the change
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
}
