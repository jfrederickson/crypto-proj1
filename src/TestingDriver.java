


public class TestingDriver {
	static BigNumber test1 = new BigNumber();
	static BigNumber test2 = new BigNumber();
	static BigNumber pos5 = new BigNumber();
	static BigNumber pos5_2 = new BigNumber();
	static BigNumber neg6 = new BigNumber();
	static BigNumber zero = new BigNumber();
	static BigNumber add1 = new BigNumber("583");
	static BigNumber add2 = new BigNumber("561");
	static BigNumber pos1 = new BigNumber("25");
	static BigNumber pos2 = new BigNumber("43");
	static BigNumber neg1 = new BigNumber("6");
	static BigNumber small1 = new BigNumber("6");
	static BigNumber small2 = new BigNumber("7");
	
	public static void main(String[] args)
	{
		test1.digits.add(1);
		test1.digits.add(2);
		test1.digits.add(3);
		test1.digits.add(4);
		
		test2.digits.add(5);
		test2.digits.add(4);
		test2.digits.add(5);
		test2.digits.add(2);
		
		pos5.digits.add(3);
		pos5.digits.add(4);
		pos5.digits.add(3);
		pos5.digits.add(2);
		pos5.digits.add(1);
		
		pos5_2.digits.add(3);
		pos5_2.digits.add(4);
		pos5_2.digits.add(4);
		pos5_2.digits.add(2);
		pos5_2.digits.add(1);
		
		neg6.digits.add(5);
		neg6.digits.add(6);
		neg6.digits.add(5);
		neg6.digits.add(3);
		neg6.digits.add(4);
		neg6.digits.add(7);
		
		zero.digits.add(0);
		zero.digits.add(0);
		zero.digits.add(0);
		zero.digits.add(0);
		zero.digits.add(0);
		

		
		
		
//		System.out.println(test1.digits);
//		test1.negate();
//		System.out.println(test1.digits);
//		
//		System.out.println(pos5.compareTo(test1)); // Should be -1
//		System.out.println(test1.compareTo(pos5)); // Should be 1
//		System.out.println(pos5.compareTo(pos5)); // Should be 0, these are equal
//		System.out.println(pos5.compareTo(neg6)); // Should be -1 (pleasepleaseplease)
//		System.out.println(neg6.compareTo(pos5));
//		
//		System.out.println(pos5.compareTo(pos5_2));
//		
//		System.out.println(zero.compareTo(pos5));
//		
//		System.out.println(zero.sign());
//		System.out.println(pos5.sign());
//		System.out.println(neg6.sign());
//		
//		System.out.println(pos5.equals(zero));
//		
		
//		System.out.println(add1);
//		System.out.println(add2);
//		test1.negate();
//		System.out.println(test1);
//		System.out.println(test1.subtract(test2));
		
//		System.out.println(test1.multiply(test2));
		
//		System.out.println(add1.add(add2));
		
		System.out.println("Add1: " + small1);
		System.out.println("Add2: " + small2);
//		System.out.println(add1.add(add2));
		System.out.println(add1.multiply(add2));
		System.out.println(small2.sign());
		
	}
	
}
