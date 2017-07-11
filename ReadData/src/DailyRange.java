import java.io.*;
import java.util.*;
import java.lang.*;

public class DailyRange {
	static HashMap rangeByDate = new HashMap(); 
	public DailyRange(){

	}
	static void addRange(String file){
		try{			
			File fw = new File(file);
			Scanner in = new Scanner(fw);
			String[] line = in.nextLine().split(",");			
			String max = line[1];
			String min = line[1];			
			while(in.hasNextLine()){				
				line = in.nextLine().split(",");
				if (toDouble(line[1])>toDouble(max)) max = line[1];
				if (toDouble(line[1])<toDouble(min)) min = line[1];				
			}
			in.close();
			rangeByDate.put(file.substring(file.indexOf('_')+1,file.indexOf('.')),(int)Math.round((toDouble(max)-toDouble(min))*64));
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
	static int greaterPrice(String value1, String value2){
		double v1,v2;
		if (toDouble(value1)>toDouble(value2)) return 1;
		else if (toDouble(value1)<toDouble(value2)) return 2;
		else return 0;
	}
	static double toDouble(String value){
		double a;
		if (value.contains("/")){
			a = Double.parseDouble(value.substring(0, value.indexOf(' ')));
			a += Double.parseDouble(value.substring(value.indexOf(' ')+1,value.indexOf('/')))/Double.parseDouble(value.substring(value.indexOf('/')+1,value.length()));
		}
		else {
			a = Double.parseDouble(value);
		}		
		return a;		
	}
	
	static int getRange(String date){
		return (int)rangeByDate.get(date);
	}
}
