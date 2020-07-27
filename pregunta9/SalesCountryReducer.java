package SalesCountry;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesCountryReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
    
	public void reduce(Text t_key, Iterator<Text> values, OutputCollector<Text,Text> output, Reporter reporter) throws IOException {
		String key = t_key.toString();
		int minimo = Integer.MAX_VALUE;
                int maximo = Integer.MIN_VALUE;
                String nombre_min = "";
                String nombre_max = "";
		while (values.hasNext()) {
                    Text value = (Text) values.next();
                    String valueString = value.toString();
                    String[] Data = valueString.split(",");   
                    if (minimo > Integer.parseInt(Data[0])){
                        minimo = Integer.parseInt(Data[0]);
                        nombre_min = Data[1];
                    }
                    if (maximo < Integer.parseInt(Data[0])){
                        maximo = Integer.parseInt(Data[0]);
                        nombre_max = Data[1];
                    }
		}
                output.collect(new Text(key+" Menor"), new Text(nombre_min+" "+minimo));
                output.collect(new Text(key+" Mayor"), new Text(nombre_max+" "+maximo));
	}
}
