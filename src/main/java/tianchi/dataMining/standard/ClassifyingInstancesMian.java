package tianchi.dataMining.standard;

import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class ClassifyingInstancesMian {
	public void classifyingInstancesRandomForest(String testDateFile,String unlabledDateFile,String resultFile) throws Exception{
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
		
		RandomForest model = new RandomForest(); // new instance of tree
		model.buildClassifier(newData); // build classifier
		
		Instances unlabeled = DataSource.read(unlabledDateFile);
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
		Instances labeled = new Instances(unlabeled);
		Instances  newunlabeled= Filter.useFilter(unlabeled, remove); // apply filter
		
		// label instances
		for (int i = 0; i < newunlabeled.numInstances(); i++) {
		  double clsLabel = model.classifyInstance(newunlabeled.instance(i));
		  labeled.instance(i).setClassValue(clsLabel);
		  System.out.println(labeled.instance(i));
		}
		// save newly labeled data
		DataSink.write(resultFile, labeled);
	}
	
	public static void main(String[] args) {
		ClassifyingInstancesMian a = new ClassifyingInstancesMian();
		try {
			a.classifyingInstancesRandomForest(args[0], args[1], args[1]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
