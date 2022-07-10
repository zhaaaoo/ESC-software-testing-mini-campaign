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

### Use Case Diagram
![image](https://user-images.githubusercontent.com/74559944/178147339-3d8246cb-e8eb-4e1f-b15b-e87b0db85968.png)

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

