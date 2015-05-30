package tianchi.dataMining.utility;

import java.util.Random;


public class RandomData {
	/**
	 * 
	 * @param readFile xxx.csv必须输入.csv，不支持.arff
	 * @param outFile
	 * @param T 正样本复制倍数
	 */
	public void randomData(String readFile,String outFile,int T){
		FileUtil in = new FileUtil(readFile, "in");
		FileUtil out = new FileUtil(outFile,"out");
		String str;
		int cnt = 0;
		int correct = 0;
		int incorrect = 0;
		while((str = in.readLine())!=null){
			if(cnt++ == 0){
				out.writeLine(str);
				continue;
			}
			String ls[] = str.split(",");
			int clas = Integer.parseInt(ls[ls.length-1]);
			if(clas == 1) correct ++;
			else incorrect++;
		}
		in.close();
		System.out.println(T+" read1 finish..");
		System.out.println("correct count:"+correct);
		System.out.println("incorrect count:"+incorrect);
		/*
		 * build array
		 */
		String text[] = new String[correct*T+incorrect];
		in = new FileUtil(readFile, "in");
		cnt = -1;
		while((str = in.readLine())!=null){
			if(cnt == -1){
				cnt++;
				continue;
			}
			String ls[] = str.split(",");
			int clas = Integer.parseInt(ls[ls.length-1]);
			if(clas == 1){
				for(int i=0;i<T;i++){
					text[cnt++] = str;
				}
			}
			else{
				text[cnt++] = str;
			}
		}
		System.out.println("read2 finish..");
		System.out.println(T+" real cnt:"+cnt+" need:"+(correct*T+incorrect));
		in.close();
		shuffle(text);
		for(int i=0;i<text.length;i++){
			out.writeLine(text[i]);
		}
		out.close();
	}
	void shuffle(String s[]){
		Random random = new Random();
		int len = s.length;
		for(int i=0;i<s.length;i++){
			String tmp = s[i];
			int idx = random.nextInt(len);
			s[i] = s[idx];
			s[idx] = tmp;
		}
	}
	public static void main(String[] args) {
		String path = Contants.write_filepath;
		RandomData rd = new RandomData();
		int T = 11;
		for(int i=3;i<T;i++){
			rd.randomData(path+"train.csv", path+"train"+i+".csv", i);
			ChangeCsvToArff.changeCsvToArff("ali", path+"train"+i+".csv", path+"train"+i+".arff");
		}
		
	}
}
