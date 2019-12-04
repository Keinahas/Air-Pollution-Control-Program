package controls.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controls.CTRL;
import controls.db.dbActions;


// class for saving data to csv file || yet coding
public class dbUpload implements ActionListener{
    private dbActions db;

    public dbUpload(dbActions db){
        this.db = db;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        try{
            if(db.connect()){
                if(db.isTable(CTRL.getFileName())){
                    int r = JOptionPane.showConfirmDialog(null, CTRL.getFileName() + "이(가) 이미 있습니다. 새로 만드시겠습니까?", "테이블 생성", JOptionPane.YES_NO_OPTION);
                    if(r == JOptionPane.NO_OPTION){
                        db.insertIntoTable(CTRL.getFileName(), CTRL.getContents(), CTRL.getHeader().size());
                        return;
                    }

                }
                db.createTable(CTRL.getFileName(), CTRL.getHeader());
                db.insertIntoTable(CTRL.getFileName(), CTRL.getContents(), CTRL.getHeader().size());
                System.out.println("dbUpload Clicked!");
            }else{
                System.out.println("Failed");
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
    }
}