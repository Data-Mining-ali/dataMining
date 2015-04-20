package tianchi.dataMining.standard;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import tianchi.dataMining.utility.Contants;
import tianchi.dataMining.utility.FileUtil;

public class MergeData {
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
	
	public  void sortData(String fileName, String desName){
		PriorityQueue<RecordItem> q = new PriorityQueue<RecordItem>(140000,
				new Comparator<RecordItem>() {
					public int compare(RecordItem a, RecordItem b) {
						if (a.clas < b.clas)
							return 1;
						else if(a.clas == b.clas)
							return 0;
						else 
							return -1;
					}
		});
		FileUtil cin = new FileUtil(fileName,"in");
		String string = null;
		string = cin.readLine();
		while((string = cin.readLine())!=null){
			q.add(new RecordItem(string));
		}
		FileUtil out = new FileUtil(desName, "out");
		while(!q.isEmpty()){
			RecordItem r = q.poll();
			out.writeLine(r.s+r.clas);
		}
		cin.close();
		out.close();		
	}
	
	public void getResult(String fileName,String desName){
		FileUtil in = new FileUtil(fileName, "in");
		FileUtil out = new FileUtil(desName, "out");
		String str;
		out.writeLine("user_id,item_id");
		int i=0;
		while(i++<500){
			str = in.readLine();	
			String ls[] = str.split(",");
			out.writeLine(ls[0]+","+ls[1]);		
		}
		in.close();
		out.close();
	}
	
	public static void mergeData(String[] filesName,String desName){
		Map<String,Integer> map =new HashMap<String,Integer>();
		for(int i=0;i<filesName.length;i++){
			FileUtil cin = new FileUtil(filesName[i],"in");
			String string = null;
			string = cin.readLine();
			while((string = cin.readLine())!=null){
				if(map.containsKey(string)){
					map.put(string, map.get(string) + 1);
				}else{
					map.put(string,1);
				}
			}
		}
		FileUtil out = new FileUtil(desName, "out");
		for(int i = filesName.length; i>0; i--){
			for (Map.Entry<String, Integer> entry : map.entrySet()){
			    if(entry.getValue()==i){
			    	out.writeLine(entry.getKey());
			    }
			}
		}
		out.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeData aModel = new MergeData();
		String path = Contants.mac_file +"output/" +"submit-data/";
		String name[] = new String[2];
		name[0] = "result_5.csv";
		//name[1] = "result_5_17.csv";
		for(int i=0;i<1;i++){
			aModel.sortData(path+name[i], path+"sort_"+i+".csv");
		}
		aModel.getResult(path+"sort_"+0+".csv", path+"sort_"+0+"_Last.csv");
		
	}

}
