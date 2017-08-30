package dev.hadoop.test.mapper;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CustomerAccountMapper extends
		Mapper<LongWritable, Text, Text, Text> {

	private Text acctNumb = new Text();
	private Text custAcctRec = new Text();
	
	public void map(LongWritable key, Text value, 
			Context context)
			throws IOException, InterruptedException {

		//In mapper, value is line, key is default to line number
		
		String line = value.toString();
		String[] fields = line.split(Pattern.quote("|"));
		
		String acctNumber = fields[2];
		
		String custAcctRecStr = value.toString();
		
		custAcctRec.set(custAcctRecStr);
		
		acctNumb.set(acctNumber);
		
		context.write(acctNumb, custAcctRec);
	}
}
