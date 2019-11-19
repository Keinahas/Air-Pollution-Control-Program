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
import javax.swing.filechooser.FileNameExtensionFilter;

public class CSVIO {
    private File file;
    private JFileChooser jfc;
    private FileNameExtensionFilter filter;

    public CSVIO(){
        file = null;
        jfc = new JFileChooser(".");
        filter = new FileNameExtensionFilter("CSV Files", "CSV","csv");
    }

    public void chooseOpen(){
        jfc.setFileFilter(filter);
        jfc.setMultiSelectionEnabled(false);
        int returnVal = jfc.showOpenDialog(null);

        if(returnVal == 0) {
            file = jfc.getSelectedFile();
        }else{
            file = null;
            System.out.println("파일 열기를 취소하였습니다.");
        }
    }

    public void chooseSave(){
        jfc.setFileFilter(filter);
        jfc.setMultiSelectionEnabled(false);
        int returnVal = jfc.showSaveDialog(null);

        if(returnVal == 0) {
            file = jfc.getSelectedFile();
        }else{
            file = null;
            System.out.println("파일 저장을 취소하였습니다.");
        }
    }


    public File Open(){
        return file;
    }

    public List<List<String>> Read(){
        BufferedReader br = null;
        List<List<String>> inputList = new ArrayList<List<String>>();
        try{
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = "";
            while((line = br.readLine()) != null)
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
                if(br != null)
                    br.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return inputList;
    }

    public void Write(List<List<String>> read){
        BufferedWriter bufWriter = null;
        
        try{
            bufWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            for(List<String> newLine : read)
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