package tianchi.dataMining;

import tianchi.dataMining.standard.ClassifyingInstancesMain;
import tianchi.dataMining.standard.GenerateSubmit;
import tianchi.dataMining.utility.Contants;

public class ResultApp {
	public void work(int k){
		try{
			ClassifyingInstancesMain aModel = new ClassifyingInstancesMain();
			String path = Contants.write_filepath; //设置目录
			String name = "train";
			name = name + k + ".arff";
		
			String files[]={name,"submit.arff","submit-data//result_"+k+".csv"};
			for (int i = 0; i < files.length; i++)
				files[i] = path + files[i];
			aModel.classifyingInstancesRandomForest(files[0],files[1],files[2]);
			System.out.println(k+" finished..");
			new GenerateSubmit().work("result_"+k+".csv",path+"submit-data//");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ResultApp app = new ResultApp();
			app.work(i);
		}
	}
}
