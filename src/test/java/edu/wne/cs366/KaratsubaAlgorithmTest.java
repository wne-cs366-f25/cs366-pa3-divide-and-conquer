package edu.wne.cs366;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * CS366 - PA3: Test Suite for Karatsuba Algorithm Implementation
 * 
 * Comprehensive test suite to verify the correctness and performance 
 * characteristics of the Karatsuba algorithm implementation.
 * 
 * @author CS366 Course Staff
 * @date Fall 2025
 */
class KaratsubaAlgorithmTest {

    /**
     * Test basic functionality with small numbers
     */
    @Test
    void testBasicMultiplication() {
        BigInteger x = new BigInteger("1234");
        BigInteger y = new BigInteger("5678");
        BigInteger expected = new BigInteger("7006652");
        
        BigInteger result = KaratsubaAlgorithm.karatsuba(x, y);
        assertEquals(expected, result, "Basic multiplication failed");
    }
    
    /**
     * Test single digit multiplication (base case)
     */
    @Test
    void testSingleDigitMultiplication() {
        BigInteger x = new BigInteger("7");
        BigInteger y = new BigInteger("8");
        BigInteger expected = new BigInteger("56");
        
        BigInteger result = KaratsubaAlgorithm.karatsuba(x, y);
        assertEquals(expected, result, "Single digit multiplication failed");
    }
    
    /**
     * Test multiplication with zero
     */
    @Test
    void testMultiplicationWithZero() {
        BigInteger x = new BigInteger("12345");
        BigInteger y = BigInteger.ZERO;
        BigInteger expected = BigInteger.ZERO;
        
        BigInteger result = KaratsubaAlgorithm.karatsuba(x, y);
        assertEquals(expected, result, "Multiplication with zero failed");
    }
    
    /**
     * Test multiplication with one
     */
    @Test
    void testMultiplicationWithOne() {
        BigInteger x = new BigInteger("98765");
        BigInteger y = BigInteger.ONE;
        BigInteger expected = new BigInteger("98765");
        
        BigInteger result = KaratsubaAlgorithm.karatsuba(x, y);
        assertEquals(expected, result, "Multiplication with one failed");
    }
    
    /**
     * Test consistency with BigInteger.multiply() for various inputs
     */
    @Test
    void testConsistencyWithStandardMultiplication() {
        BigInteger[] testNumbers = {
            new BigInteger("123"),
            new BigInteger("456"),
            new BigInteger("123456789"),
            new BigInteger("987654321"),
            new BigInteger("1234567890123456789"),
            new BigInteger("9876543210987654321")
        };
        
        for (int i = 0; i < testNumbers.length; i++) {
            for (int j = i; j < testNumbers.length; j++) {
                BigInteger x = testNumbers[i];
                BigInteger y = testNumbers[j];
                
                BigInteger expected = x.multiply(y);
                BigInteger result = KaratsubaAlgorithm.karatsuba(x, y);
                
                assertEquals(expected, result, 
                    String.format("Consistency test failed for %s × %s", x, y));
            }
        }
    }
    
    /**
     * Test algorithm performance doesn't exceed reasonable time bounds
     */
    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testPerformance() {
        BigInteger x = new BigInteger("123456789012345678901234567890");
        BigInteger y = new BigInteger("987654321098765432109876543210");
        
        BigInteger result = KaratsubaAlgorithm.karatsuba(x, y);
        BigInteger expected = x.multiply(y);
        
        assertEquals(expected, result, "Large number multiplication failed");
    }
    
    /**
     * Test commutative property: x*y = y*x
     */
    @Test
    void testCommutativeProperty() {
        BigInteger x = new BigInteger("12345");
        BigInteger y = new BigInteger("67890");
        
        BigInteger result1 = KaratsubaAlgorithm.karatsuba(x, y);
        BigInteger result2 = KaratsubaAlgorithm.karatsuba(y, x);
        
        assertEquals(result1, result2, "Commutative property test failed");
    }
    
    /**
     * Test negative numbers
     */
    @Test
    void testNegativeNumbers() {
        BigInteger x = new BigInteger("-1234");
        BigInteger y = new BigInteger("5678");
        BigInteger expected = new BigInteger("-7006652");
        
        BigInteger result = KaratsubaAlgorithm.karatsuba(x, y);
        assertEquals(expected, result, "Negative number multiplication failed");
    }
    
    /**
     * Test very large numbers
     */
    @Test
    void testVeryLargeNumbers() {
        // Generate large random-ish numbers
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
        for (int i = 0; i < 50; i++) {
            sb1.append((i % 9) + 1); // Avoid leading zeros
            sb2.append(((i * 3) % 9) + 1);
        }
        
        BigInteger x = new BigInteger(sb1.toString());
        BigInteger y = new BigInteger(sb2.toString());
        
        BigInteger expected = x.multiply(y);
        BigInteger result = KaratsubaAlgorithm.karatsuba(x, y);
        
        assertEquals(expected, result, "Very large number multiplication failed");
    }
    
    /**
     * Test the specific example from the hand-trace requirement
     */
    @Test
    void testHandTraceExample() {
        BigInteger x = new BigInteger("3412");
        BigInteger y = new BigInteger("1523");
        BigInteger expected = new BigInteger("5194276");
        
        BigInteger result = KaratsubaAlgorithm.karatsuba(x, y);
        assertEquals(expected, result, "Hand-trace example (3412 × 1523) failed");
    }
}