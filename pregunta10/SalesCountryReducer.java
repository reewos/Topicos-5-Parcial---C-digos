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
                String fecha_min = "";
                String fecha_max = "";
		while (values.hasNext()) {
                    Text value = (Text) values.next();
                    String valueString = value.toString();
                    String[] Data = valueString.split(",");   
                    if (minimo > Integer.parseInt(Data[0])){
                        minimo = Integer.parseInt(Data[0]);
                        fecha_min = Data[1];
                    }
                    if (maximo < Integer.parseInt(Data[0])){
                        maximo = Integer.parseInt(Data[0]);
                        fecha_max = Data[1];
                    }
		}
                if(key.equals("Menor fecha")){
                    output.collect(new Text(key), new Text(fecha_min+" "+minimo));
                }
                if(key.equals("Mayor fecha")){
                    output.collect(new Text(key), new Text(fecha_max+" "+maximo));
                }
	}
}
