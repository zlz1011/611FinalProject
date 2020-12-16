import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
	
	public static void writeData(String username, String content)  {
		
		String filename = "info_" + username + ".txt";
		String path = GetData.createFilePath(filename);
		content = content + "\n";
		
		try {
			FileWriter writer = new FileWriter(path,true);
			writer.write(content);
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void writeTransaction(String date, String content)  {
		
		String filename = date+"_report.txt";
		String path = GetData.createFilePath(filename);
		content = content + "\n";
		
		try {
			FileWriter writer = new FileWriter(path,true);
			writer.write(content);
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
