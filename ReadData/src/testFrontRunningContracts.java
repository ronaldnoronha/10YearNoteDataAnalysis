import java.text.*;
import java.util.*;

public class testFrontRunningContracts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String startDate = "2017-06-15";
		String endDate = "2017-06-22";
		boolean after;
		while (startDate !=endDate){
			startDate = nextTradingDate(startDate);
			System.out.println(startDate);
			//startDate = nextTradingDate(startDate);
		}

	}
	public static String nextTradingDate(String date){
		//String dt = "2008-01-01";  // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try{
		c.setTime(sdf.parse(date));
		c.add(Calendar.DATE, 1);  // number of days to add
		date = sdf.format(c.getTime());
		//System.out.println(date);
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return date;
	}

}
