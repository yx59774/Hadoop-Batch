package dev.hadoop.test.reducer;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CustomerBalanceReducer
extends Reducer<Text, FloatWritable, Text, FloatWritable> {

	private FloatWritable totalBalance = new FloatWritable();
	
	public void reduce(Text key, Iterable<FloatWritable> values,
		Context context)
    throws IOException, InterruptedException {
		
        float sum = 0;
        
        for(FloatWritable value : values) {
        	sum = sum + value.get();
  		}
        
        totalBalance.set(sum);
        
  	context.write(key, totalBalance);
	}
}
