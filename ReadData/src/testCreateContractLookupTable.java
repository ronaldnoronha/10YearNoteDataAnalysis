import java.text.*;
import java.util.*;
public class testCreateContractLookupTable {

	public static void main(String[] args) {
		Date startDate = new Date();
		Date endDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String start = "01-01-2016";
		String end = "01-04-2016";
		try{
			startDate = dateFormat.parse(start);
			endDate = dateFormat.parse(end);
			List<Date> allDates = getDatesBetween(startDate,endDate);
			for (int i =0;i<allDates.size();i++){
				System.out.println(allDates.get(i).toString());
			}
		}catch (Exception e){
			System.out.println(e.toString());
		}
	}
	public static List<Date> getDatesBetween(
			Date startDate, Date endDate) {
		List<Date> datesInRange = new ArrayList<>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);

		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);

		while (calendar.before(endCalendar)) {
			Date result = calendar.getTime();
			// stipulate day is not saturday or sunday
			if (calendar.get(Calendar.DAY_OF_WEEK)!=7 && calendar.get(Calendar.DAY_OF_WEEK)!=1){
				datesInRange.add(result);
			}
			calendar.add(Calendar.DATE, 1);
		}
		return datesInRange;
	}

}
