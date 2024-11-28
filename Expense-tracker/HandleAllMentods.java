import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class HandleAllMentods{
public static void addingExpense() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter expense name (format: Date, Description, Amount):");
    String expenseAdd = scanner.nextLine();

    try (PrintWriter pw = new PrintWriter(new java.io.FileWriter("new.txt", true))) {
        pw.println(expenseAdd); // Append the new expense
        System.out.println("Expense recorded successfully!");
    } catch (Exception e) {
        System.err.println("Error while recording expense: " + e.getMessage());
    }
}


    public static void updateExpense(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter expense name to update:");

        boolean isUpdated = false;
        String lineToUpdate = sc.nextLine();
        try {
            List<String> lines = Files.readAllLines(Paths.get("new.txt"));
            for(int i=0; i < lines.size(); i++){
                if(lines.get(i).toLowerCase().contains(lineToUpdate.toLowerCase())){
                    System.out.println("Found the line to update" + lines.get(i));
                    System.out.println("Enter new data (format: Date, Description, Amount):");
                    String updatedExpenses = sc.nextLine();
                    lines.set(i, updatedExpenses);
                    isUpdated = true;
                    break;
                }
            }
            if(isUpdated){
                Files.write(Paths.get("new.txt"), lines);
                System.out.println("Expense Updated Successfully");
            }else{
                System.out.println("Expense not found");
            }
        }catch(IOException e){
            System.out.println("Error while updating"+ e.getMessage());
        }
    }

    public static void ViewExpense(){
        try (Scanner fileReader = new Scanner(new File("new.txt"))){
            int lineNumber = 1;
            while (fileReader.hasNextLine()){
                System.out.println("Files contain" + fileReader.nextLine());
                lineNumber++;
            }
        } catch (Exception e) {
            System.out.println("Error reading file" + e.getMessage());
        }
    }

public static void deleteExpense() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the expense name to delete:");
    String expenseName = sc.nextLine();

    try {
        List<String> lines = Files.readAllLines(Paths.get("new.txt"));
        boolean isDeleted = false;

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).toLowerCase().contains(expenseName.toLowerCase())) {
                System.out.println("Expense to remove: " + lines.get(i));
                lines.remove(i);
                isDeleted = true;
                break; // Remove only the first match
            }
        }

        if (isDeleted) {
            Files.write(Paths.get("new.txt"), lines); // Write back updated content
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Expense not found.");
        }
    } catch (Exception e) {
        System.out.println("Error while deleting expense: " + e.getMessage());
    }
}

    public static void ensureFileExists(String fileName) {
    try {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
    } catch (IOException e) {
        System.out.println("Error ensuring file existence: " + e.getMessage());
    }
}
}
