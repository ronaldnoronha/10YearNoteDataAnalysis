import java.text.*;
import java.util.*;
public class testCreateContractLookupTable {

	public static void main(String[] args) {
		String start = "01-10-2016";
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
	public static int[] frontRunningContract(String[] contracts){
		int[] result = new int[3];
		try{
			File fw1 = new File(contracts[0]+"_summary.txt");
			
		} catch(Exception e) {
			System.out.println(e.getMessage());

		}



		return result;
	}

}
