
public class testTime {

	public static void main(String[] args) {
		String time = "10:00:00.000";
		String start = "07:00:00.000";
		String end = "10:00:00.000";
		Time a = new Time(time);
		System.out.println(a.checkTimeWithin(new Time(start),new Time(end)));

	}

}
