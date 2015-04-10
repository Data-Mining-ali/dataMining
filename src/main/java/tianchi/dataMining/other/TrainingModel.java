package tianchi.dataMining.other;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

//http://my.oschina.net/leopardsaga/blog/92740
public class TrainingModel {
	
	
	public void traingModel(String testDateFile,String validataDataFile) throws Exception{
		Instances data = DataSource.read("/home/tangxinye/september/python/rawdata/4.10/filter_testing1.csv");
		Instances validataData = DataSource.read("/home/tangxinye/september/python/rawdata/4.10/filter_validata1.csv");
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
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"/home/tangxinye/september/python/output/4.10/a.txt"));
		oos.writeObject(aString);
		oos.flush();
		oos.close();
		Instances unlabeled = DataSource.read("/some/where/unlabeled.arff");
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
		// create copy
		Instances labeled = new Instances(unlabeled);
		// label instances
		for (int i = 0; i < unlabeled.numInstances(); i++) {
		  double clsLabel = model.classifyInstance(unlabeled.instance(i));
		  labeled.instance(i).setClassValue(clsLabel);
		}
		// save newly labeled data
		DataSink.write("/home/tangxinye/september/python/output/4.10/result.csv", labeled);
	}	
	
	public void classifyingInstances(String testDateFile,String unlabledDateFile,String resultFile) throws Exception{
		Instances data = DataSource.read(testDateFile);		
		data.setClassIndex(data.numAttributes() - 1);		

		RandomForest model = new RandomForest(); // new instance of tree
		
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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TrainingModel aModel = new TrainingModel();
		aModel.traingModel("/home/tangxinye/september/python/rawdata/4.10/filter_testing1.csv",
				"/home/tangxinye/september/python/rawdata/4.10/filter_validata1.csv");
		aModel.classifyingInstances("/home/tangxinye/september/python/rawdata/4.10/filter_validata1.csv",
				"/home/tangxinye/september/python/rawdata/4.10/filter_191.csv",
				"/home/tangxinye/september/python/output/4.10/result.csv");

	}

}
