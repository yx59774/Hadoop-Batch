package dev.hadoop.test.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
/**
 * @author Xie Yujie
 * 
 * this class is to generate data for testing 
 * Output data contains 2 fields in each record, the first one is customer number, the second is 
 * account or balance.
 * @see https://github.com/yx59774/Hadoop-Batch
 */
public class CustomerBalanceGenerator {
	 /**
     *
     * @param  NA
     * @return NA
     * @throws IOException when writing the file ,if something wrong happened
     * then the method will throws a IOException
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer custAcctBuf = new StringBuffer();
		StringBuffer acctBalanceBuf = new StringBuffer();
		
		File hadoopCustAcct = new File(args[0]);
		File hadoopAcctBalance = new File(args[1]);
		
		try {
			
		FileWriter  out1 = new FileWriter (hadoopCustAcct);
		FileWriter  out2 = new FileWriter (hadoopAcctBalance);
        
		BufferedWriter bw1 = new BufferedWriter(out1); 
		BufferedWriter bw2 = new BufferedWriter(out2);
		
        long custnumb = 0;
        long acctnumb = 0;
        int howManyAcct = 0;
        
        BigDecimal balance = new BigDecimal("0.00");
        
			for(int i = 0 ; i<200000; i++) {
				custnumb++;
				
				howManyAcct = (int) (Math.random()*10);
				
				for(int k = 0; k < howManyAcct; k++) {
					custAcctBuf.append("CustomerAccount|");
					custAcctBuf.append(String.format("%09d", custnumb));
					custAcctBuf.append("|");
					acctnumb = (long) (Math.random()*50000000+50000001);
					custAcctBuf.append(String.format("%015d", acctnumb));
				
					acctBalanceBuf.append("AccountBalance|");
					acctBalanceBuf.append(String.format("%015d", acctnumb));
					acctBalanceBuf.append("|");
					balance = new BigDecimal((Math.random()*5000000+5000001)/1000);
					acctBalanceBuf.append(balance.floatValue());
			    
					//strBuf.append(balance.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
			
					bw1.write(custAcctBuf.toString());
					custAcctBuf.setLength(0);
					bw1.newLine();
				
					bw2.write(acctBalanceBuf.toString());
					acctBalanceBuf.setLength(0);
					bw2.newLine();
				}
		
			}
			
			bw2.close();
			out2.close();
			bw1.close();
			out1.close();
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
