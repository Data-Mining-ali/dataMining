package tianchi.dataMining.standard;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import tianchi.dataMining.data.entity.GBDTItem;
import tianchi.dataMining.utility.FileUtil;

public class EvaluationGBDT {
	Map<String, Boolean> map ;
	public LinkedList<GBDTItem > list;
	int totleRightNumber;
	
	public EvaluationGBDT(String filename){
		this.map = new HashMap<String,Boolean>();
		FileUtil cin = new FileUtil(filename, "in");
		String str = null;
		str=cin.readLine();
		while((str=cin.readLine())!=null){
			String[] tmpStrings = str.split(",");
			if (Integer.parseInt(tmpStrings[tmpStrings.length-1])==1) {
				map.put(tmpStrings[0] + "," + tmpStrings[1], true);
			}
		}
		totleRightNumber = map.size();		
	}
	
	public void sortData(String fileName) {
		list = new LinkedList<GBDTItem>();
		FileUtil cin = new FileUtil(fileName,"in");
		String string = null;
		string = cin.readLine();
		while((string = cin.readLine())!=null){
			list.add(new GBDTItem(string));
		}
		Collections.sort(list);
		cin.close();		
	}
	
	public  void evaluate(int number){
		int rightNumber=0;;
		for(int i=0;i<number;i++){
			GBDTItem item = list.get(i);
			if(map.containsKey(item.id)){
				rightNumber++;
			}
		}
		double p = rightNumber*1.0/number;
		double r = rightNumber*1.0/totleRightNumber;
		double f = 2.0*p*r/(p+r);
		System.out.println("p:" + p);
		System.out.println("r:" + r);
		System.out.println("f:" + f);
		
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

}
