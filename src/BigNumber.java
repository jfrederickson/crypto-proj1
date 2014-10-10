/**
 * This class represents a number, possibly larger than the length of an int.
 * 
 * @author Jonathan Frederickson
 */

import java.util.ArrayList;

public class BigNumber {
	
	protected ArrayList<Integer> digits;
	
	protected BigNumber() {
		digits = new ArrayList<Integer>();
	}
	
	public BigNumber(String num) {
		
	}
	
	public BigNumber(int num) {
		
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
		int len = digits.size();
		for(int i=0; i<len; i++) {
			// Replace each digit in list with 9-itself
			// (Nine's complement)
			digits.set(i, 9 - digits.get(i));
		}
		
		// Add one (ten's complement)
		digits.set(0, 1 + digits.get(0));
	}
	
	/**
	 * Multiplies two BigNumbers together
	 * TODO: Implement shift-and-add
	 * TODO: Test this method - currently untested due to lack of add/ subtract methods
	 * @param mult The number to multiply by
	 * @return the result of this BigNumber * the parameter
	 */
	public BigNumber multiply(BigNumber mult) {
		normalize();
		mult.normalize();
		
		BigNumber result = this; // Start with this BigNumber
		
		int inc = mult.sign(); // 1 if multiplier is positive, -1 if negative
		
		if(inc == 0) return mult; // Multiplier is 0, result is 0
		
		// Create a BigNumber out of the increment so we can subtract it
		BigNumber bigInc = new BigNumber(inc);
		
		// Add this BigNumber to itself "mult" times
		while(mult.sign() != 0) {
			result = result.add(this);
			// Subtract one if multiplier is positive, add if negative
			mult = mult.subtract(bigInc);
		}
		
		return result;
	}
	
	/**
	 * Divides one BigNumber by another
	 * TODO: Implement shift-and-subtract
	 * TODO: Test this method, currently untested due to lack of subtract method
	 * @param div The number to divide by
	 * @return the result of this BigNumber / the parameter
	 */
	public BigNumber divide(BigNumber div) {
		normalize();
		div.normalize();
		
		BigNumber result = this; // Start with this BigNumber
		
		if(div.sign() == 0) return null; // Divisor is 0, result is 0
		
		// As long as this BigNum is greater than the divisor, subtract div from the result
		while(compareTo(div) > 0) {
			result = result.subtract(div);
		}
		
		return result;
	}
	
	
	/**
	 * Checks the sign of this BigNumber
	 * @return -1 if this BigNumber is negative, 1 if positive, 0 if zero
	 */
	public int sign() {
		normalize();
		
		int len = digits.size();
		int num = digits.get(len-1); // Most significant digit
		
		if(num >= 5) return -1;
		else if(num < 5 && num > 0) return 1;
		
		// Leading digit is zero, check if there are digits
		// The BigNumber is positive if they are
		else for(int i=len-1; i>=0; i--) {
			if(digits.get(i) > 0) return 1;
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
		
		int len1 = digits.size();
		int len2 = cmp.digits.size();
		int msb = digits.get(len1-1); // Most significant digit of this BigNumber
		int msbcmp = cmp.digits.get(len2-1); // Most significant digit of parameter
		
		// This BigNumber is negative, parameter is positive
		if(msb >= 5 && msbcmp < 5) return 1;
		// This BigNumber is positive, param is negative
		else if(msb < 5 && msbcmp >= 5) return -1;
		// This BigNumber is longer, so greater than param
		else if(len1 > len2) return 1;
		// This is shorter, so less than param
		else if(len1 < len2) return -1;
		
		// Both are equal length, both either positive or negative
		else {
			for (int i = len1-1; i>=0; i--) {
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
	
	// Stubs
	
	public BigNumber add(BigNumber a) {
		return null;
	}
	
	public BigNumber subtract(BigNumber s) {
		return null;
	}
	
	public void normalize() {
		
	}
}
