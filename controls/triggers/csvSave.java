package controls.triggers;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import controls.CTRL;
import controls.csvio.CSVIO;
import controls.db.dbActions;

import java.awt.event.ActionEvent;

// class for saving data to csv file || yet coding
public class csvSave implements ActionListener{
    private CSVIO CSV;

    public csvSave(CSVIO CSV){
        this.CSV = CSV;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        CSV.chooseSave();
        String name = null;
        File file = CSV.Open();
        if(file == null){
            System.out.println("CSV.Choose() Error!");
            return;
        }

        int ext = file.getName().lastIndexOf(".");
        if(ext <= 0){
            name = file.getName() + ".CSV";
        }else{
            name = file.getName().substring(0, ext);
        }
        System.out.println(name);
        System.out.println("csvSave Clicked!");
        // System.out.println(CTRL.getList());
        // CSV.Write(CTRL.getList());

        // try{
        //     if(db.connect()){
        //         int ext = file.getName().lastIndexOf(".");
                    // if(ext <= 0){
                    //     System.out.println("EXT ERROR!");
                    //     return;
                    // }
                    // String name = file.getName().substring(0, file.getName().lastIndexOf("."));
                    // System.out.println(name);
        //         // db.createTable(file.getName().substring(0, file.getName().lastIndexOf(".")), br.get(0).size());
        //         br = db.SelectAllFromTable(name);
        //         // db.getNRowFromTable(file.getName().substring(0, file.getName().lastIndexOf(".")), 1);
        //         System.out.println("csvSave Clicked!");
        //         System.out.println(br);
        //     }else{
        //         System.out.println("Failed");
        //     }
        // }
        // catch(SQLException exception){
        //     exception.printStackTrace();
        // }
    }
}