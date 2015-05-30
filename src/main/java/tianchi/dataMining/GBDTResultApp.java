package tianchi.dataMining;

import tianchi.dataMining.standard.ClassifyingInstancesMain;
import tianchi.dataMining.standard.GenerateSubmit;
import tianchi.dataMining.utility.Contants;

public class GBDTResultApp {
	public void work(int k,int num){
		try{
			ClassifyingInstancesMain aModel = new ClassifyingInstancesMain();
			String path = Contants.write_filepath; //设置目录
			String name = "train";
			name = name + k + ".arff";	
			String files[]={name,"submit.arff","submit-data//result_"+k+".csv"};
			for (int i = 0; i < files.length; i++)
				files[i] = path + files[i];
			aModel.classifyingInstancesGBDT(files[0],files[1],files[2]);
			new GenerateSubmit().gbdt("result_"+k+".csv",path+"submit-data//", num);
			System.out.println(k+" finished..");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		GBDTResultApp app = new GBDTResultApp();
		for(int i=7;i<8;i++){	
		    app.work(i,400);
		}
	}
}
