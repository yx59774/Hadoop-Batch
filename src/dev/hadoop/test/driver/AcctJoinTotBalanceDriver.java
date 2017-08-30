package dev.hadoop.test.driver;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;

import dev.hadoop.test.mapper.AccountBalanceMapper;
import dev.hadoop.test.mapper.CustomerAccountMapper;

import dev.hadoop.test.reducer.CustomerBalanceReducer;

/*This class is responsible for running map reduce job*/
public class AcctJoinTotBalanceDriver extends Configured implements Tool{

	public int run(String[] args) throws Exception
	{
		Configuration conf = new Configuration();
		
		if(args.length !=2) {
			System.err.println("Usage: CustomerDriver <input path> <outputpath>");
			System.exit(-1);
		}
		
		Job job = new Job(conf, "Customer Total Balance");
		job.setJarByClass(AcctJoinTotBalanceDriver.class);
		
		//FileInputFormat.addInputPath(job, new Path(args[0]));
		//FileOutputFormat.setOutputPath(job,new Path(args[1]));
		
		MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, CustomerAccountMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, AccountBalanceMapper.class);
		
		
		job.setReducerClass(CustomerBalanceReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);
		
		System.exit(job.waitForCompletion(true) ? 0:1); 
		boolean success = job.waitForCompletion(true);
		return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		AcctJoinTotBalanceDriver driver = new AcctJoinTotBalanceDriver();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}
}
