import java.io.*;
import java.util.*;

public class CreateVolFiles {

	public CreateVolFiles(String file, int interval) {
		try{
			File fw = new File(file);
			Scanner in = new Scanner(fw);
			
			String[] line;
			line = in.nextLine().split(",");
			ArrayList<String> timestamp = createAllTimes(line[0],interval);
			String[] max = new String[timestamp.size()];
			String[] min = new String[timestamp.size()];
			int[] volume = new int[timestamp.size()];
			
			int index = getIndexTimeStamp(timestamp,line[0],interval);		
			max[index] = line[1];
			min[index] = line[1];
			volume[index] = Math.abs(Integer.parseInt(line[2]));
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				index = getIndexTimeStamp(timestamp,line[0],interval);
				if (max[index]==null) max[index] = line[1];
				else if (toDouble(line[1])>toDouble(max[index])) max[index] = line[1];				
				if (min[index]==null) min[index] = line[1];
				else if (toDouble(line[1])<toDouble(min[index])) min[index] = line[1];				
				volume[index] += Math.abs(Integer.parseInt(line[2]));
			}
			in.close();
			File fw1 = new File(file.substring(0,file.indexOf('.'))+"_"+interval+"m.txt");
			PrintWriter out = new PrintWriter(fw1);
			for (int i=0;i<timestamp.size();i++){
				out.print(timestamp.get(i)+",");
				out.print(max[i]+",");
				out.print(min[i]+",");
				out.println(volume[i]);
			}
			out.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	public static int getIndexTimeStamp(ArrayList<String> timestamps,String time, int interval){		
		time = timePeriod(time,interval);		
		for (int i=0;i<timestamps.size();i++){			
			if (timestamps.get(i).equals(time)){				
				return i;
			}
		}
		return -1;
	}
	public static double toDouble(String value){
		double output;
		if (value.contains("/")){
			String[] valueArray = value.split(" ");
			output = Double.parseDouble(valueArray[0]);
			valueArray = valueArray[1].split("/");
			output += Double.parseDouble(valueArray[0])/Double.parseDouble(valueArray[1]);
		}
		else output = Double.parseDouble(value);
		return output;
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
		output.add(startTime);
		String temp = addTime(startTime,interval);		
		while (!checkEndTime(temp)){
			output.add(temp);
			temp = addTime(temp,interval);			
		}
		return output;
	}
	public static boolean checkEndTime(String time){
		String[] timeArray = time.split(":");
		if (Integer.parseInt(timeArray[0])>=24) return true;
		else return false;
	}
	public static String addTime(String time, int interval){
		String[] timeArray = time.split(":");
		String output;
		if (Integer.parseInt(timeArray[1])+interval>=60){
			/*if (timeArray[0].equals("11")) output = "00:";
			else */
			output = String.format("%02d", Integer.parseInt(timeArray[0])+1)+":";
			output+=String.format("%02d",Integer.parseInt(timeArray[1])+interval-60)+":00.000";
		}
		else {
			output = timeArray[0]+":"+String.format("%02d",Integer.parseInt(timeArray[1])+interval)+":"+timeArray[2];
		}
		return output;
	}

}
