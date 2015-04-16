package tianchi.dataMining.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTool {
	/**
	 * 输入时间字符串，返回Date
	 * @param str 例如:2014-11-26 20
	 * @return 时间Date
	 */
	public static Date getTime(String str){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
		try {
			return format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 计算日期天数差
	 * @param s 
	 * @param t
	 * @return 天数差值
	 */
	public static int dif(Date s,Date t){
		long diff = Math.abs(t.getTime() - s.getTime());
		return (int) (diff / 1000/60/60/24);
		
	}
	public static void main(String[] args) {
		Date d = new Date();
		Date dd = getTime("2015-03-26 20");
		
		System.out.println(dif(dd,d));
	}
}
