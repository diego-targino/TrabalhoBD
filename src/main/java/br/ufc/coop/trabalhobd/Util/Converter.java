package br.ufc.coop.trabalhobd.Util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {

	/**
	Convert a string value to a java.sql.Date 
	@param value String in format dd/MM/yyyy
	@return new java.sql.Date
	*/
	public static Date StringToDate(String value) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate localDate = LocalDate.parse(value, formatter);
    
    return Date.valueOf(localDate);
	}
}
