package tianchi.dataMining.data.entity;

public class GBDTItem implements Comparable<GBDTItem>{
	public String id;
	public String feature;
	public double clas;
	public GBDTItem(String str){
		String[] tmp = str.split(",");
		this.clas = Double.parseDouble(tmp[tmp.length-1]);
		StringBuilder a = new StringBuilder();
		this.id= tmp[0]+","+tmp[1];
		for(int i=2;i<tmp.length-1;i++)
			a.append(tmp[i]).append(",");
		this.feature= a.toString();
	}

	@Override
	public int compareTo(GBDTItem o) {
		// TODO Auto-generated method stub
		if (this.clas < o.clas)
			return 1;
		else if(this.clas == o.clas)
			return 0;
		else 
			return -1;
	}
	
	class RecordItem{
		String s;
		double clas;
		public RecordItem(String str){
			String[] tmp = str.split(",");
			this.clas = Double.parseDouble(tmp[tmp.length-1]);
			StringBuilder a = new StringBuilder();
			for(int i=0;i<tmp.length-1;i++)
				a.append(tmp[i]).append(",");
			this.s = a.toString();
		}		
	}

}
