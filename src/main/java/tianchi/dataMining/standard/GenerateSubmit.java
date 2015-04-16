package tianchi.dataMining.standard;

import tianchi.dataMining.utility.FileUtil;


public class GenerateSubmit {
	/**
	 * 
	 * @param source filename
	 * @param path filepath
	 */
	public void work(String source,String path){
		FileUtil in = new FileUtil(path+source, "in");
		FileUtil out = new FileUtil(path+"submit_"+source,"out");
		String str;
		int cnt = 0;
		int correct = 0;
		int incorrect = 0;
		out.writeLine("user_id,item_id");
		while((str = in.readLine())!=null){
			if(cnt ++ == 0){
				continue;
			}
			String ls[] = str.split(",");
			int clas = Integer.parseInt(ls[ls.length-1]);
			if(clas == 1) {
				correct ++;
				out.writeLine(ls[0]+","+ls[1]);
			}
			else incorrect++;
		}
		in.close();
		out.close();
		System.out.println("read1 finish..");
		System.out.println("correct count:"+correct);
		System.out.println("incorrect count:"+incorrect);
	
	}

}
