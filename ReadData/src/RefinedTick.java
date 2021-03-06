

public class RefinedTick {
	private String startTime = new String();
	private String endTime = new String();
	private String price = new String();
	private int vol;
	private int delta;
	private int maxUp; 
	private int maxDown;
	private int cumDelta;
	
	
	public RefinedTick(String time1, String time2, String value, int num1, int num2, int num3, int num4, int num5){
		startTime = time1;
		endTime = time2;
		price = value;
		vol = num1;
		delta = num2;
		maxUp = num3;
		maxDown = num4;
		cumDelta = num5;
	}
	public void print(){
		System.out.println("Start Time:"+startTime);
		System.out.println("End Time:"+endTime);
		System.out.println("Price:"+price);
		System.out.println("Volume:"+vol);
		System.out.println("Sells:"+getSellOrders());
		System.out.println("Buys:"+getBuyOrders());
		System.out.println("Delta:"+delta);
		System.out.println("Max Up:"+maxUp);
		System.out.println("Max Down:"+maxDown);
		System.out.println("Cum Delta:"+cumDelta);
		return;
	}
	public String getStartTime(){
		return startTime;
	}
	public String getEndTime(){
		return endTime;
	}
	public String getPrice(){
		return price;
	}
	public int getVolume(){
		return vol;
	}
	public int getDelta(){
		return delta;
	}
	public int getMaxUp(){
		return maxUp;
	}
	public int getMaxDown(){
		return maxDown;
	}
	public int getCumDelta(){
		return cumDelta;
	}
	public int getBuyOrders(){
		if (delta>=0){
			return (vol-delta)/2+delta;
		} else {
			return (vol+delta)/2;
		}
	}
	public int getSellOrders(){
		return (vol-getBuyOrders());
	}
		
	
	
	

}
