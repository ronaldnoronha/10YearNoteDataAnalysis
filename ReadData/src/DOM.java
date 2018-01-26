import java.util.*;

public class DOM {
	private ArrayList<HashMap<String,String>> dom = new ArrayList<HashMap<String,String>>();
	private int threshold;
	private String instrument;
	private String date;
	private Price[] bidAsk = new Price[2];
	public DOM(RefinedTick a,int icebergThreshold, String instr, String dt){
		HashMap<String,String> b = new HashMap<String,String>();
		b.put("price",a.getPrice());
		b.put("buy", Integer.toString(a.getBuyOrders()));
		b.put("sell", Integer.toString(a.getSellOrders()));
		b.put("volume", Integer.toString(a.getVolume()));
		b.put("delta", Integer.toString(a.getDelta()));
		
		threshold = icebergThreshold;
		instrument = instr;
		date = dt;
		
		// add refined tick to dom
		dom.add(b);
		
		// price to bidAsk
		if (a.getBuyOrders()>0){
			bidAsk[0] = new Price(b.get("price"));
		}
		if (a.getSellOrders()>0){
			bidAsk[1] = new Price(b.get("price"));
		}
		
	}
	private void addTick(RefinedTick a){
		// check price in bidAsk
		if (a.getPrice().equals(bidAsk[0].toString())){
			
		}
		
	}

}
