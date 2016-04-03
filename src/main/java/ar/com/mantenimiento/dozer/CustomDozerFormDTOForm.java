package ar.com.mantenimiento.dozer;

import java.util.Date;

import org.dozer.CustomConverter;

import ar.com.mantenimiento.utility.FechaUtility;

public class CustomDozerFormDTOForm implements CustomConverter{

	
	
	@Override
	public Object convert(Object newDate, Object date, Class<?> arg2, Class<?> arg3) {
		// TODO Auto-generated method stub
//		Date date = (Date) Form;
//		FechaUtility.deStringToDate(fecha)
//		
//		System.out.println(date.toString());
		System.out.println("Custom dozer mapper: arreglo fecha");
		Date deStringToDate = FechaUtility.deStringToDate(String.valueOf(date));
		
		
		return deStringToDate;
	}

}
