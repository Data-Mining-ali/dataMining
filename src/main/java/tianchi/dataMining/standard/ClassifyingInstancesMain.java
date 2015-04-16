package tianchi.dataMining.standard;

import tianchi.dataMining.utility.FileUtil;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class ClassifyingInstancesMain {
	public void classifyingInstancesRandomForest(String testDateFile,String unlabledDateFile,String resultFile) throws Exception{
		System.out.println("testDateFile:"+testDateFile);
		System.out.println("unlabledDateFile:"+unlabledDateFile);
		System.out.println("resultFile:"+resultFile);
		
		Instances data = DataSource.read(testDateFile);		
		data.setClassIndex(data.numAttributes() - 1);						
		
		String[] options = new String[2];
		options[0] = "-R"; // "range"
		options[1] = "1,2"; // first attribute
		Remove remove = new Remove(); // new instance of filter
		remove.setOptions(options); // set options
		remove.setInputFormat(data); // inform filter about dataset
		// **AFTER** setting options
		Instances newData = Filter.useFilter(data, remove); // apply filter
		
		Classifier model = new RandomForest(); // new instance of tree
		model.buildClassifier(newData); // build classifier
		//model.setOptions(Utils.splitOptions("-I 30"));
		
		Instances unlabeled = DataSource.read(unlabledDateFile);
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
		Instances labeled = new Instances(unlabeled);
		Instances  newunlabeled= Filter.useFilter(unlabeled, remove); // apply filter
		
		// label instances
		for (int i = 0; i < newunlabeled.numInstances(); i++) {
		  double clsLabel = model.classifyInstance(newunlabeled.instance(i));
		  labeled.instance(i).setClassValue(clsLabel);
		}
		//save newly labeled data
		DataSink.write(resultFile, labeled);	
	}
	
	public void getResult(String resultFile,String resultFileLast) {
		FileUtil cin = new FileUtil(resultFile, "in");
		FileUtil out = new FileUtil(resultFileLast, "out");
		String cinString = null;
		cinString = cin.readLine();	
		out.writeLine("user_id,item_id");
		while((cinString=cin.readLine())!=null){
			if(cinString.charAt(cinString.length()-1)=='1'){
				String[] tmpStrings = cinString.split(",");
				out.writeLine(tmpStrings[0]+","+tmpStrings[1]);
			}
		}
		cin.close();
		out.close();
	}
	/**
	 * 
	 * @param args 依次是：训练集文件位置， 测试集文件位置， 结果集位置， 结果提交文件位置
	 */
	public static void main(String[] args) {
		ClassifyingInstancesMain a = new ClassifyingInstancesMain();
		try {
			a.classifyingInstancesRandomForest(args[0], args[1], args[2]);
			a.getResult(args[2],args[3]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
