import java.io.*;
import java.util.*;
/**
 * 
 * @author lingdean
 * BankSystem is the main class of this project. It is used to run the program.
 * It initializes the necessary files needed for the bank system. 
 */
public class BankSystem {

    private final String nptxt = "namePass.txt";
    private final String infotxt = "info.txt";
    private final String transtxt = GetDate.currentDate() + "_report.txt";
    private final String banktxt = "bank_money.txt";
    private final String exchangetxt = "exchange_rate.txt";
    private final String managertxt = "manager_list.txt";

    private static String npPath;
    private static String infoPath;
    private static String transPath;
    private static String bankPath;
    private static String exchangePath;
    private static String managerPath;
    private static File npFile;
    private static File infoFile;
    private static File transFile;
    private static File bankFile;
    private static File exchangeFile;
    private static File managerFile;


    public BankSystem() {
        this.setNpPath(GetData.createFilePath(this.nptxt));
        this.setInfoPath(GetData.createFilePath(this.infotxt));
        this.setTransPath(GetData.createFilePath(this.transtxt));
        this.setBankPath(GetData.createFilePath(this.banktxt));
        this.setexchangePath(GetData.createFilePath(this.exchangetxt));
        this.setmanagerPath(GetData.createFilePath(this.managertxt));
    }

    public void Run() {
        try {
            BankSystem.setNpFile(new File(BankSystem.npPath));
            BankSystem.setInfoFile(new File(BankSystem.infoPath));
            BankSystem.setTransFile(new File(BankSystem.transPath));
            BankSystem.setBankFile(new File(BankSystem.bankPath));
            BankSystem.setExchangeFile(new File(BankSystem.exchangePath));
            BankSystem.setManagerFile(new File(BankSystem.managerPath));

            if (!npFile.exists()) {
                npFile.createNewFile();
            }
            if (!infoFile.exists()) {
                infoFile.createNewFile();
            }
            if (!transFile.exists()) {
                transFile.createNewFile();
            }
            if (!bankFile.exists()) {
                bankFile.createNewFile();
                this.initBankFile();
            }
            if (!exchangeFile.exists()) {
                exchangeFile.createNewFile();
                this.initExchangeFile();
            }
            if (!managerFile.exists()) {
                managerFile.createNewFile();
                this.initManagerFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        WelcomeFrame wel_frame = new WelcomeFrame();

    }

    public void initBankFile() {
        try {
            FileWriter writer = new FileWriter(BankSystem.getBankPath(), true);
            String content = "10000000" + " " + "USD" + "\n"
                    + "10000000" + " " + "EUR" + "\n"
                    + "10000000" + " " + "GBP" + "\n"
                    + "10000000" + " " + "CNY" + "\n"
                    + "10000000" + " " + "AUD" + "\n";
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initExchangeFile(){
        try {
            FileWriter writer = new FileWriter(BankSystem.getExchangePath());
            String content = ":USD" + "\t" + ":1" + "\n"
                    + "EUR" + "\t" + "0.8204" + "\n"
                    + "GBP" + "\t" + "0.7405" + "\n"
                    + "CNY" + "\t" + "6.5301" + "\n"
                    + "AUD" + "\t" + "1.3207" + "\n";
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initManagerFile(){
        try {
            FileWriter writer = new FileWriter(BankSystem.getManagerPath());
            String content = "managerID" + " " + "passward" + "\n"
                    + "admin" + " " + "123456" + "\n";
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static String getBankPath() {
        return bankPath;
    }

    public void setBankPath(String bankPath) {
        BankSystem.bankPath = bankPath;
    }

    public static File getBankFile() {
        return bankFile;
    }

    public static void setBankFile(File bankFile) {
        BankSystem.bankFile = bankFile;
    }

    public static String getExchangePath(){return exchangePath;}

    public void setexchangePath(String exchangePath){BankSystem.exchangePath=exchangePath;}

    public static File getExchangeFile(){return exchangeFile;}

    public static void setExchangeFile(File exchangeFile){BankSystem.exchangeFile=exchangeFile;}

    public static String getManagerPath(){return managerPath;}

    public void setmanagerPath(String managerPath){BankSystem.managerPath=managerPath;}

    public static File getManagerFile(){return managerFile;}

    public static void setManagerFile(File managerFile){BankSystem.managerFile=managerFile;}


}
