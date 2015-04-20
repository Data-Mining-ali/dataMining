package tianchi.dataMining.utility;


public class ChangeCsvToArff {
	private static void generateArffHead(String relation,FileUtil out,String[]attributeName){
		out.writeLine("@relation "+relation);
		for(int i = 0; i < attributeName.length-1; i++)
			out.writeLine("@attribute "+ attributeName[i] + " numeric");
		out.writeLine("@attribute class {0,1}");
		out.writeLine("@data");
	}
	
	private static void generateArffHeadNumericClass(String relation,FileUtil out,String[]attributeName){
		out.writeLine("@relation "+relation);
		for(int i = 0; i < attributeName.length-1; i++)
			out.writeLine("@attribute "+ attributeName[i] + " numeric");
		out.writeLine("@attribute class numeric");
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
		generateArffHeadNumericClass(relation,out,cinString.split(","));
		while((cinString=cin.readLine())!=null){
			out.writeLine(cinString);
		}
		cin.close();
		out.close();
	}
	
	public static void main(String[] args) {
		//String files[] = {"train","validata","submit"};
		String files[] = {"train","submit"};
		for (int i = 0; i < files.length; i++) {
			String name = files[i];
			String csvFileName = Contants.write_filepath +  name + ".csv";
			String arffFileName = Contants.write_filepath + name + ".arff";
			ChangeCsvToArff.changeCsvToArff("ali", csvFileName, arffFileName);
		}
		
	}

}
