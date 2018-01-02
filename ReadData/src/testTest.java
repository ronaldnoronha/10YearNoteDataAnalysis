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
			String filename = "TYAH16_2016-1-22.txt";
			//CreateRefinedTickFile a = new CreateRefinedTickFile(filename);
			System.out.println(LocalTime.now());
			CreateVolFiles a;
			//a = new CreateVolFiles(filename,15);
			//MovementAnalysis b = new MovementAnalysis("TYAM17_2017-2-6_refined.txt",3);
			System.out.println(LocalTime.now());
			String price1 = "126 21.0/32";
			String price2 = "126 1.0/32";
			System.out.println(filename.substring(0,filename.indexOf(".")));
			//System.out.println(MovementAnalysis.numTicks(price1,price2));
			//CreateInstrumentSummary b = new CreateInstrumentSummary(instrument);
			//System.out.println(LocalTime.now());
		}catch (Exception e){
			System.out.println(e.toString());
		}
	}

}
