import java.io.*;
import java.util.*;

public class GetAllHighVolDates {
	private ArrayList <String> highVolDates = new ArrayList <String>();
	public GetAllHighVolDates() {
		// get score for each date
		getList();
		publish();
		
	}
	public void getList(){
		try {
			File fw = new File("frontRunningContracts.txt");
			Scanner in = new Scanner(fw);
			String[] line;
			String date;
			String instrument;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				date = line[0];
				instrument = line[1];
				checkScore(date,instrument);				
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
	}
	public void checkScore(String date, String instrument){
		PercentileScore a = new PercentileScore(date,instrument);
		if (a.getScore("07:00:00.000")>=80){
			highVolDates.add(date+","+instrument+","+a.getScore("07:00:00.000"));
		}
	}
	public void publish(){
		try{
			File fw1 = new File("highVolDates.txt");
			PrintWriter out = new PrintWriter(fw1);
			for (int i=0;i<highVolDates.size();i++){
				out.println(highVolDates.get(i));
			}
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
		
	}
}
