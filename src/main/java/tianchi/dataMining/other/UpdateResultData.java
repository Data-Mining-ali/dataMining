package tianchi.dataMining.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
			    	System.out.println(strSrc);
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


	/**
	 * 
	 * @param itemFileName  商品子集
	 * @param resultFileName 生成的结果集
	 * @param desFilename   分类为yes的结果和商品子集求交集
	 * @throws Exception
	 */
	public void generateResult(String resultFileName,String itemFileName, String desFilename) throws Exception{
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		File itemFile = new File(itemFileName);
		File tmpFile = new File(resultFileName);
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
		while((tmpResult = cinTmp.readLine())!=null && tmpResult.charAt(tmpResult.length()-1)=='s'){
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
		UpdateResultData a = new UpdateResultData();
		try {	
			//a.generateResult(args[0], args[1], args[2]);
			//a.updataData("E:/data/4.13/4.13/data/output/result.csv", "E:/data/result/filter_191.csv", "E:/data/4.13/4.13/data/output/resulttmp.csv");
			a.generateResult("E:/data/4.13/4.13/data/output/resulttmp.csv", "E:/data/result/tianchi_mobile_recommend_train_item.csv", "E:/data/4.13/4.13/data/output/resultLast.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
