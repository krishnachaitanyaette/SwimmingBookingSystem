
package swimmingbookingsystem;

import java.util.Scanner;


public class SwimmingBookingSystem {

    public static void main(String[] args) {
        System.out.println("\nThe Hatfield Junior Swimming School System ");
        
        System.out.println("\n\nBelow lessons are available in our school : ");
        SwimmingLessons.displayAllLessons();
        
        int menuChoice;
        do {
            menuChoice = menu();
            switch (menuChoice) {
                case 1 -> LoggedInDetails.newLearnerRegister();
                case 2 -> LoggedInDetails.loginToAccount();
                case 3 -> SwimmingLessons.filterLessonMenu();
                case 4 ->{
                             System.out.println("\n----------------- Exit -----------------\n");
                             System.exit(0);
                        }
                default -> System.out.println("\nPlease enter a valid choice (1-3)");
            }
        } while (menuChoice != 4);
    }
    
      
    //menu of the system
    public static int menu(){     
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nMake a choice from below menu : ");
        System.out.println("1. New Learner Registration Form");
        System.out.println("2. Login to Your Account");
        System.out.println("3. Filter Timetable");
        System.out.println("4. Exit");
        System.out.print("\nInput choice : ");

        String selectedUserChoice = input.nextLine();
        while (selectedUserChoice.equals("") || !isDigit(selectedUserChoice))
        {
            System.out.print("\nInput correct choice : ");
            selectedUserChoice = input.nextLine();
        }
        return Integer.parseInt(selectedUserChoice);
    }
 
    
    
    
    //input is digit or not
    public static boolean isDigit(String input)
    {
        if (input == null || input.isEmpty()) {
             return false;
         }
         for (int i = 0; i < input.length(); i++) {
             if (!Character.isDigit(input.charAt(i))) {
                 return false;
             }
         }
         return true;
    }
    
    
    
    
}
