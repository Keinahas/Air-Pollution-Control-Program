package controls.csvio;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVO {
	
	public void CSVOut(List<List<String>> read)
	{
        BufferedWriter bufWriter = null;
        
        try{
            bufWriter = Files.newBufferedWriter(Paths.get("C:\\Users\\admin\\Downloads\\???3.csv"),Charset.forName("UTF-8"));
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
