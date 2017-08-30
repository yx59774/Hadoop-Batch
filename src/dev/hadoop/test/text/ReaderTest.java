package dev.hadoop.test.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReaderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File hadoopText = new File("D:\\hadoopText2.txt");
		String line = "";
		
		try {
			FileReader reader = new FileReader(hadoopText);
			
			@SuppressWarnings("resource")
			BufferedReader br= new BufferedReader(reader); 
			
			StringTokenizer st = null;
			do {		
				line = br.readLine();
				st = new StringTokenizer(line, "|");
				String custNumb = st.nextToken();
				while(st.hasMoreElements()) {
					System.out.println("Token of Customer " + custNumb + " " + st.nextToken());
				}
			} while(line != null);
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
