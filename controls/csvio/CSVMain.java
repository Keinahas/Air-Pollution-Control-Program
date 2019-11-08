package controls.csvio;

public class CSVMain {
	public static void main(String[] args)
	{
		CSVI csvi = new CSVI();
		CSVO csvo = new CSVO();

		csvo.CSVOut(csvi.CSVOpen("C:\\Users\\admin\\Downloads\\�Ϻ�.csv"));
	}
}
