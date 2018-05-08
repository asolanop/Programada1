

public class GregorianCalendar {
  public int GREGORIAN_STARTING_YEAR = 1582;
    public static int DAYS_OF_WEEK = 7;
    public static int DAYS_OF_CENTURY = 100;
  public int JANUARY = 1, FEBRUARY = 2, DECEMBER = 12;
  public static int DAYS_OF_MONTH[] = {-1, 31, 28, 31, 30 ,31, 30, 31, 31, 30, 31, 30, 31}; //Empieza con -1 para calzar con los numeros de mes.
public static int CARROLS_MAGIC_NUMBERS_FOR_MONTHS[] = {-1, 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5}; // Numeros del mes utilizados en el algoritmo de Lewis Carrol para calcular el dia de la semana.
  
  /**
   * 
   * @param:
   * @return:
   */
  public boolean isLeapYear(final int year) {
    return ((year % 400 == 0) || ( year % 4 == 0  && year % 100 != 0 ));
  }
   
  
   /**
   * 
   * @param:
   * @return:
   */
  public boolean dateIsValid(final int year, final int month, final int day) {
    if(year < GREGORIAN_STARTING_YEAR) return false;
    if(month < JANUARY || month > DECEMBER) return false;
    if(isLeapYear(year)) {
      if(month == FEBRUARY) {
        //Toma en cuante el dia extra de febrero en annos bisiestos al calcular la valides de la fecha.
        return day >= JANUARY && day <= DAYS_OF_MONTH[FEBRUARY] + 1;
      }
    }
    return day >= JANUARY && day <= DAYS_OF_MONTH[month];    
  }
   
  
   /**
   * 
   * @param:
   * @return:
   */
  public String nextDay(final int year, final int month, final int day) {
      return "";
  }
  
  
   /**
   * Determina el dia de la semana, utilizando el algoritmo de Lewis Carrol. Sin embargo, como se utiliza el
   * calendario gregoriano, no se hace diferenciacion para las fechas "OldStyle" del calendario ingles,
   * los cuales utilizan el calendario jordian
   * @param:
   * @return: Entero que representa un dia de la semana, siendo estos: : 0 = domingo, 1 = lunes, 2 = martes,
   * 3 = miercoles, 4 = jueves, 5 = viernes, 6 = sabado. -1 si la fecha es invalida.
   */
  public int dayOfWeek(final int year, final int month, final int day) {
    if(!dateIsValid(year, month, day)) return -1;
    int centuryItem, yearItem, monthItem, dayItem;
    int century = year / DAYS_OF_CENTURY;
    int overYears = year % DAYS_OF_CENTURY;
      
    centuryItem = 2*(3 - (century%4)); // Numeros utilizados para la operacion tomados del algoritmo de Carrol.
    if(centuryItem >= DAYS_OF_WEEK) centuryItem = centuryItem % DAYS_OF_WEEK;
      
    yearItem = (overYears / DECEMBER + overYears % DECEMBER + (overYears % DECEMBER)/4);
    if(yearItem >= DAYS_OF_WEEK) yearItem = yearItem % DAYS_OF_WEEK;
      
    monthItem = CARROLS_MAGIC_NUMBERS_FOR_MONTHS[month];
    
    dayItem = day;
    if(dayItem >= DAYS_OF_WEEK) dayItem = dayItem % DAYS_OF_WEEK;
  
    int dayOfWeek = ((centuryItem + yearItem + monthItem + dayItem) % DAYS_OF_WEEK);
    if(isLeapYear(year) &&
       (month == JANUARY || month == FEBRUARY)) {
        // Resta un dia a los annos binarios en caso de que el mes sea enero o febrero.
        if(dayOfWeek == 0) {
          dayOfWeek = DAYS_OF_WEEK - 1;
        } else {
          dayOfWeek -= 1;
        }
    }
    return dayOfWeek;
  }

public static void main(String [] args) {
    String [] dayOfWeek = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
    GregorianCalendar gc = new GregorianCalendar();
    int year = 1752, month = 9, day = 14;
    System.out.println("Year is leap: " + gc.isLeapYear(year) );
    
    System.out.println("Day of week is: " + dayOfWeek[gc.dayOfWeek(year,month, day)]);
}
}
