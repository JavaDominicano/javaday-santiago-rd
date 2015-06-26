
package org.ecabrerar.examples.java8.datetime;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;

import org.ecabrerar.examples.java8.HolidaysQuery;
import org.ecabrerar.examples.java8.collections.CollectionsExamples;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ecabrerar
 * @date   Mar 27, 2015
 */
public class WorkingWithDateTime {

	private static final Logger logger =  LoggerFactory.getLogger(CollectionsExamples.class);

	@Test
	public void compare_two_dates_java () {

	    Calendar sinceJava8Launch = Calendar.getInstance();
	    sinceJava8Launch.set(Calendar.YEAR, 2014);
	    sinceJava8Launch.set(Calendar.MONTH, 3);
	    sinceJava8Launch.set(Calendar.DAY_OF_MONTH, 18);

	    Calendar today = Calendar.getInstance();

	    assertTrue(today.after(sinceJava8Launch));
	}

	@Test
	public void compare_two_dates_java8 () {

	    LocalDate sinceJava8Launch = LocalDate.of(2014, Month.MARCH, 18);
	    LocalDate today = LocalDate.now();

	    assertTrue(today.isAfter(sinceJava8Launch));
	}

	@Test
	public void difference_between_two_dates_java8_period() {

	    LocalDate sinceJava8Launch = LocalDate.of(2014, Month.MARCH, 18);
	    LocalDate currentDate = LocalDate.of(2015, Month.MARCH, 28);

	    Period betweenDates = Period.between(sinceJava8Launch, currentDate);

	    int diffInDays = betweenDates.getDays();
	    int diffInMonths = betweenDates.getMonths();
	    int diffInYears = betweenDates.getYears();

	    logger.info(String.format(" %d ", diffInDays));
	    logger.info(String.format(" %d ", diffInMonths));
	    logger.info(String.format(" %d ", diffInYears));

	    assertTrue(diffInDays >= 9);
	    assertTrue(diffInMonths >= 0);
	    assertTrue(diffInYears >= 1);
	}

	@Test
	public void difference_between_two_dates_java8_duration() {

	    LocalDateTime dateTime = LocalDateTime.of(2014, 3, 18, 0, 0);
	    LocalDateTime dateTime2 =  LocalDateTime.of(2015, Month.MARCH, 28,0,0);

	    int diffInNano = java.time.Duration.between(dateTime, dateTime2)
	            .getNano();
	    long diffInSeconds = java.time.Duration.between(dateTime, dateTime2)
	            .getSeconds();

	    long diffInMilli = java.time.Duration.between(dateTime, dateTime2)
	            .toMillis();
	    long diffInMinutes = java.time.Duration.between(dateTime, dateTime2)
	            .toMinutes();
	    long diffInHours = java.time.Duration.between(dateTime, dateTime2)
	            .toHours();

	    logger.info(String.format(" %d ", diffInNano));
	    logger.info(String.format(" %d ", diffInSeconds));
	    logger.info(String.format(" %d ", diffInMilli));
	    logger.info(String.format(" %d ", diffInMinutes));
	    logger.info(String.format(" %d ", diffInHours));

	    assertTrue(diffInNano >= 0);
	    assertTrue(diffInSeconds >= 32400000);
	    assertTrue(diffInMilli >= Long.parseLong("32400000000"));
	    assertTrue(diffInMinutes >= 540000);
	    assertTrue(diffInHours >= 9000);
	}

	@Test
	public void is_date_a_holiday () {

	    LocalDate date = LocalDate.of(2014, Month.JANUARY, 26);

	    Boolean isHoliday = date.query(new HolidaysQuery());

	    assertTrue(isHoliday);
	}

	@Test
	public void is_not_date_a_holiday () {

	    LocalDate date = LocalDate.of(2014, Month.NOVEMBER, 10);

	    Boolean isHoliday = date.query(new HolidaysQuery());
                    assertFalse(isHoliday);
	}

	@Test
	public void days_in_month_java() {

	    Calendar c = Calendar.getInstance();
	    c.set(Calendar.MONTH, 3);
	    c.set(Calendar.DAY_OF_MONTH, 18);
	    c.set(Calendar.YEAR, 2014);

	    int numberOfDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);

	    assertEquals(30, numberOfDays);
	}

	@Test
	public void days_in_month_java8() {

	    LocalDate date = LocalDate.of(2015, Month.MARCH, 01);

	    int length = date.getMonth().length(true);

	    assertEquals(31, length);

	}
        
        @Test
	public void days_in_month_java8_with_lengthOfMonth() {

	    LocalDate date = LocalDate.of(2015, Month.MARCH, 01);
	   
	    int length2 = date.lengthOfMonth();

	    assertEquals(31, length2);
	}

}
