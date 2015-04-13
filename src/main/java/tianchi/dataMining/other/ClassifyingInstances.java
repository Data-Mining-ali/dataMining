package tianchi.dataMining.other;

import weka.classifiers.meta.AdditiveRegression;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class ClassifyingInstances {
	
	public void classifyingInstancesRandomForest(String testDateFile,String unlabledDateFile,String resultFile) throws Exception{
		Instances data = DataSource.read(testDateFile);		
		data.setClassIndex(data.numAttributes() - 1);		

		//Logistic model = new Logistic(); // new instance of tree
		//model.setOptions(weka.core.Utils.splitOptions("-R 1.0 -M -1 -D"));
		
		RandomForest model = new RandomForest(); // new instance of tree
		//model.setOptions(weka.core.Utils.splitOptions("-R 1.0 -M -1 -D"));
		
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
		Instances labeled = new Instances(unlabeled);
		Instances  newunlabeled= Filter.useFilter(unlabeled, remove); // apply filter
		
		// label instances
		for (int i = 0; i < newunlabeled.numInstances(); i++) {
		  double clsLabel = model.classifyInstance(newunlabeled.instance(i));
		  labeled.instance(i).setClassValue(clsLabel);
		}
		// save newly labeled data
		DataSink.write(resultFile, labeled);
	}
	
	public void classifyingInstancesGBDT(String testDateFile,String unlabledDateFile,String resultFile) throws Exception{
		Instances data = DataSource.read(testDateFile);		
		data.setClassIndex(data.numAttributes() - 1);		
		
		AdditiveRegression model = new AdditiveRegression(); // new instance of tree
		//model.setOptions(weka.core.Utils.splitOptions("-R 1.0 -M -1 -D"));
		
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
		Instances labeled = new Instances(unlabeled);
		Instances  newunlabeled= Filter.useFilter(unlabeled, remove); // apply filter
		
		// label instances
		for (int i = 0; i < newunlabeled.numInstances(); i++) {
		  double clsLabel = model.classifyInstance(newunlabeled.instance(i));
		  labeled.instance(i).setClassValue(clsLabel);
		}
		// save newly labeled data
		DataSink.write(resultFile, labeled);
	}
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ClassifyingInstances aModel = new ClassifyingInstances();	
		aModel.classifyingInstancesRandomForest(args[0], args[1], args[2]);	

	}

}
