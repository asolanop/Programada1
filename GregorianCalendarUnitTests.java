import org.junit.Test;
import static org.junit.Assert.*;


public class GregorianCalendarUnitTests {
  
  
  
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
  public void dayOfWeekTest() {
    GregorianCalendar gc = new GregorianCalendar();
    //OldDays
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
    //NewDays
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
