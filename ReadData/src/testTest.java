import java.io.File;
import java.util.*;
import java.time.*;
public class testTest {
	public static void main(String[] args){
		String line = "2015/9/29, 09:59:09, 128.000000, 128.000000, 128.000000, 128.000000, 1, 1, 0, 1";
		Tick a = new Tick(line);
		System.out.println(a.print());
	}

}
