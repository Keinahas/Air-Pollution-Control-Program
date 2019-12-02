package controls.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controls.CTRL;
import controls.db.dbActions;


// class for saving data to csv file || yet coding
public class opts implements ActionListener{
    private dbActions db;
    private List<String> dates;
    private List<String> locs;
    private List<String> cols;

    public opts(dbActions db){
        this.db = db;
        dates = new ArrayList<String>();
        locs = new ArrayList<String>();
        cols = new ArrayList<String>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        String str = e.getActionCommand();
        if(str.endsWith("월")){
            dates.add("2018"+str.replace('월', '*'));
        }
        else if(CTRL.getHeader().contains(CTRL.parse(str))){
            cols.add(str);
        }
        else if(str != "옵션"){
            locs.add(CTRL.parse(str));
        }


        String[] colStrings = new String[cols.size()];
        String[] locStrings = new String[locs.size()];
        String[] dateStrings = new String[dates.size()];
        for(int i=0;i<colStrings.length;i++){
            colStrings[i] = cols.get(i);
        }
        for(int i=0;i<locStrings.length;i++){
            locStrings[i] = locs.get(i);
        }
        for(int i=0;i<dateStrings.length;i++){
            dateStrings[i] = dates.get(i);
        }
        String[][] args = {dateStrings, locStrings};
        
        if(colStrings.length * locStrings.length * dateStrings.length == 0){
            System.out.println("checkbox Clicked!:"+e.getActionCommand());
            return;
        }

        try{
            if(db.connect()){
                if(db.isTable(CTRL.getFileName())){
                    List<List<String>> temp = db.SelectColsFromTableRows(CTRL.getFileName(), colStrings, args);
                    if(temp != null){
                        CTRL.setHeader(temp.remove(0));
                        CTRL.setContents(temp);
                        CTRL.setAverageNTotal();
                    }
                }
            }else{
                System.out.println("Failed");
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
    }
}