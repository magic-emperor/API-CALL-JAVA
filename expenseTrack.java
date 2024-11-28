import java.util.Scanner;
public class expenseTrack extends HandleAllMentods{
 
    public static void main(String[] args) {
        ensureFileExists("new.txt");

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        System.err.println(running);

        while(running){
            System.out.println("Enter command: (exp/view/update/delete/exit)");

            String command = sc.nextLine().toLowerCase();
            switch (command) {
                case "exp":
                addingExpense();
                break;
                case "view":
                ViewExpense();
                break;
                case "update":
                updateExpense();
                break;
                case "delete":
                deleteExpense();
                break;
                case "exit":
                running = false;
                System.out.println("Exit successful");
                break;
                default:
                System.out.println("Invalid command! try");
            }
        }
        sc.close();
    }
}