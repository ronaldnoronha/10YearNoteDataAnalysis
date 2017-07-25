import java.io.*;
import java.util.*;
public class CreateRefinedTickFile {
	private ArrayList<String> price = new ArrayList<String>();
	private ArrayList<String> startTime = new ArrayList<String>();
	private ArrayList<String> endTime = new ArrayList<String>();
	private ArrayList<Integer> volume = new ArrayList<Integer>();
	private ArrayList<Integer> delta = new ArrayList<Integer>();
	public CreateRefinedTickFile(String filename){
		try{
			File fw = new File(filename);
			Scanner in = new Scanner(fw);
			String[] line = in.nextLine().split(",");
			//ArrayList<String> price = new ArrayList<String>();
			price.add(line[1]);
			//ArrayList<String> startTime = new ArrayList<String>();
			startTime.add(line[0]);
			//ArrayList<String> endTime = new ArrayList<String>();
			endTime.add(line[0]);
			//ArrayList<Integer> volume = new ArrayList<Integer>();
			volume.add(Math.abs(Integer.parseInt(line[2])));
			//ArrayList<Integer> delta = new ArrayList<Integer>();
			delta.add(Integer.parseInt(line[2]));
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				if (price.get(price.size()-1).equals(line[1])){
					endTime.set(endTime.size()-1, line[0]);
					volume.set(volume.size()-1, volume.get(volume.size()-1)+Math.abs(Integer.parseInt(line[2])));
					delta.set(delta.size()-1, delta.get(volume.size()-1)+Integer.parseInt(line[2]));
				} else {
					price.add(line[1]);
					startTime.add(line[0]);
					endTime.add(line[0]);
					volume.add(Math.abs(Integer.parseInt(line[2])));
					delta.add(Integer.parseInt(line[2]));
				}
			}
			in.close();
			publishRefinedTicks(filename);
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}
	public void publishRefinedTicks(String filename){
		try{
			String refinedTickFile = filename.substring(0,filename.indexOf('.'))+"_refined.txt";
			File fw = new File(refinedTickFile);
			PrintWriter out = new PrintWriter(fw);
			for (int i=0;i<price.size();i++){
				out.println(startTime.get(i)+","+endTime.get(i)+","+price.get(i)+","+volume.get(i)+","+delta.get(i));
			}
			out.close();
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}

}
