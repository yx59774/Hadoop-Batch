package dev.hadoop.test.mapper;

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
		
		String custNumber = fields[0];

		context.write(new Text(custNumber), new IntWritable(1));
	}
}
