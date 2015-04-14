package tianchi.dataMining.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class UpdateData {

	public void updataData(String src, String des) throws IOException {
		File readFile = new File(src);
		File outputs = new File(des);
		BufferedReader cin = new BufferedReader(new FileReader(readFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(outputs, true));
		String str = null;
		str = cin.readLine();
		out.write(str);
		out.newLine();
		while ((str = cin.readLine()) != null) {
			StringBuilder sb = new StringBuilder(str);
			sb.deleteCharAt(str.length() - 1);
			if (str.charAt(str.length() - 1) == '0') {
				sb.append("no");
			} else {
				sb.append("yes");
			}
			out.write(sb.toString());
			out.newLine();

		}
		cin.close();
		out.close();
	}

	public void selectYesData(String src, String des) throws IOException {
		File readFile = new File(src);
		File outputs = new File(des);
		BufferedReader cin = new BufferedReader(new FileReader(readFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(outputs, true));
		String str = null;
		str = cin.readLine();
		out.write(str);
		out.newLine();
		while ((str = cin.readLine()) != null) {
			if (str.charAt(str.length() - 1) == 's') {
				out.write(str);
				out.newLine();
			}
		}
		cin.close();
		out.close();
	}

	public void removeDataAttribute(String src, String des) throws Exception {
		Instances data = DataSource.read(src);
		data.setClassIndex(data.numAttributes() - 1);

		String[] options = new String[2];
		options[0] = "-R"; // "range"
		options[1] = "1,2"; // first attribute
		Remove remove = new Remove(); // new instance of filter
		remove.setOptions(options); // set options
		remove.setInputFormat(data); // inform filter about dataset
		// **AFTER** setting options
		Instances newData = Filter.useFilter(data, remove); // apply filter

		DataSink.write(des, newData);

	}

	public void changeClassToNum(String srcFileName, String desFileName)
			throws Exception {
		File readFile = new File(srcFileName);
		File output = new File(desFileName);
		BufferedReader cin = new BufferedReader(new FileReader(readFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(output, true));
		String str = null;
		str = cin.readLine();
		out.write(str);
		out.newLine();
		while ((str = cin.readLine()) != null) {
			StringBuilder sb = new StringBuilder(str);
			sb.deleteCharAt(str.length() - 1);
			if (str.charAt(str.length() - 1) == 's') {
				sb.delete(str.length() - 3, str.length());
				sb.append("1");
			} else {
				sb.delete(str.length() - 2, str.length());
				sb.append("0");
			}
			out.write(sb.toString());
			out.newLine();

		}
		cin.close();
		out.close();
	}

	public void selectData(String itemFileName, String srcFileName,
			String desFileName) throws Exception {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		File itemFile = new File(itemFileName);
		BufferedReader cinItem = new BufferedReader(new FileReader(itemFile));
		String item = null;
		item = cinItem.readLine();
		while ((item = cinItem.readLine()) != null) {
			String[] tmp = item.split(",");
			map.put(tmp[0], true);
		}
		cinItem.close();

		File readFile = new File(srcFileName);
		File output = new File(desFileName);

		BufferedReader cin = new BufferedReader(new FileReader(readFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(output, false));
		String str = null;
		str = cin.readLine();
		out.write(str);
		out.newLine();
		while ((str = cin.readLine()) != null) {
			StringBuilder sb = new StringBuilder();
			String[] tmp = str.split(",");
			String[] idTmp = tmp[0].split("\\$");
			double lable = Double.parseDouble(tmp[tmp.length - 1]);
			if (map.containsKey(idTmp[1])) {
				for (int i = 0; i < tmp.length - 1; i++) {
					sb.append(tmp[i]).append(",");
				}
				sb.append(lable);
				out.write(sb.toString());
				out.newLine();
			}
		}
		cin.close();
		out.close();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		UpdateData a = new UpdateData();
		//a.selectYesData("E:\\data\\4.14\\data\\intersection_data17_clas181.csv", "E:\\data\\4.14\\data\\intersection_data17_clas181Yes.csv");
		/*
		a.updataData("E:\\data\\4.14\\data\\finalTrainData.csv",
				"E:\\data\\4.14\\data\\finalTrainData1.csv");
		a.updataData("E:\\data\\4.14\\data\\intersection_data17_clas18.csv",
				"E:\\data\\4.14\\data\\intersection_data17_clas181.csv");
		a.updataData("E:\\data\\4.14\\data\\intersection_data18_clas19.csv",
				"E:\\data\\4.14\\data\\intersection_data18_clas191.csv");
         */
		// a.updataData("E:/学习资料/数据挖掘比赛/数据/Ali-splitData/sameple_filter_testing.csv",
		// "E:/学习资料/数据挖掘比赛/数据/Ali-splitData/sameple_filter_testing1.csv");
		// a.updataData("E:/学习资料/数据挖掘比赛/数据/Ali-splitData/sameple_filter_validata.csv",
		// "E:/学习资料/数据挖掘比赛/数据/Ali-splitData/sameple_filter_validata1.csv");
		try {
			a.removeDataAttribute("E:\\data\\4.12\\learn\\credit-g.arff","E:\\data\\4.12\\learn\\credit-g-2.arff");
			// a.changeClassToNum("E:/DataMining/alibaba/data/4.13/sameple_50_filter_validata.csv",
			// "E:/DataMining/alibaba/data/4.13/sameple_50_filter_validata1.csv");
			// a.selectData("E:/DataMining/alibaba/tianchi_mobile_recommend_train_user.csv","E:/DataMining/alibaba/data/4.13/GBDT50.csv",
			// "E:/DataMining/alibaba/data/4.13/GBDT50SU.csv");
			// a.selectData("E:/DataMining/alibaba/tianchi_mobile_recommend_train_user.csv","E:/DataMining/alibaba/data/Ali-splitData/filter_19.csv",
			// "E:/DataMining/alibaba/data/4.13/GBDT50SU11.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
