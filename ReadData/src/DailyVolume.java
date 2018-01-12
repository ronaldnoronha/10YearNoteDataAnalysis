import java.util.*;
import java.io.*;
public class DailyVolume {
	static HashMap volumeByDate = new HashMap(); 
	public DailyVolume(){

	}
	static void addVolume(String file){
		try{
			File fw = new File(file);
			Scanner in = new Scanner(fw);
			int sum = 0;
			String[] line;
			while(in.hasNextLine()){
				line = in.nextLine().split(",");
				sum+=Math.abs(Integer.parseInt(line[2]));
			}
			in.close();
			volumeByDate.put(file.replace('/', '-').substring(file.indexOf('_')+1,file.indexOf('.')),sum);
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
	static int getVolume(String date){
		return (int)volumeByDate.get(date);
	}
}
