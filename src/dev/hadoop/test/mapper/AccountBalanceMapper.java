package dev.hadoop.test.mapper;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AccountBalanceMapper extends
		Mapper<LongWritable, Text, Text, Text> {

	private Text acctNumb = new Text();
	private Text acctBalRec = new Text();
	
	public void map(LongWritable key, Text value, 
			Context context)
			throws IOException, InterruptedException {

		//In mapper, value is line, key is default to line number
		
		String accountBalanceStr = value.toString();
		String[] fields = accountBalanceStr.split(Pattern.quote("|"));
		
		String acctNumber = fields[1];
		
		acctNumb.set(acctNumber);  //Set Account Number as KEY
		
		acctBalRec.set(accountBalanceStr);  //Set Account Balance Record as VALUE
		
		context.write(acctNumb, acctBalRec);
	}
}
