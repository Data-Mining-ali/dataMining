package tianchi.dataMining.other;

import java.util.HashMap;
import java.util.Map;

import tianchi.dataMining.utility.Contants;
import tianchi.dataMining.utility.FileUtil;

public class Check {
	
	public void checkNum() {
		String itemPath = Contants.file+"old/tianchi_mobile_recommend_train_item.csv";
		Map<String, Boolean> itemMap = new HashMap<String,Boolean>();
		FileUtil itemCin = new FileUtil(itemPath, "in");
		String item = itemCin.readLine();
		while((item = itemCin.readLine())!=null){
			itemMap.put(item.split(",")[0], true);
		}
		itemCin.close();
		String path = Contants.file+"old/record.csv";
		FileUtil cin = new FileUtil(path, "in");
		String string = cin.readLine();
		int cnt=0;
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		while((string = cin.readLine())!=null){
			String tmp[] = string.split(",");
			if(tmp[2].equals("3")&&itemMap.containsKey(tmp[1])){
				int num =Integer.parseInt(tmp[5]);
				String idString = tmp[0]+","+tmp[1];
				if(num<=24 && !map.containsKey(idString)){
					map.put(idString, true);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		cin.close();
		
	}
	
	public void count(String fileName) {
		FileUtil cin =  new FileUtil(fileName, "in");
		String string = cin.readLine();
		System.out.println(string.split(",").length);
		cin.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//tianchi_mobile_recommend_train_item	
		Check a = new Check();
		String path = Contants.mac_file +"output/" +"train.csv";
		a.count(path);

	}
}
