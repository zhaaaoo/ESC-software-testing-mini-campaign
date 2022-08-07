public class Main {
    public static void main(String[] args){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        String file1_path = "./sample/sample_file_1.csv";
        String file2_path = "./sample/sample_file_2.csv";
        String file3_path = "./sample/sample_file_3.csv";
        String file4_path = "./sample/sample_file_output_comparing_1_and_3.csv";

        String file_test1 = "tests/resource/accountlist1.csv";
        String file_test2 = "tests/resource/accountlist2.csv";

        // Pass in the file path of the two csv files to be compared here
        CompareCSV compareCSV = new CompareCSV(file_test1, file_test2);
        
        compareCSV.compare();
    }
}
