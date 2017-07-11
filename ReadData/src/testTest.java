import java.io.File;
import java.util.Scanner;

public class testTest {
	public static void main(String[] args){
		try{
			
			System.out.println(CreateVolFiles.timePeriod("08:00:00.000",15));
			
			//DailyRange.addRange("TYAU17_14-06-2017.txt");
		}catch (Exception e){
			System.out.println(e.toString());
		}
	}

}
