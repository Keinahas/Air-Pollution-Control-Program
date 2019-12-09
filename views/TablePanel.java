package views;

import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.DefaultRowSorter;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
// import org.w3c.dom.events.MouseEvent;

public class TablePanel extends JScrollPane {
    protected int sortCol = 0;
    protected boolean isSortAsc = true;
    protected int m_result = 0;
    protected int columnsCount = 1;
    Vector vector = new Vector();
    private JTable table;
    private DefaultTableModel model;
    

    public TablePanel() {

        table = new JTable();
        table.setAutoCreateRowSorter(true);
        this.setViewportView(table);
        
    }

    public void setWholeSize(Dimension d) {
        this.setSize(d);
        this.setPreferredSize(d);
    }

    public void setData(List<String> header, List<List<String>> contents) {
        model = new DefaultTableModel();
        table.setModel(model);
        if (header == null) {
            System.out.println("header NULL");
            return;
        }
        if (contents == null) {
            System.out.println("contents NULL");
            return;
        }
        for (int i = 0; i < header.size(); i++) {
            model.addColumn(header.get(i));
        }

        for (List<String> content : contents) {
            String[] strings = new String[header.size()];
            for (int i = 0; i < strings.length; i++) {
                if (i >= content.size()) {
                    strings[i] = "";
                } else {
                    strings[i] = content.get(i);
                }
            }
            model.addRow(strings);
        }

        // table.getTableHeader().addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         int col = table.columnAtPoint(e.getPoint());
        //         String name = table.getColumnName(col);
        //         System.out.println("Column index selected " + col + " " + name);

        //     }
        // });
    }
}