import org.junit.Test;
import static org.junit.Assert.*;


public class SampleUnitTests {
  
  @Test
  public void pruebaDayOfYear() {
    assertEquals (SampleDayOfYear.dayOfYear(13,1,2000),-1);
    assertEquals (SampleDayOfYear.dayOfYear(2,29,2017),-2);
    assertEquals (SampleDayOfYear.dayOfYear(3,1,1300),-3); 
    
    
    
    
    assertEquals (SampleDayOfYear.dayOfYear(12,31,2017),365);  
    assertEquals (SampleDayOfYear.dayOfYear(1,2,2017),2);  
    assertEquals (SampleDayOfYear.dayOfYear(3,1,2000),61);
    assertEquals (SampleDayOfYear.dayOfYear(1,1,2000),1);
    assertEquals (SampleDayOfYear.dayOfYear(3,1,2017),60);
    
  }
  
  @Test
  public void pruebaleapYear() {
    
    assertEquals (SampleDayOfYear.leapYear(1700), false);
    assertEquals (SampleDayOfYear.leapYear(1300),false); 
  }
  
  
  @Test
  public void pruebanextDay() {
    
    assertEquals (SampleDayOfYear.nextDate(1300,2,2),"ERROR");
    assertEquals (SampleDayOfYear.nextDate(2017,1,25),"2017/1/26");
    assertEquals (SampleDayOfYear.nextDate(2017,1,28),"2017/1/29");
    assertEquals (SampleDayOfYear.nextDate(2017,2,28),"2017/3/1");
    assertEquals (SampleDayOfYear.nextDate(2000,2,28),"2000/2/29");
    assertEquals (SampleDayOfYear.nextDate(2017,1,29),"2017/1/30");
    assertEquals (SampleDayOfYear.nextDate(2017,2,29),"ERROR"); //l[inea 188 no se puede porque da error antes de llegar ahi
    assertEquals (SampleDayOfYear.nextDate(2000,2,29),"2000/3/1");
    assertEquals (SampleDayOfYear.nextDate(2000,1,29),"2000/1/30");
    assertEquals (SampleDayOfYear.nextDate(2000,4,30),"2000/5/1");
    assertEquals (SampleDayOfYear.nextDate(2000,6,30),"2000/7/1");
    assertEquals (SampleDayOfYear.nextDate(2000,9,30),"2000/10/1");
    assertEquals (SampleDayOfYear.nextDate(2000,11,30),"2000/12/1");
    assertEquals (SampleDayOfYear.nextDate(2000,7,30),"2000/7/31");
    assertEquals (SampleDayOfYear.nextDate(2007,7,31),"2007/8/1");
    assertEquals (SampleDayOfYear.nextDate(2000,12,31),"2001/1/1");
    
  }
  
  @Test
  public void pruebaisValidDate() {
    
    assertEquals (SampleDayOfYear.isValidDate(1581,0,0),false);
    assertEquals (SampleDayOfYear.isValidDate(1581,13,0),false);
    assertEquals (SampleDayOfYear.isValidDate(1581,3,0),false);
    assertEquals (SampleDayOfYear.isValidDate(1581,0,32),false);
    
    assertEquals (SampleDayOfYear.isValidDate(1581,13,32),false);
    assertEquals (SampleDayOfYear.isValidDate(1581,3,32),false);
    assertEquals (SampleDayOfYear.isValidDate(1581,3,1),false);
    assertEquals (SampleDayOfYear.isValidDate(1581,13,1),false);
    assertEquals (SampleDayOfYear.isValidDate(1581,0,1),false);
    
    assertEquals (SampleDayOfYear.isValidDate(1583,0,0),false);
    assertEquals (SampleDayOfYear.isValidDate(1583,13,0),false);
    assertEquals (SampleDayOfYear.isValidDate(1583,3,0),false);
    assertEquals (SampleDayOfYear.isValidDate(1583,0,32),false);
    
    assertEquals (SampleDayOfYear.isValidDate(1583,13,32),false);
    assertEquals (SampleDayOfYear.isValidDate(1583,3,32),false);
    assertEquals (SampleDayOfYear.isValidDate(1583,3,1),true);
    assertEquals (SampleDayOfYear.isValidDate(1583,13,1),false);
    assertEquals (SampleDayOfYear.isValidDate(1583,0,1),false);
  }
  
  
  
  @Test
  public void pruebavalidateDaysOfMonth() {    
    assertEquals (SampleDayOfYear.validateDaysOfMonth(0,1,2000),-1); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(13,1,2000),-1); 
    
    assertEquals (SampleDayOfYear.validateDaysOfMonth(2,0,2000),2); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(2,2,2000),2); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(2,28,2017),2); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(2,1,2000),2); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(2,29,2000),2);
    assertEquals (SampleDayOfYear.validateDaysOfMonth(2,29,2017),-2);
    
    assertEquals (SampleDayOfYear.validateDaysOfMonth(8,0,2000),-2); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(8,28,2017),8); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(8,1,2000),8); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(8,32,2000),-2);
    assertEquals (SampleDayOfYear.validateDaysOfMonth(8,31,2000),8);
    
    assertEquals (SampleDayOfYear.validateDaysOfMonth(4,0,2000),-2); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(4,28,2017),4); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(4,1,2000),4); 
    assertEquals (SampleDayOfYear.validateDaysOfMonth(4,32,2000),-2);
    assertEquals (SampleDayOfYear.validateDaysOfMonth(4,30,2000),4);
    
    
  }
  
  
  @Test
  public void pruebaDiaSiguiente() {
    assertEquals(SampleDayOfYear.nextDate(2017,7,30),"2017/7/31");
    assertEquals(SampleDayOfYear.nextDate(2003,2,28),"2003/3/1");
  }
  
}
