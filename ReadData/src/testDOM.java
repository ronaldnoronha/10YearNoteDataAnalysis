import java.io.File;
import java.util.Scanner;

public class testDOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			String date = "2016-2-26";
			String instr = "TYAH16";
			File fw  = new File(instr+"_"+date+"_refined.txt");
			Scanner in = new Scanner(fw);
			File fw1 = new File(instr+"_"+date+"_refined_movement.txt");
			Scanner in1 = new Scanner(fw1);
			String [] line;
			String [] line1;
			int counter = 0;
			line = in.nextLine().split(",");
			line1 = in1.nextLine().split(",");
			RefinedTick a = new RefinedTick(line[0],line[1],line[2],Integer.parseInt(line[3]),Integer.parseInt(line[4]),Integer.parseInt(line1[3]),Integer.parseInt(line1[4]),Integer.parseInt(line1[5]));
			//a.print();
			DOM dom = new DOM(a,5000,instr,date);
			//dom.print();
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				line1 = in1.nextLine().split(",");
				a = new RefinedTick(line[0],line[1],line[2],Integer.parseInt(line[3]),Integer.parseInt(line[4]),Integer.parseInt(line1[3]),Integer.parseInt(line1[4]),Integer.parseInt(line1[5]));
				//a.print();
				dom.addTick(a);
				counter++;
				//dom.print();
			}
			//dom.print();
			System.out.println("IcebergList");
			dom.publishIcebergList();

		} catch (Exception e){
			System.out.println(e.getMessage());
		}

	}

}
