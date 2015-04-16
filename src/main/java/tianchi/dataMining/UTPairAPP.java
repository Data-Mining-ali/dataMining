package tianchi.dataMining;

import java.util.Date;

import tianchi.dataMining.data.processing.GenerateUTP;
import tianchi.dataMining.utility.TimeTool;

public class UTPairAPP {
	/**
	 * [0,T-1] 作为统计
	 * T 数据作为clas
	 * @param T
	 */
	public static void generateN(int T){
		int hours = (18-T+1) * 24;
		String beginStr = "2014-12-"+(T-1)+" 24";
		String endStr = "2014-12-"+T+" 24";
		Date begin = TimeTool.getTime(beginStr);
		Date end = TimeTool.getTime(endStr);
		String f1 = "data"+(T-1)+"_clas"+(T)+".csv";
		GenerateUTP g = new GenerateUTP(begin, end, hours, f1);
		g.work();
	}
	public static void main(String[] args) {
		long pre = new Date().getTime();
		generateN(18);
		System.out.println("use time:"+(new Date().getTime() - pre)/1000);
	}
}
