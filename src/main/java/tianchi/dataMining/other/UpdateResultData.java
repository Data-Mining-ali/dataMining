package tianchi.dataMining.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UpdateResultData {
	
	public void updataData(String resultFileName, String srcFileName,String desFileName) throws IOException{
		File readFile = new File(resultFileName);
		File srcFile = new File(srcFileName);
		File output = new File(desFileName);
		BufferedReader cinResult = new BufferedReader(new FileReader(readFile));
		BufferedReader cinSrc = new BufferedReader(new FileReader(srcFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(output,true));
		String strSrc = null,strResult =null;
		strSrc = cinSrc.readLine();
		strResult = cinResult.readLine();
		out.write(strSrc);
		out.newLine();
		while((strResult = cinResult.readLine())!=null){
			    strSrc = cinSrc.readLine();
			    if(strResult.charAt(strResult.length()-1)=='s'){
			    	StringBuilder sb = new StringBuilder();
			    	String[] tmp = strSrc.split(",");
			    	sb.append(tmp[0]).append(",");
			    	sb.append(strResult);
			    	out.write(sb.toString());
					out.newLine();
			    }
				
		}
		cinResult.close();
		cinSrc.close();
		out.close();			
	}
	
	public void generateResult(String itemFileName, String tmpFileName,String desFilename) throws Exception{
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		File itemFile = new File(itemFileName);
		File tmpFile = new File(tmpFileName);
		File result = new File(desFilename);
		BufferedReader cinItem = new BufferedReader(new FileReader(itemFile));
		BufferedReader cinTmp = new BufferedReader(new FileReader(tmpFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(result,false));
		String item =null, tmpResult = null;
		item = cinItem.readLine();
		while((item = cinItem.readLine())!=null){
			String[] tmp = item.split(",");
			map.put(tmp[0], true);
		}
		cinItem.close();
		tmpResult = cinTmp.readLine();
		while((tmpResult = cinTmp.readLine())!=null){
			String[] tmp = tmpResult.split(",");
			String[] idTmp = tmp[0].split("\\$");
			if(map.containsKey(idTmp[1])){
				String a = idTmp[0]+"," + idTmp[1];	
				out.write(a);
				out.newLine();
			}
		}
		cinTmp.close();
		out.close();		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "51870430$330202665";
		System.out.println(str.split("\\$")[0]);
		UpdateResultData a = new UpdateResultData();
		try {
			//a.updataData("E:/data/result/result.csv", "E:/data/result/filter_19.csv", "E:/data/result/tmp.csv");
			a.generateResult("E:/data/result/tianchi_mobile_recommend_train_item.csv", "E:/data/result/tmp.csv", "E:/data/result/lastResult.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
