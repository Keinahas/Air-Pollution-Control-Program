package controls.triggers;

import java.awt.event.ActionListener;

import controls.csvio.CSVO;

import java.awt.event.ActionEvent;

public class csvSave implements ActionListener{
    private CSVI opener;
    private List<List<String>> br;
    private CSVO writer;

    public csvSave(){
        super();
        opener = new CSVI();
        writer = new CSVO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        br = opener.CSVOpen("일별평균대기오염도_2018.CSV");
        writer.CSVOut(br);
    }
}