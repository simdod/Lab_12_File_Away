import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();

        System.out.println("=== DataSaver CSV Builder ===");

        boolean more = true;

        while (more) {
            // Get data using YOUR SafeInput methods
            String first = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String last = SafeInput.getNonZeroLenString(in, "Enter Last Name");

            // ID must be digits → SafeInput.getRegExString
            String id = SafeInput.getRegExString(in, "Enter Numeric ID", "\\d+");
            id = String.format("%06d", Integer.parseInt(id));   // zero-padded to 6 digits

            String email = SafeInput.getNonZeroLenString(in, "Enter Email");
            int birthYear = SafeInput.getRangedInt(in, "Enter Year of Birth", 1900, 2050);

            // Build CSV record
            String record = first + ", " + last + ", " + id + ", " + email + ", " + birthYear;

            records.add(record);

            more = SafeInput.getYNConfirm(in, "Add another record?");
        }

        // Ask for output filename
        String filename = SafeInput.getNonZeroLenString(in, "Enter file name (without extension)");
        filename = "src/" + filename + ".csv";

        // Write CSV to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String r : records) {
                writer.write(r);
                writer.newLine();
            }
            System.out.println("\nSuccessfully saved " + records.size()
                    + " records to: " + filename);

        } catch (IOException e) {
            System.out.println("Error saving file.");
            e.printStackTrace();
        }

        in.close();
    }
}
