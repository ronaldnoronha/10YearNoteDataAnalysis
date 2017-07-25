import java.io.*;
import java.util.*;
public class ListOfDates {
	private static ArrayList<String> ListOfDates = new ArrayList<String>();

	public ListOfDates(){

	}
	static void addDate(String date){
		ListOfDates.add(date);
	}
	static boolean getDate(String date){
		for (int i =0; i<ListOfDates.size();i++){
			if (ListOfDates.get(i).equals(date)){
				return true;
			}
		}
		return false;
	}
	static void printDates(){
		for (int i =0; i<ListOfDates.size();i++){
			System.out.println(ListOfDates.get(i));
		}
	}
	static int getSize(){
		return ListOfDates.size();		
	}
	static String getIndexDate(int index){
		return ListOfDates.get(index);
	}
	static void createList(String instrument){
		/*Creates list of dates of all files seperated.*/
		try{
			File fw = new File(instrument+"_list.txt");
			Scanner in = new Scanner(fw);
			String date;
			while (in.hasNextLine()){
				date = in.nextLine();
				date = date.substring(date.indexOf('_')+1,date.indexOf('.'));
				addDate(date);
			}
			in.close();
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
