package dev.hadoop.test.text;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.lang.RandomStringUtils;

public class Customer2Generator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer strBuf = new StringBuffer();
		
		File hadoopText = new File("/home/hadoop/input/hadoopCustomer2.txt");
		try {
			
		FileWriter  out=new FileWriter (hadoopText);
		
        @SuppressWarnings("resource")
		BufferedWriter bw= new BufferedWriter(out); 

        int custnumb = 0;
        BigDecimal balance = new BigDecimal("0.00");
        
		for(int i = 0 ; i<5000; i++) {
			custnumb = (int) (Math.random()*50000+50001); 
		    	
			strBuf.append(String.format("%09d", custnumb));
			strBuf.append("|");
			for(int k=0 ; k <20; k++) {
				strBuf.append(RandomStringUtils.randomAlphanumeric(5));
				strBuf.append("|");
			}
			balance = new BigDecimal((Math.random()*5000000+5000001)/1000);
			
			strBuf.append(balance.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
			
			bw.write(strBuf.toString());
			bw.newLine();
			strBuf.setLength(0);
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
