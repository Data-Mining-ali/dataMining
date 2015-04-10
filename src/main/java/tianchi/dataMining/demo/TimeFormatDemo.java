package tianchi.dataMining.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatDemo {

	private static String formateDateToISO8601(String s) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0;i<s.length();++i){
			if(i!=10) stringBuilder.append(s.charAt(i));
			else stringBuilder.append('T');
		}
		stringBuilder.append(":00:00");
		System.out.println(stringBuilder);
		return df.format(df.parse(stringBuilder.toString()));
		
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		//TimeZone tz = TimeZone.getTimeZone("UTC");
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    //df.setTimeZone(tz);
	    Date testDate = df.parse("2004-04-03T12:00:00");
	    String nowAsISO = df.format(testDate);
	    System.out.println(nowAsISO);
	    System.out.println(formateDateToISO8601("2014-11-26 20"));

	}

}
