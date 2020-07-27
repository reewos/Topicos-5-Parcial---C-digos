package SalesCountry;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
	private final static IntWritable one = new IntWritable(1);
        public static IntWritable mayor = new IntWritable(Integer.MIN_VALUE);
        public static IntWritable menor = new IntWritable(Integer.MAX_VALUE);

	public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
                IntWritable numero;
		String valueString = value.toString();
		String[] SingleCountryData = valueString.split(",");
                if(!"Payment_Type".equals(SingleCountryData[3])){
                    numero = new IntWritable(Integer.parseInt(SingleCountryData[2]));
                    if (menor.get() > numero.get()){
                        menor = numero;
                        String texto = numero+","+SingleCountryData[4];
                        output.collect(new Text("Menor"), new Text(texto));
                    }
                    if (mayor.get() < numero.get()){
                        mayor = numero;
                        output.collect(new Text("Mayor"),new Text(numero+","+SingleCountryData[4]));
                    }
                }

	}
}
