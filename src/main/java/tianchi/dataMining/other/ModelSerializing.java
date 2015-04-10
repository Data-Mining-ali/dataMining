package tianchi.dataMining.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class ModelSerializing {
	public void modelSerializing() throws Exception {
		Classifier cls = new J48();

		// train
		Instances inst = new Instances(new BufferedReader(new FileReader(
				"E:/DataMining/alibaba/weka-3-6-12/data/breast-cancer.arff")));
		inst.setClassIndex(inst.numAttributes() - 1);
		cls.buildClassifier(inst);

		// serialize model
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"E:/DataMining/alibaba/data/j48.model"));
		oos.writeObject(cls);
		oos.flush();
		oos.close();
	}

	public void modelDeserializing() throws Exception {
		Classifier tree = (Classifier) weka.core.SerializationHelper
				.read("E:/DataMining/alibaba/data/j48.model");
		
		Instances unlabeled = new Instances(new BufferedReader(new FileReader(
				"E:/DataMining/alibaba/weka-3-6-12/data/breast-cancer.arff")));

		// set class attribute
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

		// create copy
		Instances labeled = new Instances(unlabeled);

		// label instances
		for (int i = 0; i < unlabeled.numInstances(); i++) {
			double clsLabel = tree.classifyInstance(unlabeled.instance(i));
			labeled.instance(i).setClassValue(clsLabel);
		}
		// save labeled data
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				"E:/data/labeled.arff"));
		writer.write(labeled.toString());
		writer.newLine();
		writer.flush();
		writer.close();

	}

	public static void main(String[] args) throws Exception {
		ModelSerializing a = new ModelSerializing();
		a.modelSerializing();
		a.modelDeserializing();
	}
}
