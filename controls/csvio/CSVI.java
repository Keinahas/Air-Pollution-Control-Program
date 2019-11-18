package controls.csvio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVI {

    public List<List<String>> CSVOpen(String path){
        List<List<String>> inputList = new ArrayList<List<String>>();
        BufferedReader br = null;
        
        try{
            
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
            // Charset.forName("UTF-8");
            String line = "";
            
            while((line = br.readLine()) != null)
            {
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");

                tmpList = Arrays.asList(array);
                // System.out.println(tmpList);
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
}