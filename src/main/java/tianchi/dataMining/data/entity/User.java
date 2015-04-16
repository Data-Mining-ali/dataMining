package tianchi.dataMining.data.entity;

public class User {
	static public int SlideWins[] ={1,3,5,7};
	public double avgBuy;
	public double slideWin[] = new double [SlideWins.length];
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str = a2s(slideWin)
		.append(avgBuy);
		return str.toString();
	}
	@SuppressWarnings("unused")
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
	
	@SuppressWarnings("unused")
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
