package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// import controls.db.dbActions;

public class TablePanel extends JPanel{
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    public TablePanel(String[] header, String[][] contents){
        model = new DefaultTableModel(contents, header);
        JTable table = new JTable(model);
        scrollPane = new JScrollPane(table);
        // table.setModel(model);
        // for (String head : header) {
        //     model.addColumn(head);
        // }
        // for (String[] content : contents) {
        //     model.addRow(content);
        // }
        this.add(scrollPane);
    }

    public static void main(String[] args) {
        String[] header = {
            "측정일시","측정소명","이산화질소농도(ppm)","오존농도(ppm)","이산화탄소농도(ppm)","아황산가스(ppm)","미세먼지(㎍/㎥)","초미세먼지(㎍/㎥)"
        };
        String[][] contents = {
            {"20180101","강남구","0.033","0.01","0.6","0.006","34","22"},
            {"20180101","강남대로","0.04","0.007","0.8","0.006","","17"},
            {"20180101","강동구","0.038","0.01","0.7","0.005","48","24"}
        };
        JFrame frame = new JFrame("TEST");
        frame.add(new TablePanel(header, contents));
        frame.pack();
        frame.setVisible(true);
    }
}