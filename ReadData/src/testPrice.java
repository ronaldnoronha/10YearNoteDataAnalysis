
public class testPrice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String price = "126 1.5/32" ;
		Price a = new Price(price);
		System.out.println(a.toString());
		System.out.println(a.addTicks(61));
		System.out.println(a.toString());
		System.out.println(a.toDouble());
	}

}
