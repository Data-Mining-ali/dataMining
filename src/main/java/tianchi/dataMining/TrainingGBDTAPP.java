package tianchi.dataMining;

import tianchi.dataMining.standard.EvaluationGBDT;
import tianchi.dataMining.standard.TrainingModelMain;
import tianchi.dataMining.utility.Contants;

public class TrainingGBDTAPP {
	public  void work(int k, int numbers[], EvaluationGBDT eval){
		TrainingModelMain model = new TrainingModelMain();
		try {
			String path = Contants.write_filepath;//设置目录，并在目录下要建一个文件夹名字validata-output
			String name = "train";
			name = name + k + ".arff";
			String files[] = { name, "validata.arff", "validata-output/validata_" + k + ".csv" };
			for (int i = 0; i < files.length; i++)
				files[i] = path + files[i];			
			model.traingGBDT(files[0], files[1], files[2]);
			eval.sortData(files[2]);
			System.out.println();
			System.out.println("multiple " + k +" start:");
			for(int i=0;i<numbers.length;i++){
				System.out.println(" number  "+numbers[i] +" result:");
			    eval.evaluate(numbers[i]);
			}			
			System.out.println(k+" finished..");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrainingGBDTAPP aGbdtapp = new TrainingGBDTAPP();
		EvaluationGBDT eval = new EvaluationGBDT(Contants.write_filepath + "validata.csv");
		int numbers[] =  {400,500,600};
		for(int i=1;i<6;i++){
			aGbdtapp.work(i, numbers, eval);;
		}	
	}

}
