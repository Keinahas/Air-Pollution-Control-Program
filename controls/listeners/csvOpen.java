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
        CTRL.setAverageNTotal();
        try{
            if(CTRL.DB.connect()){
                if(CTRL.DB.isDataBase("aoop"))
                    CTRL.DB.useDB("aoop");
            }
        }catch(SQLException exception){
            exception.printStackTrace();
        }
    }
}