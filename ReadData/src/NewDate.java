
public class NewDate {
	private int day;
	private int month;
	private int year;
	
	public NewDate(String dt) {
		String[] words = dt.split("/");
		year = Integer.parseInt(words[0]);
		month = Integer.parseInt(words[1]);
		day = Integer.parseInt(words[2]);
	}
	
	public String toString() {
		String result = "";
		result+=day+"-"+month+"-"+year;
		return result;
	}

}
