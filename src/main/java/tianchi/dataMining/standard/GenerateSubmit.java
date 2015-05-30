package tianchi.dataMining.standard;

import java.util.Collections;
import java.util.LinkedList;

import tianchi.dataMining.data.entity.GBDTItem;
import tianchi.dataMining.utility.FileUtil;


public class GenerateSubmit {
	/**
	 * 
	 * @param source filename
	 * @param path filepath
	 */
	public void randomForest(String source,String path){
		FileUtil in = new FileUtil(path+source, "in");
		FileUtil out = new FileUtil(path+"submit_"+source,"out");
		String str;
		int cnt = 0;
		int correct = 0;
		int incorrect = 0;
		out.writeLine("user_id,item_id");
		while((str = in.readLine())!=null){
			if(cnt ++ == 0){
				continue;
			}
			String ls[] = str.split(",");
			int clas = Integer.parseInt(ls[ls.length-1]);
			if(clas == 1) {
				correct ++;
				out.writeLine(ls[0]+","+ls[1]);
			}
			else incorrect++;
		}
		in.close();
		out.close();
		System.out.println("read1 finish..");
		System.out.println("correct count:"+correct);
		System.out.println("incorrect count:"+incorrect);
	
	}
	
	
	public void gbdt(String source,String path,int num){
		FileUtil cin = new FileUtil(path+source, "in");
		FileUtil out = new FileUtil(path+"submit_"+source,"out");
		LinkedList<GBDTItem> list = new LinkedList<GBDTItem>();		
		String string = null;
		string = cin.readLine();
		while((string = cin.readLine())!=null){
			list.add(new GBDTItem(string));
		}
		Collections.sort(list);
		out.writeLine("user_id,item_id");
		for(int i=0;i<num;i++){
			GBDTItem r = list.get(i);
			System.out.println(r.id +"   "+  r.clas);
			out.writeLine(r.id);
		}
		cin.close();
		out.close();	
	}

}
