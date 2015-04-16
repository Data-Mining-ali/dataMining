package tianchi.dataMining;

import tianchi.dataMining.utility.ChangeCsvToArff;
import tianchi.dataMining.utility.Contants;
import tianchi.dataMining.utility.RandomData;



/**
 * 处理转换训练集，验证集，提交集
 * 需要3份数据：｛train.csv,validata.csv,submit.csv｝
 * @author v11
 *
 */
public class DataApp {
	/**
	 * 
	 * @param train 训练集文件名
	 * @param validata
	 * @param submit
	 * @param T
	 */
	public void work(String train,String validata,String submit,int T){
		//first random data from train
		
		String path = Contants.write_filepath; // 设置你的目录
		String files[] = new String[2+T];
		files[0] = train;
		files[1] = validata;
		files[2] = submit;
		RandomData rd = new RandomData();
		for(int i=2;i<=T;i++){
			rd.randomData(path+train+".csv", path+train+i+".csv", i);
			files[i+1] = train+i;
		}
		for (int i = 0; i < files.length; i++) {
			String name = files[i];
			String csvFileName = Contants.write_filepath + name + ".csv";
			String arffFileName = Contants.write_filepath + name + ".arff";
			ChangeCsvToArff.changeCsvToArff("ali", csvFileName, arffFileName);
		}
	}
	public void work(int T){
		work("train","validata","submit",T);
	}
	public static void main(String[] args) {
		new DataApp().work(7);
	}
}
