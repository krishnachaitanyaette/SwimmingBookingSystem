

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import swimmingbookingsystem.ClassBookings;
import swimmingbookingsystem.LoggedInDetails;
import swimmingbookingsystem.RegisteredLearners;
import swimmingbookingsystem.SwimmingLessons;


public class JUnitSystemTestingClass {
    


     @Test
     public void case1lessonByGradeLevel() {
         int gradeLevel = 3;
         System.out.println("\n----Test Case to filter lessons by Grade Level : "+gradeLevel);
         SwimmingLessons.viewLessonsByGradeLevel(gradeLevel);
     }
     
     

     @Test
     public void case2loginLearner() {
        boolean loggedIn = false;
        
        System.out.println("----Test Case to login learner");
        
        List<RegisteredLearners> regsiteredLearners = RegisteredLearners.getRegisteredLearners();
        
        int regID = 1;
        String password = "password1";
        
        for(RegisteredLearners obj : regsiteredLearners){
            if(obj.getRegisteredID() == regID && obj.getPassword().equalsIgnoreCase(password)){
                LoggedInDetails.id = obj.getRegisteredID();
                LoggedInDetails.roleType = "Learner";
                loggedIn = true;
                break;
            }
        }
        
        assertTrue(loggedIn);
        System.out.println("Logged In");
     }
     
     
     

     @Test
     public void case3bookNewClass() {
         System.out.println("\n----Test Case to book new class");

         int lessonNo = 1;
         ClassBookings.bookNewClass(lessonNo);
     }
     
     

     @Test
     public void case4duplicateBooking() {
        System.out.println("\n----Test Case to check duplicate booking");
        int lessonNo = 1;
        ClassBookings.bookNewClass(lessonNo);
     }
     

     @Test
     public void case5allowedSelectedLesson() {
        System.out.println("\n----Test Case to check whether the selected lesson is allowed to logged in learner or not");
        
         int lessonNo = 6;
         ClassBookings.bookNewClass(lessonNo);
         

     }
     
     
     
}
