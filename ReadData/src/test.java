
public class test {
	public static void main(String[] args){
		String instrument = "TYAU17";
		//CreateFiles.createFiles(instrument);
		//CreateFiles.printList(instrument);
		ListOfDates.createList(instrument);
		int numOfDays = ListOfDates.getSize();
		for(int i=0;i<numOfDays;i++){
			DailyVolume.addVolume(instrument+"_"+ListOfDates.getIndexDate(i)+".txt");
			System.out.print(ListOfDates.getIndexDate(i)+" ");
			System.out.print(DailyVolume.getVolume(ListOfDates.getIndexDate(i))+ "\t");
			DailyRange.addRange(instrument+"_"+ListOfDates.getIndexDate(i)+".txt");
			System.out.println(DailyRange.getRange(ListOfDates.getIndexDate(i)));
		}
	}
}
/* TO DO break data into smaller pockets and organise by date */