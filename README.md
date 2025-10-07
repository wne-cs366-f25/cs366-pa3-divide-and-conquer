# CS366 - PA3: Karatsuba Algorithm & Divide-and-Conquer

This assignment combines theoretical analysis with practical implementation of the Karatsuba algorithm for fast integer multiplication. You will analyze asymptotic complexity, trace through the divide-and-conquer algorithm, and implement a working solution in Java.

## Part A: Karatsuba Algorithm Hand Trace

**Manual Algorithm Trace (Submit on Paper or Digital with Stylus)**

Using the provided **Karatsuba Trace Handout** (available in class and as a printout), manually trace through the Karatsuba algorithm for multiplying two 4-digit numbers.

**Problem:** Multiply 3412 × 1523 using the Karatsuba algorithm.

**Requirements:**

- Show all recursive calls and intermediate calculations
- Identify the values for x₁, y₁, x₂, y₂, A, B, C, D at each step
- Submit your hand-traced work on paper or digitally using a stylus
- **Also submit digitally:** Final values for x₁, y₁, x₂, y₂, A, B, C, D in your `ANALYSIS.md`

**Karatsuba Algorithm Review:**

For two n-digit numbers x and y:

- x = x₁ × 10^(n/2) + x₀
- y = y₁ × 10^(n/2) + y₀

Traditional multiplication requires 4 multiplications:

- x × y = x₁y₁ × 10^n + (x₁y₀ + x₀y₁) × 10^(n/2) + x₀y₀

Karatsuba reduces this to 3 multiplications:

- A = x₁ × y₁
- C = x₀ × y₀
- B = (x₁ + x₀)(y₁ + y₀) - A - C
- Result: A × 10^n + B × 10^(n/2) + C

## Part B: Programming Implementation

Implement the Karatsuba algorithm for fast integer multiplication using Java's `BigInteger` class.

### Method Implementation: `karatsuba(BigInteger x, BigInteger y)`

Complete the implementation in `KaratsubaAlgorithm.java` following the divide-and-conquer paradigm.

**Requirements:**

1. **Base Case:** Handle single-digit numbers with standard multiplication
2. **Recursive Case:**
   - Split numbers into high and low parts
   - Make 3 recursive calls for A, C, and (x₁+x₀)(y₁+y₀)
   - Combine results using Karatsuba formula
3. **Helper Methods:** Implement `getDigitCount()` and `splitNumber()`

**Implementation Guidelines:**

- Use `BigInteger` for all arithmetic operations
- Handle edge cases (different digit lengths, negative numbers)
- Follow the algorithm structure from the provided pseudocode
- Add appropriate comments explaining the logic

### Getting Started

#### Step 1: Run the Test Suite

Test your implementation as you develop:

```bash
./gradlew test
```

The test suite will verify:

- Correctness against known multiplication results
- Consistency with `BigInteger.multiply()`
- Performance characteristics
- Edge case handling

#### Step 2: Test with Main Method

Experiment with the provided test cases:

```bash
./gradlew run
```

The main method includes:

- Multiple test cases of increasing complexity
- Timing comparisons between standard and Karatsuba multiplication
- Verification that both methods produce identical results

#### Step 3: Complete Your Analysis

Document your findings in `ANALYSIS.md`:

- Mathematical proofs for Part B
- Hand-trace work from Part A
- Performance observations from your implementation
- Theoretical vs. empirical complexity analysis

# Submission Requirements

## Files to Submit

Your completed assignment should include:

1. **KaratsubaAlgorithm.java** - Your complete Karatsuba implementation
2. **ANALYSIS.md** - Complete theoretical analysis and hand-trace work
3. **Hand-traced worksheet** - Physical or digital stylus work (include in zip or print and hand in)
4. All original files (build.gradle, tests, etc.)

## Submission Process

### Method 1: DevContainer (Recommended)

```bash
tar -czf pa3-YOURNAME.zip /workspace/assignments/pa3
```

Download the file from the container and upload to Kodiak.

### Method 2: Local Development

Right-click the assignment folder, compress to zip, and upload to Kodiak.

## Grading Criteria

- **Submission (33.3%):** Complete submission with all required files and components
- **Completeness (33.3%):** All problems attempted in both programming and theoretical components
- **Correctness (33.3%):** Accurate implementations and mathematical analysis

## Getting Help

- **Office Hours**: Tuesday & Wednesday 12:30-1:30 PM (Herman 207)
- **Syllabot**: Use the course AI assistant for conceptual questions
- **Email the Instructor**: Reach out to the instructor for guidance
- **Discussion**: Collaborate on approaches but write your own code and analysis

## Academic Integrity

This is an **individual assignment**. You may:

- Discuss algorithmic approaches and mathematical concepts
- Use course materials, textbook, and lecture notes
- Ask questions during office hours
- Collaborate on understanding the theory

You may **NOT**:

- Copy code from other students or online sources
- Share your completed implementations
- Use AI tools to generate your solutions
- Submit work you don't fully understand

The goal is to develop your skills in both theoretical analysis and practical algorithm implementation.

**Due Date:** October 23 by 11:59 PM

**Late Policy:** 10% per day, maximum 5 days late

---

_Course content developed by Declan Gray-Mullen for WNEU with Claude_
