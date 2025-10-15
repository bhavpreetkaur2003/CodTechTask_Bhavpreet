import java.io.*;
import java.util.Scanner;

class FileDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "myfile.txt";
        int choice;

        do {
            System.out.println("\n--- FILE HANDLING MENU ---");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Add (Modify) File");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear input buffer

            try {
                if (choice == 1) {
                    System.out.print("Enter text to write: ");
                    String text = sc.nextLine();
                    FileWriter fw = new FileWriter(fileName); // overwrite mode
                    fw.write(text);
                    fw.close();
                    System.out.println("File written successfully.");
                } 
                else if (choice == 2) {
                    FileReader fr = new FileReader(fileName);
                    int i;
                    System.out.println("\nFile content:");
                    while ((i = fr.read()) != -1) {
                        System.out.print((char) i);
                    }
                    fr.close();
                    System.out.println();
                } 
                else if (choice == 3) {
                    System.out.print("Enter text to add: ");
                    String add = sc.nextLine();
                    FileWriter fw = new FileWriter(fileName, true); // append mode
                    fw.write("\n" + add);
                    fw.close();
                    System.out.println("File modified successfully.");
                }
                else if (choice == 4) {
                    System.out.println("Exiting...");
                } 
                else {
                    System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 4);

        sc.close();
    }
}
