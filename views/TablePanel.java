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
        // table.getTableHeader().addMouseListener(new MouseListener(){
        //     public void mouseClicked(MouseEvent arg0) {

        //     }           
        //     public void mouseEntered(MouseEvent arg0) {         
        //     }           
        //     public void mouseExited(MouseEvent arg0) {              
        //     }
        //     public void mousePressed(MouseEvent arg0) {             
        //     }
        //     public void mouseReleased(MouseEvent arg0) {
        //     }           
        // });

        // table.getTableHeader().addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent mouseEvent) {
        //       int index = convertColumnIndexToModel(columnAtPoint(mouseEvent.getPoint()));
        //       if (index >= 0) {
        //         System.out.println("Clicked on column " + index);
        //       }
        //     };
        //   });
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                TableColumnModel colModel = table.getColumnModel();
                int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
                int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

                if (modelIndex < 0)
                    return;
                if (sortCol == modelIndex)
                    isSortAsc = !isSortAsc;
                else
                    sortCol = modelIndex;

                 for (int i = 0; i < columnsCount; i++) { 
                    TableColumn column = colModel.getColumn(i);
                    column.setHeaderValue(table.getColumnName(column.getModelIndex()));
                }
                table.getTableHeader().repaint();

                Collections.sort(vector,new MyComparator(isSortAsc));
                table.tableChanged(new TableModelEvent(table.getModel()));
                table.repaint();
            }
        });
    }
    class MyComparator implements Comparator {
        protected boolean isSortAsc;
      
        public MyComparator( boolean sortAsc) {
          isSortAsc = sortAsc;
        }
      
        public int compare(Object o1, Object o2) {
          if (!(o1 instanceof Integer) || !(o2 instanceof Integer))
            return 0;
          Integer s1 = (Integer) o1;
          Integer s2 = (Integer) o2;
          int result = 0;
          result = s1.compareTo(s2);
          if (!isSortAsc)
            result = -result;
          return result;
        }
      
        public boolean equals(Object obj) {
          if (obj instanceof MyComparator) {
            MyComparator compObj = (MyComparator) obj;
            return compObj.isSortAsc == isSortAsc;
          }
          return false;
        }
    }
    public List<List<String>> getData(){
        List<List<String>> stringLists = new ArrayList<List<String>>();

        return stringLists;
    }
}