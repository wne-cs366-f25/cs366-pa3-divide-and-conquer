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

- x₁ = **\_**
- x₀ = **\_**

**y = 1523:**

- y₁ = **\_**
- y₀ = **\_**

### Recursive Calculations

#### Calculate A = x₁ × y₁

A = **\_** × **\_** = **\_**

#### Calculate C = x₀ × y₀

C = **\_** × **\_** = **\_**

#### Calculate (x₁ + x₀)(y₁ + y₀)

- x₁ + x₀ = **\_** + **\_** = **\_**
- y₁ + y₀ = **\_** + **\_** = **\_**
- (x₁ + x₀)(y₁ + y₀) = **\_** × **\_** = **\_**

#### Calculate B = (x₁ + x₀)(y₁ + y₀) - A - C

B = **\_** - **\_** - **\_** = **\_**

### Final Combination

Using the Karatsuba formula: A × 10ⁿ + B × 10^(n/2) + C

**Result:**

- A × 10⁴ = **\_** × 10000 = **\_**
- B × 10² = **\_** × 100 = **\_**
- C = **\_**

**Final Answer:** **\_** + **\_** + **\_** = **\_**

### Verification

Check your answer using standard multiplication: 3412 × 1523 = **\_**

---

## Part B: Programming Implementation Analysis

### Complexity Analysis

#### Karatsuba Algorithm Recurrence

Write the recurrence relation for the Karatsuba algorithm:

T(n) = [Answer Here]

**Master Theorem Application:**

**Which case of the Master Theorem applies?**

[Answer Here]

**Final complexity:**

[Answer Here]

### Implementation Observations

After implementing and testing your Karatsuba algorithm:

#### Performance Results

Fill in the following table based on your program's output (you will need to modify the main method to test these inputs):

| Input Size | Standard Time (ns) | Karatsuba Time (ns) | Speedup Factor |
| ---------- | ------------------ | ------------------- | -------------- |
| 4 digits   |                    |                     |                |
| 8 digits   |                    |                     |                |
| 16 digits  |                    |                     |                |
| 32 digits  |                    |                     |                |

#### Analysis Questions

1. **At what input size does Karatsuba become faster than standard multiplication?**

[Answer Here]

2. **Does your empirical data match the theoretical predictions? Explain any discrepancies.**

[Answer Here]

---

## Reflection

### Understanding Check

1. **What is the key insight that makes Karatsuba faster than traditional multiplication?**

[Answer Here]

2. **How does the divide-and-conquer paradigm apply to this problem?**

[Answer Here]

3. **Why is the recurrence T(n) = 3T(n/2) + O(n) rather than T(n) = 4T(n/2) + O(n)?**

[Answer Here]
