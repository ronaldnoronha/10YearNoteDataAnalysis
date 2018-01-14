
public class Time {
	private double seconds;
	private String timeString;
	public Time(String time){
		String[] line = time.split(":");
		seconds = Double.parseDouble(line[0])*60*60+Double.parseDouble(line[1])*60+Double.parseDouble(line[2]);
		timeString = time;
	}
	public double getSeconds(){
		return seconds;
	}
	public boolean checkTimeWithin(Time a, Time b){
		if (seconds>=a.getSeconds() && seconds<=b.getSeconds()){
			return true;
		}
		return false;
	}
	public String toString(){
		return timeString;
	}
}
