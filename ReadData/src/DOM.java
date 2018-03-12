import java.util.*;

public class DOM {
	private ArrayList<HashMap<String,String>> dom = new ArrayList<HashMap<String,String>>();
	private ArrayList<HashMap<String,String>> icebergList = new ArrayList<HashMap<String,String>>();
	private int threshold;
	private String instrument;
	private String date;
	private Price[] bidAsk = new Price[2];
	// parameter for iceberg list. 
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
		adjustBidAsk(a);
		checkIceberg(a);

	}
	public void addTick(RefinedTick a){
		// find price in the dom
		int index = findPrice(a.getPrice());
		// check price in bidAsk
		int scenario;
		if (bidAsk[0]!=null && bidAsk[1]!=null){
			if (a.getPrice().equals(bidAsk[0].toString()) && a.getPrice().equals(bidAsk[1].toString())) scenario = 1;
			else if (a.getPrice().equals(bidAsk[0].toString())) scenario = 2;
			else if (a.getPrice().equals(bidAsk[1].toString())) scenario = 3;
			else scenario = 4;
		} else if (bidAsk[0]!=null){
			if (a.getPrice().equals(bidAsk[0].toString())) scenario = 2;
			else scenario = 4;
		} else {
			if (a.getPrice().equals(bidAsk[1].toString())) scenario = 3;
			else scenario = 4;
		}
		//System.out.println(scenario);
		int orders;
		switch (scenario){
		case 1: orders = Integer.parseInt(dom.get(index).get("sell"))+a.getSellOrders();
		dom.get(index).put("sell",Integer.toString(orders));
		orders = Integer.parseInt(dom.get(index).get("buy"))+a.getBuyOrders();
		dom.get(index).put("buy",Integer.toString(orders));
		checkIceberg(a);
		break;
		case 2: orders = Integer.parseInt(dom.get(index).get("sell"))+a.getSellOrders();
		dom.get(index).put("sell",Integer.toString(orders));
		if (a.getBuyOrders()>0) {
			dom.get(index).put("buy", Integer.toString(a.getBuyOrders()));
			bidAsk[1] = new Price(a.getPrice());
		}
		checkIceberg(a);
		break;
		case 3: orders = Integer.parseInt(dom.get(index).get("buy"))+a.getBuyOrders();
		dom.get(index).put("buy",Integer.toString(orders));
		if (a.getSellOrders()>0){
			dom.get(index).put("sell", Integer.toString(a.getSellOrders()));
			bidAsk[0] = new Price(a.getPrice());
		}
		checkIceberg(a);
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
			checkIceberg(a);
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
		//sortDom(dom);
		sortDomInsertionSort();
	}
	public void sortDomInsertionSort(){
		/*i ← 1
		while i < length(A)
		    j ← i
		    while j > 0 and A[j-1] > A[j]
		        swap A[j] and A[j-1]
		        j ← j - 1
		    end while
		    i ← i + 1
		end while*/
			
		int i = 1;
		int j;
		Price price1;
		Price price2;
		HashMap<String,String> a = new HashMap<String,String>();
		HashMap<String,String> b = new HashMap<String,String>();
		while (i<dom.size()){
			j=i;
			price1 = new Price(dom.get(j).get("price"));
			price2 = new Price(dom.get(j-1).get("price"));
			
			while ((j>0) && price1.greaterThan(price2)==1){
				// swap
				a = dom.get(j);
				b = dom.get(j-1);
				dom.set(j, b);
				dom.set(j-1, a);
				j--;
			}
			i++;
		}
	}
	private ArrayList<HashMap<String,String>> sortDom(ArrayList<HashMap<String,String>> list){
		// split
		System.out.println("sorting " +list.size());
		ArrayList<HashMap<String,String>> L = new ArrayList<HashMap<String,String>>();
		ArrayList<HashMap<String,String>> R = new ArrayList<HashMap<String,String>>();
		int size = list.size();
		if (size<=1) return list;
		for (int i = 0;i<(int)size/2;i++){
			L.add(list.get(i));
		}
		for (int i = (int)size/2;i<size;i++){
			R.add(list.get(i));
		}
		// sort halves
		System.out.println(L.size()+" "+R.size());
		if (L.size()>1) L = sortDom(L);
		System.out.println("L size: "+L.size());
		if (R.size()>1) R = sortDom(R);

		// Merge halves
		ArrayList<HashMap<String,String>> sortedList = new ArrayList<HashMap<String,String>>();
		Price lPrice;
		Price rPrice;
		System.out.println("List :"+list.size());
		System.out.println(L.size()+" "+R.size());
		System.out.println(L.isEmpty()+" "+R.isEmpty());
		while (L.isEmpty() && R.isEmpty()){
			if(L.isEmpty() || R.isEmpty()){
				if (L.isEmpty()) {
					sortedList.add(R.get(0));
					R.remove(0);
				} else {
					sortedList.add(L.get(0));
					L.remove(0);
				}
			} else {
				lPrice = new Price(L.get(0).get("price"));
				rPrice = new Price(R.get(0).get("price"));
				if (lPrice.greaterThan(rPrice)!=1){
					sortedList.add(L.get(0));
					L.remove(0);
				} else {
					sortedList.add(R.get(0));
					R.remove(0);
				}
			}
		}
		return sortedList;
	}
	private int findPrice(String price){
		for (int i=0;i<dom.size();i++){
			if (dom.get(i).get("price").equals(price)) return i;
		}
		return -1;
	}
	public void print(){
		System.out.println("Price"+"\t"+"Sells"+"\t"+"Buys");
		for (int i = 0; i<dom.size();i++){
			System.out.println(dom.get(i).get("price")+"\t"+dom.get(i).get("sell")+"\t"+dom.get(i).get("buy"));
		}
		//System.out.println("Bid-Ask: " + bidAsk[0].toString()+"\t"+bidAsk[1].toString()); 
	}
	private int[] getOrders(String Price){
		int[] result = new int[2];
		for (int i=0;i<dom.size();i++){
			if (Price.equals(dom.get(i).get("price"))){
				result[0] = Integer.parseInt(dom.get(i).get("sell"));
				result[1] = Integer.parseInt(dom.get(i).get("buy"));
				break;
			}
		}
		return result;
	}
	private void checkIceberg(RefinedTick a){
		int[] orders = getOrders(a.getPrice());
		HashMap<String,String> iceberg = new HashMap<String,String>();
		if (orders[0]>=threshold || orders[1]>=threshold){
			iceberg.put("price",bidAsk[0].toString());
			iceberg.put("sell",Integer.toString(orders[0]));
			iceberg.put("buy",Integer.toString(orders[1]));
			iceberg.put("time",a.getEndTime());
			iceberg.put("maxUp",Integer.toString(a.getMaxUp()));
			iceberg.put("maxDown",Integer.toString(a.getMaxDown()));
			icebergList.add(iceberg);
		}
	}
	public void publishIcebergList(){
		for (int i=0;i<icebergList.size();i++){
			System.out.println(icebergList.get(i).get("time")+"\t"+icebergList.get(i).get("price")+"\t"+icebergList.get(i).get("sell")+"\t"+icebergList.get(i).get("buy")+"\t"+icebergList.get(i).get("maxUp")+"\t"+icebergList.get(i).get("maxDown"));
		}
	}
}
