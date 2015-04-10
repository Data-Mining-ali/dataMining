package tianchi.dataMining.other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesUpdateable;

import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;

import weka.core.converters.CSVLoader;

import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;

//http://my.oschina.net/leopardsaga/blog/92740
public class TrainingModel {
	
	
	public void traingModel() throws Exception{
		Instances data = DataSource.read("/home/tangxinye/september/python/rawdata/4.10/filter_testing.csv");
		Instances validataData = DataSource.read("/home/tangxinye/september/python/rawdata/4.10/filter_validata.csv");
		data.setClassIndex(data.numAttributes() - 1);
		validataData.setClassIndex(validataData.numAttributes() - 1);

		RandomForest model = new RandomForest(); // new instance of tree
		/*
		String[] options = new String[1];
		options[0] = "-U"; // unpruned tree
		model.setOptions(options); // set the options
		*/
		
		model.buildClassifier(data); // build classifier
		
		Evaluation eval = new Evaluation(data);
		eval.evaluateModel(model, validataData);
		String aString = eval.toSummaryString("\nresults\n\n", true);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"/home/tangxinye/september/python/output/4.10/a.txt"));
		oos.writeObject(aString);
		oos.flush();
		oos.close();
		/*
		System.out.println(eval.toSummaryString("\nResults\n\n", false));
	
		System.out.println(model.toString());
		*/
	}	

	public void zengliangTraingModel() throws Exception{
		//Instances validataData = DataSource.read("E:/DataMining/alibaba/data/Ali-splitData/filter_testing.csv");
		//validataData.setClassIndex(validataData.numAttributes() - 1);

		CSVLoader loader = new CSVLoader();

		loader.setFile(new File("E:/DataMining/alibaba/data/Ali-splitData/filter_validata.csv"));

		Instances structure = loader.getStructure();

		structure.setClassIndex(structure.numAttributes() - 1);

		// train NaiveBayes

		NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
		String[] options = new String[2];  
		  
	    options[0] = "-R";                             // "range"  
	  
	    options[1] = "1";                              // first attribute  
	  
	    Remove remove = new Remove();                 // new instance of filter  
	  
	    remove.setOptions(options);  
	    remove.setInputFormat(structure); 

		nb.buildClassifier(structure);

		Instance current;

		while ((current = loader.getNextInstance(structure)) != null){
			nb.updateClassifier(current);
		}
		System.out.println(nb.toString());
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TrainingModel aModel = new TrainingModel();
		aModel.traingModel();

	}

}
