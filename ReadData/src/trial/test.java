package trial;
import java.util.*;

public class test {
	public static void main(String[] args){
		/*int[] A = {-1, -1, -1};
		System.out.println(sum(A,0));
		String a  = "a0ie";
		//System.out.println(a.contains("[0-9]"));
		ArrayList<Integer> stops = new ArrayList<Integer>();
		//stops.add(0);
		//stops.add(1);
		System.out.println(stops.contains(0));*/
		//[40, 40, 100, 80, 20], [3, 3, 2, 2, 3], 3, 5, 200
		int[] A = {40, 40, 40, 100, 80, 20};
		int[] B =  {3, 3, 1, 2, 2, 3};
		int M = 3;
		int X = 5;
		int Y = 200;
		int queueStart = 0;
		int num = 0;
		int passengers = 0;
		while (queueStart<A.length){
			passengers = passengersTaken(A,B,X,Y,queueStart);
			num+=numStops(B,queueStart,passengers)+1;
			queueStart+=passengers;
			System.out.println(num);
			System.out.println(queueStart);
			System.out.println(passengers);
			
		}
		System.out.println(num);
		/*System.out.println(passengersTaken(A,B,X,Y,queueStart));
		queueStart+=passengersTaken(A,B,X,Y,queueStart);
		System.out.println(passengersTaken(A,B,X,Y,queueStart));*/

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
