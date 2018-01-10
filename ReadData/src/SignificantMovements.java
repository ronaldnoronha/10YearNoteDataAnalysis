import java.util.*;
import java.io.*;
public class SignificantMovements {
	private ArrayList<HashMap<String,String>> significantMovements = new ArrayList<HashMap<String,String>>();
	public SignificantMovements() {
		// Read the high vol dates file
		try {
			File fw = new File("highVolDates.txt");
			Scanner highVolDates = new Scanner(fw);
			String[] line;
			// supply ins
			
			HashMap<String, String> hmap = new HashMap<String, String>();
			int counter = 0;
			while (highVolDates.hasNextLine() && counter<2){
				line = highVolDates.nextLine().split(",");
				significantMovements = openRefinedMovementFile(line[0], line[1]);
				counter++;
			}
			createResultsFile(significantMovements);

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		// open the refined movement file for each date
		// look for max up or down above 8 ticks in between 7am and 10am
		//

	}
	public static ArrayList<HashMap<String,String>> openRefinedMovementFile(String date, String instrument){
		try{
			File fw = new File(instrument+"_"+date+"_refined_movement.txt");
			Scanner in = new Scanner(fw);
			String[] line;
			String start = "07:00:00.000";
			String end = "10:00:00.000";
			HashMap<String, String> hmap;
			Time a;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				a = new Time(line[1]);
				if (a.checkTimeWithin(new Time(start),new Time(end)) && (Double.parseDouble(line[3])>8 || Double.parseDouble(line[4])>8)){
					hmap = new HashMap<String, String>();
					hmap.put("date",date);
					hmap.put("instrument",instrument);
					hmap.put("time",line[1]);
					hmap.put("price",line[2]);
					hmap.put("maxUp",line[3]);
					hmap.put("maxDown",line[4]);
					hmap.put("cum delta",line[5]);
					significantMovements.add(hmap);
					if (Double.parseDouble(hmap.get("maxUp"))>8){
						while (in.hasNextLine()){
							line = in.nextLine().split(",");
							a = new Time(line[1]);
							if (a.checkTimeWithin(new Time(start),new Time(end)) && )
							
						}
					}
					// check if hmap.get("maxUp") or hmap.get("maxDown") is greater than 8
					
				}
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return significantMovements;
	}
	public static void createResultsFile(ArrayList<HashMap<String,String>> significantMovements){
		try{
			File fw = new File("significantMovements.txt");
			PrintWriter out = new PrintWriter(fw);
			for (int i = 0; i<significantMovements.size();i++){
				out.print(significantMovements.get(i).get("date")+",");
				out.print(significantMovements.get(i).get("instrument")+",");
				out.print(significantMovements.get(i).get("time")+",");
				out.print(significantMovements.get(i).get("price")+",");
				out.print(significantMovements.get(i).get("maxUp")+",");
				out.print(significantMovements.get(i).get("maxDown")+",");
				out.println(significantMovements.get(i).get("cum delta"));
			}
			out.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static void narrowSignificantMovements(ArrayList<HashMap<String,String>> significantMovements){
		/*if the consecutive ticks starting from the first one has the large movement only pick the first one. 

			If the price is still within the movement of the previous large movement, dont record. 

		 * 
		 */
	}
}
