import java.io.*;
import java.util.*;

public class testGetAllHighVolDates {

	public static void main(String[] args) {
		/* Get all high vol dates using the frontrunning contracts.
		 */
		try {
			File fw = new File("frontRunningContracts.txt");
			File fw1 = new File("highVolDates.txt");
			Scanner in = new Scanner(fw);
			PrintWriter out = new PrintWriter(fw1);
			String[] line;
			String date;
			String instrument;
			PercentileScore a;
			ArrayList <String> highVolDates = new ArrayList <String>();
			while (in.hasNextLine()){
				//System.out.println(in.nextLine());
				line = in.nextLine().split(",");
				date = line[0];
				instrument = line[1];
				a = new PercentileScore(date,instrument);
				if (a.getScore7am()>=75) {
					out.println(date+","+instrument+","+a.getScore7am());
					highVolDates.add(date);
				}
			}
			in.close();
			out.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
