import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== QUICKCHAT REGISTRATION ===");

        // 1. Ask for first and last name - needed for login message
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        Login loginSystem = new Login(firstName, lastName);

        // 2. Registration Loop - keep asking until correct format
        String username;
        String password;
        String cellNumber;
        String registrationResult;

        while (true) {
            System.out.println("\n--- Create Account ---");
            System.out.print("Enter Username (must contain _ and <=5 chars): ");
            username = scanner.nextLine();

            System.out.print("Enter Password (8 chars, capital, number, special): ");
            password = scanner.nextLine();

            System.out.print("Enter SA Cell Number (e.g +27821234567): ");
            cellNumber = scanner.nextLine();

            // Call registerUser - it checks all 3 conditions
            registrationResult = loginSystem.registerUser(username, password, cellNumber);
            System.out.println("\n" + registrationResult);

            // If all 3 captured, break loop. If not, loop again
            if (registrationResult.contains("successfully")) {
                // The method returns combined success message only if all pass
                // Check if it contains "Cell phone number successfully added"
                if (registrationResult.contains("Cell phone number successfully added")) {
                    break;
                }
            }
            System.out.println("\nPlease try again - fix the errors above.");
        }

        // 3. Login Section
        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter Username to Login: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter Password to Login: ");
        String loginPassword = scanner.nextLine();

        // Use the two methods from assignment
        boolean isLoggedIn = loginSystem.loginUser(loginUsername, loginPassword);
        String loginStatus = loginSystem.returnLoginStatus(loginUsername, loginPassword);

        System.out.println("\n" + loginStatus);

        // Bonus: Keep asking until login succeeds (optional but good for demo)
        while (!isLoggedIn) {
            System.out.println("\n--- Try Again ---");
            System.out.print("Enter Username: ");
            loginUsername = scanner.nextLine();
            System.out.print("Enter Password: ");
            loginPassword = scanner.nextLine();
            
            loginStatus = loginSystem.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginStatus);
            isLoggedIn = loginSystem.loginUser(loginUsername, loginPassword);
        }

        System.out.println("\nYou are now logged in to QuickChat!");
        scanner.close();
    }
}