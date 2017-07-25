import java.io.*;
import java.util.*;
public class CreateInstrumentSummary {
	public CreateInstrumentSummary(String instrument){
		try{
			File fw = new File(instrument+"_list.txt");
			Scanner in = new Scanner(fw);
			String filename;
			String date;
			File fw1 = new File(instrument+"_summary.txt");
			PrintWriter out = new PrintWriter(fw1);
			while (in.hasNextLine()){
				filename = in.nextLine();
				DailyVolume.addVolume(filename);
				DailyRange.addRange(filename);
				date = filename.substring(filename.indexOf('_')+1,filename.indexOf('.'));
				out.println(date+","+DailyVolume.getVolume(date.replace('/','-'))+","+DailyRange.getRange(date.replace('/','-')));
				//System.out.print(ListOfDates.getIndexDate(i)+"\t");
				//System.out.print(DailyVolume.getVolume(ListOfDates.getIndexDate(i).replace('/','-'))+ "\t");			
				//System.out.println(DailyRange.getRange(ListOfDates.getIndexDate(i).replace('/','-')));				
			}
			out.close();
			in.close();
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
