import java.io.*;
import java.util.*;

public class CreateDataForPercentile {
	private String[] times = new String[96];
	private int[][] volume;
	private int[][] range;
	private int numDays;
	public CreateDataForPercentile(){
		createTimes();
		getSize();
		populateMatrices();
		publish();
	}
	public void createTimes(){
		for (int i=0;i<times.length;i++){
			times[i] = String.format("%02d",(int)i/4)+":"+String.format("%02d",(i%4)*15)+":00.000";
		}
	}
	public void getSize(){
		int counter = 0;
		try{
			File fw = new File("frontRunningContracts.txt");
			Scanner in = new Scanner(fw);
			while (in.hasNextLine()){
				String line = in.nextLine();
				counter++;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		if (counter>0){
			volume = new int[96][counter];
			range = new int[96][counter];
		}
		numDays = counter;
	}
	public void populateMatrices(){
		try{
			File fw = new File("frontRunningContracts.txt");
			Scanner in = new Scanner(fw);
			String[] line;
			int counter = 0;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");				
				addVolumeRange(line[1],line[0],counter);
				counter++;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void addVolumeRange(String instrument, String date, int counter){
		try{
			File fw = new File(instrument+"_"+date+"_15m.txt");
			Scanner in = new Scanner(fw);
			String[] line;
			int index;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");				
				index = getTimeIndex(line[0]);
				volume[index][counter] = Integer.parseInt(line[4]);
				range[index][counter] = Integer.parseInt(line[7]);
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public int getTimeIndex(String time){
		for (int i=0;i<times.length;i++){
			times[i] = String.format("%02d",(int)i/4)+":"+String.format("%02d",(i%4)*15)+":00.000";
			if (time.equals(times[i])) return i;
		}
		return -1;
	}
	public void publish(){
		try{
			File fw = new File("Range.txt");
			PrintWriter out = new PrintWriter(fw);
			for (int i =0; i<96;i++){
				for (int j=0;j<numDays;j++){
					if (j==numDays-1) {
						out.print(range[i][j]);
						out.println();
					}
					else{
						out.print(range[i][j]+",");
					}
				}				
			}
			out.close();
			fw = new File("Volume.txt");
			out = new PrintWriter(fw);
			for (int i =0; i<96;i++){
				for (int j=0;j<numDays;j++){
					if (j==numDays-1) {
						out.print(volume[i][j]);
						out.println();
					}
					else{
						out.print(volume[i][j]+",");
					}
				}				
			}
			out.close();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}
