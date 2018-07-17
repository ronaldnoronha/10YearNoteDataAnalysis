
public class DOMTick {
	public Price price;
	private Time startTime;
	private int bids;
	private int asks;
	public DOMTick(String time, String p, String v, String d) {
		startTime = new Time(time);
		price = new Price(p);

		int volume = Integer.parseInt(v);
		int delta = Integer.parseInt(d);

		if (delta>=0) {
			asks = (volume-delta)/2+delta;
			bids = (volume-delta)/2;
		} else {
			bids = (volume+delta)/2-delta;
			asks = (volume+delta)/2;
		}
	}

	public void addBids(int b) {
		bids+= b;
	}

	public void addAsks(int a) {
		asks+= a;
	}
	
	public void setBids(int b) {
		bids = b;
	}
	
	public void setAsks(int a) {
		asks = a;
	}

	public void setStartTime(String time) {
		startTime = new Time(time);
	}

	public int getBids() {
		return bids;
	}

	public int getAsks() {
		return asks;
	}

	public Time getStartTime() {
		return startTime;
	}
	
	public String toString() {
		return startTime.toString()+" "+price.toString()+" "+bids+" "+asks;
	}
}
