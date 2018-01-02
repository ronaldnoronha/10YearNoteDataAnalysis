import java.io.File;
import java.text.*;
import java.util.*;
public class testCreateContractLookupTable {

	public static void main(String[] args) {
		String start = "01-01-2016";
		String end = "29-12-2017";
		CreateContractLookupTable a = new CreateContractLookupTable(start,end);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		
//		Date tryDate = new Date();
//		try{
//			tryDate = dateFormat.parse(start);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		String[] contracts = new String[2];
//		contracts = possibleContracts(tryDate);
//		System.out.println(tryDate.toString());
//		System.out.println(contracts[0]);
//		System.out.println(contracts[1]);
//		int [] data = frontRunningContract(contracts, tryDate);
//		System.out.println(data[0]);
//		System.out.println(data[1]);
//		System.out.println(data[2]);
//		
	}
}
