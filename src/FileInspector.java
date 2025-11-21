import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class FileInspector {
    public static void main(String[] args) {
        try {
            // JFileChooser opens in src directory
            JFileChooser chooser = new JFileChooser("src");
            int result = chooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("Processing file: " + file.getName());

                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;

                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line); // echo line to screen

                    lineCount++;
                    String[] words = line.trim().split("\\s+");
                    if (!line.trim().isEmpty()) {
                        wordCount += words.length;
                    }
                    charCount += line.length();
                }
                scanner.close();

                // Summary report
                System.out.println("\n--- File Summary ---");
                System.out.println("File name: " + file.getName());
                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of words: " + wordCount);
                System.out.println("Number of characters: " + charCount);
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
