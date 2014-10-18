package bigNumber;



public class TestingDriver {
	static BigNumber test1 = new BigNumber();
	static BigNumber test2 = new BigNumber();
	static BigNumber pos5 = new BigNumber();
	static BigNumber pos5_2 = new BigNumber();
	static BigNumber neg6 = new BigNumber();
	static BigNumber zero = new BigNumber("0");
	static BigNumber add1 = new BigNumber("9583");
	static BigNumber add2 = new BigNumber("561");
	static BigNumber pos1 = new BigNumber("25");
	static BigNumber pos2 = new BigNumber("43");
	static BigNumber neg1 = new BigNumber("6");
	static BigNumber small1 = new BigNumber("10");
	static BigNumber small2 = new BigNumber("8");
	static BigNumber num20 = new BigNumber("20");
	static BigNumber neg2 = new BigNumber("8");
	static BigNumber num2 = new BigNumber("2");
	static BigNumber num10 = new BigNumber("10");
	static BigNumber num5 = new BigNumber("5");
	static BigNumber num0 = new BigNumber("0");
	static BigNumber num6 = new BigNumber("06");
	static BigNumber num3 = new BigNumber("3");
	static BigNumber neg10 = new BigNumber("90");
	static BigNumber num31 = new BigNumber("31");
	static BigNumber neg26 = new BigNumber("74");
	static BigNumber divtest1 = new BigNumber("117852727");
	
	public static void main(String[] args)
	{
//		System.out.println("Answer: " + num31.divMod(neg26));
		System.out.println(divtest1.divide(num2));
	}
	
}
