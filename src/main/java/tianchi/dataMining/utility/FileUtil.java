package tianchi.dataMining.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	BufferedReader cin;
	BufferedWriter out;

	public FileUtil(String FileName, String type) {
		File file = new File(FileName);
		try {
			if (type.equals("in")) {
				cin = new BufferedReader(new FileReader(file));
			} else if (type.equals("out")) {
				out = new BufferedWriter(new FileWriter(file, false));
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if(cin!=null){
				cin.close();
			} 
			if(out!=null){
				out.flush();
				out.close();
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeLine(String string) {
		// TODO Auto-generated method stub
		try {
			out.write(string);
			out.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public String readLine() {
		// TODO Auto-generated method stub
		try {
			return cin.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
	}

}
