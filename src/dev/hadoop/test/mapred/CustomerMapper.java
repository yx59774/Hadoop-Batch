package dev.hadoop.test.mapred;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CustomerMapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	public void map(LongWritable key, Text value, 
			Context context)
			throws IOException, InterruptedException {

		//In mapper, value is line, key is default to line number
		
		String line = value.toString();
		String[] fields = line.split(Pattern.quote("|"));
		int no_of_fields = fields.length;
		
		String custNumber = fields[0];
		float custBalance = Float.valueOf(fields[no_of_fields-1]);
		
		context.write(new Text(custNumber), new IntWritable(1));
	}
}
