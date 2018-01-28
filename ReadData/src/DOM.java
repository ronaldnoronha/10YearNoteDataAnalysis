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
		// find price in the dom
		int index = findPrice(a.getPrice());
		// check price in bidAsk
		int scenario;
		if (a.getPrice().equals(bidAsk[0].toString()) && a.getPrice().equals(bidAsk[1].toString())) scenario = 1;
		else if (a.getPrice().equals(bidAsk[0].toString())) scenario = 2;
		else if (a.getPrice().equals(bidAsk[1].toString())) scenario = 3;
		else scenario = 4;
		int orders;
		switch (scenario){
		case 1: orders = Integer.parseInt(dom.get(index).get("sell"))+a.getSellOrders();
		dom.get(index).put("sell",Integer.toString(orders));
		orders = Integer.parseInt(dom.get(index).get("buy"))+a.getBuyOrders();
		dom.get(index).put("buy",Integer.toString(orders));
		break;
		case 2: orders = Integer.parseInt(dom.get(index).get("sell"))+a.getSellOrders();
		dom.get(index).put("sell",Integer.toString(orders));
		if (a.getBuyOrders()>0) {
			dom.get(index).put("buy", Integer.toString(a.getBuyOrders()));
			bidAsk[1] = new price(a.getPrice());
		}
		break;
		case 3: orders = Integer.parseInt(dom.get(index).get("buy"))+a.getBuyOrders();
		dom.get(index).put("buy",Integer.toString(orders));
		if (a.getSellOrders()>0){
			dom.get(index).put("sell", Integer.toString(a.getSellOrders()));
			bidAsk[0] = new price(a.getPrice());
		}
		break; 
		case 4: 
			if (index==-1){
				addNewPriceToDom(a);
				adjustBidAsk(a);
			} else{
				if (a.getBuyOrders()>0) {
					dom.get(index).put("buy", Integer.toString(a.getBuyOrders()));
				}
				if (a.getSellOrders()>0){
					dom.get(index).put("sell", Integer.toString(a.getSellOrders()));
				}
				adjustBidAsk(a);
			}
			break;
		}
	}
	private void adjustBidAsk(RefinedTick a){
		if (a.getBuyOrders()>0){
			bidAsk[1] = new Price(a.getPrice());
		}
		if (a.getSellOrders()>0){
			bidAsk[0] = new Price(a.getPrice());
		}
	}
	private void addNewPriceToDom(RefinedTick a){
		// add to dom
		HashMap<String,String> b = new HashMap<String,String>();
		b.put("price",a.getPrice());
		b.put("buy", Integer.toString(a.getBuyOrders()));
		b.put("sell", Integer.toString(a.getSellOrders()));
		b.put("volume", Integer.toString(a.getVolume()));
		b.put("delta", Integer.toString(a.getDelta()));
		dom.add(b);
		// sort dom
		sortDom(dom);

	}
	private void sortDom(ArrayList<HashMap<String,String>> list){
		// split
		int size = list.size();
		ArrayList<HashMap<String,String>> L = new ArrayList<HashMap<String,String>>();
		ArrayList<HashMap<String,String>> R = new ArrayList<HashMap<String,String>>();
		for (int i = 0;i<list.size()/2;i++){
			L.add(list.get(i));
		}
		for (int i = list.size()/2+1;i<list.size();i++){
			R.add(list.get(i));
		}
		// sort halves
		L = sortDom(L);
		R = sortDom(R);
		// Merge halves
		int i = 0;
		while (i<list.size()){
			if (L.get(0).get("price"))
		}
	}
	private int findPrice(String price){
		for (int i=0;i<dom.size();i++){
			if (dom.get(i).get("price").equals(price)) return i;
		}
		return -1;
	}

}
