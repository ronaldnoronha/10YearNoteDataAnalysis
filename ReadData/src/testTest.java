import java.io.File;
import java.util.*;
import java.time.*;
public class testTest {
	public static void main(String[] args){
		try{
			//System.out.println(CreateVolFiles.timePeriod("08:00:00.000",15));
			//System.out.println(CreateVolFiles.checkEndTime("24:45:00.000"));
			//System.out.println(LocalTime.now());
			//DailyRange.addRange("TYAU17_14-06-2017.txt");
			//String instrument = "TYAU17";
			String filename = "TYAU17_14-06-2017.txt";
			CreateRefinedTickFile a = new CreateRefinedTickFile(filename);
			//System.out.println(LocalTime.now());
			//CreateInstrumentSummary b = new CreateInstrumentSummary(instrument);
			//System.out.println(LocalTime.now());
		}catch (Exception e){
			System.out.println(e.toString());
		}
	}

}
