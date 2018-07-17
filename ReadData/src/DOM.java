import java.util.ArrayList;
import java.util.HashMap;

public class DOM {
	private ArrayList<DOMTick> dom;
	private Price prevPrice = null;
	public DOM() {
		dom = new ArrayList<DOMTick>();
	}
	/*
	public DOM(String fileName) {
		try {
			File f = new File(fileName);
			Scanner in = new Scanner(f);
			DOMTick tick;
			while (in.hasNextLine()) {
				tick = createTick(in.nextLine());
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
	 */
	public void addTick(DOMTick tick) {
		// process flow
		int index = findTick(tick.price);
		ArrayList<DOMTick> sorted;
		if(index<0) {
			// price not in the ladder
			dom.add(tick);
			sorted = sortDom(dom);
		} else {
			// price in the ladder
			if(tick.price.greaterThan(prevPrice) ==1) {
				// check if previous price is higher or lower than current price
				dom.get(index).addAsks(tick.getAsks());
				dom.get(index).setBids(tick.getBids());
			} else {
				// refresh orders and add to orders
				dom.get(index).addBids(tick.getBids());
				dom.get(index).setAsks(tick.getAsks());
			}
		}
		// update prev price
		prevPrice = tick.price;
	}

	private ArrayList<DOMTick> sortDom(ArrayList<DOMTick> list){
		// split
		System.out.println("Mergesort not implemented");
		System.out.println("sorting " + list.size());

		int size = list.size();
		if (size<=1) return list;

		// split
		ArrayList<DOMTick> L = new ArrayList<DOMTick>();
		ArrayList<DOMTick> R = new ArrayList<DOMTick>();
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
		ArrayList<DOMTick> sortedList = new ArrayList<DOMTick>();
		Price lPrice;
		Price rPrice;

		System.out.println("List :"+list.size());
		System.out.println(L.size()+" "+R.size());
		System.out.println(L.isEmpty()+" "+R.isEmpty());

		// merge
		while (!L.isEmpty() && !R.isEmpty()){
			if (L.isEmpty()) {
				sortedList.add(R.get(0));
				R.remove(0);
			} else if (R.isEmpty()) {
				sortedList.add(L.get(0));
				L.remove(0);
			} else {
				lPrice = L.get(0).price;
				rPrice = R.get(0).price;
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

	private int findTick(Price p) {
		for (int i =0;i<dom.size();i++) {
			if (dom.get(i).price.equals(p)) {
				return i;
			}
		}
		return -1;
	}

	private void adjustDOM() {

	}

	public void publishDOM() {
		for(int i=0;i<dom.size();i++) {
			System.out.println(dom.get(i).toString());
		}
	}
}
