package controls.csvio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CSVIO {
    private File file;
    private JFileChooser jfc;
    private FileNameExtensionFilter filter;

    // constructor
    public CSVIO(){
        file = null;
        jfc = new JFileChooser(".");
        filter = new FileNameExtensionFilter("CSV Files", "CSV","csv");
        jfc.setSelectedFile(null);
        jfc.setFileFilter(filter);
        jfc.setMultiSelectionEnabled(false);
    }

    public void chooseOpen(){
        int returnVal = jfc.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            file = jfc.getSelectedFile();
        }else{
            file = null;
            System.out.println("파일 열기를 취소하였습니다.");
        }
    }

    public void chooseSave(){
        String fileName = null;
        int returnVal = jfc.showSaveDialog(null);


        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileName = jfc.getSelectedFile().getName();
            if(!fileName.endsWith(".csv") && !fileName.endsWith(".CSV")){
                fileName += ".CSV";
            }
            file = new File(fileName);
            if(file.exists()){
                int r = JOptionPane.showConfirmDialog(null, file.getName() + "이(가) 이미 있습니다. 바꾸시겠습니까?", "다른 이름으로 저장 확인", JOptionPane.YES_NO_OPTION);
                if(r == JOptionPane.NO_OPTION){
                    chooseSave();
                }
            }
        }else{
            file = null;
            System.out.println("파일 저장을 취소하였습니다.");
        }
    }


    public File Open(){
        return file;
    }

    public List<List<String>> Read(){
        BufferedReader sList = null;
        List<List<String>> inputList = new ArrayList<List<String>>();
        try{
            sList = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = "";
            while((line = sList.readLine()) != null)
            {
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                tmpList = Arrays.asList(array);
                inputList.add(tmpList);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(sList != null)
                    sList.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return inputList;
    }

    public void Write(List<List<String>> sList){
        BufferedWriter bufWriter = null;
        
        try{
            bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for(List<String> newLine : sList)
            {
                List<String> list = newLine;
                for(String data : list)
                {
                    bufWriter.write(data);
                    bufWriter.write(",");
                }
                bufWriter.newLine();
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
	        try{
	            if(bufWriter != null)
	                bufWriter.close();
	        }catch(IOException e){
	            e.printStackTrace();
	        }
        }
	}
}