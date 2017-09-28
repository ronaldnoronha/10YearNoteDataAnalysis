import java.io.*;
import java.util.*;


public class testPercentileScore {

	public static void main(String[] args) {
		try{
			String date = "2017-9-21";
			File fw = new File("TYAZ17_"+date+"_15m.txt");
			Scanner in = new Scanner(fw);
			int[][] data = new int[96][2];
			String[] line;
			int k = 0;
			while(in.hasNextLine()){
				line = in.nextLine().split(",");
				data[k][0] = Integer.parseInt(line[4]);
				data[k][1] = Integer.parseInt(line[7]);
				k++;
			}
			double[] score = rowScore(data);

			for (int i =0;i<score.length;i++){
				System.out.println(String.format("%02d",(int)i/4)+":"+String.format("%02d",(i%4)*15)+":00.000"+"\t"+score[i]);
			}

			// Add time lookup for 15m files
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static double[] rowScore(int[][] data){
		double[] result = new double[96];
		try{
			File fw  = new File("frontRunningContracts.txt");
			Scanner in = new Scanner(fw);
			String[] line;
			ArrayList <String> listOfFiles = new ArrayList <String>();
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				listOfFiles.add(createFileName(line[1],line[0],"15m"));
			}
			in.close();

			int[][] volume = new int[96][listOfFiles.size()];
			int[][] range = new int[96][listOfFiles.size()];

			int score = 0;
			int index;
			for (int i = 0;i<listOfFiles.size();i++){
				fw = new File(listOfFiles.get(i));
				in = new Scanner(fw);
				while (in.hasNextLine()){
					line = in.nextLine().split(",");
					index = getTimeIndex(line[0]);
					if (index!=-1) {
						volume[index][i] = Integer.parseInt(line[4]);
						range[index][i] = Integer.parseInt(line[7]);
					}				
				}
			}
			for (int i = 0;i<result.length;i++){
				for (int j =0;j<listOfFiles.size();j++){
					if (data[i][0]<=volume[i][j] && data[i][1]<=range[i][j]){
						score++;
					}
				}
				result[i] = 100.0-score*100.0/listOfFiles.size();
				score = 0;
			}
			File fw1 = new File("volumetest.txt");
			PrintWriter out = new PrintWriter(fw1);
			for (int i =0;i<volume.length;i++){
				for (int j =0;j<volume[0].length;j++){
					out.print(volume[i][j]+",");
				}
				out.println();
			}
			out.close();
			fw1 = new File("rangetest.txt");
			out = new PrintWriter(fw1);
			for (int i =0;i<range.length;i++){
				for (int j =0;j<range[0].length;j++){
					out.print(range[i][j]+",");
				}
				out.println();
			}
			out.close();

		} catch (Exception e){
			System.out.println(e.getMessage());
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