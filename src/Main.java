public class Main {
    public static void main(String[] args){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        String file1_path = "./sample/sample_file_1.csv";
        String file2_path = "./sample/sample_file_2.csv";
        String file3_path = "./sample/sample_file_3.csv";
        String file4_path = "./sample/sample_file_output_comparing_1_and_3.csv";

        CompareCSV compareCSV = new CompareCSV(file1_path, file3_path);

        compareCSV.compare();
    }
}
