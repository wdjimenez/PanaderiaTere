/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import pos.model.ProdInactive;

/**
 *
 * @author wdjimenez
 */
public class ProdInactiveTableModel extends AbstractTableModel {

    private final List<ProdInactive> productoList;

    private final String[] columnNames = new String[]{
        "Id", "Nombre", "Inactivo"
    };
    private final Class[] columnClass = new Class[]{
        Integer.class, String.class, Boolean.class
    };

    public ProdInactiveTableModel(List<ProdInactive> pList) {
        this.productoList = pList;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return productoList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdInactive row = productoList.get(rowIndex);
        if (0 == columnIndex) {
            return row.getId();
        } else if (1 == columnIndex) {
            return row.getNombre();
        } else if (2 == columnIndex) {
            return row.getInactivo();
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            return true;
        }

        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ProdInactive row = productoList.get(rowIndex);
        if (2 == columnIndex) {
            row.setInactivo((Boolean) aValue);
        }
    }
}
