import java.util.*;
import java.io.*;
import java.time.LocalTime;

public class testSignificantMovements {

	public static void main(String[] args) {
		System.out.println(LocalTime.now());
		ArrayList<SignificantTick> a = getSignificantRefinedTicks("2017-12-13","TYAZ17");
		System.out.println(a.toString());
		System.out.println(LocalTime.now());
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
		
}
