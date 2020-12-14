
import java.io.*;
import java.util.ArrayList;
// it is used for reading txt
public class GetData {
    public static ArrayList<String[]> read(String filepath,boolean headline){
        ArrayList<String[]> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;
            if(headline){
                line = br.readLine(); // skips the first line
            }
            while((line = br.readLine()) != null){
                String [] info = line.split("\\s+");
                result.add(info);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    public static String createFilePath(String filename){
        // puts the data profile
        File file = new File("Data");
        String filepath = file.getAbsolutePath() + File.separator + filename;
        return filepath;
    }
}
