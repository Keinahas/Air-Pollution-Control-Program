package controls.csvio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVI {

    public List<List<String>> CSVOpen(String path){
        List<List<String>> inputList = new ArrayList<List<String>>();
        BufferedReader br = null;
        
        try{
            br = Files.newBufferedReader(Paths.get(path));
            Charset.forName("UTF-8");
            String line = "";
            
            while((line = br.readLine()) != null)
            {
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");

                tmpList = Arrays.asList(array);
                System.out.println(tmpList);
                inputList.add(tmpList);
            }
            
        //검사 및 예의상황용
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

    // public static void main(String[] args) {
    //     CSVI csv = new CSVI();
    //     System.out.println(csv.CSVOpen("일별평균대기오염도_2018.CSV"));
    // }
}