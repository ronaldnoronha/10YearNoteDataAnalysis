import java.time.LocalTime;

public class testAnalyser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(LocalTime.now());
		String start = "01-01-2016";
		String end = "29-03-2018";
		CreateContractLookupTable a = new CreateContractLookupTable(start,end);
		System.out.println(LocalTime.now());
		GetAllHighVolDates b = new GetAllHighVolDates();
		System.out.println(LocalTime.now());
		SignificantMovements c = new SignificantMovements();
		System.out.println(LocalTime.now());
		

	}

}
