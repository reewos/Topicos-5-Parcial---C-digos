package SalesCountry;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesCountryReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
    
	public void reduce(Text t_key, Iterator<Text> values, OutputCollector<Text,Text> output, Reporter reporter) throws IOException {
		String key = t_key.toString();
		double latitud = 33;
                double longitud = -117;
                double margen = 1;
                List<String> nombres = new ArrayList<String>();
		while (values.hasNext()) {
                    Text value = (Text) values.next();
                    String valueString = value.toString();
                    String[] Data = valueString.split(",");
                    double la = Double.parseDouble(Data[1]);
                    double lo = Double.parseDouble(Data[2]);
                    if (latitud - margen < la && la < latitud + margen && longitud - margen < lo && lo < longitud + margen){
                        if(!nombres.contains(Data[0])){
                            nombres.add(Data[0]);
                        }
                        
                    }
		}
                if(nombres.size() != 0){
                    String listString = "";
                    for (String s : nombres)
                    {
                        listString += s + ", ";
                    }
                    output.collect(new Text("Cercanos de latitud="+latitud+" longitud="+longitud+", Ciudad:"+key), new Text(listString));
                }
	}
}
