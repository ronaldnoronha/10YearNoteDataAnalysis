import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class CreateFiles {
	public CreateFiles(){

	}
	static void createFiles(String instrument){
		try{
			File file = new File(instrument+".txt");
			Scanner in = new Scanner(file);
			/* TO DO - date wise */
			int count = 0;
			String[] line = in.nextLine().split(",");
			line = in.nextLine().split(",");
			String dateFile = line[0];
			ListOfDates.addDate(dateFile);				
			File file1 = new File(instrument+"_"+changeDate(dateFile)+".txt");			
			PrintWriter pw = new PrintWriter(file1);
			pw.print(line[1]+","+toFractions(Double.parseDouble(line[5]))+",");
			pw.print(Integer.parseInt(line[6])-Integer.parseInt(line[7]));
			pw.println();
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				if (line[0].equals(dateFile)) {
					pw.print(line[1]+","+toFractions(Double.parseDouble(line[5]))+",");
					pw.print(Integer.parseInt(line[6])-Integer.parseInt(line[7]));
					pw.println();
				}
				else{
					pw.close();
					dateFile = line[0];
					ListOfDates.addDate(dateFile);
					file1 = new File(instrument+"_"+changeDate(dateFile)+".txt");
					pw = new PrintWriter(file1);
					pw.print(line[1]+","+toFractions(Double.parseDouble(line[5]))+",");
					pw.print(Integer.parseInt(line[6])-Integer.parseInt(line[7]));
					pw.println();
				}
			}
			pw.close();
			in.close();
		}catch (Exception e){
			System.out.println("error: "+e.toString());
		}
	}
	public static String changeDate(String date){
		String[] dateArray = date.split("/");
		return dateArray[0]+"-"+dateArray[1]+"-"+dateArray[2];
	}
	static String toFractions(Double value){
		int fraction = (int)((value-Math.floor(value))*100);
		if (fraction==0) return String.format("%.0f",value);
		for(int i =0;i<63;i++){
			if (fraction == Math.round(100*(i+1)/64)){
				if (i==64) return (int)Math.floor(value)+"";
				else return (int)Math.floor(value)+" "+String.format("%.1f",(i+1)*0.5)+"/"+32;
			}
		}
		return null;
	}
	static void printList(String instrument){
		try {
			File fw = new File(instrument+"_list.txt");
			PrintWriter out;		
			out = new PrintWriter(fw);
			for (int i=0;i<ListOfDates.getSize();i++){
				out.println(instrument+"_"+changeDate(ListOfDates.getIndexDate(i))+".txt");
			}
			out.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}
