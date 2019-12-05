package controls.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;

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
            int pos = str.lastIndexOf("월");
            int num = Integer.parseInt(str.substring(0,pos));
            if(num < 10)
                if(((JCheckBox)(e.getSource())).isSelected())
                    dates.add("20180"+num);
                else
                    dates.remove("20180"+num);
            else
                if(((JCheckBox)(e.getSource())).isSelected())
                    dates.add("2018"+num);
                else
                    dates.remove("2018"+num);
        }
        else{
            for(String string : CTRL.getHeader())
                if(string == str){
                    if(((JCheckBox)(e.getSource())).isSelected())
                        cols.add(str);
                    else
                        cols.remove(str);
                    break;
                }
            for (String string : CTRL.locOpts)
                if(string == str){
                    if(((JCheckBox)(e.getSource())).isSelected())
                        locs.add(str);
                    else
                        locs.remove(str);
                    break;
                }
        }
        
        System.out.println("cols.size():"+cols.size()+" locs.size():"+locs.size()+" dates.size():"+dates.size());

        String[] colStrings = new String[cols.size()];
        String[] locStrings = new String[locs.size()];
        String[] dateStrings = new String[dates.size()];
        for(int i=0;i<colStrings.length;i++){
            colStrings[i] = CTRL.parse(cols.get(i));
        }
        for(int i=0;i<locStrings.length;i++){
            locStrings[i] = locs.get(i);
        }
        for(int i=0;i<dateStrings.length;i++){
            dateStrings[i] = dates.get(i);
        }
        String[][] args = {dateStrings, locStrings};
        
        if((colStrings.length * locStrings.length * dateStrings.length) == 0){
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