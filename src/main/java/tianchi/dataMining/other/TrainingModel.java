package tianchi.dataMining.other;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
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
		File outputs = new File(evaluationFile);
		BufferedWriter out = new BufferedWriter(new FileWriter(outputs,false));
		String ans =eval.toMatrixString("result:");
		out.write(ans);
		out.newLine();
		out.write("0：");out.newLine();	
		out.write("precision:"+ eval.precision(0));
		out.newLine();
		out.write("recall:"+ eval.recall(0));
		out.newLine();
		out.write("fMeasure:"+ eval.fMeasure(0));
		out.newLine();
				
		out.write("1：");out.newLine();
		out.write("precision:"+ eval.precision(1));
		out.newLine();
		out.write("recall:"+ eval.recall(1));
		out.newLine();
		out.write("fMeasure:"+ eval.fMeasure(1));
		out.newLine();
		out.close();
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
		File outputs = new File(evaluationFile);
		BufferedWriter out = new BufferedWriter(new FileWriter(outputs,false));
		String ans =eval.toMatrixString("result:");
		out.write(ans);
		out.newLine();
		out.write("0：");out.newLine();	
		out.write("precision:"+ eval.precision(0));
		out.newLine();
		out.write("recall:"+ eval.recall(0));
		out.newLine();
		out.write("fMeasure:"+ eval.fMeasure(0));
		out.newLine();
				
		out.write("1：");out.newLine();
		out.write("precision:"+ eval.precision(1));
		out.newLine();
		out.write("recall:"+ eval.recall(1));
		out.newLine();
		out.write("fMeasure:"+ eval.fMeasure(1));
		out.newLine();
		out.close();
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
/*
		aModel.traingLR("/home/tangxinye/september/python/rawdata/4.10/filter_testing1.csv",
				"/home/tangxinye/september/python/rawdata/4.10/filter_validata1.csv",
				"/home/tangxinye/september/python/output/4.10/LR.txt");
*/
		System.out.println(args[2]);
		aModel.traingLR(args[0],args[1],args[2]);	

	}

}
