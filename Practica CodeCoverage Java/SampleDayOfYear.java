import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 *
 * @author CodeCoverage Developer
 */
public class SampleDayOfYear {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintStream out = System.out; 

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here}
        int year, day, month;
        System.out.print("Digite el valor del dia: ");
        day = Integer.parseInt(in.readLine());
        System.out.print("Digite el valor del mes: ");
        month = Integer.parseInt(in.readLine());
        System.out.print("Digite el valor del año: ");
        year = Integer.parseInt(in.readLine());
        int numDay = dayOfYear(month, day, year);
        switch (numDay) {
            case -1:
                System.out.println("Mes incorrecto");
                break;
            case -2:
                System.out.println("Dia incorrecto");
                break;
            case -3:
                System.out.println("Año incorrecto");
                break;
            default:
                System.out.println("El numero de dia es: " + numDay);
                System.out.println("El año es bisiesto? " + leapYear(year));
                break;
        }
    }
 
    public static boolean isValidDate(final int year, final int month, final int dayOfMonth){
      
    int[] DAYS_IN_MONTH = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    int FEBRUARY = 2;
      
    if((dayOfMonth >= 1 && dayOfMonth <= 31) && (month >= 1 && month <= 12) && (year >= 1582)){
      
      if((leapYear(year) && (month == FEBRUARY) && (dayOfMonth == 29))  
        || (dayOfMonth <= DAYS_IN_MONTH[month])){
        
        return true;
      
      }else{
        return false;
      }
      
    }else{
      return false;
    }
    }
    
    public static int dayOfYear(int month, int dayOfMonth, int year) {
        int totalDays = validateDaysOfMonth(month, dayOfMonth,year);
        if (totalDays != -1 && totalDays != -2) {
            totalDays = validateYear(year);
            if (totalDays != -3) {                
                int daysPerMonthArray[]
                        = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
                totalDays = dayOfMonth;
                for (int i = 1; i < month; i++) {
                    totalDays += daysPerMonthArray[i-1];
                }
                
                if(leapYear(year) && month > 2) {
                  totalDays++;
                }
            }
        }
        return totalDays;
    }
        public static int validateDaysOfMonth(int month, int dayOfMonth, int year) {
        int isNotCorrect = validateMonth(month);
        if (isNotCorrect > -1) {
            switch (month) {
                case 2://febraury days in month: 28
                    if (dayOfMonth < 1 || dayOfMonth > 28) {
                        if(!leapYear(year)){
                          isNotCorrect = -2;
                        }
                    }
                    break;
              case 1:   //IDO
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 11:
                case 12://months with 31 days
                    if (dayOfMonth < 1 || dayOfMonth > 31) {
                        isNotCorrect = -2;
                    }
                    break;
                default://month with 30 days
                    if (dayOfMonth < 1 || dayOfMonth > 30) {
                          isNotCorrect = -2;
                    }
                    break;
            }
        }
        return isNotCorrect;
    }

        
    public static int[] devuelveArray (int d, int m, int a) {
          
    int[] arregloParaDevolver ; //= {0,0,0};
    arregloParaDevolver = new int[3];
          
    arregloParaDevolver[0] = d+1;
    arregloParaDevolver[1] = m+1;
    arregloParaDevolver[2] = a+1;
    return arregloParaDevolver;
    }
        
    public static int validateMonth(int pmonth) {
        int month = pmonth;
        if (month > 12 || month < 1) {   
            month = -1;//month not correct
        }
        return month;
    }
        public static int validateYear(int pyear) {
        int yeay = pyear;
        if (yeay < 1582) {
            yeay = -3;//year not correct
        }
        return yeay;
    }
    public static boolean leapYear(int pyear){
        boolean isLeap = false;
        if (validateYear(pyear) > -3) {        
              if ((pyear % 400 == 0) | (pyear % 4 == 0 & pyear % 100 != 0)) {
                  isLeap = true;
              } 
             } 
        return isLeap;
    }
    
    public static String nextDate(final int year, final int month, final int dayOfMonth){
    
    int nextDay = dayOfMonth;
    int nextMonth = month;
    int nextYear = year;
    
    int FIRST_DAY = 1;
    int JANUARY = 1;
    int FEBRUARY = 2;
    int APRIL = 4;
    int JUNE = 6;
    int SEPTEMBER = 9;
    int NOVEMBER = 11;
    int DECEMBER = 12;
 
    if(isValidDate(year, month, dayOfMonth)){
      if(dayOfMonth <= 27){
        nextDay++;
      }else if(dayOfMonth == 28){
        if(month != FEBRUARY){
          nextDay++;
        }else{
          if(leapYear(year)){    
            nextDay++;
          }else{
            nextDay = FIRST_DAY;
            nextMonth++;
          }
        }
      }else if(dayOfMonth == 29){
        if(month != FEBRUARY){
          nextDay++;
        }else{
          if(leapYear(year)){   //isLeapYear
            nextDay = FIRST_DAY;
            nextMonth++;
          }
        }
      }else if(dayOfMonth == 30){
        if(month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER){
          nextDay = FIRST_DAY;
          nextMonth++;
        }else{
          nextDay++;
        }
      }else if(month == DECEMBER){
        nextDay = FIRST_DAY;
        nextMonth = JANUARY;
        nextYear++;
      }else{
        nextDay = FIRST_DAY;
        nextMonth++;
      }
      
      return "" + nextYear + "/" + nextMonth + "/" + nextDay;
    }else{
      return "ERROR";
    }
  }
}
