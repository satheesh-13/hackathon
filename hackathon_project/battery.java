package hackathon_main;
import java.text.DecimalFormat;
import java.io.*;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.nio.file.*;


public class battery {

	public static void main(String[] args)throws Exception 
	  { 
	
		String str1="Uid u0a202";//to get the drain value
		String str2="Foreground activities";//to get the foreground value
		
		float Bat_percentage=0,Bat_drain=0;//initializing the values
		
		File file = new File("C:\\Users\\Indiran S\\Desktop\\Battery.txt"); //reading the file
		
		BufferedReader reader = new BufferedReader(new FileReader(file)); 
		
		String string,string2,string3,Foreground_value=""; //initalizing
		
		while ((string = reader.readLine()) != null) //reading till the EOF	
		{
			string2=string;
			String[] array = string2.split(":");//spliting the 1 st value before colan for comapring
			if(array.length>1)
			{ 
				array[0]=array[0].trim();//to remove spaces
				if(array[0].equals(str1)  )//comparing the 1st values to get the desired value
				{
					string3=array[1];
		         String[] array1 = string3.split("\\(");//taking the values till barces
		         Bat_drain=Float.parseFloat(array1[0]); //saving the value
		         //System.out.println(Bat_drain);
				}
				if(array[0].equals(str2))//similar to above process its being done
				{
					string3=array[1];
		         String[] array1 = string3.split("\\(r");
		         Foreground_value=array1[0].trim();
		        // System.out.println(Foreground_value);
				} 
			}
	   
			  
		}
		
		Bat_percentage=(Bat_drain/1000);//value calculation
		//System.out.println(Bat_percentage);
		//creating json object
		
		JSONParser parser=new JSONParser();//json objects
		JSONObject hack=new JSONObject();
		
		//formating the result according to the output
		
		String pattern = "0.###";//pattern to truncate the value drain
		DecimalFormat decimalFormat = new DecimalFormat(pattern);

		String format = decimalFormat.format(Bat_drain);
		DecimalFormat formatter = new DecimalFormat("0.00000000");//pattern to trruncate the value percentage
		String format1 = decimalFormat.format(Bat_percentage);
		//System.out.println(format);
		
		//storing in json object
		
		hack.put("Foreground_time",Foreground_value);
		hack.put("Battery_drain" ,format);
		hack.put("Battery_percentage" ,format1);
		
		//writing on to the json file
		
		FileWriter file_new=new FileWriter("C:\\Users\\Indiran S\\Desktop\\Battery_OPSample1.json");
		file_new.write("values after formating nd storing\n");
		file_new.write(hack.toString());
		file_new.flush();
		//System.out.println("values after formating nd storing");
		System.out.println(hack);
		
	  } 
	
}


