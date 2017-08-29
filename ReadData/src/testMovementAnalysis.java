import java.time.LocalTime;

public class testMovementAnalysis {

	public static void main(String[] args) {
		System.out.println(LocalTime.now());
		MovementAnalysis b = new MovementAnalysis("TYAM17_2017-5-18_refined.txt",4);
		System.out.println(LocalTime.now());

	}

}
