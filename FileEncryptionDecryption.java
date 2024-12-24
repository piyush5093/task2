import java.io.*;
import java.util.Scanner;

public class FileEncryptionDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("File Encryption and Decryption Tool");
        System.out.println("1. Encrypt a file");
        System.out.println("2. Decrypt a file");
        System.out.print("Choose an option (1 or 2): ");
        int choice = scanner.nextInt();

        System.out.print("Enter the file path: ");
        scanner.nextLine(); // consume newline
        String filePath = scanner.nextLine();

        System.out.print("Enter a numeric key for encryption/decryption: ");
        int key = scanner.nextInt();

        switch (choice) {
            case 1:
                processFile(filePath, key, true);
                break;
            case 2:
                processFile(filePath, key, false);
                break;
            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }

    // Method to encrypt or decrypt a file
    private static void processFile(String filePath, int key, boolean isEncrypt) {
        File inputFile = new File(filePath);
        File outputFile = new File(isEncrypt ? "encrypted_file.txt" : "decrypted_file.txt");

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data ^ key); // XOR operation
            }

            System.out.println((isEncrypt ? "Encryption" : "Decryption") +
                    " completed. Output file: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
