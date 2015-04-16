package tianchi.dataMining.data.entity;

public class UTPair {
	static public int Hours[] = {3,5,10,16,24,24*3,24*7};
	static public int Intervals[] = {3,7,16,24};
	static public int LasVsSum[] = {3,5,9,16,24,24*3};
	public int hours[][] = new int[Hours.length][4];
	public double lasVsSum[][] = new double[LasVsSum.length][4];
	public int intervals[] = new int[Intervals.length];
	public int lastAction[] = new int[4];
	public int lastDupAction[] = new int[4];
	public int isBuy;
	public int isDupBuy;
	public int hasActionAfterBuy;
	public double avgBuy;
	public int clas = 0;
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str = aa2s(hours)
		.append(a2s(intervals))
		.append(aa2s(lasVsSum))
		.append(a2s(lastAction))
		.append(a2s(lastDupAction))
		.append(isBuy).append(',')
		.append(isDupBuy).append(',')
		.append(hasActionAfterBuy).append(',')
		.append(avgBuy).append(',');
		return str.append(clas).toString();
	};

	private StringBuilder aa2s(int a[][]){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<a.length;i++){
			str.append(a2s(a[i]));
		}
		return str;
	}
	private StringBuilder a2s(int a[]){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<a.length;i++){
			str.append(a[i]).append(',');
		}
		return str;
	}
	private StringBuilder aa2s(double a[][]){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<a.length;i++){
			str.append(a2s(a[i]));
		}
		return str;
	}
	private StringBuilder a2s(double a[]){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<a.length;i++){
			str.append(a[i]).append(',');
		}
		return str;
	}
}
