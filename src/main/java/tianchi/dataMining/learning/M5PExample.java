package tianchi.dataMining.learning;
import weka.classifiers.*;
import weka.classifiers.trees.*;
import weka.core.*;
import weka.experiment.*;

import java.util.Vector;

public class M5PExample {
  public final static String FILENAME = "/tmp/m5pexample.save";

  public final static String URL = "jdbc:mysql://localhost:3306/weka_old";

  public final static String USER = "someuser";

  public final static String PASSWORD = "somepassword";
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
public void train() throws Exception {
    System.out.println("Training...");

    // load training data from database
    InstanceQuery query = new InstanceQuery();
    query.setDatabaseURL(URL);
    query.setUsername(USER);
    query.setPassword(PASSWORD);
    query.setQuery("select * from Results0");
    Instances data = query.retrieveInstances();
    data.setClassIndex(13);

    // train M5P
    M5P cl = new M5P();
    // further options...
    cl.buildClassifier(data);

    // save model + header)
	Vector v = new Vector();
    v.add(cl);
    v.add(new Instances(data, 0));
    SerializationHelper.write(FILENAME, v);

    System.out.println("Training finished!");
  }

  @SuppressWarnings("rawtypes")
public void predict() throws Exception {
    System.out.println("Predicting...");

    // load data from database that needs predicting
    InstanceQuery query = new InstanceQuery();
    query.setDatabaseURL(URL);
    query.setUsername(USER);
    query.setPassword(PASSWORD);
    query.setQuery("select * from Results0");  // retrieves the same table only for simplicty reasons.
    Instances data = query.retrieveInstances();
    data.setClassIndex(14);

    // read model and header
    Vector v = (Vector) SerializationHelper.read(FILENAME);
    Classifier cl = (Classifier) v.get(0);
    Instances header = (Instances) v.get(1);

    // output predictions
    System.out.println("actual -> predicted");
    for (int i = 0; i < data.numInstances(); i++) {
      Instance curr = data.instance(i);
      // create an instance for the classifier that fits the training data
      // Instances object returned here might differ slightly from the one
      // used during training the classifier, e.g., different order of
      // nominal values, different number of attributes.
      Instance inst = new Instance(header.numAttributes());
      inst.setDataset(header);
      for (int n = 0; n < header.numAttributes(); n++) {
        Attribute att = data.attribute(header.attribute(n).name());
        // original attribute is also present in the current dataset
        if (att != null) {
          if (att.isNominal()) {
            // is this label also in the original data?
            // Note:
            // "numValues() > 0" is only used to avoid problems with nominal 
            // attributes that have 0 labels, which can easily happen with
            // data loaded from a database
            if ((header.attribute(n).numValues() > 0) && (att.numValues() > 0)) {
              String label = curr.stringValue(att);
              int index = header.attribute(n).indexOfValue(label);
              if (index != -1)
                inst.setValue(n, index);
            }
          }
          else if (att.isNumeric()) {
            inst.setValue(n, curr.value(att));
          }
          else {
            throw new IllegalStateException("Unhandled attribute type!");
          }
        }
      }

      // predict class
      double pred = cl.classifyInstance(inst);
      System.out.println(inst.classValue() + " -> " + pred);
    }

    System.out.println("Predicting finished!");
  }

  public static void main(String[] args) throws Exception {
    M5PExample m = new M5PExample();
    m.train();
    m.predict();
  }
}
