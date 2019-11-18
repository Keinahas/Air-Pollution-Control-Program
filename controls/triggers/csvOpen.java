package controls.triggers;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import controls.csvio.CSVI;
import controls.db.dbActions;

import java.awt.event.ActionEvent;

public class csvOpen implements ActionListener{
    private CSVI opener;
    private List<List<String>> br;
    private dbActions db;


    public csvOpen(){
        super();
        db = new dbActions();
        opener = new CSVI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        br = opener.CSVOpen("일별평균대기오염도_2018.CSV");
        try{
            if(db.connect()){
                db.createTable("일별평균대기오염도_2018",String.join(",", br.get(0)).split(","));
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