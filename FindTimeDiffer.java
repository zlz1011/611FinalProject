import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Date; 

public class FindTimeDiffer {

	public static long findDateDiffer(String start_date, String end_date) {
		
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		long differ_in_days = 0;
		
		try { 
            Date d1 = sdf.parse(start_date); 
            Date d2 = sdf.parse(end_date); 
            long difference_in_time  = d2.getTime() - d1.getTime(); 
            differ_in_days  = (difference_in_time /(1000 * 60 * 60 * 24)) % 365; 
    
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		return differ_in_days;
		
	}
}
