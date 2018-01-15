

public class SignificantTick {
	private String instrument;
	private String date;
	private Time start;
	private Time end;
	private Price price;
	private int maxUp;
	private int maxDown;
	private int volume;
	private int delta;
	public SignificantTick(String instr, String dt, String startTime, String value, String up, String down, String vol, String del){
		instrument = instr;
		date = dt;
		start = new Time(startTime);
		price = new Price(value);
		maxUp = Integer.parseInt(up);
		maxDown = Integer.parseInt(down);
		volume = Integer.parseInt(vol);
		delta = Integer.parseInt(del);
	}
	public void addEndTime(String endTime){
		end = new Time(endTime);
	}
	public void addVolume(String vol){
		volume += Integer.parseInt(vol);
	}
	public void addDelta(String del){
		delta += Integer.parseInt(del);
	}
	public String getEndMovement(int movement){
		return price.addTicks(movement);
	}
	public int getMaxUp(){
		return maxUp;
	}
	public int getMaxDown(){
		return maxDown;
	}
	public String getInstrument(){
		return instrument;
	}
	public String getDate(){
		return date;
	}
	public Time getStartTime(){
		return start;
	}
	public Time getEndTime(){
		return end;
	}
	public Price getPrice(){
		return price;
	}
	public int getVolume(){
		return volume;
	}
	public int getDelta(){
		return delta;
	}
	
	public String toString(){
		return (instrument+ " "+ date+ " "+start.toString()+" "+end.toString()+" ");
	}

}
