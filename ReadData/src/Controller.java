import java.io.File;
import java.time.LocalTime;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		System.out.println(LocalTime.now());
		String instrument = "TYAH18";
		
		// Builder method
		/*
		Builder builder = new Builder(instrument);
		builder.readFile();
		*/
		
		CreateFiles.createFiles(instrument);
		CreateFiles.printList(instrument);
		int numOfDays = ListOfDates.getSize();
		CreateVolFiles a;
		for(int i=0;i<numOfDays;i++){			
			a = new CreateVolFiles(instrument+"_"+ListOfDates.getIndexDate(i).replace('/','-')+".txt",15);
		}
		CreateInstrumentSummary b = new CreateInstrumentSummary(instrument);
		// Create refined tick file
		try{
			File fw = new File(instrument+"_list.txt");
			Scanner in = new Scanner(fw);
			String line;
			CreateRefinedTickFile c;
			MovementAnalysis d;
			while (in.hasNextLine()){
				line = in.nextLine();
				c = new CreateRefinedTickFile(line);
				d = new MovementAnalysis(line.substring(0,line.indexOf("."))+"_"+"refined"+".txt",3);
			}
		}catch (Exception e){
			System.out.println(e.toString());
		}
		
		System.out.println(LocalTime.now());
	}

}
