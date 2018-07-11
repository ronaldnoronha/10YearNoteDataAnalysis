
public class Tick {
	public Price price;
	public NewDate date;
	public Time startTime;
	public Time endTime;
	public int buyOrders;
	public int sellOrders;
	public Tick(String line) {
		String[] words = line.split(", ");
		date = new NewDate(words[0]);
		startTime = new Time(words[1]);
		endTime = new Time(words[1]);
		price = new Price(words[2]);
		buyOrders = Integer.parseInt(words[7]);
		sellOrders = Integer.parseInt(words[8]);
		//System.out.println(line);
	}
	public String print() {
		String result = "";
		result+=date.toString()+",";
		result+=startTime.toString()+",";
		result+=endTime.toString()+",";
		result+=price.toString()+",";
		result+=buyOrders+",";
		result+=sellOrders+",";
		return result;
	}
	
}
