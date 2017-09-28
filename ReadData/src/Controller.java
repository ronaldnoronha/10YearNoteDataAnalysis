import java.time.LocalTime;

public class Controller {

	public static void main(String[] args) {
		System.out.println(LocalTime.now());
		String instrument = "TYAZ17";
		CreateFiles.createFiles(instrument);
		CreateFiles.printList(instrument);
		int numOfDays = ListOfDates.getSize();
		CreateVolFiles a;
		for(int i=0;i<numOfDays;i++){			
			a = new CreateVolFiles(instrument+"_"+ListOfDates.getIndexDate(i).replace('/','-')+".txt",15);
		}
		CreateInstrumentSummary b = new CreateInstrumentSummary(instrument);
		System.out.println(LocalTime.now());

	}

}
