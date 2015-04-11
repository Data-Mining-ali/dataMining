package tianchi.dataMining.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

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
	public void removeDataAttribute(String src, String des) throws Exception{
		Instances data = DataSource.read(src);		
		data.setClassIndex(data.numAttributes() - 1);		
		
		String[] options = new String[2];
		options[0] = "-R"; // "range"
		options[1] = "1"; // first attribute
		Remove remove = new Remove(); // new instance of filter
		remove.setOptions(options); // set options
		remove.setInputFormat(data); // inform filter about dataset
		// **AFTER** setting options
		Instances newData = Filter.useFilter(data, remove); // apply filter		
		
		DataSink.write(des, newData);
			
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		UpdateData a = new UpdateData();

		a.updataData("E:/学习资料/数据挖掘比赛/数据/Ali-splitData/sameple_filter_testing.csv", "E:/学习资料/数据挖掘比赛/数据/Ali-splitData/sameple_filter_testing1.csv");
		a.updataData("E:/学习资料/数据挖掘比赛/数据/Ali-splitData/sameple_filter_validata.csv", "E:/学习资料/数据挖掘比赛/数据/Ali-splitData/sameple_filter_validata1.csv");
		//a.updataData("/home/tangxinye/september/python/rawdata/4.10/filter_19.csv", "/home/tangxinye/september/python/rawdata/4.10/filter_191.csv");

		/*
		try {
			a.removeDataAttribute("E:/data/text.csv", "E:/data/filter_testingOthers.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

	}

}
