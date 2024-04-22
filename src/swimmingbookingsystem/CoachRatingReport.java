
package swimmingbookingsystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class CoachRatingReport implements ReportInterface {
    
      
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
        
        List<LessonReviews> lessonReviews = LessonReviews.getLessonReviews();
        List<ClassBookings> classBookings = ClassBookings.getClassBookings();
        List<SwimmingLessons> swimmingLessons = SwimmingLessons.getSwimmingLessons();   
        List<SwimmingCoaches> swimmingCoaches = SwimmingCoaches.getSwimmingCoaches();
         
        HashMap<String, Integer> reviews = new HashMap<>();
        HashMap<String, Integer> totalratingRecords = new HashMap<>();
        HashMap<String, Double> calAvgRating = new HashMap<>();
       
        System.out.println();
       
        for (LessonReviews obj : lessonReviews){
           
            int lessonNo = 0;
            int lessonBy = 0;
            for (SwimmingLessons swimminglessonObj : swimmingLessons){
                
                 //Parse date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");             
                
                String lessonMonth = "";
                try {
                    LocalDateTime parsedDateTime = LocalDate.parse(swimminglessonObj.getLessonOn(), formatter).atStartOfDay();
                    lessonMonth = parsedDateTime.format(DateTimeFormatter.ofPattern("M"));
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing datetime: " + e.getMessage());
                }
                
                for(ClassBookings bookingObj : classBookings){
                    if(bookingObj.getBookingUniqNo() == obj.getBookingNo()){
                        lessonNo = bookingObj.getLessonNo();
                        break;
                    }
                }
            }
            
            for (SwimmingLessons swimminglessonObj : swimmingLessons){
                if(swimminglessonObj.getLessonNo() == lessonNo){
                    lessonBy = swimminglessonObj.getLessonBy();
                    break;
                }
            }
            
            String coachName = "";
                
            for(SwimmingCoaches coachObj : swimmingCoaches){

                if(coachObj.getRegisteredID() == lessonBy){
                    coachName = coachObj.getName();
                    break;
                }
            }
                    
            int ratingAddition = obj.getRating();

            reviews.put(coachName, reviews.getOrDefault(coachName, 0) + ratingAddition);
            totalratingRecords.put(coachName, totalratingRecords.getOrDefault(coachName, 0) + 1);
        }

        for (String coach : reviews.keySet()) {
            int coachRatings = reviews.get(coach);
            int totalRatingByEachCoach = totalratingRecords.get(coach);

            if (totalRatingByEachCoach > 0) {
                double avg = (double) coachRatings / totalRatingByEachCoach;
                double ans = Math.round(avg * 10.0) / 10.0; 
                calAvgRating.put(coach, ans);
            }
        }

        if(!calAvgRating.isEmpty()){
            System.out.println("\n-------------------------------------------");
            System.out.printf("|%-20s |%-20s| \n", "Coach", "Avg Rating");
            System.out.println("-------------------------------------------");
            for (String coach : calAvgRating.keySet()) {
                double avgRating = calAvgRating.get(coach);
                System.out.printf("|%-20s| %-20s| \n", coach, avgRating);
                                   
            }
          
            System.out.println("-------------------------------------------");
        }else{
            System.out.println("Report Data does not exist");
        }
    }
}
