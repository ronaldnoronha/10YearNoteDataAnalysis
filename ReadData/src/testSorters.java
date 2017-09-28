import java.io.*;
import java.time.LocalTime;
import java.util.*;
public class testSorters {

	public static void main(String[] args) {
		// read frontRunning file
		try{
			File fw  = new File("frontRunningContracts.txt");
			Scanner in = new Scanner(fw);
			ArrayList<Integer> volumeList = new ArrayList<Integer>();
			ArrayList<Integer> rangeList = new ArrayList<Integer>();
			String[] line;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				volumeList.add(Integer.parseInt(line[2]));
				rangeList.add(Integer.parseInt(line[3]));
			}
			int[] volume = new int[volumeList.size()];
			int[] range = new int[rangeList.size()];
			for(int i =0;i<volumeList.size();i++){
				volume[i] = volumeList.get(i);
				range[i] = rangeList.get(i);
			}
			// get data for range and volume in an arrayList
			// convert the range and volume data in arrays to pass to sorts
			System.out.println(LocalTime.now());
			// start time
			// do one sort
			// end time
			//int[] volumeSorted = insertionSort(volume);
			int[] volumeSorted = mergeSort(volume);
			int[] rangeSorted = mergeSort(range);
			System.out.println(LocalTime.now());
			
			// Print Percentile report
			
			System.out.println("Score"+"\t"+"Volume"+"\t"+"Range"+"\t"+"Combination");
			System.out.println("5%"+"\t"+percentile(volumeSorted,5)+"\t"+percentile(rangeSorted,5)+"\t"+(double)actualCombinationScore(5,volume,range)/volume.length*100);
			System.out.println("15%"+"\t"+percentile(volumeSorted,15)+"\t"+percentile(rangeSorted,15)+"\t"+(double)actualCombinationScore(15,volume,range)/volume.length*100);
			System.out.println("25%"+"\t"+percentile(volumeSorted,25)+"\t"+percentile(rangeSorted,25)+"\t"+(double)actualCombinationScore(25,volume,range)/volume.length*100);
			System.out.println("50%"+"\t"+percentile(volumeSorted,50)+"\t"+percentile(rangeSorted,50)+"\t"+(double)actualCombinationScore(50,volume,range)/volume.length*100);
			System.out.println("75%"+"\t"+percentile(volumeSorted,75)+"\t"+percentile(rangeSorted,75)+"\t"+(double)actualCombinationScore(75,volume,range)/volume.length*100);
			System.out.println("85%"+"\t"+percentile(volumeSorted,85)+"\t"+percentile(rangeSorted,85)+"\t"+(double)actualCombinationScore(85,volume,range)/volume.length*100);
			System.out.println("95%"+"\t"+percentile(volumeSorted,95)+"\t"+percentile(rangeSorted,95)+"\t"+(double)actualCombinationScore(95,volume,range)/volume.length*100);
			
			for (int i=0;i<volumeSorted.length;i++){
				//System.out.println(volumeSorted[i]);
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static int actualCombinationScore(double score, int[] volume, int[] range){
		// range and volume scores to lookup
		int rangeScore = percentile(mergeSort(range),score);
		int volumeScore = percentile(mergeSort(volume),score);
		int j = 0;
		// count the number of instances above the scores.
		for (int i =0;i<volume.length;i++){
			if (volume[i]>=volumeScore && range[i]>=rangeScore) j++;
		}
		return j;
	}
	public static int percentile(int[] array, double score){
		int length = (int) (array.length*score/100);
		return array[length];
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
