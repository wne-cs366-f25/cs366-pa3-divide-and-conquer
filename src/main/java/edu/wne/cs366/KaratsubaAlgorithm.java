package edu.wne.cs366;

import java.math.BigInteger;

/**
 * CS366 - PA3: Karatsuba Algorithm & Divide-and-Conquer
 * 
 * This assignment focuses on implementing the Karatsuba algorithm for fast integer multiplication
 * using the divide-and-conquer paradigm, alongside theoretical analysis problems.
 * 
 * @author Student Name
 * @date Due: October 23, 2025
 */
public class KaratsubaAlgorithm {
    
    /**
     * Implements the Karatsuba algorithm for multiplying two large integers.
     * 
     * The Karatsuba algorithm reduces the number of single-digit multiplications
     * from 4 to 3 by using the divide-and-conquer approach:
     * 
     * For two n-digit numbers x and y:
     * x = x1 * 10^(n/2) + x0
     * y = y1 * 10^(n/2) + y0
     * 
     * Traditional: x*y = x1*y1*10^n + (x1*y0 + x0*y1)*10^(n/2) + x0*y0
     * Karatsuba: x*y = A*10^n + B*10^(n/2) + C
     * where:
     * A = x1 * y1
     * C = x0 * y0  
     * B = (x1 + x0)(y1 + y0) - A - C
     * 
     * @param x first integer to multiply
     * @param y second integer to multiply
     * @return the product of x and y
     */
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        // Handle negative numbers
        boolean negativeResult = (x.signum() < 0) ^ (y.signum() < 0);
        x = x.abs();
        y = y.abs();
        
        // Base case: if either number has 1 digit, use standard multiplication
        int xDigits = getDigitCount(x);
        int yDigits = getDigitCount(y);
        
        if (xDigits == 1 || yDigits == 1) {
            BigInteger result = x.multiply(y);
            return negativeResult ? result.negate() : result;
        }
        
        // Make sure both numbers have the same number of digits (pad with zeros if needed)
        int n = Math.max(xDigits, yDigits);
        if (n % 2 == 1) {
            n++; // Make n even for easier splitting
        }
        
        int half = n / 2;
        
        // Split x and y into high and low parts
        BigInteger[] xParts = splitNumber(x, half);
        BigInteger[] yParts = splitNumber(y, half);
        
        BigInteger x1 = xParts[0]; // high part of x
        BigInteger x0 = xParts[1]; // low part of x
        BigInteger y1 = yParts[0]; // high part of y
        BigInteger y0 = yParts[1]; // low part of y
        
        // Recursively calculate the three products
        BigInteger A = karatsuba(x1, y1); // A = x1 * y1
        BigInteger C = karatsuba(x0, y0); // C = x0 * y0
        BigInteger B = karatsuba(x1.add(x0), y1.add(y0)).subtract(A).subtract(C); // B = (x1+x0)(y1+y0) - A - C
        
        // Combine results using Karatsuba formula: A * 10^n + B * 10^(n/2) + C
        BigInteger result = A.multiply(BigInteger.TEN.pow(n))
                             .add(B.multiply(BigInteger.TEN.pow(half)))
                             .add(C);
        
        return negativeResult ? result.negate() : result;
    }
    
    /**
     * Helper method to get the number of digits in a BigInteger.
     * 
     * @param num the BigInteger to count digits for
     * @return the number of digits in num
     */
    private static int getDigitCount(BigInteger num) {
        if (num.equals(BigInteger.ZERO)) {
            return 1;
        }
        return num.abs().toString().length();
    }
    
    /**
     * Helper method to split a BigInteger into high and low parts.
     * 
     * For a number with n digits, split at position n/2.
     * Example: 1234 with split at 2 gives high=12, low=34
     * 
     * @param num the number to split
     * @param splitPosition where to split (from the right)
     * @return array where [0] is high part, [1] is low part
     */
    private static BigInteger[] splitNumber(BigInteger num, int splitPosition) {
        BigInteger divisor = BigInteger.TEN.pow(splitPosition);
        BigInteger high = num.divide(divisor);
        BigInteger low = num.remainder(divisor);
        return new BigInteger[]{high, low};
    }
    
    /**
     * Standard multiplication for comparison and verification.
     * 
     * @param x first integer
     * @param y second integer
     * @return product using BigInteger's built-in multiply
     */
    public static BigInteger standardMultiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }
    
    /**
     * Main method for testing the Karatsuba implementation.
     * 
     * Tests various cases and compares performance with standard multiplication.
     */
    public static void main(String[] args) {
        System.out.println("CS366 - PA3: Karatsuba Algorithm Implementation");
        System.out.println("================================================");
        
        // Test cases with 4, 8, 16, and 32 digits
        BigInteger[] testCasesX = {
            new BigInteger("1234"),                                    // 4 digits
            new BigInteger("12345678"),                                // 8 digits
            new BigInteger("1234567890123456"),                        // 16 digits
            new BigInteger("12345678901234567890123456789012")         // 32 digits
        };
        
        BigInteger[] testCasesY = {
            new BigInteger("5678"),                                    // 4 digits
            new BigInteger("87654321"),                                // 8 digits
            new BigInteger("6543210987654321"),                        // 16 digits
            new BigInteger("98765432109876543210987654321098")         // 32 digits
        };
        
        System.out.println("\nTesting Karatsuba Implementation:");
        System.out.println("---------------------------------");
        
        for (int i = 0; i < testCasesX.length; i++) {
            BigInteger x = testCasesX[i];
            BigInteger y = testCasesY[i];
            
            System.out.printf("\nTest %d: %s × %s\n", i + 1, x, y);
            
            // Time standard multiplication
            long startTime = System.nanoTime();
            BigInteger standardResult = standardMultiply(x, y);
            long standardTime = System.nanoTime() - startTime;
            
            // Time Karatsuba multiplication
            startTime = System.nanoTime();
            BigInteger karatsubaResult = karatsuba(x, y);
            long karatsubaTime = System.nanoTime() - startTime;
            
            System.out.printf("Standard result:  %s\n", standardResult);
            System.out.printf("Karatsuba result: %s\n", karatsubaResult);
            System.out.printf("Results match: %s\n", standardResult.equals(karatsubaResult));
            System.out.printf("Standard time:  %d nanoseconds\n", standardTime);
            System.out.printf("Karatsuba time: %d nanoseconds\n", karatsubaTime);
            
            if (karatsubaTime > 0) {
                double speedup = (double) standardTime / karatsubaTime;
                System.out.printf("Speedup factor: %.2fx\n", speedup);
            }
        }
        
        System.out.println("\n================================================");
        System.out.println("Complete the theoretical analysis in ANALYSIS.md");
        System.out.println("and hand-trace the algorithm using the provided worksheet.");
    }
}