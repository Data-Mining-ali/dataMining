package tianchi.dataMining.other;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

//http://my.oschina.net/leopardsaga/blog/92740
public class TrainingModel {
	class Result{
		double precision, recall,fMeasure;
		Result(double precision, double recall,double fMeasure){
			this.fMeasure = fMeasure;
			this.precision = precision;
			this.recall = recall;
		}
		public String toString(){
			return "precision:" + precision +"\n"
					+"recall:" + recall +"\n"
					+"fMeasure:" + fMeasure +"\n";
			
		}
	}
	
	
	public void traingRandomForest(String testDateFile,String validataDataFile,String evaluationFile) throws Exception{
		Instances data = DataSource.read(testDateFile);
		Instances validataData = DataSource.read(validataDataFile);
		data.setClassIndex(data.numAttributes() - 1);
		validataData.setClassIndex(validataData.numAttributes() - 1);

		RandomForest model = new RandomForest(); // new instance of tree
		
		String[] options = new String[2];
		options[0] = "-R"; // "range"
		options[1] = "1"; // first attribute
		Remove remove = new Remove(); // new instance of filter
		remove.setOptions(options); // set options
		remove.setInputFormat(data); // inform filter about dataset
		// **AFTER** setting options
		Instances newData = Filter.useFilter(data, remove); // apply filter
		Instances newValidataData = Filter.useFilter(validataData, remove);
		model.buildClassifier(newData); // build classifier
		
		Evaluation eval = new Evaluation(newData);
		eval.evaluateModel(model, newValidataData);
		String aString = eval.toSummaryString("\nresults\n\n", true);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(evaluationFile));
		oos.writeObject(aString);
		oos.flush();
		oos.close();
	}	
	
	public void classifyingInstances(String testDateFile,String unlabledDateFile,String resultFile) throws Exception{
		Instances data = DataSource.read(testDateFile);		
		data.setClassIndex(data.numAttributes() - 1);		

		Logistic model = new Logistic(); // new instance of tree
		
		String[] options = new String[2];
		options[0] = "-R"; // "range"
		options[1] = "1"; // first attribute
		Remove remove = new Remove(); // new instance of filter
		remove.setOptions(options); // set options
		remove.setInputFormat(data); // inform filter about dataset
		// **AFTER** setting options
		Instances newData = Filter.useFilter(data, remove); // apply filter
		
		model.buildClassifier(newData); // build classifier
		
		Instances unlabeled = DataSource.read(unlabledDateFile);
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
		// create copy
		Instances labeled = new Instances(unlabeled);
		// label instances
		for (int i = 0; i < unlabeled.numInstances(); i++) {
		  double clsLabel = model.classifyInstance(unlabeled.instance(i));
		  labeled.instance(i).setClassValue(clsLabel);
		}
		// save newly labeled data
		DataSink.write(resultFile, labeled);
	}
	
	public void traingLR(String testDateFile,String validataDataFile,String evaluationFile) throws Exception{
		Instances data = DataSource.read(testDateFile);
		Instances validataData = DataSource.read(validataDataFile);
		data.setClassIndex(data.numAttributes() - 1);
		validataData.setClassIndex(validataData.numAttributes() - 1);

		Logistic model = new Logistic(); // new instance of tree
		
		String[] options = new String[2];
		options[0] = "-R"; // "range"
		options[1] = "1"; // first attribute
		Remove remove = new Remove(); // new instance of filter
		remove.setOptions(options); // set options
		remove.setInputFormat(data); // inform filter about dataset
		// **AFTER** setting options
		Instances newData = Filter.useFilter(data, remove); // apply filter
		Instances newValidataData = Filter.useFilter(validataData, remove);
		model.buildClassifier(newData); // build classifier
		
		Evaluation eval = new Evaluation(newData);
		eval.evaluateModel(model, newValidataData);
		Result result = new Result(eval.precision(1), eval.recall(1),eval.fMeasure(1));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(evaluationFile));
		oos.writeObject(result.toString());
		oos.flush();
		oos.close();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TrainingModel aModel = new TrainingModel();
/*
		aModel.traingLR("D:/环境安装包/weka-3-6-12/data/breast-cancer.arff",
				"D:/环境安装包/weka-3-6-12/data/breast-cancer.arff",
				"E:/data/c.txt");
		
	/*
		aModel.classifyingInstances("/home/tangxinye/september/python/rawdata/4.10/filter_validata1.csv",
				"/home/tangxinye/september/python/rawdata/4.10/filter_191.csv",
				"/home/tangxinye/september/python/output/4.10/result.csv");
				*/

		aModel.traingLR("/home/tangxinye/september/python/rawdata/4.10/filter_testing1.csv",
				"/home/tangxinye/september/python/rawdata/4.10/filter_validata1.csv",
				"/home/tangxinye/september/python/output/4.10/LR.txt");

				

	}

}
