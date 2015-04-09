package tianchi.dataMining.other;

import java.io.File;

import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;

//http://my.oschina.net/leopardsaga/blog/92740
public class TrainingModel {
	public void traingModel() throws Exception{
		Instances data = DataSource.read("/some/where/dataset.csv");
		String[] options = new String[1];

		options[0] = "-U"; // unpruned tree

		J48 tree = new J48(); // new instance of tree

		tree.setOptions(options); // set the options

		tree.buildClassifier(data); // build classifier
	}
	
	public void zengliangTraingModel() throws Exception{
		ArffLoader loader = new ArffLoader();

		loader.setFile(new File("/some/where/dataset.csv"));

		Instances structure = loader.getStructure();

		structure.setClassIndex(structure.numAttributes() - 1);

		// train NaiveBayes

		NaiveBayesUpdateable nb = new NaiveBayesUpdateable();

		nb.buildClassifier(structure);

		Instance current;

		while ((current = loader.getNextInstance(structure)) != null){
			nb.updateClassifier(current);
		}		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
