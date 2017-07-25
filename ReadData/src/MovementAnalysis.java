import java.io.*;
import java.util.*;
public class MovementAnalysis {
	public MovementAnalysis(String filename, int tolerance){
		try{
			File fw = new File(filename);
			Scanner in = new Scanner(fw);
			ArrayList<Integer> maxUp = new ArrayList<Integer>();
			ArrayList<Integer> maxDown = new ArrayList<Integer>();
			ArrayList <String> highWatermark  = new ArrayList <String>();
			ArrayList <String> lowWatermark = new ArrayList <String>();
			String[] line = in.nextLine().split(",");
			maxUp.add(0);
			maxDown.add(0);
			highWatermark.add(line[1]);
			lowWatermark.add(line[1]);
			in.close();
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
