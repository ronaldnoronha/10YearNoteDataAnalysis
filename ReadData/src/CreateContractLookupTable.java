import java.io.*;
import java.text.*;
import java.util.*;
public class CreateContractLookupTable {
	private List<Date> allDates = new ArrayList<Date>();
	public CreateContractLookupTable(String start, String end){
		Date startDate = new Date();
		Date endDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try{
			startDate = dateFormat.parse(start);
			endDate = dateFormat.parse(end);
			allDates = getDatesBetween(startDate,endDate);
		}catch (Exception e){
			System.out.println(e.toString());
		}
		createFrontRunningListFile();
	}
	public List<Date> getDatesBetween(
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
	public static String[] possibleContracts(Date a){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(a);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR)-2000;
		String contract1 = "";
		String contract2 = "";

		if (month == 0) {
			contract1 = "TYAH"+year;
			contract2 = "TYAM"+year;
		}
		else if (month == 1) {
			contract1 = "TYAH"+year;
			contract2 = "TYAM"+year;
		}
		else if (month == 2) {
			contract1 = "TYAH"+year;
			contract2 = "TYAM"+year;
		}
		else if (month == 3) {
			contract1 = "TYAM"+year;
			contract2 = "TYAU"+year;
		}
		else if (month == 4) {
			contract1 = "TYAM"+year;
			contract2 = "TYAU"+year;
		}
		else if (month == 5) {
			contract1 = "TYAM"+year;
			contract2 = "TYAU"+year;
		}
		else if (month == 6) {
			contract1 = "TYAU"+year;
			contract2 = "TYAZ"+year;
		}
		else if (month == 7) {
			contract1 = "TYAU"+year;
			contract2 = "TYAZ"+year;
		}
		else if (month == 8) {
			contract1 = "TYAU"+year;
			contract2 = "TYAZ"+year;
		}
		else if (month == 9) {
			contract1 = "TYAZ"+year;
			contract2 = "TYAH"+(year+1);
		}
		else if (month == 10) {
			contract1 = "TYAZ"+year;
			contract2 = "TYAH"+(year+1);
		}
		else if (month == 11) {
			contract1 = "TYAZ"+year;
			contract2 = "TYAH"+(year+1);
		}

		String[] result = {contract1,contract2};
		return result;
	}
	public static int[] frontRunningContract(String[] contracts,Date date){
		// Make a choise of which contract to pick out of the two. 
		int[] result = new int[3];
		try{
			File fw1 = new File(contracts[0]+"_summary.txt");
			Scanner in = new Scanner(fw1);
			String [] line = new String[3];
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date summaryDate = new Date();
			int vol1,vol2;
			int range1,range2;
			vol1 = 0;
			range1 = 0;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				summaryDate = dateFormat.parse(line[0]);
				if (summaryDate.equals(date)){
					vol1 = Integer.parseInt(line[1]);
					range1 = Integer.parseInt(line[2]);
					break;
				}
			}
			in.close();

			// Reading the second file.
			fw1 = new File(contracts[1]+"_summary.txt");
			in = new Scanner(fw1);
			vol2 = 0;
			range2 = 0;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				summaryDate = dateFormat.parse(line[0]);
				if (summaryDate.equals(date)){
					vol2 = Integer.parseInt(line[1]);
					range2 = Integer.parseInt(line[2]);
					break;
				}
			}
			in.close();
			if (vol1>vol2){
				result[0] = 1;
				result[1] = vol1;
				result[2] = range1;
			}
			else{
				result[0] = 2;
				result[1] = vol2;
				result[2] = range2;
			}


		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	public void createFrontRunningListFile(){
		String[] contracts = new String[2];
		int[] frontRunner = new int[3];
		try{
			File fw = new File("frontRunningContracts.txt");
			PrintWriter out = new PrintWriter(fw);
			Calendar temp = new GregorianCalendar();
			for (int i =0;i<allDates.size();i++){
				contracts = possibleContracts(allDates.get(i));
				frontRunner = frontRunningContract(contracts,allDates.get(i));
				if(frontRunner[1]!=0){
					temp.setTime(allDates.get(i));
					out.print(temp.get(Calendar.YEAR)+"-");
					out.print((temp.get(Calendar.MONTH)+1)+"-");
					out.print(temp.get(Calendar.DAY_OF_MONTH)+",");
					if(frontRunner[0]==1) out.print(contracts[0]+",");
					else out.print(contracts[1]+",");
					out.println(frontRunner[1]+","+frontRunner[2]);					
				}
			}
			out.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

	}

}
