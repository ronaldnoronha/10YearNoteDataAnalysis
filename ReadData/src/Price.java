
public class Price {
	private String price;
	public Price(String value){
		price = value;
	}
	public double toDouble(){
		String[] line = price.split(" ");
		Double num = Double.parseDouble(line[0]);
		Double fraction = 0.00;
		if (line.length>1) {
			String[] line2 = line[1].split("/");
			fraction = Double.parseDouble(line2[0])/Double.parseDouble(line2[1]);
		}
		return num+fraction;
	}
	public String toString(){
		return price;
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
	public String addTicks(int ticks){
		String newPrice;
		newPrice = toFractions(toDouble()+(double)ticks/64);
		return newPrice;
	}
	public int greaterThan(Price a){
		if (toDouble()>a.toDouble()){
			return 1;
		} else if (toDouble()==a.toDouble()){
			return 0;
		} else {
			return -1;
		}
	}
	public boolean equals(Price a) {
		if(toDouble()==a.toDouble()) {
			return true;
		} else {
			return false;
		}
	}
}
