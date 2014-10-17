/**
 * This class represents a number, possibly larger than the length of an int.
 * 
 * @author Jonathan Frederickson
 * @author Tara Crittenden
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class BigNumber {
	
	protected ArrayList<Integer> digits;
	
    /**
     * Constructor for class BigNumber. Transforms a string argument into an
     * ArrayList of Integers. This assumes (as of current version) that the String
     * contains only numbers and no characters.
     * 
     * @param s : a String of numbers
     */
    public BigNumber(String s) {
        digits = new ArrayList<Integer>();
        //fill the newly created ArrayList of Integers
        fillNumbers(s);

    
    }
	
	
    /**
     * Private Constructor for class BigNumber used only by BigNumber. Instantiates
     * a new clean BigNumber for use in putting the resulting values of the 
     * mathematical operations into.
     */
    protected BigNumber() {
        digits = new ArrayList<Integer>();
    }
    
    /**
     * Private class to create a new BigNumber when you already have
     * the digits ArrayList.
     */
    protected BigNumber(ArrayList<Integer> digits) {
    	this.digits = digits;
    }
	
    /**
     * Fills an ArrayList with Integers, given the String, starting with the least 
     * significant number first. Converts the string into an Integer before 
     * putting it into the ArrayList. Assumes that the string contains only 
     * numbers.
     * 
     * @param s : the String to be put into the ArrayList.
     */
    private void fillNumbers(String s) {
        Integer num;
        //start with the rightmost character of the string
        for(int i=s.length()-1; i>=0; i--) {
            //put the least significant digits in first
            num = new Integer(s.charAt(i)-48); //s.char(i)-48 will return numbers 0-9
            digits.add(num);
        }
    }
    
	public BigNumber(int num) {
		
	}
	
	/**
     * Adds two BigNumbers together. Takes this BigNumber and a new BigNumber
     * bigN, adds the individual numbers in each slot of the ArrayList starting
     * with the least most significant digit, and puts the result into a new 
     * BigNumber result, which is then returned.
     * 
     * @param bigN : BigNumber to be added to this BigNumber
     * @return result : The resulting BigNumber of the addition of this BigNumber
     *          and bigN.
     */
    protected BigNumber add(BigNumber bigN) {
        //create a new empty BigNumber object
        BigNumber result = new BigNumber();
        //used for when the result is over 9. carryover is the tens place holder.
        int carryOver = 0, temp = 0;
        //checking to make sure each BigNumber is of the same size
        //if they are not, the smaller of the two will be 'padded'
        //see the method pad(int n) for more details...
        if(digits.size() > bigN.size()) { 
            bigN.pad(digits.size() + 1); //bigN is smaller, so pad it til it equals numbers.size()
            this.pad(bigN.size());
        }
        else if(digits.size() < bigN.size()) {
            this.pad(bigN.size() + 1); //numbers is smaller, so pad it til it equals bigN.size()
            bigN.pad(digits.size());
        }
        else {
        	bigN.pad(digits.size() + 1);
        	this.pad(digits.size() + 1);
        }
        
        //time to iterate through the ArrayList adding the two numbers found at i.
        for(int i=0; i<digits.size(); i++) {
            //add the numbers, storing them in temp.
            temp = this.get(i) + bigN.get(i) + carryOver;
            //change carryOver back to 0 to avoid any funny addition errors
            carryOver = 0;
            //check to see if temp is greater then 9, in which case....
            if(temp > 9) {
                //calculate carryOver.
                carryOver = temp/10;
                //calculate temp.
                temp = temp%10;
            }
            //now that the numbers have been added, add temp to the BigNumber result.
            result.add(temp);
            
            //for some techinal reasons involving tens complement....
            //if the last number is greater than 5...make sure that it stays positive
            //or negative, depending on what the two original BigNumbers were
//            if(i==digits.size()-1)
//                //if both numbers are negative or if one is zero, the result should still be neagetive
//                if(((digits.get(digits.size()-1)>4) && (bigN.get(digits.size()-1)>4)) 
//                        || (bigN.sign()==0)) {
//                    result.add(9); //pad a 9 at the end so it stays negative
//                }
//                //if both numbers are postive or if one is nine, the result should still be postive
//                else if(((digits.get(digits.size()-1)<5) && (digits.get(digits.size()-1) != 0)) && (((bigN.get(digits.size()-1)<5) && (bigN.get(digits.size()-1) != 0))) || (bigN.sign()==1)) {
//                    result.add(0); //pad a 0 at the end so it stays positive
//                }
        }
        //finally return the result of the addition of the two BigNumbers
        result.normalize();
        return result;
    }
    
    /**
     * Subtracts two BigNumbers from each other. Uses the add(BigNumber bigindexN)
     * function. Negates the parameter bigN before passing it through to add(bigN).
     * 
     * @param bigN : BigNumber to be subtracted from this BigNumber.
     * @return add(bigN) : the result of the subtraction
     */
    protected BigNumber subtract(BigNumber bigN) {
    	if(bigN == this) return new BigNumber("0");
    	
        //subtracting is the same as adding a negative number, so negate the given
        //BigNumber and pass it through to add(bigN).
    	bigN.negate();
    	//System.out.println(this);
    	//System.out.println(bigN);
        BigNumber result =  add(bigN);
        bigN.negate(); // Negate bigN again to avoid ugly side effects
        return result;
    }
	
	/**
	 * Compares two BigNumbers for equality.
	 * Side effect: This BigNumber and the one passed to this method will
	 * be normalized.
	 * @param cmp
	 * @return True if two BigNumbers are equal, false otherwise
	 */
	public boolean equals(BigNumber cmp) {
		// Both BigNumbers must be normalized - show standard
		// representation of number
		normalize();
		cmp.normalize();
		
		int len = digits.size();
		
		// If they're different sizes, they're obviously not equal
		// No need for the expensive loop
		if(cmp.digits.size() != len) return false;
		
		if(compareTo(cmp) != 0) return false;
		
		return true;
	}
	
	/**
	 * Negates this BigNumber using ten's complement notation
	 */
	public void negate() {
		if(sign() == 0) return;
		int len = digits.size();
		int count = 0;
		
		while(digits.get(count) == 0) {
			count++;
		}
		
		digits.set(count, 10 - digits.get(count));
		count++;
		
		while(count < len) {
			// Replace each digit in list with 9-itself
			// (Nine's complement)
			digits.set(count, 9 - digits.get(count));
			count++;
		}
		
		// Add one (ten's complement)
//		digits.set(0, 1 + digits.get(0));
	}
	
	/**
	 * Multiplies two BigNumbers together
	 * TODO: Implement shift-and-add
	 * @param mult The number to multiply by
	 * @return the result of this BigNumber * the parameter
	 */
	public BigNumber multiply(BigNumber multiplier) {
		// Both BigNumbers need to be normalized
		normalize();
		multiplier.normalize();
		
		// We need to keep track of whether the number we're multiplying
		// by is negative - this makes multiplication much easier.
		boolean negative = false;
		
		// Make copies of BigNumbers so we don't accidentally do things to them
		BigNumber result = new BigNumber(this.toString()); // Start with this BigNumber
		BigNumber mult = new BigNumber(multiplier.toString());
		
		// Create a BigNumber with an increment of 1 so we can add/subtract it
		BigNumber bigInc = new BigNumber("1");
		
		if(mult.sign() == 0) return mult; // Multiplier is 0, result is 0
		
		else if(mult.sign() == -1) {
			mult.negate();
			negative = true;
		}
		
		// Add this BigNumber to itself "mult" times
		while(mult.compareTo(bigInc) != 0) {
			result = result.add(this);
			// Subtract one if multiplier is positive, add if negative
			if(mult.sign() == 1) {
				mult = mult.subtract(bigInc);
			}
			
			else if(mult.sign() == -1) {
				mult = mult.add(bigInc);
			}
			result.normalize();
		}
		result.normalize();
		if(negative) {
			result.negate();
			return result;
		}
		return result;
	}
	
	/**
	 * Divides one BigNumber by another
	 * TODO: Implement shift-and-subtract
	 * @param div The number to divide by
	 * @return the result of this BigNumber / the parameter
	 */
	public BigNumber divide(BigNumber div) {
		DivResult result = divShared(div);
		return result.quotient;
	}
	
	public BigNumber divMod(BigNumber div) {
		DivResult result = divShared(div);
		return result.remainder;
	}
	
	private DivResult divShared(BigNumber div) {
		normalize();
		div.normalize();
		int negcount = 0;
		
		BigNumber numerator = this;
		
		if(div.sign() == 0) return null;
		
		if(numerator.sign() == -1) {
			numerator.negate();
			negcount++;
		}
		if(div.sign() == -1) {
			div.negate();
			negcount++;
		} 
		
		BigNumber result = new BigNumber("0");
		BigNumber one = new BigNumber("1");
		
		// As long as this BigNum is greater than the divisor, subtract div from the result
		while(numerator.greaterThan(div) || numerator.equals(div)) {
			result = result.add(one);
			numerator = numerator.subtract(div);
		}
		if(negcount == 1) {
			result.negate();
		}
		return new DivResult(result, numerator);
	}

	/**
	 * Checks the sign of this BigNumber
	 * @return -1 if this BigNumber is negative, 1 if positive, 0 if zero
	 */
	public int sign() {
		BigNumber tmp = new BigNumber(this.toString());
		tmp.normalize();
		
		int len = tmp.digits.size();
		int num = tmp.digits.get(len-1); // Most significant digit
		
		if(num >= 5) return -1;
		else if(num < 5 && num > 0) return 1;
		
		// Leading digit is zero, check if there are digits
		// The BigNumber is positive if they are
		else for(int i=len-1; i>=0; i--) {
			if(tmp.digits.get(i) > 0) return 1;
		}
		// We've looped through the whole number, and all digits are 0
		return 0;
	}
	
	/**
	 * Compares the size of two BigNumbers
	 * Side effects: Both BigNumbers being compared will be normalized
	 * @param cmp
	 * @return -1 if parameter is smaller than this BigNumber, 1 if greater than, or 0 if equal
	 */
	public int compareTo(BigNumber cmp) {
		// Both BigNumbers need to be normalized to compare them easily
		normalize();
		cmp.normalize();
		
		int thisLen = digits.size();
		int cmpLen = cmp.digits.size();
		int msb = digits.get(thisLen-1); // Most significant digit of this BigNumber
		int msbcmp = cmp.digits.get(cmpLen-1); // Most significant digit of parameter
		
		// This BigNumber is negative, parameter is positive
		if(msb >= 5 && msbcmp < 5) return 1;
		// This BigNumber is positive, param is negative
		else if(msb < 5 && msbcmp >= 5) return -1;
		// This BigNumber is longer, so param is less
		else if(thisLen > cmpLen) return -1;
		// This is shorter, so param is greater
		else if(thisLen < cmpLen) return 1;
		
		// Both are equal length, both either positive or negative
		else {
			for (int i = thisLen-1; i>=0; i--) {
				int curThis = digits.get(i);
				int curCompare = cmp.digits.get(i);
				// Matching digit in param is greater
				if(curThis < curCompare) return 1;
				// Matching digit in param is less
				else if(curThis > curCompare) return -1;
			}
			// Checked all digits in both BigNumbers
			// At this point they have to be equal
			return 0;
		}
	}
	
	/**
	 * 
	 * @param cmp
	 * @return true if this BigNumber is greater than cmp
	 */
	public boolean greaterThan(BigNumber cmp) {
		if(compareTo(cmp) == -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param cmp
	 * @return true if this BigNumber is less than cmp
	 */
	public boolean lessThan(BigNumber cmp) {
		if(compareTo(cmp) == 1) {
			return true;
		}
		return false;
	}
	
	
	/**
     * This method will return the mod of this BigNumber and another in the 
     * format this mod bigN. The result is a BigNumber. In the case of two 
     * negative BigNumbers, the result will be a negative BigNumber.
     * the form is a mod m.
     * 
     * @param bigN : The BigNumber to be modded to this.
     * @returns The mod of this.
     */
    protected BigNumber mod(BigNumber bigN) {
        BigNumber tempA = new BigNumber(this.toString());
        BigNumber tempM = new BigNumber(bigN.toString());
        BigNumber result = new BigNumber();
        // a mod m = r
        
        //some boolean variables...
        boolean Aneg = false;
        boolean Mneg = false;
        
        //negate if nesscary
        if(tempA.sign() == -1) {
        	Aneg = true;
        	tempA.negate();
        }
        if(tempM.sign() == -1) {
        	Mneg = true;
        	tempM.negate();
        }
        
        //now starts the actual mathing part...
        //check if either are negative
        if(Aneg || Mneg) {
        	if(Mneg && !Aneg) {
        		//only m is negative
        		if(tempA.compareTo(tempM) == -1) {
        			// a > m
        			result = tempA.subtract(tempM).mod(tempM);
        			result.negate();
        			return result;
        		}
        		else {
        			// a < m
        			result = tempA.subtract(tempM);
        			//result.negate();
        			return result;
        		}
        	}
        	
        	else if(Mneg && Aneg) {
        		//both a and m are negative
        		if(tempA.compareTo(tempM) == 1) {
        			// a < m
        			result = tempA;
        			result.negate();
        			return result;
        		}
        		else { 
        			//some duplicated code :(
        			// a > m
        			result = tempA.subtract(tempM).mod(tempM);
        			//result.negate();
        			return result;
        		}
        	}
        	else if(!Mneg && Aneg) {
        		//only a is negative
        		if(tempA.compareTo(tempM) == 1) {
        			//a < m
        			return tempM.subtract(tempA);
        		}
        		else {
        			// a > m
        			result = tempM.subtract(tempA);
        			//add until the number is positive
        			while(result.sign() == -1) {
        				result = result.add(tempM);
        			}
        			return result;
        		}
        	}
        	//do some math involving negative numbers
        }
        else {
        	//both numbers are positive
        	//testing a < m
        	if(tempA.compareTo(tempM) == 1)
        		return tempA;
        	else // (a-m) mod m
        		return tempA.subtract(tempM).mod(tempM);
        }
        
        System.out.println("It went somewhere where it should not have gone. :(");
        return bigN;
    }
    
    /**
     * This function will 'pad' the end of the ArrayList (or the beginning of the
     * number) with either 0s or 9s depending on whether the number si positive
     * or negative. Uses a comparison to another BigNumber array size to know
     * how many digits to pad it with. Used on adding and subtracting two 
     * BigNumbers.
     * 
     * @param n : the wanted size of the final array.
     */
    private void pad(int n) {
        //if the last number is greater than 5...
        if(digits.get(digits.size()-1) >= 5)
            for(int i=digits.size(); i<n; i++)
                //pad with 9s.
                digits.add(9);
        else
            for(int i=digits.size(); i<n; i++)
                //else pad with 0s.
                digits.add(0);
    }
    
    /**
     * Normalize will take any leading 0s or 9s and effectively remove them if 
     * they are unnecessary. It will not remove them in the case of say +527: 
     * +527 will still be represented as 0527 so as to avoid it meaning 483 in
     * tens complement.
     */
    protected void normalize() {
    	if(digits.size() != 1) {
	        //remove unnessacary 9s for any number starting with 5 or more
	        if((digits.get(digits.size()-1)) == 9) {
	            //if the second to last number is still greater then 5, then the 9
	            //can be removed
	            if((digits.get(digits.size()-2)) >= 5) {
	            	digits.remove(digits.size()-1);
	                //call this again to remove anymore 9s.
	                normalize();
	            }
	        }
	        //remove unnessacary 0s for any number starting with 4 or less
	        else if((digits.get(digits.size()-1)) == 0) {
	            if((digits.get(digits.size()-2)) < 5) {
	                //if the second to last number is still less than 4, then the 0
	                //can be removed
	            	digits.remove(digits.size()-1);
	                //call this again to remove anymore 0s.
	                normalize();
	            }
	        }
    	}
    }
	
    /**
     * Returns the length of the BigNumber. Not to be confused with size() from
     * ArrayList. As with the get(int n) and add(int n) methods, this method is
     * intended for use by another BigNumber. Used for computational purposes.
     * 
     * @return the length of the BigNumber.
     */
    private int size() {
        return digits.size();
    }
    
    /**
     * Gets a number from a BigNumber given the index. Not to be used in retrieving
     * numbers in this BigNumber but rather for retrieving numbers in another
     * bigNumber. Used for computational purposes.
     * 
     * @param n : index of the intended int to be retrieved.
     * @return the int that was found.
     */
    private int get(int n) {
        return digits.get(n);
    }
    
    /**
     * Adds a number to a BigNumber. Not to be used in appending numbers to this 
     * BigNumber but rather for another BigNumber. Used for computational purposes.
     * 
     * @param n : int to be added.
     */
    private void add(int n) {
        digits.add(n);
    }
	
    /**
     * Returns the BigNumber in String format with the most significant number 
     * as the first in the string.
     * 
     * @return s : String of BigNumber.
     */
    public String toString() {
        String s = "";
        for(int i=digits.size()-1; i>=0; i--)
            s += digits.get(i).toString();
        return s;
    }
    
    /**
     * Reverses the order of the elements in this BigNumber
     */
    protected void reverse() {
    	Collections.reverse(digits);
    }
    
    class DivResult {
    	public BigNumber quotient;
    	public BigNumber remainder;
    	
    	public DivResult(BigNumber quotient, BigNumber remainder) {
    		this.quotient = quotient;
    		this.remainder = remainder;
    	}
    }
    
}
