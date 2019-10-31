package de.cristiano.marathon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paragraph {

	private static SimpleDateFormat newDateFormatter = new SimpleDateFormat("DD/MM/YYYY");

	private static SimpleDateFormat oldDateFormatter = new SimpleDateFormat("MM/DD/YYYY");

	public static String changeDateFormat(String paragraph) {
		List<String> dates = findDates(paragraph);
		List<String> newDates = changeDatesFormat(dates);

		return replaceDates(paragraph, dates, newDates);
	}

	private static String replaceDates(String paragraph, List<String> oldDates, List<String> newDates) {
		String newParagraph = null;
		for (int i = 0; i < newDates.size(); i++) {
			String oldDate = oldDates.get(i);
			String newDate = newDates.get(i);
			newParagraph = paragraph.replaceAll(oldDate, newDate);

		}
		return newParagraph;
	}

	private static List<String> changeDatesFormat(List<String> dates) {
		List<String> newDates = new ArrayList<>();
		for (Iterator<String> oldDate = dates.iterator(); oldDate.hasNext();) {
			newDates.add(formatDate(oldDate.next()));
		}
		return newDates;
	}

	private static String formatDate(String date) {
		if (date == null) {
			return null;
		}
		try {
			return newDateFormatter.format(oldDateFormatter.parse(date));
		} catch (ParseException e) {
			System.out.println("Error while converting:" + date);
			return null;
		}

	}

	private static List<String> findDates(String paragraph) {
		List<String> dates = new ArrayList<>();
		Pattern pattern = Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{4})");
		Matcher matcher = pattern.matcher(paragraph);
		while (matcher.find()) {
			dates.add(matcher.group());
		}
		return dates;
	}

	public static void main(String[] args) {
		System.out.println(changeDateFormat("Last time it rained was on 07/25/2013 and today is 08/09/2013."));
	}
}
