
package swimmingbookingsystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class RegisteredLearnerBookingReport implements ReportInterface {
    
    @Override
    public void viewReport() {
        
        Scanner  input = new Scanner(System.in);
        System.out.print("\nInput Month Number to view Report (1 to 12) : ");
        String month = input.nextLine();
        
        if(month.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(month) || (Integer.parseInt(month) < 1 || 
                Integer.parseInt(month) > 12)){
            do{
                System.out.print("\nInput Month Number to view Report (1 to 12) : ");
                month = input.nextLine();
            }while(month.equalsIgnoreCase("") || !SwimmingBookingSystem.isDigit(month) || (Integer.parseInt(month) < 1
                    || Integer.parseInt(month) > 12));
        }

        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        List<RegisteredLearners> registeredLearners = RegisteredLearners.getRegisteredLearners();
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();
       
        System.out.println();

        HashMap<String, int[]> registeredLearnersBookings = new HashMap<>();
        Set<String> uniqueRegIDs = new HashSet<>();             
        for (int j = 0; j < classBookings.size(); j++) {

            for (int i = 0; i < registeredLearners.size(); i++) {
                String monthNumber = "";
                for (SwimmingLessons resgisterdLearnerobj : swimmingLessons) {

                    if(resgisterdLearnerobj.getLessonNo() == classBookings.get(j).getLessonNo()){
                        //Parse timetable classOn field
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");             

                        try {
                            LocalDateTime parsedDateTime = LocalDate.parse(resgisterdLearnerobj.getLessonOn(), formatter).atStartOfDay();
                            monthNumber = parsedDateTime.format(DateTimeFormatter.ofPattern("M"));
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date -  " + e.getMessage());
                        }
                        break;
                    }
                }

                String uniqueKey = String.valueOf(registeredLearners.get(i).getRegisteredID()) + classBookings.get(j).getBookingUniqNo();

                if (!uniqueRegIDs.contains(uniqueKey)) {
                    uniqueRegIDs.add(uniqueKey);

                    if (registeredLearners.get(i).getRegisteredID() == classBookings.get(j).getRegisteredID()) {

                        String uniqueCode = String.valueOf(registeredLearners.get(i).getRegisteredID());
                        int[] counter = registeredLearnersBookings.getOrDefault(uniqueCode, new int[3]);

                        if (classBookings.get(j).getBookingStatus().equalsIgnoreCase(ClassBookings.CLASS_BOOKED) 
                                || classBookings.get(j).getBookingStatus().equalsIgnoreCase(ClassBookings.CLASS_CHANGED)) {
                            counter[0]++;
                        }
                        if (classBookings.get(j).getBookingStatus().equalsIgnoreCase(ClassBookings.CLASS_CANCEL)) {
                            counter[1]++;
                        }
                        if (classBookings.get(j).getBookingStatus().equalsIgnoreCase(ClassBookings.CLASS_ATTENDED)) {
                            counter[2]++;
                        }
                        registeredLearnersBookings.put(String.valueOf(uniqueCode), counter);
                    }
                }
            }
        }
        
        if(!registeredLearnersBookings.isEmpty()){
            int counters =1;
            
            System.out.println("\n\n================================================================");
            for (Map.Entry<String, int[]> entry : registeredLearnersBookings.entrySet()) {
                String regID = entry.getKey();
                int[] counter = entry.getValue();

                //Registered Learners Data
                String name = "";
                int learnerGrade = 0;
                for (int i = 0; i < registeredLearners.size(); i++) {
                   if(registeredLearners.get(i).getRegisteredID() == Integer.parseInt(regID)){
                       name = registeredLearners.get(i).getName();
                       learnerGrade = registeredLearners.get(i).getGradeLevel();
                       break;
                   }
                }
                System.out.println("\n\nRegistered Learner Record No : "+counters);

                System.out.println("\nName : "+ name);
                System.out.println("Current Grade : "+learnerGrade+"\n");
                
                System.out.println("\nNumber Of Booked/Changed Lessons : "+counter[0]);
                System.out.println("Number Of Attend Lessons : "+counter[2]);
                System.out.println("Number Of Cancel Lessons : "+counter[1]);
                
                counters++;
            }
            System.out.println("\n\n================================================================");

        }else{
             System.out.println("Report Data does not exist");
        }
        
    }
    
}
