import java.util.*;
import java.io.*;
public class SignificantMovements {
	private ArrayList<RefinedTick> significantMovements = new ArrayList<RefinedTick>();
	public SignificantMovements() {
		// Read the high vol dates file
		try {
			File fw = new File("highVolDates.txt");
			Scanner highVolDates = new Scanner(fw);
			String[] line;
			// supply filename to lookup
			int counter = 0;
			while (highVolDates.hasNextLine() && counter<2){
				line = highVolDates.nextLine().split(",");
				significantMovements = getSignificantRefinedTicks(line[0], line[1]);
				// call function to give all significant ticks from the file
				// save to significant movements
				//next
			// results file
			HashMap<String, String> hmap = new HashMap<String, String>();
				
				
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
	public static ArrayList<SignificantTick> getSignificantRefinedTicks(String date, String instrument){
		ArrayList<SignificantTick> listOfTicks = new ArrayList<SignificantTick>();
		try{
			File fw1 = new File(instrument+"_"+date+"_refined.txt");
			Scanner in1 = new Scanner(fw1);
			
			File fw2 = new File(instrument+"_"+date+"_refined_movement.txt");
			Scanner in2 = new Scanner(fw2);
			
			String[] line1;
			String[] line2;
			String start = "07:00:00.000";
			String end = "10:00:00.000";
			Time a;
			SignificantTick b;
			String endPrice;
			while (in1.hasNextLine()){
				
				line1 = in1.nextLine().split(",");
				line2 = in2.nextLine().split(",");
				
				a = new Time(line2[1]);
				if (a.checkTimeWithin(new Time(start),new Time(end)) && (Double.parseDouble(line2[3])>8 || Double.parseDouble(line2[4])>8)){
					b = new SignificantTick(instrument,date,line2[1],line2[2],line2[3],line2[4],line1[4],line1[5]);
					if (b.getMaxUp()>8) {
						endPrice = b.getEndMovement(b.getMaxUp());
					}
					else {
						endPrice = b.getEndMovement(-1*b.getMaxDown());
					}
					// while price has not reached the endPrice
					
					while (!endPrice.equals(line1[2])){
						line1 = in1.nextLine().split(",");
						line2 = in2.nextLine().split(",");
					}
					b.addEndTime(line2[1]);
					listOfTicks.add(b);				
				}
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return listOfTicks;
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
