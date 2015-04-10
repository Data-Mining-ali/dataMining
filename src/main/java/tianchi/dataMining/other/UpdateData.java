package tianchi.dataMining.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateData {
	
	public void updataData(String src, String des) throws IOException{
		File readFile = new File(src);
		File outputs = new File(des);
		BufferedReader cin = new BufferedReader(new FileReader(readFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(outputs,true));
		String str = null;
		str = cin.readLine();
		out.write(str);
		out.newLine();
		while((str = cin.readLine())!=null){
			    StringBuilder sb = new StringBuilder(str);
			    sb.deleteCharAt(str.length()-1);
				if(str.charAt(str.length()-1) == '0'){
					sb.append("no");
				}else{
					sb.append("yes");
				}
				out.write(sb.toString());
				out.newLine();
				
		}
			cin.close();
		    out.close();
			
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		UpdateData a = new UpdateData();
		a.updataData("/home/tangxinye/september/python/rawdata/4.10/filter_testing.csv", "/home/tangxinye/september/python/rawdata/4.10/filter_testing1.csv");
		a.updataData("/home/tangxinye/september/python/rawdata/4.10/filter_validata.csv", "/home/tangxinye/september/python/rawdata/4.10/filter_validata1.csv");
		a.updataData("/home/tangxinye/september/python/rawdata/4.10/filter_19.csv", "/home/tangxinye/september/python/rawdata/4.10/filter_191.csv");

	}

}
