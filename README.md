# ESC_software_testing_mini_campaign
This is part of the 50.003 Elements of Software Construction.

Student ID: 1005460

## Problem Statement: Data Reconciliation

### Description
Consider a CSV file that stores a list of records (e.g., records of bank accounts).
You are required to write a software program that reads two such CSV files, compares records
stored in these CSV files row by row against a unique combination and records all mismatches
as exceptions. Finally, the software program generates another csv file listing the exceptions.

### Example
Consider customer files that contain customer id, customer account number,
currency, account type (e.g., savings/current) and available balance. Compare available balance
against a unique combination of customer id, customer account number, account type and
currency. Generate a CSV file with records from both the files and corresponding to the
mismatched amount (i.e., balance) for the unique combination

## Problem Solution

### Use Case Diagram
![image](https://user-images.githubusercontent.com/74559944/178147339-3d8246cb-e8eb-4e1f-b15b-e87b0db85968.png)

### Equivalence Class & Boundary Value Analysis
In the table below, we consider 3 example cases. Given the unique combination exists in both csv files, we check 3 types of balance values (lower, equal, and higher than 1st csv file). Account records have 2 possible outcomes (exception or not-exception). In the case where there is an exception, we take the 2 boundary values, whereby the balances are not equal, and one is greater/lower than the other.

For 1st row, given the same unique combination, the balance of 2nd csv is lower than that of the 1st csv, making this equivalence class pair a “lower” bound.


For 2nd row, given the same unique combination, the balance of 2nd csv is equal to that of the 1st csv, making this equivalence class pair the “middle”.


For 3rd row, given the same unique combination, the balance of 2nd csv is higher than that of the 1st csv, making this equivalence class pair a “higher” bound.

|    <br>No.    	|    <br>Account from 1st csv    	|    <br>Account from 2nd csv    	|    <br>Supposed outcome    	|
|---	|---	|---	|---	|
|    <br>1    	| Customer ID = “ID1”<br>Account No. = “BOS12345”<br>Currency = “USD”<br>Type = “SAVINGS”<br>Balance = “1000”    	| Customer ID = “ID1”<br>Account No. = “BOS12345”<br>Currency = “USD”<br>Type = “SAVINGS”<br>Balance = “500”    	| Write these 2 records into the new csv file as the balance for 2nd csv is lower than that of the 1st csv for that unique account.    	|
|    <br>2    	| Customer ID = “ID1”<br>Account No. = “BOS12345”<br>Currency = “USD”<br>Type = “SAVINGS”<br>Balance = “1000”    	| Customer ID = “ID1”<br>Account No. =  “BOS12345”<br>Currency = “USD”<br>Type = “SAVINGS”<br>Balance = “1000”    	| These 2 records will not be written into the new csv file as the unique combination matches as well as the balance. 	|
|    <br>3    	| Customer ID = “ID1”<br>Account No. = “BOS12345”<br>Currency = “USD”<br>Type = “SAVINGS”<br>Balance = “1000”    	| Customer ID = “ID1”<br>Account No. = “BOS12345”<br>Currency = “USD”<br>Type = “SAVINGS”<br>Balance = “2000”    	| Write these 2 records into the new csv file as the balance for 2nd csv is higher than that of the 1st csv for that unique account.    	|

### Implementation
In `Main.java`, include the string paths and pass them into the below function and run it.
```java
// Pass in the file path of the two csv files to be compared here
CompareCSV compareCSV = new CompareCSV(file_test1, file_test2);
```

`CompareCSV.java` will take in the two file paths and compare them using the `compare()` function. It reads the files line by line and creates an Account class to keep record of `customer_id`, `acc_num`, `acc_type`, `currency` and `balance`. During so, it checks and compares against both files to find any mismatch, write them to a new file which will be outputted as `mismatched_records_X_and_Y.csv`.

### Testing
Under the tests folder, there are 3 main classes: `AccountTest.java`, `CompareCSV.java` and `FuzzyTest.java`. The resource folder contains some CSV for testing purposes.

`AccountTest.java` tests for the functions within `Account.java` class. Using parameterized test, the 3 boundary values are created and included for each pair of files to be compared. The test `compareAccountTest()` checks that the compare method that was overriden in `Account.java` is implemented correctly.

`CompareCSV.java` tests for the functions within `CompareCSV.java` class. Follow the steps if you want to customise or use your own files for the testing. 

1. If your two input files has mismatched records, set `mismatch = true`
  ```java
  // 1.change to true if file 1 and file 2 have mismatched records
  mismatch = true;
  ```
2. Here we consider 2 use scenarios of the test file:
    - (A) Is the case where you already have a csv file that contains the correct mismtached csv records
    - (B) Is the case where you ONLY have 2 files that you want to compare, BUT you know the lines that      are mismatched.
3. Set the option for fuzzy testing
  ```java
  // 3. Set fuzzing to true if you want to fuzz the output file, note that we only fuzz the file in the 1st arg
  fuzzing = true;
  ```
4. Pass in the correct variable to the function below
  ```java
  // 4. Change the variable
  setCSV(file1_path, file3_path);
  ```
5. Following step 3, use the 2 functions accordingly
    - (A) Is the case where you already have a csv file that contains the correct mismtached csv records
      ```java
      // 5(a). Use this function if you do not have the correct output file
      compareCSV_no_output_file(file1_path, file3_path);
      ```
    - (B) Is the case where you ONLY have 2 files that you want to compare, BUT you know the lines that      are mismatched.
      ```java
      // 5(b). Use this function if you have the correct output file
      compareCSV_has_output_file();
      ```


## Tasks Timeline
### Week 8 [Document and Code] - Completed on 09/07/2022 under commit 'task 2 complete'
Use case diagram should be complete. All Java (or
similar) files should be complete in terms of implementing the functionality and (in the
bare minimum) the sample test files should pass. Deadline 10th July, 11.59pm.

### Week 9 [Document] - Completed on 15/07/2022 under commit 'task 3 complete'
A report on your boundary value analysis and equivalence class
partitioning should be complete. Deadline 17th July, 11.59pm.

### Week 10 [Code] - Completed on 24/07/2022 under commit 'task 4 complete'
All (Junit) test files conducting unit and system level testing should be
complete. Deadline 24th July, 11.59pm.

### Week 12 [Code] - Completed on 07/08/2022 under commit 'bug fix'
A refactored/bug fixed version of the code. The fuzzer code is
complete and properly documented for running by a third-party. The implementations of
others are also distributed this week for independent testing by group members.
Deadline 7th Aug, 11.59pm.

### Week 13 [Document and Code]
All new tests written for the third party implementation
should be complete. A report to briefly explain the bugs found in other’s implementation
should be complete. Each new bug found in another implementation will have a bounty
(in terms of bonus marks: 5 marks / unique bug). Deadline 14th Aug, 11.59pm.

