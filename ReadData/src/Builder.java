import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class Builder {
	private String fileName;
	private String instrument;
	public Builder(String instr) {
		instrument = instr;
		fileName = instrument+".txt";
	}
	
	public void readFile(){
		try {
			File file = new File(fileName);
			Scanner in = new Scanner(file);
			// just to pass through the header
			String tmp = in.nextLine();
			System.out.println(tmp);
			int counter= 0;
			ArrayList<Tick> dayList = new ArrayList();
			while (in.hasNextLine() && counter<15) {
				Tick a = new Tick(in.nextLine());
				
				System.out.println(a.print());
				if (a.price.equals(b.price)) {
					
				}
				b = a;
				counter++;
			}
			in.close();
		} catch (Exception e) {
			System.out.println("error: "+e.toString());
		}
		
		
	}
	private Tick createTick(String line) {
		Tick a = new Tick(line);
		return a;
	}
	

}
