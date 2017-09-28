import java.io.*;
import java.util.*;
public class testPeakAnalysis {
	public static void main(String[] args) {
		try{
			File fw  = new File("frontRunningContracts.txt");
			Scanner in = new Scanner(fw);
			String[] line;
			ArrayList <String> listOfFiles = new ArrayList <String>();
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				//System.out.println(line[0]+","+line[1]+","+line[2]+","+line[3]);
				listOfFiles.add(createFileName(line[1],line[0],"15m"));
			}
			in.close();

			int[][] volume = new int[96][listOfFiles.size()];
			int[][] range = new int[96][listOfFiles.size()];
			int index;
			for (int i = 0;i<listOfFiles.size();i++){
				fw = new File(listOfFiles.get(i));
				in = new Scanner(fw);
				while (in.hasNextLine()){
					line = in.nextLine().split(",");
					index = getTimeIndex(line[0]);
					if (index!=-1) volume[index][i] = Integer.parseInt(line[3]);
				}
			}
			double[] averageVolume = new double[96];
			averageVolume = rowAverage(volume);
			
			for (int i =0;i<averageVolume.length;i++){
				System.out.println(String.format("%02d",(int)i/4)+":"+String.format("%02d",(i%4)*15)+":00.000"+"\t"+averageVolume[i]);
			}

			// Add time lookup for 15m files
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	public static double[] rowAverage(int[][] data){
		double[] result = new double[data.length];
		int sum = 0;
		int counter = 0;
		for (int i=0;i<data.length;i++){
			for (int j=0;j<data[0].length;j++){
				if (data[i][j]!=0) {
					sum+=data[i][j];
					counter++;
				}
			}
			result[i]=sum/counter;
			sum = 0;
			counter = 0;
		}
		return result;
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

