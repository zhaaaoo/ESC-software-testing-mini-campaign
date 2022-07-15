# ESC_software_testing_mini_campaign
This is part of the 50.003 Elements of Software Construction

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


### Tasks Timeline
#### Week 8 [Document and Code]
Use case diagram should be complete. All Java (or
similar) files should be complete in terms of implementing the functionality and (in the
bare minimum) the sample test files should pass. Deadline 10th July, 11.59pm.

### Week 9 [Document]
A report on your boundary value analysis and equivalence class
partitioning should be complete. Deadline 17th July, 11.59pm.

### Week 10 [Code]
All (Junit) test files conducting unit and system level testing should be
complete. Deadline 24th July, 11.59pm.

### Week 12 [Code]
A refactored/bug fixed version of the code. The fuzzer code is
complete and properly documented for running by a third-party. The implementations of
others are also distributed this week for independent testing by group members.
Deadline 7th Aug, 11.59pm.

### Week 13 [Document and Code]
All new tests written for the third party implementation
should be complete. A report to briefly explain the bugs found in other’s implementation
should be complete. Each new bug found in another implementation will have a bounty
(in terms of bonus marks: 5 marks / unique bug). Deadline 14th Aug, 11.59pm.

