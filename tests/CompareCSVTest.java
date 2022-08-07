import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

public class CompareCSVTest {

    private Boolean mismatch;
    private Boolean fuzzing;
    private FuzzyTest fuzzyTest;
    private String fuzzed_path;
    private String file1_path;
    private String file2_path;
    private String file3_path;
    private String correct_output_given;
    private CompareCSV compareCSV;
    private ArrayList<Account> wrong_records = new ArrayList<Account>();
    private ArrayList<Account> output_records = new ArrayList<Account>();
    private ArrayList <Integer> file1_wrongline = new ArrayList<Integer>();
    private ArrayList <Integer> file2_wrongline = new ArrayList<Integer>();

    // test to check that the compare function of class CompareCSV works correctly
    @Before
    public void compareCSV() {
        // 1.change to true if file 1 and file 2 have mismatched records
        mismatch = true;

        // 2(a). uncomment below if you have the correct output file
        System.out.println(System.getProperty("user.dir"));
        String file1_path = "tests/resource/sample_file_1.csv";
        String file2_path = "tests/resource/sample_file_2.csv";
        String file3_path = "tests/resource/sample_file_3.csv";
        correct_output_given = "tests/resource/sample_file_output_comparing_1_and_3.csv";

        // 2(b). uncomment below if you do not have the correct output file and only know the wrong index of account in csv
        // file1_path = "tests/resource/accountlist1.csv";
        // file2_path = "tests/resource/accountlist2.csv";
        // file1_wrongline.add(0,2);
        // file2_wrongline.add(0,2);

        // 3. Set fuzzing to true if you want to fuzz the output file
        fuzzing = true;

        // 4. Change the variable
        setCSV(file1_path, file3_path);

        // 5(a). Use this function if you do not have the correct output file
        compareCSV_no_output_file(file1_path, file3_path);

        // 5(b). Use this function if you have the correct output file
        // compareCSV_has_output_file();
    }
    

    // helper function
    private void setCSV(String file1_path, String file2_path) {
        compareCSV = new CompareCSV(file1_path, file2_path);
        if (fuzzing) {
            fuzzed_path = "tests/resource/fuzzed_" + file1_path.charAt(file1_path.lastIndexOf(".") - 1) + ".csv";
            fuzzyTest = new FuzzyTest(fuzzed_path, correct_output_given);
            fuzzyTest.generateFuzzedCsv();
            compareCSV = new CompareCSV(fuzzed_path, file2_path);
        }
    }
    
    // use this function if you do not have the correct output file
    public void compareCSV_no_output_file(String file1_path, String file2_path) {
        // Read file 1 and 2 and create a Class Account object for the wrong lines and add into the wrong_records list
        if (fuzzing) {
            file1_path = fuzzed_path;
            file1_wrongline = fuzzyTest.file1_wrongline;
        }
        try {
            BufferedReader file1 = new BufferedReader(new java.io.FileReader(file1_path));
            String line1 = file1.readLine();
            int line_num = 1;
            while (line1 != null) {
                String[] record1 = line1.split(",");
                Account account1 = new Account(record1[0], record1[1], record1[2], record1[3], record1[4]);
                if (file1_wrongline.contains(line_num)) {
                    wrong_records.add(account1);
                }
                line1 = file1.readLine();
                line_num++;
            }
            file1.close();
            BufferedReader file2 = new BufferedReader(new java.io.FileReader(file2_path));
            String line2 = file2.readLine();
            line_num = 1;
            while (line2 != null) {
                String[] record2 = line2.split(",");
                Account account2 = new Account(record2[0], record2[1], record2[2], record2[3], record2[4]);
                if (file2_wrongline.contains(line_num)) {
                    wrong_records.add(account2);
                }
                line2 = file2.readLine();
                line_num++;
            }
            file2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // use this function if you have the correct output file
    public void compareCSV_has_output_file() {
        // create a Account object for each line in the correct output file and add it to the wrong_records list
        try {
            BufferedReader correct_output = new BufferedReader(new java.io.FileReader(correct_output_given));
            String line1 = correct_output.readLine();
            line1 =null;
            while (line1 != null) {
                String[] record1 = line1.split(",");
                Account account1 = new Account(record1[0], record1[1], record1[2], record1[3], record1[4]);
                wrong_records.add(account1);
                line1 = correct_output.readLine();
            }
            correct_output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void compareCSVTest() {
        compareCSV.compare();
        // checks that an output file is created
        // print everything in wrong_records list
        for (Account account : wrong_records) {
            System.out.println(account.toString());
        }
        if (wrong_records.size() <= 0) {
            // check that no new output file is created
            assertEquals(false, new File(compareCSV.getOutput_file()).exists());
        } else {
            assertTrue(new File(compareCSV.getOutput_file()).exists());
            // creates an array of Account objects from each entry in the output csv file and add to the output_records list
            try {
                BufferedReader file = new BufferedReader(new java.io.FileReader(compareCSV.getOutput_file()));
                String line = file.readLine();
                while (line != null) {
                    String[] record = line.split(",");
                    Account account = new Account(record[0], record[1], record[2], record[3], record[4]);
                    output_records.add(account);
                    line = file.readLine();
                }
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
    
                // checks that the output file is same as wrong_records list
                assertEquals(wrong_records, output_records);
            }

        }
    }
}
