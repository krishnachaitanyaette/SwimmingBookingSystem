
package swimmingbookingsystem;

import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class LoggedInDetails {
    
    public static String roleType = "";
    public static int id = 0;
    
      
    //New Learner register
    public static void newLearnerRegister(){
        Scanner input = new Scanner(System.in);

        System.out.print("\nLearners should be "+RegisteredLearners.MIN_AGE_VAL+" - "+RegisteredLearners.MAX_AGE_VAL+ " years old to fill registration form. Input Y to fill form : ");
        
        String isValid = input.nextLine();
        
        if(isValid.equalsIgnoreCase("Y") || isValid.equalsIgnoreCase("Yes")){
            
            System.out.print("\nPlease Input Age (4-11) : ");
            String age = input.nextLine();

            if(age.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(age) || Integer.parseInt(age) 
                    < RegisteredLearners.MIN_AGE_VAL || Integer.parseInt(age) > RegisteredLearners.MAX_AGE_VAL){
                do{
                    System.out.print("\nPlease Input Age (4-11) :  ");
                    age = input.nextLine();
                }while(age.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(age) || Integer.parseInt(age)
                        < RegisteredLearners.MIN_AGE_VAL || Integer.parseInt(age) > RegisteredLearners.MAX_AGE_VAL);
            }
            
            System.out.print("\nPlease Input Name : ");
            String name = input.nextLine();

            if(name.equalsIgnoreCase("")){
                do{
                    System.out.print("\nPlease Input Name : ");
                    name = input.nextLine();
                }while(name.equalsIgnoreCase(""));
            }

            System.out.print("\nPlease Input Password : ");
            String pass = input.nextLine();

            if(pass.equalsIgnoreCase("")){
                do{
                    System.out.print("\nPlease Input Password : ");
                    pass = input.nextLine();
                }while(pass.equalsIgnoreCase(""));
            }

            System.out.print("\nPlease Input Contact : ");
            String contact = input.nextLine();

            if(contact.equalsIgnoreCase("")){
                do{
                    System.out.print("\nPlease Input Contact : ");
                    contact = input.nextLine();
                }while(contact.equalsIgnoreCase(""));
            }

            System.out.print("\nPlease Input your Grade Level ("+RegisteredLearners.MIN_GRADE_VAL+" - "+ RegisteredLearners.MAX_GRADE_VAL+") : ");
            String grade = input.nextLine();

            if(grade.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(grade) || (Integer.parseInt(grade) < RegisteredLearners.MIN_GRADE_VAL || 
                    Integer.parseInt(grade) > RegisteredLearners.MAX_GRADE_VAL)){
                do{
                    System.out.print("\nPlease Input your Grade Level ("+RegisteredLearners.MIN_GRADE_VAL+" TO "+ RegisteredLearners.MAX_GRADE_VAL+") : ");
                    grade = input.nextLine();
                }while(grade.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(grade) || (Integer.parseInt(grade) < 
                        RegisteredLearners.MIN_GRADE_VAL || 
                        Integer.parseInt(grade) > RegisteredLearners.MAX_GRADE_VAL));
            }

            System.out.print("\nPlease Input Gender (M/F): ");
            String gender = input.nextLine();

            if(gender.equalsIgnoreCase("") || (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F"))){
                do{
                    System.out.print("\nPlease Input Gender (M/F): ");
                    gender = input.nextLine();
                }while(gender.equalsIgnoreCase("") || (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")));
            }

            Random random = new Random();
            int regID = random.nextInt(90) + 10; 

            //Add Obj to ArrayList
            RegisteredLearners obj = new RegisteredLearners(regID,name,Integer.parseInt(grade),contact,gender,
                    pass,Integer.parseInt(age));
            RegisteredLearners.regsiteredLearners.add(obj);
            System.out.println("\nRegistered with Registration ID: "+regID);
        }
        
    }
    
    
    //login to account
    public static void loginToAccount(){
        
        boolean notmanagingStaff = true;
        boolean notLearner = true;
        
        List<RegisteredLearners> regsiteredLearners = RegisteredLearners.getRegisteredLearners();
        List<ManagingStaffs> managingStaffs = ManagingStaffs.getManagingStaffs();
        
        
        Scanner input = new Scanner(System.in);
        System.out.print("\nInput Registered ID : ");
        String regID = input.nextLine();
        
        if(regID.equalsIgnoreCase("")){
            do{
                System.out.print("\nInput Registered ID : ");
                regID = input.nextLine();
            }while(regID.equalsIgnoreCase(""));
        }
        
        System.out.print("\nInput Password : ");
        String password = input.nextLine();
        
        if(password.equalsIgnoreCase("")){
            do{
                System.out.print("\nInput Password : ");
                password = input.nextLine();
            }while(password.equalsIgnoreCase(""));
        }
        
       
        for(RegisteredLearners obj : regsiteredLearners){
            if(obj.getRegisteredID() == Integer.parseInt(regID) && obj.getPassword().equalsIgnoreCase(password)){
                LoggedInDetails.id = obj.getRegisteredID();
                LoggedInDetails.roleType = "Learner";
                notLearner = false;

                System.out.println("\n==================================================================");
                System.out.println("Login Success");
                System.out.println("Lessons with grade level "+obj.getGradeLevel() + " or "+(obj.getGradeLevel()+1)+" are allowed for booking");
                System.out.println("==================================================================");
                RegisteredLearners.registerdLearnerDashboardOption();
                return;
            }
        }
        for(ManagingStaffs obj : managingStaffs){
            if(obj.getStaffRegID() == Integer.parseInt(regID) && obj.getPassword().equalsIgnoreCase(password)){
                LoggedInDetails.id = obj.getStaffRegID();
                LoggedInDetails.roleType = "ManagingStaff";
                notmanagingStaff = false;
                System.out.println("\n======================================");
                System.out.println("Login Success");
                System.out.println("======================================");
                ManagingStaffs.managingStaffDashboard();
                return;
            }
        }
        
        if(notmanagingStaff && notLearner){
            System.out.println("\nIncorrect Credentials");
            return;
        }
    }
    
    
    //Logout
    public static void logout(){
        LoggedInDetails.id = 0;
        LoggedInDetails.roleType = "";
        System.out.println("\n===============================");
        System.out.println("Logout From System Successful");
        System.out.println("===============================\n");
    }
    
}
