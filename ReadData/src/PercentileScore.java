import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PercentileScore {
	int[][] data = new int[96][2];
	private double score7AM;
	double[] score = new double[96];
	public PercentileScore(String date, String instrument) {
		// read file
		readFile(instrument, date);
		// get score for each time step
		rowScore();
	}
	public double getScore(String time){
		int index = getTimeIndex(time);
		return score[index];
	}
	public void readFile(String instrument, String date){
		try{
			File fw = new File(instrument +"_"+date+"_15m.txt");
			Scanner in = new Scanner(fw);
			String[] line;
			int k;
			while(in.hasNextLine()){
				line = in.nextLine().split(",");
				k = getTimeIndex(line[0]);
				data[k][0] = Integer.parseInt(line[4]);
				data[k][1] = Integer.parseInt(line[7]);
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void rowScore(){
		// open range and volume file.
		try{
			File fw1 = new File("Range.txt");
			Scanner in1 = new Scanner(fw1);
			File fw2 = new File("Volume.txt");
			Scanner in2 = new Scanner(fw2);
			String[] line1;
			String[] line2;
			int total = 0;
			int timeIndex = 0;
			while (in1.hasNextLine()){
				line1 = in1.nextLine().split(",");
				line2 = in2.nextLine().split(",");
				System.out.println(line1.length);
				score[timeIndex] = 0;
				for (int i = 0; i<line1.length; i++){
					total++;
					System.out.println(line1[i]+ " "+line2[i]);
					System.out.println(timeIndex);
					System.out.println(data[timeIndex][1]+" "+data[timeIndex][2]);
					if (Integer.parseInt(line1[i])<=data[timeIndex][2] && Integer.parseInt(line2[i])<=data[timeIndex][1]){
						score[timeIndex]+=1;
						System.out.print(score+" ");
					}
					System.out.println();
				}
				score[timeIndex] = score[timeIndex]/total;
				timeIndex++;
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public double getScore7am(){
		return score7AM;
	}

	public static int getTimeIndex (String time){
		String[] times = new String[96];
		for (int i=0;i<times.length;i++){
			times[i] = String.format("%02d",(int)i/4)+":"+String.format("%02d",(i%4)*15)+":00.000";
			if (time.equals(times[i])) return i;
		}
		return -1;
	}
	public static String createFileName(String instrument, String date, String interval){
		String result = instrument;
		String[] dateString = date.split("-");
		result = result+"_"+dateString[2];
		if (Integer.parseInt(dateString[1])<10) dateString[1] = "0"+dateString[1];
		result = result+"-"+ dateString[1]	+ "-" + dateString[0]+ "_"+interval+".txt";
		result = instrument + "_" + date + "_" + interval + ".txt";
		return result;	 
	}

}
