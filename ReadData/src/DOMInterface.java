
public class DOMInterface {
	private DOM a;
	public DOMInterface() {
		a = new DOM();
	}
	
	public void addTick(String line) {
		DOMTick tick = createTick(line);
		
		// get tick of the price
		a.addTick(tick);
		
		// get tick of the price
		
		// check 
		
		// create iceberg
		
	}
	
	private DOMTick createTick(String line) {
		String[] words = line.split(",");
		DOMTick tick = new DOMTick(words[0],words[2],words[6],words[7]);
		return tick;
	}
	
	public void publishDom() {
		a.publishDOM();
	}
	
	public void publishIcebergs() {
		
	}

}
