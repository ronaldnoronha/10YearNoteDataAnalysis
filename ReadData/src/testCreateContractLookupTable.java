import java.io.File;
import java.text.*;
import java.util.*;
public class testCreateContractLookupTable {

	public static void main(String[] args) {
		String start = "01-01-2016";
		String end = "31-12-2016";
		CreateContractLookupTable a = new CreateContractLookupTable(start,end);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date tryDate = new Date();
		try{
			tryDate = dateFormat.parse(start);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String[] contracts = new String[2];
		contracts = possibleContracts(tryDate);
		System.out.println(contracts[0]);
		System.out.println(contracts[1]);
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
		
		try{
			File fw1 = new File(contracts[0]+"_summary.txt");
			Scanner in = new Scanner(fw1);
			String [] line = new String[3];
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date summaryDate = new Date();
			int vol1,vol2;
			int range1,range2;
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
				int[] result = {1,vol1,range1};
			}
			else{
				int[] result = {2,vol2,range2};
			}

			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
