import java.io.*;
import java.time.LocalTime;
import java.util.*;
public class testSorters {

	public static void main(String[] args) {
		// read frontrunning file
		try{
			File fw  = new File("frontRunningContracts.txt");
			Scanner in = new Scanner(fw);
			ArrayList<Integer> volumeList = new ArrayList<Integer>();
			String[] line;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				volumeList.add(Integer.parseInt(line[2]));
			}
			int[] volume = new int[volumeList.size()];
			for(int i =0;i<volumeList.size();i++){
				volume[i] = volumeList.get(i);
				System.out.println(volume[i]);
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		
		// get data for range and volume in an arrayList
		// convert the range and volume data in arrays to pass to sorts
		System.out.println(LocalTime.now());
		// start time
		// do one sort
		// end time
		System.out.println(LocalTime.now());
		// do again for the other sort

	}
	public static void insertionSort(){

	}
	public static void mergeSort(){

	}

}
