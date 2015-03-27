/**
 *
 */
package org.ecabrerar.examples.java8;

import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author ecabrerar
 * @date   Mar 27, 2015
 */
public class DiasFestivosQuery implements TemporalQuery<Boolean>{

	 static List<MonthDay> DIAS_FESTIVOS = Lists.newArrayList(
	            MonthDay.of(Month.JANUARY, 1), // A�o Nuevo
	            MonthDay.of(Month.JANUARY, 6), // D�a Santos Reyes
	            MonthDay.of(Month.JANUARY, 21), // D�a de la Altagracia
		        MonthDay.of(Month.JANUARY, 26), // D�a del Natalicio de Duarte
		        MonthDay.of(Month.FEBRUARY, 27), // D�a Independecia Nacional
	            MonthDay.of(Month.MAY, 1),   //D�a Trabajador
	            MonthDay.of(Month.AUGUST, 16), //D�a Restauraci�n
	            MonthDay.of(Month.SEPTEMBER, 24), //D�a de las Mercedes
	            MonthDay.of(Month.NOVEMBER, 6), //D�a Constituci�n
	            MonthDay.of(Month.DECEMBER, 25) // Christmas December 25***

	    );

	@Override
	public Boolean queryFrom(TemporalAccessor date) {

		MonthDay diaMesActual = MonthDay.from(date);

		return DIAS_FESTIVOS.contains(diaMesActual);
	}

}
