

public class GregorianCalendar() {
  public int GREGORIAN_STARTING_YEAR = 1582;
  public int JANUARY = 1, FEBRUARY = 2, DECEMBER = 12;
  public static int DAYS_OF_MONTH[] = {0, 31, 28, 31, 30 ,31, 30, 31, 31, 30, 31, 30, 31}; //Empieza con 0 para calzar con los nœmeros de mes.
  
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
        //Toma en cuante el d’a extra de febrero en a–os bisiestos al calcular la valides de la fecha.
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
  public Date nextDay(final int year, final int month, final int day) {
    
  }
  
  
   /**
   * Determina el día de la semana, utilizando el algoritmo de Lewis Carrol.
   * @param:
   * @return: Entero que representa un d’a de la semana, siendo estos: : 0 = domingo, 1 = lunes, 2 = martes,
   * 3 = miŽrcoles, 4 = jueves, 5 = viernes, 6 = s‡bado. -1 si la fecha es invalida.
   */
  public int dayOfWeek(final int year, final int month, final int day) {
    if(!dateIsValid()) return -1;
    int centuryItem, yearItem, monthItem, dayItem;
    int century = year/100;
    int overYears = year%100;
    if(isOldStyleDate(year, month, day)) {

    } else {
        centuryItem = 2*(3 - (century/4));
    }
    yearItem = (overYears/12 + overYears%12 + (overYears%12)/4);
    dayItem = day;

    dayOfWeek = 
  }

  public boolean isOldStyleDate(final int year, final int month, final int day) {

  }

public static void main(String [] args) {

}
}
