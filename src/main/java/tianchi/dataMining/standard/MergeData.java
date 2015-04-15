package tianchi.dataMining.standard;

import java.util.HashMap;
import java.util.Map;

import tianchi.dataMining.utility.FileUtil;

public class MergeData {
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
		String path = "E:\\data\\4.15\\result\\last\\";
		String[] filesName = new String[4];
		filesName[0] = path+ "2last.csv";
		filesName[1] = path+ "3last.csv";
		filesName[2] = path+ "4last.csv";
		filesName[3] = path+ "5last.csv";
		String desName = path + "merge.csv";
		MergeData.mergeData(filesName, desName);
		

	}

}
