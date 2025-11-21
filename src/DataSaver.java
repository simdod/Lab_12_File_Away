import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataSaver {

    public static void main(String[] args) {
        ArrayList<String> records = new ArrayList<>();
        boolean keepGoing = true;

        System.out.println("=== DataSaver CSV Builder ===");

        while (keepGoing) {
            String first = SafeInput.getNonZeroLenString("Enter First Name");
            String last = SafeInput.getNonZeroLenString("Enter Last Name");

            String id = SafeInput.getRegExString("Enter ID (numeric)", "\\d+");
            id = String.format("%06d", Integer.parseInt(id));

            String email = SafeInput.getNonZeroLenString("Enter Email");
            int birthYear = SafeInput.getRangedInt("Enter Year of Birth", 1900, 2050);

            String record = first + ", " + last + ", " + id + ", " + email + ", " + birthYear;
            records.add(record);

            keepGoing = SafeInput.getYNConfirm("Add another record?");
        }

        String filename = SafeInput.getNonZeroLenString("Enter output filename (without extension)");
        filename = "src/" + filename + ".csv";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            for (String r : records) {
                writer.write(r);
                writer.newLine();
            }

            writer.close();
            System.out.println("\nSaved " + records.size() + " records to: " + filename);

        } catch (IOException e) {
            System.out.println("Error saving the file.");
            e.printStackTrace();
        }
    }
}
