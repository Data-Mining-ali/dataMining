package tianchi.dataMining.utility;

public class ChangeCsvToArff {
	private static void generateArffHead(String relation,FileUtil out,String[]attributeName){
		out.writeLine("@relation "+relation);
		for(int i = 0; i < attributeName.length-1; i++)
			out.writeLine("@attribute "+ attributeName[i] + " numeric");
		out.writeLine("@attribute class{0,1}");
		out.writeLine("@data");
	}
	
	/**
	 * 
	 * @param relation
	 * @param csvFileName
	 * @param arffFileName
	 */
	public static void changeCsvToArff(String relation,String csvFileName,String arffFileName) {
		FileUtil cin = new FileUtil(csvFileName, "in");
		FileUtil out = new FileUtil(arffFileName, "out");
		String cinString = null;
		cinString = cin.readLine();	
		generateArffHead(relation,out,cinString.split(","));
		while((cinString=cin.readLine())!=null){
			out.writeLine(cinString);
		}
		cin.close();
		out.close();
	}
	
	public static void main(String[] args) {
		String csvFileName ="E:\\DataMining\\alibaba\\data\\4.15\\rawdata\\intersection_data18_clas19.csv";
		String arffFileName ="E:\\DataMining\\alibaba\\data\\4.15\\rawdata\\data\\intersection_data18_clas19.arff";
		ChangeCsvToArff.changeCsvToArff("hahahah", csvFileName, arffFileName);
		
	}

}
