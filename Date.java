public class Date { 
  private int year; 
  private int month;
  private int day;


  /**
   * Constructo de Date
   * @param year: entero representando el anno a crear
   * @param month: entero representando el mes a crear
   * @param day: entero representando el dia del mes a crear
   */ 
  public Date (int year, int month, int day) { 
    this.year = year; 
    this.month = month;
    this.day = day;
  } 
  
  /**
   * Constructor Date
   * @param date: Representacion en forma de arreglo de la fecha donde la posicion 0 debe ser el anno
   *              La posicion 1 el mes y la posicion 2 el dia
   */ 
  public Date (int[] date) {
    year = date[0];
    month = date[1];
    day = date[2];
  }
  
   public int getYear() { 
    return year; 
  }
  public int getMonth() { 
    return month; 
  }
  
   public int getDay() { 
    return day; 
  }
   
   /**
    * Conviere un objeto Date a su representación en forma de arreglo
    * @return Un arreglo de 3 enteros donde la posición 0 representa el anno, la posición 1 el mes y la 2 el día del mes.
    */ 
   public int[] toArray() {
     int[] dateArray = new int[3];
     dateArray[0] = this.year;
     dateArray[1] = this.month;
     dateArray[2] = this.day;
     return dateArray;
   }
} 