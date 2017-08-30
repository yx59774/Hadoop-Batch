package dev.hadoop.test.driver;


import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import dev.hadoop.test.mapper.CustomerBalanceMapper;
import dev.hadoop.test.reducer.CustomerBalanceReducer;

/*This class is responsible for running map reduce job*/
public class CustomerBalanceDriver extends Configured implements Tool{

	public int run(String[] args) throws Exception
	{
	
		if(args.length !=2) {
			System.err.println("Usage: CustomerDriver <input path> <outputpath>");
			System.exit(-1);
		}
		
		Job job = new Job();
		job.setJarByClass(CustomerBalanceDriver.class);
		job.setJobName("Customer Total Balance");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		job.setMapperClass(CustomerBalanceMapper.class);
		job.setReducerClass(CustomerBalanceReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
		
		System.exit(job.waitForCompletion(true) ? 0:1); 
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		CustomerBalanceDriver driver = new CustomerBalanceDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}
}
