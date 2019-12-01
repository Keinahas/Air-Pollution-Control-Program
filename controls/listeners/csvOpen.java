package controls.listeners;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controls.CTRL;
import controls.csvio.CSVIO;
import controls.db.dbActions;

import java.awt.event.ActionEvent;

// class for opening csv file || yet coding
public class csvOpen implements ActionListener{
    private CSVIO CSV;

    // constructor
    public csvOpen(CSVIO CSV){
        this.CSV = CSV;
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

        List<List<String>> lists = CSV.Read();

        // System.out.println(sList);
        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        System.out.println("opened file : " + fileName);
        System.out.println("csvOpen Clicked!");
        CTRL.setFileName(fileName);
        CTRL.setHeader(lists.remove(0));
        CTRL.setContents(lists);

        // System.out.println(sList.get(0));

        // try{
        //     if(db.connect()){
        //         String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        //         System.out.println("opened file : " + fileName);
        //         // if(db.isTable(fileName)){
        //         //     db.DropTable(fileName);
        //         // }
        //         // db.createTable(fileName, br.get(0).size());
        //         // int size = br.size();
        //         // for (int i = 0; i < size; i++) {
        //         //     db.insertIntoTable(fileName, String.join(",", br.get(i)).split(","), br.get(0).size());
        //         // }
        //         System.out.println("csvOpen Clicked!");
        //     }else{
        //         System.out.println("Failed");
        //     }
        // }
        // catch(SQLException exception){
        //     exception.printStackTrace();
        // }
    }
}