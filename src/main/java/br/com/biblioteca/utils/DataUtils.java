package br.com.biblioteca.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {

	public static String converterLocalDateParaString(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = localDate.format(formatter);
		return dataFormatada;
	}

	public static String converterLocalDateTimeParaStringDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String dataFormatada = localDateTime.format(formatter);
		return dataFormatada;
	}

	public static String converterLocalDateTimeParaStringDate(LocalDateTime localDateTime) {

		if (localDateTime != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataFormatada = localDateTime.format(formatter);
			return dataFormatada;
		}
		return "";
	}

	/**
	 * Converter String (dd/mm/aaaa) para LocalDate
	 * 
	 * @param dataString
	 * @return {@link LocalDate}
	 */
	public static LocalDate converterStringParaLocalDate(String dataString) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

		LocalDate localDate = LocalDate.parse(dataString, formatter);

		return localDate;
	}

	/**
	 * Converter String (aaaa-mm-dd hh:mm) para LocalDataTime
	 * 
	 * @param dataString
	 * @return {@link LocalDateTime}
	 */
	public static LocalDateTime converterStringParaLocalDateTime(String dataString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dataString, formatter);
	}
	
	public static void main(String[] args) {

		// Obtém LocalDate de hoje
//		LocalDate hoje = LocalDate.now();
//
//		System.out.println("LocalDate antes de formatar: " + hoje);
//
//		System.out.println("LocalDate depois de formatar: " + converterLocalDateParaString(hoje));
//
//		// Obtém LocalDateTime de agora
//		LocalDateTime agora = LocalDateTime.now();
//
//		System.out.println("LocalDateTime antes de formatar: " + agora);
//
//		System.out.println("LocalDateTime depois de formatar: " + converterLocalDateTimeParaStringDateTime(agora));

		String date = "2016-08-16 00:00";

		System.out.println("LocalDateTime depois de formatar: " + converterStringParaLocalDateTime(date));

	}
}
