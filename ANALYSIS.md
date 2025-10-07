# CS366 - PA3: Analysis

## Part A: Karatsuba Algorithm Hand Trace

**Problem:** Multiply 3412 × 1523 using the Karatsuba algorithm.

### Initial Setup

- x = 3412 (4 digits)
- y = 1523 (4 digits)
- n = 4, so n/2 = 2

### First Level Split

Split each number at position 2 (from the right):

**x = 3412:**

- x₁ = **34**
- x₀ = **12**

**y = 1523:**

- y₁ = **15**
- y₀ = **23**

### Recursive Calculations

#### Calculate A = x₁ × y₁

A = **34** × **15** = **510**

#### Calculate C = x₀ × y₀

C = **12** × **23** = **276**

#### Calculate (x₁ + x₀)(y₁ + y₀)

- x₁ + x₀ = **34** + **12** = **46**
- y₁ + y₀ = **15** + **23** = **38**
- (x₁ + x₀)(y₁ + y₀) = **46** × **38** = **1748**

#### Calculate B = (x₁ + x₀)(y₁ + y₀) - A - C

B = **1748** - **510** - **276** = **962**

### Final Combination

Using the Karatsuba formula: A × 10ⁿ + B × 10^(n/2) + C

**Result:**

- A × 10⁴ = **510** × 10000 = **5100000**
- B × 10² = **962** × 100 = **96200**
- C = **276**

**Final Answer:** **5100000** + **96200** + **276** = **5196476**

### Verification

Check your answer using standard multiplication: 3412 × 1523 = **5196476** ✓

---

## Part B: Programming Implementation Analysis

### Complexity Analysis

#### Karatsuba Algorithm Recurrence

Write the recurrence relation for the Karatsuba algorithm:

T(n) = 3T(n/2) + O(n)

**Master Theorem Application:**

**Which case of the Master Theorem applies?**

For T(n) = 3T(n/2) + O(n), we have:
- a = 3
- b = 2
- f(n) = O(n)

Since n^(log_b(a)) = n^(log_2(3)) ≈ n^1.585, and f(n) = O(n) = O(n^1), we have f(n) = O(n^c) where c = 1 < log_2(3).

This corresponds to Case 1 of the Master Theorem.

**Final complexity:**

T(n) = O(n^(log_2(3))) = O(n^1.585)

### Implementation Observations

After implementing and testing your Karatsuba algorithm:

#### Performance Results

Fill in the following table based on your program's output:

| Input Size | Standard Time (ns) | Karatsuba Time (ns) | Speedup Factor |
| ---------- | ------------------ | ------------------- | -------------- |
| 4 digits   | ~15590            | ~756248             | 0.02x          |
| 9 digits   | ~1683             | ~1895794            | 0.00x          |
| 20 digits  | ~14989            | ~3216092            | 0.00x          |

#### Analysis Questions

1. **At what input size does Karatsuba become faster than standard multiplication?**

Karatsuba typically becomes faster than standard multiplication for numbers with more than 10-20 digits, though this depends on the implementation and constant factors. For very small numbers, the overhead of recursion makes standard multiplication faster.

2. **Does your empirical data match the theoretical predictions? Explain any discrepancies.**

For small inputs, Karatsuba may appear slower due to recursion overhead and constant factors. The theoretical advantage becomes apparent with larger inputs. Any discrepancies for small inputs are expected due to the constant factors being significant compared to the asymptotic behavior.

---

## Reflection

### Understanding Check

1. **What is the key insight that makes Karatsuba faster than traditional multiplication?**

The key insight is reducing the number of recursive multiplications from 4 to 3 by cleverly computing (x₁ + x₀)(y₁ + y₀) and then subtracting the already computed products A and C to get B. This reduces the complexity from O(n²) to O(n^1.585).

2. **How does the divide-and-conquer paradigm apply to this problem?**

Divide-and-conquer applies by:
- **Divide**: Split each n-digit number into two n/2-digit parts
- **Conquer**: Recursively multiply the smaller parts
- **Combine**: Use the Karatsuba formula to combine the results of the subproblems

3. **Why is the recurrence T(n) = 3T(n/2) + O(n) rather than T(n) = 4T(n/2) + O(n)?**

Traditional multiplication requires 4 recursive calls: x₁y₁, x₁y₀, x₀y₁, and x₀y₀. Karatsuba reduces this to 3 calls by computing A = x₁y₁, C = x₀y₀, and (x₁ + x₀)(y₁ + y₀), then calculating B = (x₁ + x₀)(y₁ + y₀) - A - C to get the middle term x₁y₀ + x₀y₁ without explicitly computing both products.
