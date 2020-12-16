import java.io.*;
import java.util.*;

public class BankSystem {
	
	private final String nptxt = "namePass.txt";
	private final String infotxt = "info.txt";
	private final String transtxt = GetDate.currentDate()+"_report.txt";
		
	private static String npPath;
	private static String infoPath;
	private static String transPath;
	private static File npFile;
	private static File infoFile;
	private static File transFile;
	
	public BankSystem() {
		this.setNpPath(GetData.createFilePath(this.nptxt));
		this.setInfoPath(GetData.createFilePath(this.infotxt));
		this.setTransPath(GetData.createFilePath(this.transtxt));
	}
	
	public void Run() {
		try {
			BankSystem.setNpFile(new File(BankSystem.npPath));
			BankSystem.setInfoFile(new File(BankSystem.infoPath));
			BankSystem.setTransFile(new File(BankSystem.transPath));
			
			if(! npFile.exists()) {
				npFile.createNewFile();
			}
			if(! infoFile.exists()) {
				infoFile.createNewFile();
			}
			if(! transFile.exists()) {
				transFile.createNewFile();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		WelcomeFrame wel_frame = new WelcomeFrame();
		
	}

	public static String getNpPath() {
		return npPath;
	}

	public void setNpPath(String npPath) {
		BankSystem.npPath = npPath;
	}

	public static String getInfoPath() {
		return infoPath;
	}

	public void setInfoPath(String infoPath) {
		BankSystem.infoPath = infoPath;
	}

	public static String getTransPath() {
		return transPath;
	}

	public void setTransPath(String transPath) {
		BankSystem.transPath = transPath;
	}

	public static File getNpFile() {
		return npFile;
	}

	public static void setNpFile(File npFile) {
		BankSystem.npFile = npFile;
	}

	public static File getInfoFile() {
		return infoFile;
	}

	public static void setInfoFile(File infoFile) {
		BankSystem.infoFile = infoFile;
	}

	public static File getTransFile() {
		return transFile;
	}

	public static void setTransFile(File transFile) {
		BankSystem.transFile = transFile;
	}
	
	

}
