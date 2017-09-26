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
			}
			// get data for range and volume in an arrayList
			// convert the range and volume data in arrays to pass to sorts
			System.out.println(LocalTime.now());
			// start time
			// do one sort
			// end time
			int[] volumeSorted = mergeSort(volume);
			System.out.println(LocalTime.now());
			// do again for the other sort
			for (int i=0;i<volumeSorted.length;i++){
				System.out.println(volumeSorted[i]);
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static int[] insertionSort(int[] A){
		int i = 1;
		int j, temp;
		while (i < A.length){
			j = i;
			while (j > 0 && A[j-1] > A[j]){
				temp  = A[j];
				A[j] = A[j-1];
				A[j-1] = temp;
				j = j-1;
			}
			i++;
		}
		return A;
	}
	public static int[] mergeSort(int[] A){
		// base case
		//System.out.println(A.length);
		if (A.length<2){			
			return A;			
		}
		// split in two
		int halfLength = A.length/2;
		int[] left = new int[halfLength];
		int[] right = new int[A.length-halfLength];
		for (int i =0;i<left.length;i++){
			left[i]=A[i];
		}
		for (int i =0;i<right.length;i++){
			right[i]=A[i+halfLength];
		}		
		left = mergeSort(left);
		right = mergeSort(right);
		int i = 0;
		int j = 0;
		int[] sortedArray = new int[left.length+right.length];
		int k = 0;
		while (i<left.length && j<right.length){
			if (left[i]<right[j]){
				sortedArray[k]=left[i];        		
				i++;
			}
			else {
				sortedArray[k]=right[j];        		
				j++;
			}
			k++;
		}
		if (i<left.length){
			for (;i<left.length;i++){
				sortedArray[k] = left[i];
				k++;
			}
		}
		if (j<right.length){
			for (;j<right.length;j++){
				sortedArray[k] = right[j];
				k++;
			}
		}
		return sortedArray;
	}

}
