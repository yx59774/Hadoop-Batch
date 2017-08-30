package dev.hadoop.test.mapper;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CustomerBalanceMapper extends
		Mapper<LongWritable, Text, Text, FloatWritable> {

	private Text custNumb = new Text();
	private FloatWritable custBal = new FloatWritable();
	
	public void map(LongWritable key, Text value, 
			Context context)
			throws IOException, InterruptedException {

		//In mapper, value is line, key is default to line number
		
		String line = value.toString();
		String[] fields = line.split(Pattern.quote("|"));
		int no_of_fields = fields.length;
		
		String custNumber = fields[0];
		custNumb.set(custNumber);
		float custBalance = Float.valueOf(fields[no_of_fields-1]);
		custBal.set(custBalance);
		context.write(custNumb, custBal);
	}
}
