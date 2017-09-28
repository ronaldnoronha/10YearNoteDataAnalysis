import java.time.LocalTime;

public class testMovementAnalysis {

	public static void main(String[] args) {
		System.out.println(LocalTime.now());
		MovementAnalysis b = new MovementAnalysis("TYAZ17_2017-9-25_refined.txt",3);
		System.out.println(LocalTime.now());

	}

}
