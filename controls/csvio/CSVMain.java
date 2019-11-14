package controls.csvio;

import java.nio.file.Paths;
import java.util.List;

public class CSVMain {
	public static void main(String[] args)
	{
		CSVI csvi = new CSVI();
		CSVO csvo = new CSVO();

		List<List<String>> str1 = csvi.CSVOpen("일별평균대기오염도_2018.CSV");
		csvo.CSVOut(str1);
	}
}
