import java.io.*;
import java.time.LocalTime;
import java.util.*;


public class testPercentileScore {

	public static void main(String[] args) {
		System.out.println(LocalTime.now());
		PercentileScore a = new PercentileScore("2017-11-10","TYAZ17");
		System.out.println(a.getScore("07:00:00.000"));
		System.out.println(LocalTime.now());
	}	
}