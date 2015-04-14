package tianchi.dataMining.other;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

//http://my.oschina.net/leopardsaga/blog/92740
public class TrainingModelLearn {
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
	
	
	
	public void traingJ48(String testDateFile,String validataDataFile,String evaluationFile) throws Exception{
		Instances data = DataSource.read(testDateFile);
		Instances validataData = DataSource.read(validataDataFile);
		data.setClassIndex(data.numAttributes() - 1);
		validataData.setClassIndex(validataData.numAttributes() - 1);


		J48 model = new J48(); // new instance of tree
		//model.setOptions(options);
		
		model.buildClassifier(data); // build classifier		
		
		//Evaluation.evaluateModel(model, options);
		Evaluation eval = new Evaluation(data);
		eval.evaluateModel(model, validataData);
		String ans =eval.toMatrixString("result:");
		System.out.println(ans);
		System.out.println(eval.fMeasure(0));
		System.out.println(eval.fMeasure(1));
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TrainingModelLearn aModel = new TrainingModelLearn();

		aModel.traingJ48("E:/data/4.12/learn/credit-g.arff",
				"E:/data/4.12/learn/credit-g-test.arff",
				"E:/data/4.12/learn/c.txt");
		
	}

}
