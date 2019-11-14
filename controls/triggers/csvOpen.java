package controls.triggers;

import java.awt.event.ActionListener;
import java.util.List;

import controls.csvio.CSVI;

import java.awt.event.ActionEvent;

public class csvOpen implements ActionListener{
    private CSVI opener;
    private List<List<String>> br;

    public csvOpen(){
        super();
        opener = new CSVI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        br = opener.CSVOpen("일별평균대기오염도_2018.CSV");
    }
}