import java.time.LocalTime;

public class testCreateRefinedTickFile {

	public static void main(String[] args) {
		try{
			System.out.println(LocalTime.now());
			String filename = "TYAZ17_2017-9-25.txt";
			CreateRefinedTickFile a = new CreateRefinedTickFile(filename);
			System.out.println(LocalTime.now());
			//MovementAnalysis b = new MovementAnalysis("TYAM17_2017-2-6_refined.txt",3);
			//System.out.println(LocalTime.now());
		}catch (Exception e){
			System.out.println(e.toString());
		}

	}

}
