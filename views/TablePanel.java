package views;

import java.awt.Dimension;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends JScrollPane{
    private JTable table;
    private DefaultTableModel model;

    public TablePanel(){
        table = new JTable();
        this.setViewportView(table);
    }

    public void setWholeSize(Dimension d){
        this.setSize(d);
        this.setPreferredSize(d);
    }

    public void setData(List<String> header, List<List<String>> contents){
        model = new DefaultTableModel();
        table.setModel(model);
        if(header == null){
            System.out.println("header NULL");
            return;
        }
        if(contents == null){
            System.out.println("contents NULL");
            return;
        }
        for(int i=0;i<header.size();i++){
            model.addColumn(header.get(i));
        }
        for (List<String> content : contents) {
            String[] strings = new String[header.size()];
            for(int i=0;i<strings.length;i++){
                if(i >= content.size()){
                    strings[i] = "";
                }else{
                    strings[i] = content.get(i);
                }
            }
            model.addRow(strings);
        }
    }

    public List<List<String>> getData(){
        List<List<String>> stringLists = new ArrayList<List<String>>();

        return stringLists;
    }
}