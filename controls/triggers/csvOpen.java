package controls.triggers;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controls.csvio.CSVIO;
import controls.db.dbActions;

import java.awt.event.ActionEvent;

public class csvOpen implements ActionListener{
    private CSVIO CSV;
    private List<List<String>> br;
    private dbActions db;


    public csvOpen(){
        super();
        br = null;
        db = new dbActions();
        CSV = new CSVIO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
            CSV.chooseOpen();
            File file = CSV.Open();
            if(file == null){
                System.out.println("CSV.Choose() Error!");
                return;
            }
            br = CSV.Read();
            try{
                if(db.connect()){
                    String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
                    db.createTable(fileName, br.get(0).size());
                    int size = br.size();
                    for (int i = 0; i < size; i++) {
                        db.insertIntoTable(fileName, String.join(",", br.get(i)).split(","));
                    }
                }else{
                    System.out.println("Failed");
                }
            }
            catch(SQLException exception){
                exception.printStackTrace();
            }
        // System.out.println(br.get(0));
    }
}