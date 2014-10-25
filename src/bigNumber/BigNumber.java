package bigNumber;

/**
 * BigNumber class is designed with the intent to perform basic mathematical 
 * operations on numbers which are too big to be stored into the Java int. It 
 * acheives this by storing indiviual numbers of type Inetger into an ArrayList 
 * of type Integer. The numbers are taken from a string provided by the user and
 * then stored in the ArrayList with the least signifcant digit in slot 0. This 
 * makes for easy computation later on. Current mathematical operations include 
 * adding, subtracting, dividing, multiplying, comparing, and modding two BigNumber as well 
 * as normalizing BigNumbers.
 * 
 * @author Jonathan Frederickson
 * @author Tara Crittenden
 */

import java.util.ArrayList;
import java.util.List;

public class BigNumber {
	
	//storage for the BigNumber
	protected List<Integer> digits;
	//storage for the factors of the BigNumber
	protected List<BigNumber> factors = new ArrayList<BigNumber>();
	
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
        
        //setting up the factors of an ArrayList. Every BigNumber is a factor of 1
        //to avoid recursively calling this constructor, call the other one and add 1 to the BigNumber
        BigNumber one = new BigNumber();
        one.digits.add(1);
        factors.add(one); //to be used later in finding the factors of a BigNumber.
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
    protected BigNumber(List<Integer> numbers) {
    	this.digits = (ArrayList<Integer>) numbers;
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
    public BigNumber add(BigNumber bigN) {
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
        //regardless of the sizes, pad an extra digit onto the end to catch overflow when adding
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
    public BigNumber subtract(BigNumber bigN) {
    	if(bigN == this) 
    		return new BigNumber("0");
    	
        //subtracting is the same as adding a negative number, so negate the given
        //BigNumber and pass it through to add(bigN).
    	bigN.negate();
        BigNumber result =  add(bigN);
        bigN.negate(); // Negate bigN again to return the correct result
        return result;
    }
	
	/**
	 * Compares two BigNumbers to each other. 
	 * @param cmp
	 * @return True if two BigNumbers are equal, false otherwise
	 */
	private boolean equals(BigNumber cmp) {
		// Both BigNumbers must be normalized - show standard
		// representation of number
		normalize();
		cmp.normalize();
		
		int len = digits.size();
		
		// If they're different sizes, they're obviously not equal
		// No need for the expensive loop
		if(cmp.digits.size() != len) return false;
		
		// compareTo() will return 0 if equal
		if(compareTo(cmp) != 0) return false;
		
		return true;
	}
	
	/**
	 * Negates this BigNumber using ten's complement notation
	 * Uses the algorithm discussed in class
	 */
	private void negate() {
		// BigNumber is 0, we don't need to worry about negating
		if(sign() == 0) return;
		
		int len = digits.size();
		int count = 0;
		
		// Scan from low order digit, copy (ignore) zeroes
		while(digits.get(count) == 0) {
			count++;
		}
		
		// First nonzero: subtract from 10
		digits.set(count, 10 - digits.get(count));
		count++;
		
		// All remaining digits: subtract from 9
		while(count < len) {
			digits.set(count, 9 - digits.get(count));
			count++;
		}
	}
	
	/**
	 * Multiplies two BigNumbers together
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
		
		// Multiplier is negative; negate while we're doing calculations
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
		// Multiplier was negative, so we need to negate
		if(negative) {
			result.negate();
			return result;
		}
		return result;
	}
	
	/**
	 * This method will return the quotient of this BigNumber and another in the
	 * format 'this'/bigN. The result is a BigNumber.  In the case of 0 /
     * (any BigNumber) or (any BigNumber) / 0, it returns 0. Uses tDivide(BigNumber bigN), 
     * a shared method with mod(BigNumber bigN), returning the first index of the array. 
	 * 
	 * @param bigN : The BigNumber to be divided by this.
     * @returns The quotient of 'this' / bigN.
	 */
	public BigNumber divide(BigNumber div) {
		BigNumber[] result = tDivide(div);
		return result[0];
	}
	
	/**
     * This method will return the mod of this BigNumber and another in the 
     * format 'this' mod bigN. The result is a BigNumber. In the case of 0 mod
     * (any BigNumber), it returns 0. This method does not handle any negative modding, so
     * caution is advised when using this mod. Uses tDivide(BigNumber bigN), a shared method
     * with divide(BigNumber bigN), returning the second index of the array.
     * 
	 * @param bigN : The BigNumber to be modded to this.
     * @returns The mod of this.
	 */
	public BigNumber mod(BigNumber div) {
		BigNumber[] result = tDivide(div);
		return result[1];
	}
	
	/**
	 * Checks the sign of this BigNumber
	 * @return -1 if this BigNumber is negative, 1 if positive, 0 if zero
	 */
	protected int sign() {
		BigNumber tmp = new BigNumber(this.toString());
		tmp.normalize();
		
		int len = tmp.digits.size();
		int num = tmp.digits.get(len-1); // Most significant digit
		
		// High order digit greater than 5, result is negative
		if(num >= 5) return -1;
		// High order digit less than 5, result is positive
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
	 * Used to determine if this BigNumber is greater than another
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
	 * Used to determine if this BigNumber is less than another
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
     * This function will 'pad' the end of the ArrayList (or the beginning of the
     * number) with either 0s or 9s depending on whether the number is positive
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
    private void normalize() {
    	if(digits.size() != 1) {
	        //remove unnecessary 9s for any number starting with 5 or more
	        if((digits.get(digits.size()-1)) == 9) {
	            //if the second to last number is still greater then 5, then the 9
	            //can be removed
	            if((digits.get(digits.size()-2)) >= 5) {
	            	digits.remove(digits.size()-1);
	                //call this again to remove anymore 9s.
	                normalize();
	            }
	        }
	        //remove unnecessary 0s for any number starting with 4 or less
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
     * This method is used by both divide(BigNumber bigN) and mod(BigNumber bigN).
     * Both methods are similar in that both involve alot of subtracting. Returns an 
     * array of BigNumber containing both the quotient and remainder of bigN.
     * 
     * @param bigN : The BigNumber to be divided/modded
     * @return An array of BigNumber with index 0 being the quotient and index 1 being the 
     * 			remainder of bigN
     */
    private BigNumber[] tDivide(BigNumber bigN) {
    	//work with positive numbers to make things easier
    	//but keep track of which one is negative (for the mod portion)
    	int neg = 0;
    	int neg2 = 0;
    	if(bigN.sign() == -1) {
    		bigN.negate();
    		neg++;
    	}
    	if(this.sign() == -1) {
    		this.negate();
    		neg2++;
    	}   		
    	
    	BigNumber quotient = new BigNumber();
    	BigNumber remainder = new BigNumber();
    	//if this < bigN return 0
    	if(bigN.compareTo(this) == -1) {
    		quotient.add(0);
    		//if both are positive then remainder is this as well
    		if(neg == 0 && neg2 == 0)
    			remainder = this;
    	}
    	//else this > bigN so do some math
    	else {
    		BigNumber copyOfThis = new BigNumber(this.toString());
    		for(int i = copyOfThis.size()-1; i >= 0; i--) {
    			//used as temp storage for the digit Arraylist in BigNumber
    			//gets a sublist in the digit ArrayList of 'this'
    			//the size of the sublist varies each iteration, depending on how large bigN is
    			List<Integer> temp = copyOfThis.digits.subList(i, copyOfThis.size());
    			BigNumber comparingNumbers = new BigNumber(); //used in the actual comparing of the numbers within digits
    			comparingNumbers.digits = temp;
    			
    			int howMany = 0; //counter for how many times subtract was called
    			//if the sublist comparingNumbers > bigN, subtract bigN from comparingNumbers until bigN > comparingNumbers
    			if((bigN.compareTo(comparingNumbers) == 1) || (bigN.compareTo(comparingNumbers) == 0)) {
    				for(int t = 0; (bigN.compareTo(comparingNumbers) == 1) || (bigN.compareTo(comparingNumbers) == 0); t++) {
    					//subtract bigN from comaringNumbers until bigN is greater again, keeping track of how many subtracts
    					comparingNumbers = comparingNumbers.subtract(bigN);
    					howMany = t+1;
    				}
    				//normalize then pad to 'this' size
    				comparingNumbers.normalize();
    				comparingNumbers.pad(temp.size());
    				
    				
    				//Jon, what's the logic behind this?
    				for(int j = 0; j < temp.size(); j++) {
    					temp.set(j, comparingNumbers.digits.get(j));
    				}
    			}
    			quotient.digits.add(0, howMany);
    			remainder = comparingNumbers;
    		}
    	}
    	quotient.normalize();
    	remainder.normalize();
    	
    	//negate if only one is negative
    	if(neg == 1) {
    		quotient.negate();
    		remainder.negate();
    	}
    	//then return quotient and remainder
    	return new BigNumber[] {quotient, remainder};
    }
    
    /**
     * This method will factor a BigNumber. It returns nothing. instead it modifies a field 
     * factors in this class. factors lists all the factors in a BigNumber and is already pre-
     * populated with 1 (as all numbers have the factor 1). Uses the divide and mod methods
     * heavily.
     */
    protected void factor() {
    	BigNumber copyOfThis = new BigNumber(this.toString());
    	BigNumber two = new BigNumber("2"); //used to divide this in half
    	BigNumber zero = new BigNumber("0"); //used for comparisons        
    	BigNumber upperLimit = copyOfThis.divide(two);
    	
    	//divide the BigNumber in half....
    	for(BigNumber i = two; (i.compareTo(upperLimit) == 1); i = i.add(new BigNumber("1"))) {  
    		//and check whether any number goes evenly into it 
    		if(copyOfThis.mod(i).compareTo(zero) == 0) {
    			//add the factor i to the Arraylist  
    			factors.add(i);
    			// (this/i) is another factor so add it.
    			BigNumber factor2 = this.divide(i); 
    			factors.add(factor2);
    			upperLimit = factor2; //reassign upperLimit to reduce needless checks
    		}   		
    	}
    	//this is a factor of itself
    	factors.add(copyOfThis);
    	//normalize the BigNumbers as they may have gotten 0s or 9s appended in front whilst adding to the list
    	for(BigNumber bn : factors)
    		bn.normalize();
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
    
}
