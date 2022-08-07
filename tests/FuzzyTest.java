import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class FuzzyTest {
    String csv_file_path;
    String output_file_path;
    ArrayList <Integer> file1_wrongline = new ArrayList<Integer>();

    public FuzzyTest(String csv_file_path, String output_file_path) {
        this.csv_file_path = csv_file_path;
        this.output_file_path = output_file_path;
    }


    // Takes in the correct csv file and generates a a csv file with fuzzed entries
    public void generateFuzzedCsv() {
        try {
            BufferedReader file = new BufferedReader(new java.io.FileReader(csv_file_path));
            String line = file.readLine();
            ArrayList<String> fuzzed_records = new ArrayList<>();
            fuzzed_records.add(line);
            line = file.readLine();
            int line_num = 2;
            while (line != null) {

                Integer skip_line = (int) (Math.random() * 10);
                while ((skip_line != 0) && (line != null)) {
                    line = file.readLine();
                    line_num++;
                    skip_line = (int) (Math.random() * 10);
                }

                // adds the lines that are fuzzed
                file1_wrongline.add(line_num);

                String[] record = line.split(",");
                String[] fuzzed_record = record;
                /*
                 * Generates a random number that represents the fuzz method
                 * 0 - Replace a random character in the input string to a random character or number
                 * 1 - Trimming an input string at a randomly chosen position
                 * 2 - Swap random adjacent characters in the input string
                 */
                Integer fuzz_type = (int) (Math.random() * 3);
                switch (fuzz_type) {
                    case 0:
                        for (int i=0; i<5; i++){
                            // remove the " character from the input string
                            String to_fuzz = record[i].replace("\"", "");
                            fuzzed_record[i] = "\"" + randReplace(to_fuzz) + "\"";
                        }
                        fuzzed_records.add(String.join(",", fuzzed_record));
                        break;
                    case 1:
                        for (int i=0; i<5; i++){
                            String to_fuzz = record[i].replace("\"", "");
                            fuzzed_record[i] = "\"" + randTrim(to_fuzz) + "\"";
                        }
                        fuzzed_records.add(String.join(",", fuzzed_record));
                        break;
                    case 2:
                        for (int i=0; i<5; i++){
                            String to_fuzz = record[i].replace("\"", "");
                            fuzzed_record[i] = "\"" + swapAdjacent(to_fuzz) + "\"";
                        }
                        fuzzed_records.add(String.join(",", fuzzed_record));
                        break;
                }

                line = file.readLine();
                line_num++;
            }
            file.close();
            writeToFile(fuzzed_records, output_file_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(ArrayList<String> records, String output_file_path) {
        try {
            BufferedWriter file = new BufferedWriter(new java.io.FileWriter(output_file_path));
            for (String record : records) {
                file.write(record);
                file.newLine();
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Replace a random character in the input string to a random character or number
    private static String randReplace(String input) {
        int position = (int) (Math.random() * input.length());
        char[] chars = input.toCharArray();
        Integer num_char = (int) (Math.random() * 2);

        char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] nums = "0123456789".toCharArray();
        if (num_char == 0) {
            chars[position] = alpha[(int) (Math.random() * 26)];
        } else {
            chars[position] = nums[(int) (Math.random() * 10)];
        }

        return new String(chars);
    }


    // Trimming an input string at a randomly chosen position
    private static String randTrim(String input) {
        int position = (int) (Math.random() * input.length());
        String output = input.substring(0, position) + input.substring(position + 1);
        return output;
    }

    // Swap random adjacent characters at random position
    private static String swapAdjacent(String input) {
        Integer index = (int) (Math.random() * input.length());
        char[] input_char_arr = input.toCharArray();
        if (index == input.length()-1 ) {
            char temp = input_char_arr[index];
            input_char_arr[index] = input_char_arr[index - 1];
            input_char_arr[index - 1] = temp;
            String output = new String(input_char_arr);
            return output;
        } else {
            char temp = input_char_arr[index];
            input_char_arr[index] = input_char_arr[index + 1];
            input_char_arr[index + 1] = temp;
            String output = new String(input_char_arr);
            return output;
        }
    }



}
