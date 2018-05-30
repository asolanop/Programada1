/**
 * Clase GregorianCalendar
 * Brinda funciones para realizar calculos de fechas del calendario Gregoriano.
 */

public class GregorianCalendar {
  public final int GREGORIAN_STARTING_YEAR = 1582;
  public final int DAYS_OF_WEEK = 7;
  public final int DAYS_OF_BASE_YEAR = 365;
  public final int DAYS_OF_CENTURY = 100;
  public final int JANUARY = 1, FEBRUARY = 2, MARCH = 3, DECEMBER = 12;
  public final int DAYS_OF_MONTH[] = {-1, 31, 28, 31, 30 ,31, 30, 31, 31, 30, 31, 30, 31}; //Empieza con -1 para calzar con los numeros de mes.
  public final int CARROLS_MAGIC_NUMBERS_FOR_MONTHS[] = {-1, 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5}; // Numeros del mes utilizados en el algoritmo de Lewis Carrol para calcular el dia de la semana. Empieza con -1 para calzar con los numeros de mes.
  public final int ACTION_ADD_DAY = 1, ACTION_ADD_MONTH = 2, ACTION_ADD_YEAR = 3; // Constantes represenan las acciones a tomar al definir le nueva fecha del dia siguiente, para el metodo nextDay
  public final int ACTION_SUBSTRACT_DAY = 1, ACTION_SUBSTRACT_MONTH = 2, ACTION_SUBSTRACT_YEAR = 3; // Constantes represenan las acciones a tomar al definir le nueva fecha del dia anterior, para el metodo previewsDay
  
  
  /**
   * Determina si un anno es bisiesto o no: Es bisiesto al ser divisible por 400 O por 4 pero no por 100
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
        return day <= DAYS_OF_MONTH[FEBRUARY] + 1;
      }
    }
    return day >= JANUARY && day <= DAYS_OF_MONTH[month];    
  }
  
  /**
   * Wrapper sobre dateIsValid(int, int, int) la cual recibe un objeto Date y lo pasa a la función inicial
   * para simplificar el desempaquetado del objeto Date al quere validarlo
   * @param date: Objeto Date que representa la fecha que se desea validar
   * @return: True si la fecha es valida, False en otro caso.
   */
  public boolean dateIsValid(final Date date) {
    return dateIsValid(date.getYear(), date.getMonth(), date.getDay());
  }
  
  
  /**
   * Determina el dia siguiente de la fecha ingresada.
   * @param year: AÃ±o utilizado para determinar la fecha del dia siguiente. Tambien utilizado para saber
   * si el anno en cuestion es bisiesto
   * @param month: Mes para determiar la fecha del dia siguiente
   * @param day: Dia del mes utilizado para determiar la fecha del dia siguiente
   * @return: Una hilera con la fecha del dÃ­a sigueinte en formato: A, M, D; Hilera de aviso en caso de fecha
   * invalida
   */
  public String nextDay(final int year, final int month, final int day) {
    if(!dateIsValid(year, month, day)) return "Fecha invalida";
    String nextDay = "";
    switch (month) {
      case FEBRUARY:
        if(!isLeapYear(year)) {
        if(day < DAYS_OF_MONTH[FEBRUARY]) {
          // Agrega un dia a los dias del mes.
          nextDay = getNewDate(year, month, day, ACTION_ADD_DAY);
        } else {
          // Agrega un mes y reinicia el dia del mes en 1.
          nextDay = getNewDate(year, month, day, ACTION_ADD_MONTH);
        }
      } else { //Anno bisiesto
        if(day < DAYS_OF_MONTH[FEBRUARY] + 1) {
          // Agrega un dia a los dias de febrero.
          nextDay = getNewDate(year, month, day, ACTION_ADD_DAY);
        } else {
          // Agrega un mes y reinicia el dia del mes en 1.
          nextDay = getNewDate(year, month, day, ACTION_ADD_MONTH);
        }
      }
      break;
      case DECEMBER:
        if(day < DAYS_OF_MONTH[DECEMBER]) {
        // Agrega un dia a los dias del mes.
        nextDay = getNewDate(year, month, day, ACTION_ADD_DAY);
      } else {
        // Agrega un anno y reinicia el dia del mes en 1 y el mes en enero.
        nextDay = getNewDate(year, month, day, ACTION_ADD_YEAR);
      }
      break;
      default:
        if(day < DAYS_OF_MONTH[month]) {
        // Agrega un dia a los dias del mes.
        nextDay = getNewDate(year, month, day, ACTION_ADD_DAY);
      } else {
        // Agrega un mes y reinicia el dia del mes en 1.
        nextDay = getNewDate(year, month, day, ACTION_ADD_MONTH);
      }
      break;
    }
    return nextDay;
  }
  
  
  /**
   * Retorna la nueva fecha para el dia siguiente, de acuerdo a la tupla ingresada y la accion requerida
   * @param year: Anno inicial para determinar la fecha del dia siguiente
   * @param month: Mes inicial para determiar la fecha del dia siguiente
   * @param day: Dia del mes inicial para determiar la fecha del dia siguiente
   * @param action: Accion a tomar con los dias ingresados para poder dar la nueva fecha. Tres posibles valores son:
   *                ACTION_ADD_YEAR = añadir uno al año, resetear el dia en uno y el mes en enero
   *                ACTION_ADD_MONTH = añadir uno al mes, resetear el dia en 1
   *                ACTION_ADD_DAY = añadir uno al dia, mantener los demas valores
   * @return: Retorna la nueva tupla con la fecha del dia siguiente.
   */ 
  public String getNewDate(final int year, final int month, final int day, final int action) {
    int newYear, newMonth, newDay;
    switch(action) {
      case ACTION_ADD_YEAR:
        // Agrega un anno y reinicia el dia del mes en 1 y el mes en enero.
        newDay = 1;
        newMonth = JANUARY;
        newYear = year+1;
        break;
      case ACTION_ADD_MONTH:
        // Agrega un mes y reinicia el dia del mes en 1.
        newDay = 1;
        newMonth = month+1;
        newYear = year;
        break;
      default:
        // Agrega un dia a los dias del mes.
        newDay = day+1;
        newMonth = month;
        newYear = year;
        break;
    }
    return newYear + ", " + newMonth + ", " + newDay;
  }
  
  
  /**
   * 
   * 
   */ 
  public int[] futureDays(Date date, int daysFuture) {
    int[] resultDate = date.toArray();
    
    if(daysFuture <= 0){
      return resultDate;
    }else{
      String futureDate = nextDay(date.getYear(), date.getMonth(), date.getDay());
      String[] parts = futureDate.split(", ");
      int year = Integer.parseInt(parts[0]);
      int month = Integer.parseInt(parts[1]);
      int day = Integer.parseInt(parts[2]);
      return futureDays(new Date(year, month, day), daysFuture - 1); 
    } 
  }
  
  
  /**
   * Determina el dia pasado de la fecha ingresada.
   * @param year: AÃ±o utilizado para determinar la fecha del dia anterior. Tambien utilizado para saber
   * si el anno en cuestion es bisiesto
   * @param month: Mes para determiar la fecha del dia anterior
   * @param day: Dia del mes utilizado para determiar la fecha del dia anterior
   * @return: Un arreglo de enteros con la fecha del dÃ­a anterior en formato: [A][M][D]; Arreglo con 
   *          celdas en "-1" en otro caso
   */
  public int[] previousDay(final int year, final int month, final int day) {
    if(!dateIsValid(year, month, day)) {
      int error[] = new int[3];
      error[0] = -1; error[1] = -1; error[2] = -1;
      return error;
    } 
    int[] nextDay;
    switch (month) {
      case JANUARY:
        if(day > 1) {
        // Resta un dia a los dias del mes.
        nextDay = getPreviousDate(year, month, day, ACTION_SUBSTRACT_DAY);
      } else {
        // Resta un anno, actualiza el dia del mes y el mes en Diciembre.
        nextDay = getPreviousDate(year, month, day, ACTION_SUBSTRACT_YEAR);
      }
      break;
      default:
        if(day > 1) {
        // Resta un dia a los dias del mes.
        nextDay = getPreviousDate(year, month, day, ACTION_SUBSTRACT_DAY);
      } else {
        // Resta un mes.
        nextDay = getPreviousDate(year, month, day, ACTION_SUBSTRACT_MONTH);
      }
      break;
    }
    return nextDay;
  }
  
  
  /**
   * Retorna la nueva fecha para el dia anterior, de acuerdo a la tupla ingresada y la accion requerida
   * @param year: Anno inicial para determinar la fecha del dia anterior
   * @param month: Mes inicial para determiar la fecha del dia anterior
   * @param day: Dia del mes inicial para determiar la fecha del dia anterior
   * @param action: Accion a tomar con los dias ingresados para poder dar la nueva fecha. Tres posibles valores son:
   *                ACTION_SUBSTRACT_YEAR = resta uno al año, coloca el día en 31 y el mes en diciembre
   *                ACTION_SUBSTRACT_MONTH = resta uno al mes, resetear el dia en la cantidad de días del nuevo mes
   *                ACTION_SUBSTRACT_DAY = resta uno al dia, mantener los demas valores
   * @return: Retorna la nueva tupla con la fecha del dia siguiente.
   */ 
  public int[] getPreviousDate(final int year, final int month, final int day, final int action) {
    int newYear = -1, newMonth = -1, newDay = -1;
    switch(action) {
      case ACTION_SUBSTRACT_YEAR:
        // Resta un anno y coloca el dia del mes al final del nuevo mes y el mes en diciembre.
        newDay = DAYS_OF_MONTH[DECEMBER];
        newMonth = DECEMBER;
        newYear = year-1;
        break;
      case ACTION_SUBSTRACT_MONTH:
        // Resta un mes y coloca el dia del mes al final del nuevo mes.
        if(month == MARCH) {
        //Si el mes acutal es marzo debe colocarse 29 como día del mes si el anno es bisiesto y 28 en caso de que no lo sea.
        if(isLeapYear(year)) {
          newDay = DAYS_OF_MONTH[FEBRUARY] + 1;
        } else {
          newDay = DAYS_OF_MONTH[FEBRUARY];
        }
        newMonth = FEBRUARY;
      } else {
        newMonth = month - 1;
        newDay = DAYS_OF_MONTH[newMonth];
      }
      newYear = year;
      break;
      default:
        // Resta un dia a los dias del mes.
        newMonth = month;
        newDay = day-1;
        newYear = year;
        break;
    }
    int newDate[] = new int[3];
    newDate[0] = newYear; 
    newDate[1] = newMonth;
    newDate[2] = newDay;
    return newDate;
  }
  
  
  /**
   * Determina una fecha en N días en el pasado de acuerdo al parámetro ingresado.
   * @param date: La fecha base para calcular la fecha pasada
   * @param daysPast: Entero que determina cuantos días en el pasado se debe calcular.
   * @returns Arreglo de 3 enteros que representa la fecha pasada, donde la celda 0 del arreglo representa el anno
   *          la celda 1 representa el mes y la celda 2 representa el día del mes
   */ 
  public int[] previousDate(Date date, int daysPast) {
    //Obtiene la fecha en formato de arreglo: [ANNO],[MES],[DIA].
    int[] resultDate = date.toArray();
    
    if(daysPast <= 0){
      //Si ya se contaron todos los días a retroceder se retorna la fecha actual
      return resultDate;
    }else{
      //Si faltan días por retroceder se llama a pastDate para recuperar el día pasado inmediato y se llama recursivamente
      //a la función previewsDays con la nueva fecha.
      int[] pastDate = previousDay(date.getYear(), date.getMonth(), date.getDay());
      return previousDate(new Date(pastDate), daysPast - 1); 
    } 
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
  
  
  /**
   * Determina la cantidad de días transcurridos para una fecha a partir del año 0.
   * @param date: Fecha a calcularle la cantidad de dias totales
   * @return entero que representa los días transcurridos desde el año 0 hasta la fecha dada.
   */
  public int countDaysOfDate(Date date) {
    //cantidad de días en total antes del año evaluado (sin contar días extra por años bisiestos) según año de la fecha
    int totalDaysDate = ((date.getYear() - GREGORIAN_STARTING_YEAR))  * DAYS_OF_BASE_YEAR;
    
    //cantidad de días en total antes de la fecha evaluada para el año de la fecha, según mes de la fecha
    for (int i=1; i< date.getMonth(); i++)
      totalDaysDate += DAYS_OF_MONTH[i];
    
    //cantidad de días del mes de la fecha evaluada
    totalDaysDate += date.getDay();
    
    //Por último, se suma los días extra de los años bisiestos tomando en cuenta el año presente, si es bisiesto
    //y si es una fecha que incluye febrero (mes > febrero)
    totalDaysDate += countLeapYears(date);
    
    return totalDaysDate;
  }
  
  
  /**
   * 
   * 
   */
  int countLeapYears(Date date){
    int years = date.getYear();
    int daysOfLeapYears = 0;
    
    //Se evalua cada año antes del dado para ver si es bisiesto, si lo es, se debe sumar un día a los días extra por bisiestos
    for (int i= GREGORIAN_STARTING_YEAR; i < years; i++){
      if(isLeapYear(i)){
        daysOfLeapYears += 1;
      } 
    }
    //Si la fecha incluía un mes mayor a febrero hay que tomar en cuenta el año en cuestión a la hora de sumar los días extra por bisiestos
    if (date.getMonth() > FEBRUARY)
      if(isLeapYear(years)){
      daysOfLeapYears += 1;
    } 
    
    return daysOfLeapYears;
  }
  
  
  /**
   * Determina la cantidad de días entre dos fechas dadas
   * @param date1: Fecha número 1 a ser evaluada con la fecha 2
   * @param date2: Fecha número 2 a ser evaluada con la fecha 1
   * @return: Entero que representa los días que hay entre dos fechas dadas
   */
  public int daysBetweenDates(Date date1, Date date2) {
    if(!dateIsValid(date1) || !dateIsValid(date2)) return -1;
      
    //Se calculan la cantidad de días totales para ambas fechas
    int totalDaysDate1 = countDaysOfDate(date1);
    int totalDaysDate2 = countDaysOfDate(date2);
    
    //Se saca la diferencia de días para determinar cuántos días hay entre las fechas dadas
    int daysBetweenTwoDates = totalDaysDate1 - totalDaysDate2;
    
    //Devuelve el número positivo del cálculo
    return (daysBetweenTwoDates < 0 ? -daysBetweenTwoDates : daysBetweenTwoDates);
  }  
  
  
  public static void main(String [] args) {
    String [] dayOfWeek = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
    GregorianCalendar gc = new GregorianCalendar();
    int year = 2000, month = 2, day = 1;
    System.out.println("La fecha: " + year + ", " + month + ", " + day + " es válida? " + gc.dateIsValid(year, month, day));
    System.out.println("Año es bisiesto?: " + gc.isLeapYear(year) );
    System.out.println("Día de la semana es: " + dayOfWeek[gc.dayOfWeek(year,month, day)]);
    System.out.println("La fecha del día siguiente es: " + gc.nextDay(year,month, day));
    
    Date date1 = new Date(year, month, day);
    Date date2 = new Date(2004, 4, 5);
    int daysFuture = 1;
    int daysPast = 366;
    System.out.println("La fecha 2: " + date2.getYear() + ", " + date2.getMonth() + ", " + date2.getDay() + " es válida? " + gc.dateIsValid(date2));
    System.out.println("Día(s) entre las fehcas: " + gc.daysBetweenDates(date1, date2));
    int result[] =  gc.futureDays(date2, 1);
    System.out.println(daysFuture + " día(s) futuros para al fecha 2: " + result[0] + ", " + result[1] + ", " + result[2]);
    
    result  = gc.previousDate(date2, 366);
    System.out.println(daysPast + " dia(s) previos para al fecha 2: " + result[0] + ", " + result[1] + ", " + result[2]);
    
  }
}