import java.io.*;
import java.util.*;
public class MovementAnalysis {
	private ArrayList<Integer> upToleranceIndex = new ArrayList<Integer>();
	private ArrayList<Integer> downToleranceIndex = new ArrayList<Integer>();
	private ArrayList <String> price  = new ArrayList <String>();
	public MovementAnalysis(String filename, int tolerance){
		try{
			File fw = new File(filename);
			Scanner in = new Scanner(fw);

			String[] line;
			while (in.hasNextLine()){
				line = in.nextLine().split(",");
				price.add(line[2]);
			}			
			in.close();
			for (int i = 0; i<price.size();i++){
				System.out.println("Position "+i);
				upToleranceIndex.add(maxMovementIndex(new ArrayList<String>(price.subList(i, price.size())),tolerance,1));
				if (i==0){
					break;
				}
				System.out.println("Position "+i+" up "+upToleranceIndex.get(i));
				downToleranceIndex.add(maxMovementIndex(new ArrayList<String>(price.subList(i, price.size())),tolerance,-1));
				
			}
			String publishName = filename.substring(0,filename.indexOf("."))+"_movmement.txt";
			publishMovementAnalysis(filename);
		}catch (Exception e){
			System.out.println(e.toString());
		}
	}
	public void publishMovementAnalysis(String filename){
		try{
			File fw = new File(filename);
			PrintWriter out = new PrintWriter(fw);
			for (int i=0;i<price.size();i++){
				out.println(price.get(i)+","+upToleranceIndex.get(i)+","+downToleranceIndex.get(i));
			}
			out.close();
		} catch (Exception e){
			
		}
	}
	public int maxMovementIndex(ArrayList<String> price,int tolerance,int direction){
		/*direction 1 for up and -1 for down*/
		int slIndex, nextTickIndex;
		if (direction==1){
			slIndex = find(price,addTicks(price.get(0),-3),-1);
			nextTickIndex = find(price,addTicks(price.get(0),1),1);
		} else if (direction==-1){
			slIndex = find(price,addTicks(price.get(0),3),1);
			nextTickIndex = find(price,addTicks(price.get(0),1),-1);
		} else{
			return -100;
		}
		System.out.println("SL "+slIndex);
		System.out.println("Next "+nextTickIndex);
		System.out.println("trailing SL "+addTicks(price.get(0),-3));
		System.out.println(price.size());
		System.out.println(price.get(0));
		ArrayList<String> reducedPrice;
		if (slIndex<nextTickIndex && slIndex!=-1) {
			return slIndex;
		} else {
			reducedPrice = new ArrayList<String>(price.subList(nextTickIndex, price.size()));
			return nextTickIndex + maxMovementIndex(reducedPrice,tolerance,direction);
		}
	}
	public int find(ArrayList<String> price,String value, int param){
		/* 1 for greater than or equal to price, 0 for equal to price 
		 * and -1 for less than or equal to price. */		
		if (param==1){
			for (int i=0;i<price.size();i++){
				if(toDouble(price.get(i))>=toDouble(value)){
					return i+1;
				}
			}
		}else if (param ==0){
			for (int i=0;i<price.size();i++){
				if(toDouble(price.get(i))==toDouble(value)){
					return i+1;
				}
			}

		}else if (param ==-1){
			for (int i=0;i<price.size();i++){
				if(toDouble(price.get(i))<=toDouble(value)){
					return i+1;
				}
			}
		}
		return -1;


	}
	public String addTicks(String price, int ticks){
		String newPrice;
		newPrice = toFractions(toDouble(price)+(double)ticks/64);
		return newPrice;
	}
	public double toDouble(String value){
		double output;
		if (value.contains("/")){
			String[] valueArray = value.split(" ");
			output = Double.parseDouble(valueArray[0]);
			valueArray = valueArray[1].split("/");
			output += Double.parseDouble(valueArray[0])/Double.parseDouble(valueArray[1]);
		}
		else output = Double.parseDouble(value);
		return output;
	}
	public String toFractions(Double value){
		int fraction = (int)((value-Math.floor(value))*100);
		if (fraction==0) return String.format("%.0f",value);
		for(int i =0;i<63;i++){
			if (fraction == Math.round(100*(i+1)/64)){
				if (i==64) return (int)Math.floor(value)+"";
				else return (int)Math.floor(value)+" "+String.format("%.1f",(i+1)*0.5)+"/"+32;
			}
		}
		return null;
	}
}
