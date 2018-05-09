/**
 * Clase GregorianCalendar
 * Brinda funciones para realizar calculos de fechas del calendario Gregoriano.
 */

public class GregorianCalendar {
  public final int GREGORIAN_STARTING_YEAR = 1582;
  public final int DAYS_OF_WEEK = 7;
  public final int DAYS_OF_CENTURY = 100;
  public final int JANUARY = 1, FEBRUARY = 2, DECEMBER = 12;
  public final int DAYS_OF_MONTH[] = {-1, 31, 28, 31, 30 ,31, 30, 31, 31, 30, 31, 30, 31}; //Empieza con -1 para calzar con los numeros de mes.
  public final int CARROLS_MAGIC_NUMBERS_FOR_MONTHS[] = {-1, 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5}; // Numeros del mes utilizados en el algoritmo de Lewis Carrol para calcular el dia de la semana. Empieza con -1 para calzar con los numeros de mes.
  
  /**
   * Determina si un anno es bisiesto o no.
   * @param year: Anno en cuestion para determinar si es bisiesto o no.
   * @return: True si el anno es bisiesto. False si no lo es.
   */
  public boolean isLeapYear(final int year) {
    return ((year % 400 == 0) || ( year % 4 == 0  && year % 100 != 0 ));
  }
  
  
  /**
   * Determina si la fecha ingresada es valida para el calendario gregoriano.
   * @param year: Determina si el anno ingresado es valido, i.e mayor que 1582
   * @param month: Mes del anno. Determina si el mes es valido, i.e si se encuentra entre 1 y 12
   * @param day: Determina si el dia, con respecto al mes es valido. Es decir, si es igual o menor a la
   * cantidad de dias que tiene el mes del anno en cuestion.
   * @return: True si la fecha es valida, False en otro caso.
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
   * Determina el dia siguiente de la fecha ingresada.
   * @param year: Año utilizado para determinar la fecha del dia siguiente. Tambien utilizado para saber
   * si el anno en cuestion es bisiesto
   * @param month: Mes para determiar la fecha del dia siguiente
   * @param day: Dia del mes utilizado para determiar la fecha del dia siguiente
   * @return: Una hilera con la fecha del día sigueinte en formato: A, M, D; Hilera de aviso en caso de fecha
   * invalida
   */
  public String nextDay(final int year, final int month, final int day) {
    if(!dateIsValid(year, month, day)) return "Fecha invalida";
    int newYear, newMonth, newDay;
    switch (month) {
      case FEBRUARY:
        if(!isLeapYear(year)) {
        if(day < DAYS_OF_MONTH[FEBRUARY]) {
          // Agrega un dia a los dias del mes.
          newDay = day+1;
          newMonth = month;
          newYear = year;
        } else {
          // Agrega un mes y reinicia el dia del mes en 1.
          newDay = 1;
          newMonth = month+1;
          newYear = year;
        }
      } else { //Anno bisiesto
        if(day < DAYS_OF_MONTH[FEBRUARY] + 1) {
          // Agrega un dia a los dias de febrero.
          newDay = day+1;
          newMonth = month;
          newYear = year;
        } else {
          // Agrega un mes y reinicia el dia del mes en 1.
          newDay = 1;
          newMonth = month+1;
          newYear = year;
        }
      }
      break;
      case DECEMBER:
        if(day < DAYS_OF_MONTH[DECEMBER]) {
        // Agrega un dia a los dias del mes.
        newDay = day+1;
        newMonth = month;
        newYear = year;
      } else {
        // Agrega un anno y reinicia el dia del mes en 1 y el mes en enero.
        newDay = 1;
        newMonth = JANUARY;
        newYear = year+1;
      }
      break;
      default:
        if(day < DAYS_OF_MONTH[month]) {
        // Agrega un dia a los dias del mes.
        newDay = day+1;
        newMonth = month;
        newYear = year;
      } else {
        // Agrega un mes y reinicia el dia del mes en 1.
        newDay = 1;
        newMonth = month+1;
        newYear = year;
      }
    }
    return newYear + ", " + newMonth + ", " + newDay;
  }
  
  
  /**
   * Determina el dia de la semana, utilizando el algoritmo de Lewis Carrol. Sin embargo, como se utiliza el
   * calendario gregoriano, no se hace diferenciacion para las fechas "OldStyle" del calendario ingles,
   * los cuales utilizan el calendario jordian. Algoritmo tomado de:
   * https://www.tandfonline.com/doi/abs/10.1080/10724117.2002.11974617
   * @param year: Anno para calular el dia de la semana. Tambien utilizado para determinar si es bisiesto
   * @param month: Mes para calcular el dia de la semana
   * @param day: Dia el cual se indica a que dia de la semana corresponde.
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
    
    yearItem = overYears / DECEMBER + overYears % DECEMBER + (overYears % DECEMBER)/4;
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
    int year = 1982, month = 9, day = 14;
    System.out.println("La fecha: " + year + ", " + month + ", " + day + " es válida? " + gc.dateIsValid(year, month, day));
    System.out.println("Año es bisiesto?: " + gc.isLeapYear(year) );
    System.out.println("Día de la semana es: " + dayOfWeek[gc.dayOfWeek(year,month, day)]);
    System.out.println("La fecha del día siguiente es: " + gc.nextDay(year,month, day));
  }
}
