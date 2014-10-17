package tests;

import static org.junit.Assert.*;
import bigNumber.BigNumber;

import org.junit.Before;
import org.junit.Test;

public class DivisionTester {
    BigNumber test1 = new BigNumber("186"); //3 digit postive, 186
    BigNumber test2 = new BigNumber("341"); //3 digit postive, 341
    BigNumber test3 = new BigNumber("732"); //3 digit negative
    BigNumber test4 = new BigNumber("695"); //3 digit negative
    BigNumber test5 = new BigNumber("31"); //2 digit postive, 31
    BigNumber test6 = new BigNumber("74"); //2 digit negative, 74
    BigNumber test7 = new BigNumber("30"); //2 digit postive ending in 0
    BigNumber test8 = new BigNumber("290"); //3 digit postive ending in 0
    BigNumber test9 = new BigNumber("59"); //2 digit negative ending in 9
    BigNumber test10 = new BigNumber("999"); //3 digit negative ending in 9
    BigNumber test11 = new BigNumber("0"); //0 digit
    BigNumber test12 = new BigNumber("94893"); //really big negative number
    BigNumber pos10 = new BigNumber("10");
    BigNumber neg6 = new BigNumber("94");
	
	@Before
	public void setUp() throws Exception {
        BigNumber test1 = new BigNumber("186"); //3 digit postive, 186        

	}

	@Test
	public void test() {
		assertEquals("-5107/186 must be -27", "73", test12.divide(test1).toString());
		assertEquals("-10/6 must be -1", "9", pos10.divide(neg6).toString());
	}

}
