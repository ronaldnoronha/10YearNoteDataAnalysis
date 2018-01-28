import java.io.File;
import java.util.Scanner;

public class testDOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			File fw  = new File("TYAH16_2016-1-4_refined.txt");
			Scanner in = new Scanner(fw);
			File fw1 = new File("TYAH16_2016-1-4_refined_movement.txt");
			Scanner in1 = new Scanner(fw1);
			String [] line;
			String [] line1;
			int counter = 0;
			line = in.nextLine().split(",");
			line1 = in1.nextLine().split(",");
			RefinedTick a = new RefinedTick(line[0],line[1],line[2],Integer.parseInt(line[3]),Integer.parseInt(line[4]),Integer.parseInt(line1[3]),Integer.parseInt(line1[4]),Integer.parseInt(line1[5]));
			a.print();
			DOM dom = new DOM(a,1000,"TYAH16","2016-1-4");
			dom.print();
			while (in.hasNextLine() && counter<3){
				line = in.nextLine().split(",");
				line1 = in1.nextLine().split(",");
				a = new RefinedTick(line[0],line[1],line[2],Integer.parseInt(line[3]),Integer.parseInt(line[4]),Integer.parseInt(line1[3]),Integer.parseInt(line1[4]),Integer.parseInt(line1[5]));
				a.print();
				dom.addTick(a);
				//dom.print();
				counter++;
			}
			//}

		} catch (Exception e){
			System.out.println(e.getMessage());
		}

	}

}
