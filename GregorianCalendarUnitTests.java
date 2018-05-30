import org.junit.Test;
import static org.junit.Assert.*;


public class GregorianCalendarUnitTests {
  
  @Test
  public void previousDayTest() {
    GregorianCalendar gc = new GregorianCalendar();
    assertArrayEquals(gc.previousDay(2018,1,1), new int[] {2017,12,31}); 
    assertArrayEquals(gc.previousDay(2018,1,5), new int[] {2018,1,4});
    assertArrayEquals(gc.previousDay(2018,2,1), new int[] {2018,1,31});
    assertArrayEquals(gc.previousDay(2018,2,20), new int[] {2018,2,19});
    assertArrayEquals(gc.previousDay(2016,2,29), new int[] {2016,2,28});
    assertArrayEquals(gc.previousDay(2018,2,29), new int[] {-1,-1,-1});
    assertArrayEquals(gc.previousDay(2016,2,31), new int[] {-1,-1,-1});
    assertArrayEquals(gc.previousDay(2016,3,1), new int[] {2016,2,29});
    assertArrayEquals(gc.previousDay(2018,3,1), new int[] {2018,2,28});
    assertArrayEquals(gc.previousDay(2018,3,5), new int[] {2018,3,4});
    assertArrayEquals(gc.previousDay(2016,6,1), new int[] {2016,5,31});
    assertArrayEquals(gc.previousDay(2016,6,20), new int[] {2016,6,19});
    assertArrayEquals(gc.previousDay(2016,6,31), new int[] {-1,-1,-1});
    assertArrayEquals(gc.previousDay(2016,7,1), new int[] {2016,6,30});
    assertArrayEquals(gc.previousDay(2016,7,20), new int[] {2016,7,19});
  }
  
  @Test
  public void previousDateTest() {
    GregorianCalendar gc = new GregorianCalendar();
    assertArrayEquals(gc.previousDate(new Date(2018, 1, 1), 0), new int[] {2018, 1, 1}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 1, 1), 1), new int[] {2017,12,31});  
    assertArrayEquals(gc.previousDate(new Date(2018, 1, 1), 365), new int[] {2017, 1, 1}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 1, 1), 366), new int[] {2016,12,31}); 
    assertArrayEquals(gc.previousDate(new Date(2016, 1, 1), 367), new int[] {2014,12,30}); 
    assertArrayEquals(gc.previousDate(new Date(2016, 1, 20), 7), new int[] {2016,1,13}); 
    assertArrayEquals(gc.previousDate(new Date(2016, 1, 31), 32), new int[] {2015,12,30}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 2, 1), 1), new int[] {2018,1,31}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 2, 20), 7), new int[] {2018,2,13}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 2, 28), 32), new int[] {2018,1,27}); 
    assertArrayEquals(gc.previousDate(new Date(2016, 2, 29), 366), new int[] {2015,2,28}); 
    assertArrayEquals(gc.previousDate(new Date(2016, 2, 29), 0), new int[] {2016,2,29}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 3, 1), 1), new int[] {2018,2,28}); 
    assertArrayEquals(gc.previousDate(new Date(2016, 3, 1), 1), new int[] {2016,2,29}); 
    assertArrayEquals(gc.previousDate(new Date(2016, 3, 20), 7), new int[] {2016,3,13}); 
    assertArrayEquals(gc.previousDate(new Date(2016, 3, 31), 367), new int[] {2015,3,30}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 6, 1), 7), new int[] {2018,5,25}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 6, 20), 32), new int[] {2018,5,19}); 
    assertArrayEquals(gc.previousDate(new Date(2016, 6, 30), 366), new int[] {2015,6,30}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 7, 1), 7), new int[] {2018,6,24}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 7, 20), 0), new int[] {2018,7,20}); 
    assertArrayEquals(gc.previousDate(new Date(2018, 7, 31), 365), new int[] {2017,7,31}); 
  }
  
  @Test
  public void countDaysTest() {
    GregorianCalendar gc = new GregorianCalendar();
    assertEquals(gc.countDaysOfDate( new Date(1582, 1, 1)), 1);
    assertEquals(gc.countDaysOfDate( new Date(1582, 1, 31)), 31);
    assertEquals(gc.countDaysOfDate( new Date(1582, 2, 1)), 32);
    assertEquals(gc.countDaysOfDate( new Date(1582, 2, 28)), 59);
    assertEquals(gc.countDaysOfDate( new Date(1582, 3, 1)), 60);
    assertEquals(gc.countDaysOfDate( new Date(1582, 12, 31)), 365);
    assertEquals(gc.countDaysOfDate( new Date(1583, 1, 1)), 366);
    
    assertEquals(gc.countDaysOfDate( new Date(2016,1,1)), 158516);
    assertEquals(gc.countDaysOfDate( new Date(2016,1,20)), 158535);
    assertEquals(gc.countDaysOfDate( new Date(2016,1,31)), 158546);
    assertEquals(gc.countDaysOfDate( new Date(2016,2,1)), 158547);
    assertEquals(gc.countDaysOfDate( new Date(2016,2,20)), 158566);
    assertEquals(gc.countDaysOfDate( new Date(2016,2,29)), 158575);
    assertEquals(gc.countDaysOfDate( new Date(2016,3,1)), 158576);
    assertEquals(gc.countDaysOfDate( new Date(2016,3,20)), 158595);
    assertEquals(gc.countDaysOfDate( new Date(2016,6,20)), 158687);
    assertEquals(gc.countDaysOfDate( new Date(2016,6,30)), 158697);
    assertEquals(gc.countDaysOfDate( new Date(2016,7,1)), 158698);
    assertEquals(gc.countDaysOfDate( new Date(2016,12,1)), 158851);
    assertEquals(gc.countDaysOfDate( new Date(2016,12,20)), 158870);
    assertEquals(gc.countDaysOfDate( new Date(2016,12,31)), 158881);
    assertEquals(gc.countDaysOfDate( new Date(2017,1,1)), 158882);
    assertEquals(gc.countDaysOfDate( new Date(2017,1,20)), 158901);
    assertEquals(gc.countDaysOfDate( new Date(2017,1,31)), 158912);
    assertEquals(gc.countDaysOfDate( new Date(2017,2,1)), 158913);
    assertEquals(gc.countDaysOfDate( new Date(2017,2,20)), 158932);
    assertEquals(gc.countDaysOfDate( new Date(2017,2,28)), 158940);
    assertEquals(gc.countDaysOfDate( new Date(2017,3,1)), 158941);
    assertEquals(gc.countDaysOfDate( new Date(2017,3,20)), 158960);
    assertEquals(gc.countDaysOfDate( new Date(2017,6,20)), 159052);
    assertEquals(gc.countDaysOfDate( new Date(2017,6,30)), 159062);
    assertEquals(gc.countDaysOfDate( new Date(2017,7,1)), 159063);
    assertEquals(gc.countDaysOfDate( new Date(2017,12,1)), 159216);
    assertEquals(gc.countDaysOfDate( new Date(2017,12,20)), 159235);
    assertEquals(gc.countDaysOfDate( new Date(2017,12,31)), 159246);
    assertEquals(gc.countDaysOfDate( new Date(2018,1,1)), 159247);
  }
  
  @Test 
  public void daysBetweenDatesTest() {
    GregorianCalendar gc = new GregorianCalendar();
    assertEquals(gc.daysBetweenDates(new Date(2016,1,1), new Date(2015,1,1)), 365);
    assertEquals(gc.daysBetweenDates(new Date(2011,6,20), new Date(2013,6,20)), 731);
    assertEquals(gc.daysBetweenDates(new Date(2016,3,1), new Date(2016,3,1)), 0);    
    assertEquals(gc.daysBetweenDates(new Date(2011,2,29), new Date(2013,6,20)), -1);    
    assertEquals(gc.daysBetweenDates(new Date(2011,2,28), new Date(2013,6,31)), -1);    
    assertEquals(gc.daysBetweenDates(new Date(2016,13,1), new Date(2016,2,31)), -1);    
  }
  
  
  
  
  //Pruebas de los métodos pasados. Se mantenien por completitud de las pruebas.
  @Test
  public void isLeapYearTest() {
    GregorianCalendar gc = new GregorianCalendar();
    assertEquals (gc.isLeapYear(2000), true);   
    assertEquals (gc.isLeapYear(2004), true);   
    assertEquals (gc.isLeapYear(2006), false);   
    assertEquals (gc.isLeapYear(7001), false);   
    assertEquals (gc.isLeapYear(1700), false);
  }
  
  @Test
  public void dateIsValidTest() {
    GregorianCalendar gc = new GregorianCalendar();
    assertEquals (gc.dateIsValid(2000,1,25), true);
    assertEquals (gc.dateIsValid(2000,1,31), true);
    assertEquals (gc.dateIsValid(2000,2,28), true);
    assertEquals (gc.dateIsValid(1700,2,28), true);
    assertEquals (gc.dateIsValid(2000,2,29), true);
    assertEquals (gc.dateIsValid(2000,2,30), false);
    assertEquals (gc.dateIsValid(2000,2,31), false);
    assertEquals (gc.dateIsValid(2000,3,31), true);
    assertEquals (gc.dateIsValid(2040,4,30), true);
    assertEquals (gc.dateIsValid(2040,5,31), true);
    assertEquals (gc.dateIsValid(2031,6,30), true);
    assertEquals (gc.dateIsValid(2051,7,31), true);
    assertEquals (gc.dateIsValid(2040,8,31), true);
    assertEquals (gc.dateIsValid(2040,9,30), true);
    assertEquals (gc.dateIsValid(2000,10,31), true);
    assertEquals (gc.dateIsValid(2031,11,30), true);
    assertEquals (gc.dateIsValid(2068,12,31), true);
    assertEquals (gc.dateIsValid(2051,7,0), false);
    assertEquals (gc.dateIsValid(2000,1,32), false);
    assertEquals (gc.dateIsValid(2000,2,30), false);
    assertEquals (gc.dateIsValid(1700,2,29), false);
    assertEquals (gc.dateIsValid(2000,3,32), false);
    assertEquals (gc.dateIsValid(2040,4,31), false);
    assertEquals (gc.dateIsValid(2040,5,32), false);
    assertEquals (gc.dateIsValid(2031,6,31), false);
    assertEquals (gc.dateIsValid(2051,7,32), false);
    assertEquals (gc.dateIsValid(2040,8,32), false);
    assertEquals (gc.dateIsValid(2040,9,31), false);
    assertEquals (gc.dateIsValid(2010,10,32), false);
    assertEquals (gc.dateIsValid(2031,11,31), false);
    assertEquals (gc.dateIsValid(2068,12,32), false);
    assertEquals (gc.dateIsValid(2031,13,30), false);
    assertEquals (gc.dateIsValid(2068,0,32), false);
    assertEquals (gc.dateIsValid(1000,11,25), false);
    assertEquals (gc.dateIsValid(1582,11,25), true);
    assertEquals (gc.dateIsValid(1581,11,25), false);
    assertEquals (gc.dateIsValid(1583,11,25), true);    
  }
  
  @Test
  public void nextDayTest() {
    GregorianCalendar gc = new GregorianCalendar();
    assertEquals(gc.nextDay(2000,1,31), "2000, 2, 1");  
    assertEquals(gc.nextDay(2000,1,20), "2000, 1, 21");
   assertEquals(gc.nextDay(2000,2,20), "2000, 2, 21");
    assertEquals(gc.nextDay(2000,2,28), "2000, 2, 29");
    assertEquals(gc.nextDay(1700,2,28), "1700, 3, 1");
    assertEquals(gc.nextDay(2000,2,29), "2000, 3, 1");
    assertEquals(gc.nextDay(2000,3,31), "2000, 4, 1");
    assertEquals(gc.nextDay(2000,3,20), "2000, 3, 21");
    assertEquals(gc.nextDay(2040,4,30), "2040, 5, 1");
    assertEquals(gc.nextDay(2040,4,20), "2040, 4, 21");
    assertEquals(gc.nextDay(2040,5,31), "2040, 6, 1");
    assertEquals(gc.nextDay(2040,5,20), "2040, 5, 21");
    assertEquals(gc.nextDay(2031,6,30), "2031, 7, 1");
    assertEquals(gc.nextDay(2031,6,20), "2031, 6, 21");
    assertEquals(gc.nextDay(2051,7,31), "2051, 8, 1");
    assertEquals(gc.nextDay(2051,7,20), "2051, 7, 21");
    assertEquals(gc.nextDay(2040,8,31), "2040, 9, 1");
    assertEquals(gc.nextDay(2040,8,20), "2040, 8, 21");
    assertEquals(gc.nextDay(2040,9,30), "2040, 10, 1");
    assertEquals(gc.nextDay(2040,9,20), "2040, 9, 21");
    assertEquals(gc.nextDay(2000,10,31), "2000, 11, 1");
    assertEquals(gc.nextDay(2000,10,20), "2000, 10, 21");
    assertEquals(gc.nextDay(2031,11,30), "2031, 12, 1");
    assertEquals(gc.nextDay(2031,11,20), "2031, 11, 21");
    assertEquals(gc.nextDay(2068,12,31), "2069, 1, 1");
    assertEquals(gc.nextDay(2068,12,20), "2068, 12, 21");
  }
  
  @Test
  public void dayOfWeekTest() {
    GregorianCalendar gc = new GregorianCalendar();
    //OldStyle
    assertEquals(gc.dayOfWeek(1676,1,20), 1);
    assertEquals(gc.dayOfWeek(1593, 1, 20), 3);
    assertEquals(gc.dayOfWeek(1676,2,20), 4);
    assertEquals(gc.dayOfWeek(1593, 2, 20), 6);
    assertEquals(gc.dayOfWeek(1676,3,20), 5);
    assertEquals(gc.dayOfWeek(1593, 3, 20),6 );
    assertEquals(gc.dayOfWeek(1676,4,20), 1);
    assertEquals(gc.dayOfWeek(1593, 4, 20), 2);
    assertEquals(gc.dayOfWeek(1676,5,20), 3);
    assertEquals(gc.dayOfWeek(1593, 5, 20), 4);
    assertEquals(gc.dayOfWeek(1676,6,20), 6);
    assertEquals(gc.dayOfWeek(1593, 6, 20),0 );
    assertEquals(gc.dayOfWeek(1676,7,20), 1);
    assertEquals(gc.dayOfWeek(1593, 7, 20),2 );
    assertEquals(gc.dayOfWeek(1676,8,20), 4);
    assertEquals(gc.dayOfWeek(1593, 8, 20), 5);
    assertEquals(gc.dayOfWeek(1676,9,20), 0);
    assertEquals(gc.dayOfWeek(1593, 9, 20), 1);
    assertEquals(gc.dayOfWeek(1676,10,20), 2);
    assertEquals(gc.dayOfWeek(1593, 10, 20), 3);
    assertEquals(gc.dayOfWeek(1676,11,20), 5);
    assertEquals(gc.dayOfWeek(1593, 11, 20), 6);
    assertEquals(gc.dayOfWeek(1676,12,20), 0);
    assertEquals(gc.dayOfWeek(1593, 12, 20),1 );
    assertEquals(gc.dayOfWeek(1752, 9 ,1), 5);
    assertEquals(gc.dayOfWeek(1752, 9 ,2), 6);
    assertEquals(gc.dayOfWeek(1752, 9 ,3), 0);
    assertEquals(gc.dayOfWeek(1676,2,29), 6);
    assertEquals(gc.dayOfWeek(1676,3,1), 0);
    assertEquals(gc.dayOfWeek(1593, 2, 28), 0);
    assertEquals(gc.dayOfWeek(1593, 3, 1), 1);
    //NewStyle
    assertEquals(gc.dayOfWeek(2016,1,20), 3);
    assertEquals(gc.dayOfWeek(1783,1,20), 1);
    assertEquals(gc.dayOfWeek(2016,2,20), 6);
    assertEquals(gc.dayOfWeek(1783,2,20), 4);
    assertEquals(gc.dayOfWeek(2016,3,20), 0);
    assertEquals(gc.dayOfWeek(1783,3,20), 4);
    assertEquals(gc.dayOfWeek(2016,4,20), 3);
    assertEquals(gc.dayOfWeek(1783,4,20), 0);
    assertEquals(gc.dayOfWeek(2016,5,20), 5);
    assertEquals(gc.dayOfWeek(1783,5,20), 2);
    assertEquals(gc.dayOfWeek(2016,6,20), 1);
    assertEquals(gc.dayOfWeek(1783,6,20), 5);
    assertEquals(gc.dayOfWeek(2016,7,20), 3);
    assertEquals(gc.dayOfWeek(1783,7,20), 0);
    assertEquals(gc.dayOfWeek(2016,8,20), 6);
    assertEquals(gc.dayOfWeek(1783,8,20), 3);
    assertEquals(gc.dayOfWeek(2016,9,20), 2);
    assertEquals(gc.dayOfWeek(1783,9,20), 6);
    assertEquals(gc.dayOfWeek(2016,10,20), 4);
    assertEquals(gc.dayOfWeek(1783,10,20), 1);
    assertEquals(gc.dayOfWeek(2016,11,20), 0);
    assertEquals(gc.dayOfWeek(1783,11,20), 4);
    assertEquals(gc.dayOfWeek(2016,12,20), 2);
    assertEquals(gc.dayOfWeek(1783,12,20), 6);
    assertEquals(gc.dayOfWeek(1752,9,13), 3);
    assertEquals(gc.dayOfWeek(1752,9,14), 4);
    assertEquals(gc.dayOfWeek(1752,9,15), 5);
    assertEquals(gc.dayOfWeek(2016,2,29), 1);
    assertEquals(gc.dayOfWeek(2016,3,1), 2);
    assertEquals(gc.dayOfWeek(1783,2,28), 5);
    assertEquals(gc.dayOfWeek(1783,3,1), 6);
  }
  
  
}
