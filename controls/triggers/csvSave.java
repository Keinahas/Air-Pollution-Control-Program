package controls.triggers;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import controls.csvio.CSVIO;
import controls.db.dbActions;

import java.awt.event.ActionEvent;

public class csvSave implements ActionListener{
    private CSVIO CSV;
    private dbActions db;
    private List<List<String>> br;

    public csvSave(){
        super();
        br = null;
        CSV = new CSVIO();
        db = new dbActions();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        CSV.chooseSave();
        File file = CSV.Open();
        if(file == null){
            System.out.println("CSV.Choose() Error!");
            return;
        }
        try{
            if(db.connect()){
                // db.createTable(file.getName().substring(0, file.getName().lastIndexOf(".")), br.get(0).size());
                // br = db.SelectAllFromTable(name);
                System.out.println("csvSave Clicked!");
            }else{
                System.out.println("Failed");
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        CSV.Write(br);
    }
}