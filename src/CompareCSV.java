import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;
/*
Description: Consider a CSV file that stores a list of records (e.g., records of bank accounts).
You are required to write a software program that reads two such CSV files, compares records
stored in these CSV files row by row against a unique combination and records all mismatches
as exceptions. Finally, the software program generates another csv file listing the exceptions.

Example: Consider customer files that contain customer id, customer account number,
currency, account type (e.g., savings/current) and available balance. Compare available balance
against a unique combination of customer id, customer account number, account type and
currency. Generate a CSV file with records from both the files and corresponding to the
mismatched amount (i.e., balance) for the unique combination.
 */

public class CompareCSV {
    
    private String file1_path;
    private String file2_path;
    private ArrayList<String> compared_records;
    private String output_file;

    public CompareCSV(String file1_path, String file2_path) {
        this.file1_path = file1_path;
        this.file2_path = file2_path;
        this.output_file = "";
        this.compared_records = new ArrayList<>();
    }

    /**
     * Read file 1 and create a Class Account object for each record.
     * Search file 2 using the account number and compare.
     * If there is a mismatch, create a Class Exception object and add to a list.
     * Write the list to a file.
     */
    public void compare() {
        try {
            BufferedReader file1 = new BufferedReader(new java.io.FileReader(file1_path));
            String line1 = file1.readLine();

            while (line1 != null) {
                String[] record1 = line1.split(",");

                Account account1 = new Account(record1[0], record1[1], record1[2], record1[3], record1[4]);

                // search file 2 for account number
                String result = searchCsvLine(file2_path, 1, account1.acc_num);
                if (result != null) {
                    String[] record2 = result.split(",");
                    Account account2 = new Account(record2[0], record2[1], record2[2], record2[3], record2[4]);
                    if (!account1.equals(account2)) {
                        Exception exception = new Exception(account1.toString() + "\n" + account2.toString());
                        System.out.println(exception);
                        compared_records.add(account2.toString() + "\n" + account1.toString());
                    }
                }

                line1 = file1.readLine();
  
            }
            file1.close();
            if (compared_records.size() > 0) {
                writeToFile(compared_records);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(ArrayList<String> towrite) {
        try {
            String file_name = "mismatched_records_" + this.file1_path.charAt(this.file1_path.lastIndexOf(".") - 1) + "_and_" + this.file2_path.charAt(this.file2_path.lastIndexOf(".") - 1) + ".csv";
            // set the new file's path in the same directory as file1_path
            String file_path = this.file1_path.substring(0, this.file1_path.lastIndexOf("/") + 1) + file_name;
            this.setOutput_file(file_path);
            FileWriter fw = new FileWriter(file_path);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String line : towrite) {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns a string which is comma separate column values for matching row otherwise null. 
     * @param searchColumnIndex
     * @param searchString
     * @return
     * @throws IOException 
     * 
     */
    public static String searchCsvLine(String path,int searchColumnIndex, String searchString) throws IOException {
        String resultRow = null;
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ( (line = br.readLine()) != null ) {
            String[] values = line.split(",");
            if(values[searchColumnIndex].equals(searchString)) {
                resultRow = line;
                break;
            }
        }
        br.close();
        return resultRow;
    }

    public String getFile1_path() {
        return file1_path;
    }

    public String getFile2_path() {
        return file2_path;
    }

    public ArrayList<String> getCompared_records() {
        return compared_records;
    }

    public String getOutput_file() {
        return output_file;
    }

    public void setOutput_file(String output_file) {
        this.output_file = output_file;
    }
}
