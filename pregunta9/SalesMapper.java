package SalesCountry;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
	private final static IntWritable one = new IntWritable(1);

	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
                IntWritable numero;
		String valueString = value.toString();
		String[] SingleCountryData = valueString.split(",");
                if(!"Payment_Type".equals(SingleCountryData[3])){
                    numero = new IntWritable(Integer.parseInt(SingleCountryData[2]));
                    String texto = numero+","+SingleCountryData[4];
                    output.collect(new Text(SingleCountryData[7]), new Text(texto));
                }

	}
}
