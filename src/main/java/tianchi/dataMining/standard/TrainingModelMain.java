package tianchi.dataMining.standard;

import tianchi.dataMining.utility.Contants;
import tianchi.dataMining.utility.FileUtil;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class TrainingModelMain {
	
	private void outputEvaluation(String evaluationFile, Evaluation eval) throws Exception {
		FileUtil out = new FileUtil(evaluationFile, "out");
		String ans =eval.toMatrixString("Matrix:");
		out.writeLine(ans);
		out.writeLine("");
		
		out.writeLine("index 0 class：");	
		out.writeLine("precision:"+ eval.precision(0));
		out.writeLine("recall:"+ eval.recall(0));
		out.writeLine("fMeasure:"+ eval.fMeasure(0));
		out.writeLine("");
				
		out.writeLine("index 1 class：");
		out.writeLine("precision:"+ eval.precision(1));
		out.writeLine("recall:"+ eval.recall(1));
		out.writeLine("fMeasure:"+ eval.fMeasure(1));

		out.close();
	}
	
	public void traingRandomForest(String testDateFile,String validataDataFile,String evaluationFile) throws Exception{
		String[] options = new String[2];
		options[0] = "-R"; // "range"
		options[1] = "1,2"; // first attribute
		Remove remove = new Remove(); // new instance of filter
		remove.setOptions(options); // set options		
		
		Instances data = DataSource.read(testDateFile);
		data.setClassIndex(data.numAttributes() - 1);
		remove.setInputFormat(data);  // inform filter about dataset
		Instances newData = Filter.useFilter(data, remove); // apply filter
		
		Instances validataData = DataSource.read(validataDataFile);
		validataData.setClassIndex(validataData.numAttributes() - 1);
		Instances newValidataData = Filter.useFilter(validataData, remove);

		RandomForest model = new RandomForest(); // new instance of tree
		//model.setOptions(Utils.splitOptions("-I 30"));
		model.buildClassifier(newData); // build classifier
		
		Evaluation eval = new Evaluation(newData);
		eval.evaluateModel(model, newValidataData);
		outputEvaluation(evaluationFile,eval);    
	}
	
	public static void main(String[] args) throws Exception {
		/*
		TrainingModelMain a = new TrainingModelMain();
		try {
			a.traingRandomForest(args[0], args[1], args[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		for(int k=1;k<7;k++){
			TrainingModelMain aModel = new TrainingModelMain();
			String path = Contants.write_filepath;
			String name = "train";
			if(k>1) name = name +k+".arff";
			else name = name +".arff";
			String files[]={name,"validata.arff","result_"+k+".txt"};
			for(int i=0;i<files.length;i++) files[i] = path+files[i];
			aModel.traingRandomForest(files[0],files[1],files[2]);
		}
	}

}
