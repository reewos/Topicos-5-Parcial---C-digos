package hadoopparcial;



import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesCountryReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text t_key, Iterator<IntWritable> values, OutputCollector<Text,IntWritable> output, Reporter reporter) throws IOException {
		Text key = t_key;
		int suma_total_tipo_estado =0;
                
                while(values.hasNext()){
                    IntWritable value_temp = values.next();
                   
                    suma_total_tipo_estado+= value_temp.get();
                }
                output.collect(key, new IntWritable(suma_total_tipo_estado));
        }
}
