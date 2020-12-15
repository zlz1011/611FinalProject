/**
 * get current date from date.txt and save the changed date to it
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {
    static private void checkexist()throws IOException {
        File file = new File(GetData.createFilePath("date.txt"));
        if (!file.exists()){
            file.createNewFile();
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write("2020-12-16");
            fileWriter.close();
        }
    }
    static public String currentDate(){
        String reStr="";
        try{
            checkexist();
            String currentDate=GetData.readlines(GetData.createFilePath("date.txt"),false).get(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sdf.parse(currentDate);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            Date dt1 = rightNow.getTime();
            reStr = sdf.format(dt1);

        }catch (ParseException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return reStr;
    }

    static public  String changeDay(int change){
        String reStr="";
        try{
            checkexist();
            String currentDate=GetData.readlines(GetData.createFilePath("date.txt"),false).get(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sdf.parse(currentDate);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_MONTH, change);
            Date dt1 = rightNow.getTime();
            reStr = sdf.format(dt1);

            File file = new File(GetData.createFilePath("date.txt"));
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(reStr);
            fileWriter.close();

        }catch (ParseException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return reStr;
    }

}
