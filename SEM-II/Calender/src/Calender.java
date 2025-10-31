import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Calender {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        LocalDate date ;

        int month;
        int year;
        System.out.println("pls enter year");
        year = scanner.nextInt();
        System.out.println("pls enter month");
        month = scanner.nextInt();
        YearMonth ym = YearMonth.of(year, month);
        int lenght = ym.lengthOfMonth();
        
        date = LocalDate.of(year, month, 1);
        System.out.println(date.getDayOfWeek());
        String Mname = date.getMonth().toString().toLowerCase();
        Mname = Mname.substring(0, 1).toUpperCase() + Mname.substring(1); 


        System.out.println("Calendar for the month of " + Mname +"," + year);
        System.out.printf("%5s%5s%5s%5s%5s%5s%5s\n", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
        
        int firstDayIndex = date.getDayOfWeek().getValue(); 
        
        for(int i = 1; i < firstDayIndex; i++) {
            System.out.printf("%5s", "");
        }
        for(int day = 1; day <= lenght; day++) {
            System.out.printf("%5d", day);
            LocalDate current = LocalDate.of(year, month, day);
            if (current.getDayOfWeek().getValue() == 7) {
                System.out.println();
            }
        }

        System.out.println();
        scanner.close();
    }

}
