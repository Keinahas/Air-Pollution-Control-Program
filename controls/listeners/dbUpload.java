package controls.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controls.CTRL;
import controls.db.dbActions;


// class for saving data to csv file || yet coding
public class dbUpload implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(CTRL.getFileName()==null){
            JOptionPane.showMessageDialog(null, "Open CSV file first plz.");
            return;
        }
        try{
            if(CTRL.isConnected()){
                if(!CTRL.DB.isDataBase("aoop")){
                    int r = JOptionPane.showConfirmDialog(null, "데이터베이스가 없습니다. 새로 만드시겠습니까?", "데이터베이스 생성", JOptionPane.YES_NO_OPTION);
                    if(r == JOptionPane.NO_OPTION)
                        return;
                    CTRL.DB.createDataBase("aoop");
                }
                if(CTRL.DB.isTable(CTRL.getFileName())){
                    int r = JOptionPane.showConfirmDialog(null, CTRL.getFileName() + "이(가) 이미 있습니다. 새로 만드시겠습니까?", "테이블 생성", JOptionPane.YES_NO_OPTION);
                    if(r == JOptionPane.NO_OPTION){
                        CTRL.DB.insertIntoTable(CTRL.getFileName(), CTRL.realContents, CTRL.colOpts.length);
                        return;
                    }
                }
                CTRL.DB.createTable(CTRL.getFileName(), CTRL.getHeader());
                CTRL.DB.insertIntoTable(CTRL.getFileName(), CTRL.realContents, CTRL.getHeader().size());
                System.out.println("dbUpload Clicked!");
            }else{
                //NO DB
                JOptionPane.showMessageDialog(null, "Database Server not Connected");
                System.out.println("Failed");
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
    }
}