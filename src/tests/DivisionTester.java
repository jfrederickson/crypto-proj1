package tests;

//import static org.junit.Assert.*;
//import bigNumber.BigNumber;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class DivisionTester {
//    BigNumber test1 = new BigNumber("186"); //3 digit postive, 186
//    BigNumber test2 = new BigNumber("341"); //3 digit postive, 341
//    BigNumber test3 = new BigNumber("732"); //3 digit negative
//    BigNumber test4 = new BigNumber("695"); //3 digit negative
//    BigNumber test5 = new BigNumber("31"); //2 digit postive, 31
//    BigNumber test6 = new BigNumber("74"); //2 digit negative, 74
//    BigNumber test7 = new BigNumber("30"); //2 digit postive ending in 0
//    BigNumber test8 = new BigNumber("290"); //3 digit postive ending in 0
//    BigNumber test9 = new BigNumber("59"); //2 digit negative ending in 9
//    BigNumber test10 = new BigNumber("999"); //3 digit negative ending in 9
//    BigNumber test11 = new BigNumber("0"); //0 digit
//    BigNumber test12 = new BigNumber("94893"); //really big negative number
//    BigNumber pos10 = new BigNumber("10");
//    BigNumber neg6 = new BigNumber("94");
//    BigNumber pos10000 = new BigNumber("10000");
//    BigNumber pos5 = new BigNumber("05");
//    BigNumber neg600 = new BigNumber("9400");
//    BigNumber pos250 = new BigNumber("250");
//    BigNumber neg250 = new BigNumber("750");
//    BigNumber neg100 = new BigNumber("900");
//    BigNumber neg4999 = new BigNumber("5001");
//    BigNumber pos2 = new BigNumber("2");
//    
//	
//	@Before
//	public void setUp() throws Exception {
//        BigNumber test1 = new BigNumber("186"); //3 digit postive, 186        
//
//	}
//
//	@Test
//	public void test() {
//		assertEquals("-5107/186 must be -27", "73", test12.divide(test1).toString());
//		assertEquals("-10/6 must be -1", "9", pos10.divide(neg6).toString());
//		assertEquals("186%341 must be 186", "186", test1.divMod(test2).toString());
//		assertEquals("341%186 must be 155", "155", test2.divMod(test1).toString());
//		assertEquals("31%30 must be 1", "1", test5.divMod(test7).toString());
//		assertEquals("30%31 must be 30", "30", test7.divMod(test5).toString());
//		assertEquals("695%732 must be 63", "63", 
//				test4.divMod(test3).toString());
//		assertEquals("732%695 must be 732", "732", test3.divMod(test4).toString());
//		assertEquals("74%59 must be 74", "74", test6.divMod(test9).toString());
//		assertEquals("59%74 must be 85", "85", test9.divMod(test6).toString());
////		assertEquals("186%-268 must be 918", "918", 
////				test1.divMod(test3).toString());
//		
//		assertEquals("732/695 must be 0", "0", test3.divide(test4).toString());
//		assertEquals("695/732 must be 1", "1", test4.divide(test3).toString());
//		assertEquals("10000/05 must be 2000", "2000", pos10000.divide(pos5).toString());
//		assertEquals("05/10000 must be 0", "0", pos5.divide(pos10000).toString());
////		try {
////			assertEquals("Divide by zero", null, pos10.divide(test11).toString());
////		}
////		catch(Exception e) {
////			System.err.println("Divide by zero");
////		}
//		assertEquals("-600/250 must be -2", "8", neg600.divide(pos250).toString());
//		assertEquals("-100/-250 must be 0", "0", neg100.divide(neg250).toString());
//		assertEquals("-250/-100 must be 2", "2", neg250.divide(neg100).toString());
//		assertEquals("0/10 must be 0", "0", test11.divide(pos10).toString());
//		assertEquals("186/-268 must be 0", "0", 
//		test1.divide(test3).toString());
//		assertEquals("-268/186 must be -1", "9", 
//		test3.divide(test1).toString());
//		assertEquals("-4999/2 must be -2499", "7501", neg4999.divide(pos2).toString());
//	}
//
//}
