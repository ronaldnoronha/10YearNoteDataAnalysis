package trial;
import java.util.*;

public class test {
	public static void main(String[] args){
		int month = 3;
        String monthString;
        switch (month) {
            case 1:  monthString = "January";
                     break;
            case 2:  monthString = "February";
                     break;
            case 3:  monthString = "March";
                     break;
            case 4:  monthString = "April";
                     break;
            case 5:  monthString = "May";
                     break;
            case 6:  monthString = "June";
                     break;
            case 7:  monthString = "July";
                     break;
            case 8:  monthString = "August";
                     break;
            case 9:  monthString = "September";
                     break;
            case 10: monthString = "October";
                     break;
            case 11: monthString = "November";
                     break;
            case 12: monthString = "December";
                     break;
            default: monthString = "Invalid month";
                     break;
        }
        System.out.println(monthString);

	}

	   

	        

	public static int passengersTaken(int[] A, int[] B, int X, int Y, int queueStart){
		int totalPassengers=0;
		int totalWeight=0;
		int numPassengers= 0;
		while ((queueStart+numPassengers)<A.length) {
			if (totalPassengers+1<=X && (totalWeight+A[queueStart+numPassengers])<=Y) {
				totalPassengers+=1;
				totalWeight+=A[queueStart+numPassengers];
				numPassengers+=1;
			}
			else {
				return numPassengers; 
			}
		}
		return numPassengers;  
	}
	public static int numStops(int[] B, int queueStart,int passengersTaken){
		ArrayList<Integer> stops = new ArrayList<Integer>();
		for (int i = queueStart;i<queueStart+passengersTaken;i++){
			if (!stops.contains(B[i])) stops.add(B[i]);
		}
		return stops.size();
	}
	public static int sum(int[] A, int index){
		int total=0;
		if (index<0) return 0;
		for (int i =0;i<index+1;i++){
			total+=A[i];
		}    
		return total;    
	} 

}
