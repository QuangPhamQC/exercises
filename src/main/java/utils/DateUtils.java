package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String getTimeStamp() {
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}

	public static String getRandomTextNumber() {
		Date date = new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
	}

	public static String getLocalDate() {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
		return dateFormat.format(currentDate);
	}

	public static String getPreviousLocalDate(int day) {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
		return dateFormat.format(currentDate.minusDays(day));
	}

	public static String getFullDateFormat() {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
		return dateFormat.format(currentDate);
	}

	public static String getRandomNumber() {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMddyyyyhhmmss");
		return dateFormat.format(currentDate);
	}
	
	public static String getCurrentDate() {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dateFormat.format(currentDate);
	}

	public static String getCurrentDayOfWeek() {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE");
		return dateFormat.format(currentDate);
	}
	
	public static String plusDayOfCurrentDay(int number) {
		LocalDateTime targetDate = LocalDateTime.now().plusDays(number);
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return dateFormat.format(targetDate);
	}

	public static String getCurrentMonth() {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM");
		return dateFormat.format(currentDate);
	}

	public static String getCurrentHour() {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH");
		return dateFormat.format(currentDate);
	}

	public static String getCurrentMinute() {
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("mm");
		return dateFormat.format(currentDate);
	}

	public static String getMonthFromTimeStamp(long timeStamp) {
		Date date = new Date(timeStamp);
		DateFormat dateFormat = new SimpleDateFormat("MMM");
		return dateFormat.format(date);
	}

	public static String getYearFromTimeStamp(long timeStamp) {
		Date date = new Date(timeStamp);
		DateFormat dateFormat = new SimpleDateFormat("YYYY");
		return dateFormat.format(date);
	}

	public static long convertDateToTimeStamp(int dateOfMonth, int month, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, dateOfMonth);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.YEAR, year);

		cal.set(Calendar.HOUR_OF_DAY, 7);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date date = cal.getTime();
		return date.getTime();

	}
}
