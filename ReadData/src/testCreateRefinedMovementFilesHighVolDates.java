import java.io.*;
import java.util.*;

public class testCreateRefinedMovementFilesHighVolDates {
	public static void main(String[] args) {
		try {
			File fw = new File("highVolDates.txt");
			Scanner in = new Scanner(fw);
			String[] line;
			CreateRefinedTickFile a;
			MovementAnalysis b;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				a = new CreateRefinedTickFile(line[1]+"_"+line[0]+".txt");
				b = new MovementAnalysis(line[1]+"_"+line[0]+"_"+"refined"+".txt",3);
				//System.out.println(in.nextLine());
			} 
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}
}