import java.io.*;
import java.util.*;

public class CreateVolFiles {

	public static void main(String[] args) {
		try{
			File fw = new File(args[1]);
			Scanner in = new Scanner(fw);
			File fw1 = new File(args[1].substring(0,args[1].indexOf('.'))+"_15m.txt");
			PrintWriter out = new PrintWriter(fw1);
			String[] line;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");			
			}

		}catch(Exception e){
			System.out.println(e.toString());
		}

	}
	public static String timePeriod(String time,int granularity){
		String output;
		String[] timeBroken = time.split(":");
		int hour = Integer.parseInt(timeBroken[0]);
		int min = Integer.parseInt(timeBroken[1]);
		double secs = Double.parseDouble(timeBroken[2]);
		output = timeBroken[0]+":";		
		for (int i = 0;i<60/granularity; i++){
			if (min<(i+1)*granularity){				
				return output+String.format("%02d", i*granularity)+":00.000";
			}
		}
		return output;
	}
	/* Create an array of all times based on the interval and the first time. */
	public static ArrayList<String> createAllTimes(String firstTime, int interval){
		ArrayList<String> output = new ArrayList<String>();
		String startTime = timePeriod(firstTime,interval);
		return output;
	}

}
