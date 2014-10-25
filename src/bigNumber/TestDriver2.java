package bigNumber;

public class TestDriver2 {
	
	/**
     * Used for testing purposes......
     * Please ignore.
     */
    public static void main(String[] args) {
        System.out.println("Testing the addition of new Big Numbers....");
        BigNumber test1 = new BigNumber("186"); //3 digit postive, 186
        System.out.println("tes1: " + test1.toString());        
        BigNumber test2 = new BigNumber("341"); //3 digit postive, 341
        System.out.println(" test2: " + test2.toString());
        BigNumber test3 = new BigNumber("732"); //3 digit negative
        System.out.println(" test3: " + test3.toString());
        BigNumber test4 = new BigNumber("695"); //3 digit negative
        System.out.println(" test4: " + test4.toString());
        BigNumber test5 = new BigNumber("31"); //2 digit postive, 31
        System.out.println(" test5: " + test5.toString());
        BigNumber test6 = new BigNumber("74"); //2 digit negative, 74
        System.out.println(" test6: " + test6.toString());
        BigNumber test7 = new BigNumber("30"); //2 digit postive ending in 0
        System.out.println(" test7: " + test7.toString());
        BigNumber test8 = new BigNumber("290"); //3 digit postive ending in 0
        System.out.println(" test8: " + test8.toString());
        BigNumber test9 = new BigNumber("59"); //2 digit negative ending in 9
        System.out.println(" test9: " + test9.toString());
        BigNumber test10 = new BigNumber("999"); //3 digit negative ending in 9
        System.out.println(" test10: " + test10.toString());
        BigNumber test11 = new BigNumber("0"); //0 digit
        System.out.println(" test11: " + test11.toString());
        BigNumber test12 = new BigNumber("94893"); //really big negative number
        System.out.println(" test12: " + test12.toString());
        BigNumber berTest1 = new BigNumber("2168211218041261");
        System.out.println(" berTest1: " + berTest1.toString());
        BigNumber berTest2 = new BigNumber("117852727");
        System.out.println(" berTest2: " + berTest2.toString());
        
        System.out.println();
        System.out.println();
        System.out.println("Testing the add(BigNumber bigN) function with BigNumbers of varying lengths....");
        BigNumber result1 = test2.add(test1);       
        System.out.println("3 digit postive addition: result1 = test1 + test2: " + result1.toString());        
        BigNumber result2 = test3.add(test2);
        System.out.println("3 digit pos/neg addition: result2 = test3 + test2: " + result2.toString());        
        BigNumber result3 = test3.add(test4);
        System.out.println("3 digit negative addition: result3 = test3 + test4: " + result3.toString());
        BigNumber result4 = test1.add(test5);
        System.out.println("3 and 2 digit postive addition: result4 = test1 + test5: " + result4.toString());
        BigNumber result5 = test3.add(test6);
        System.out.println("3 and 2 digit negative addition: result5 = test3 + test6: " + result5.toString());
        BigNumber result6 = test1.add(test6);
        System.out.println("3 digit postive and 2 digit negative addition: result6 = test1 + test6: " + result6.toString());
        BigNumber result7 = test3.add(test5);
        System.out.println("3 digit negative and 2 digit postive addition: result7 = test3 + test5: " + result7.toString());
        BigNumber result8 = test7.add(test5);
        System.out.println("2 digit positive ending in 0 and 2 digit postive addition: result8 = test7 + test5: " + result8.toString());
        BigNumber result9 = test7.add(test6);
        System.out.println("2 digit positive ending in 0 and 2 digit negative addition: result9 = test7 + test6: " + result9.toString());
        BigNumber result10 = test7.add(test1);
        System.out.println("2 digit positive ending in 0 and 3 digit postive addition: result10 = test7 + test1: " + result10.toString());
        BigNumber result11 = test7.add(test4);
        System.out.println("2 digit positive ending in 0 and 3 digit negative addition: result11 = test7 + test4: " + result11.toString());       
        BigNumber result12 = test8.add(test5);
        System.out.println("3 digit positive ending in 0 and 2 digit postive addition: result12 = test8 + test5: " + result12.toString());
        BigNumber result13 = test8.add(test6);
        System.out.println("3 digit positive ending in 0 and 2 digit negative addition: result13 = test8 + test6: " + result13.toString());
        BigNumber result14 = test8.add(test1);
        System.out.println("3 digit positive ending in 0 and 3 digit postive addition: result14 = test8 + test1: " + result14.toString());
        BigNumber result15 = test8.add(test4);
        System.out.println("3 digit positive ending in 0 and 3 digit negative addition: result15 = test8 + test4: " + result15.toString());       
        BigNumber result16 = test9.add(test5);
        System.out.println("2 digit positive ending in 9 and 2 digit postive addition: result16 = test9 + test5: " + result16.toString());
        BigNumber result17 = test9.add(test6);
        System.out.println("2 digit positive ending in 9 and 2 digit negative addition: result17 = test9 + test6: " + result17.toString());
        BigNumber result18 = test9.add(test1);
        System.out.println("2 digit positive ending in 9 and 3 digit postive addition: result18 = test9 + test1: " + result18.toString());
        BigNumber result19 = test9.add(test4);
        System.out.println("2 digit positive ending in 9 and 3 digit negative addition: result19 = test9 + test4: " + result19.toString());       
        BigNumber result20 = test10.add(test5);
        System.out.println("3 digit positive ending in 9 and 2 digit postive addition: result20 = test10 + test5: " + result20.toString());
        BigNumber result21 = test10.add(test6);
        System.out.println("3 digit positive ending in 9 and 2 digit negative addition: result21 = test10 + test6: " + result21.toString());
        BigNumber result22 = test10.add(test1);
        System.out.println("3 digit positive ending in 9 and 3 digit postive addition: result22 = test10 + test1: " + result22.toString());
        BigNumber result23 = test10.add(test4);
        System.out.println("3 digit positive ending in 9 and 3 digit negative addition: result23 = test10 + test4: " + result23.toString());

        System.out.println();
        System.out.println("Testing the mod(BigNumber bigN) fucntion in general.....");
        BigNumber result24 = test1.mod(test2);
        System.out.println("3 digit postive modding: result24: test1 % test2: " + result24.toString());
        BigNumber result25 = test2.mod(test1);
        System.out.println("3 digit postive modding: result25: test2 % test1: " + result25.toString());
        BigNumber result26 = test5.mod(test7);
        System.out.println("2 digit positive modding: result26: test5 % test7: " + result26.toString());
        BigNumber result27 = test7.mod(test5);
        System.out.println("2 digit positive modding: result27: test7 % test5: " + result27.toString());
        //BigNumber result28 = test3.mod(test4);
        //System.out.println("3 digit negative modding: result28: test3 % test4: " + result28.toString());
        //BigNumber result29 = test4.mod(test3);
        //System.out.println("3 digit negative modding: result29: test4 % test3: " + result29.toString());
        //BigNumber result30 = test6.mod(test9);
        //System.out.println("2 digit negative modding: result30: test6 % test9: " + result30.toString());
        //BigNumber result31 = test9.mod(test6);
        //System.out.println("2 digit negative modding: result31: test9 % test6: " + result31.toString());
        //BigNumber result32 = test1.mod(test3);
        //System.out.println("3 digit postive and negative modding: result32: test1 % test3: " + result32.toString());
        //BigNumber result33 = test3.mod(test1);
        //System.out.println("3 digit negative and positive modding: result33: test3 % test1: " + result33.toString());
        //BigNumber result34 = test5.mod(test6);
        //System.out.println("2 digit postive and negative modding: result34: test5 % test6: " + result34.toString());
        //BigNumber result35 = test6.mod(test5);
        //System.out.println("2 digit negative and positive modding: result35: test6 % test5: " + result35.toString());
        BigNumber result36 = test5.mod(test5);
        System.out.println("a = m modding: result36: test5 % test5: " + result36.toString());
        //BigNumber result37 = test11.mod(test3);
        //System.out.println("0 mod 3 digit negative modding: result37: test11 % test3: " + result37.toString());
        BigNumber result38 = test11.mod(test1);
        System.out.println("0 mod 3 digit positive modding: result38: test11 % test1: " + result38.toString());
        //BigNumber result39 = test11.mod(test6);
        //System.out.println("0 mod 2 digit negative modding: result39: test11 % test6: " + result39.toString());
        BigNumber result40 = test11.mod(test5);
        System.out.println("0 mod 2 digit positive modding: result40: test11 % test5: " + result40.toString());
        //BigNumber result41 = test12.mod(test1);
        //System.out.println("Really big negative number mod 3 digit positive modding: result41: test12 % test1: " + result41.toString());
        //BigNumber result42 = test1.mod(test12);
        //System.out.println("3 digit positive and really big negative number modding: result42: test1 % test12: " + result42.toString());
        
        System.out.println();
        System.out.println("Testing the tDivide(BigNumber bigN) method.....");
        BigNumber result43 = test1.divide(test5);
        System.out.println("result43: test1 / test5: 186/31 = " + result43.toString());
        BigNumber result44 = test5.divide(test1);
        System.out.println("result44: test5 / test1: 31/186 = " + result44.toString());
        BigNumber result45 = test1.divide(test6);
        System.out.println("result45: test1 / test6: 186/-26 = " + result45.toString());
        BigNumber result46 = test6.divide(test1);
        System.out.println("result46: test6 / test1: -26/186 = " + result46.toString());
        BigNumber result47 = test3.divide(test1);
        System.out.println("result47: test3 / test1: -268/186 = " + result47.toString());
        BigNumber result48 = test1.divide(test3);
        System.out.println("result48: test1 / test3: 186/-268 = " + result48.toString());
        BigNumber result49 = test1.divide(test1);
        System.out.println("result49: test1 / test1: 186/186 = " + result49.toString());
        BigNumber result50 = test3.divide(test6);
        System.out.println("result50: test3 / test6: -268/-26 = " + result50.toString());
        BigNumber result51 = test6.divide(test3);
        System.out.println("result51: test6 / test3: -26/-268 = " + result51.toString());
        BigNumber result52 = test6.divide(test6);
        System.out.println("result52: test6 / test6: -26/-26 = " + result52.toString());
        BigNumber result53 = test12.divide(test1);
        System.out.println("result53: test12 / test1: -5107/186 = " + result53.toString());
        
        System.out.println();
        System.out.println("Testing the factor(BigNumber bigN) method.....");
        test1.factor();
        System.out.println("The factors of test1 are: " + test1.factors);
        test2.factor();
        System.out.println("The factors of test2 are: " + test2.factors);
        test3.factor();
        System.out.println("The factors of test3 are: " + test3.factors);
        test5.factor();
        System.out.println("The factors of test5 are: " + test5.factors);
        test7.factor();
        System.out.println("The factors of test7 are: " + test7.factors);
        test8.factor();
        System.out.println("The factors of test8 are: " + test8.factors);
        berTest2.factor();
        System.out.println("The factors of berTest2 are: " + berTest2.factors);
        berTest1.factor();
        System.out.println("The factors of berTest1 are: " + berTest1.factors);
        }

}
