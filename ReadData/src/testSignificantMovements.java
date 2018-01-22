import java.util.*;
import java.io.*;
import java.time.LocalTime;

public class testSignificantMovements {

	public static void main(String[] args) {
		System.out.println(LocalTime.now());
		//ArrayList<SignificantTick> a = getSignificantRefinedTicks("2017-12-13","TYAZ17");
		//System.out.println(a.toString());
		SignificantMovements a = new SignificantMovements();
		System.out.println(LocalTime.now());
	}
}
