/*
 * ParseXML.java
 *
 * This code was based on the Java example in Processing XML with Java (Elliotte Harold).
 * More information on this, see:
 *      https://docs.oracle.com/javase/tutorial/jaxp/dom/readingXML.html
 * 
 * The code was provided to the students of CSC 468: GUI Spring 2016 by Dr. John Weiss. 
 * It has been tailored for this project.
 *
 * ParseXML takes in an xml and parses the data. The data stored into a data structure
 * WeatherPoint for each entry. The points are then collected into a list.
 * ParseXML also can be given a directory and will parse ALL XML files in it. 
 *
 */







import java.awt.BorderLayout;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import java.io.File;
import org.jfree.ui.RefineryUtilities;

public class ParseXML 
{

    /*
    * Function: Main
    * Author: Marcus Haberling
    * Simple function for testing and debugging
    */
    public static void main( String[] args )
    {
        List<WeatherPoint> list = parseDirectory("./Data");
    }

    /*
    * Function: parseDirectory
    * Author: Marcus Haberling
    * Goes through a supplied directory name reading in WeatherPoints
    * from each xml file. The are all added to a list which is returned;
    */
  	public static List<WeatherPoint> parseDirectory( String directory )
	{
		ArrayList<WeatherPoint> toReturn = new ArrayList<WeatherPoint>();
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
		for(int i = 0; i < listOfFiles.length; i++) //loop through files
		{
			String filename = listOfFiles[i].getAbsolutePath();
			if(filename.endsWith(".xml")||filename.endsWith(".XML"))
			{		
                //if an xml file, call parseweather on it to get xml
				toReturn.addAll(parseWeather(filename));
			}
   		}
        return toReturn;
  	}

    /*
    * Function: parseWeather
    * Author: Marcus Haberling
    * Goes through a supplied filed and loads all the weatherpoints in
    * the file and then puts them into a list wich is then returned
    * this code was based off of the ParseXML2 example supplied by
    * Dr. Weiss
    */
    public static List<WeatherPoint> parseWeather( String file )
    {
        SAXBuilder builder;
        Document doc;
        Element root;
        List<WeatherPoint> toReturn = new ArrayList<WeatherPoint>();
        builder = new SAXBuilder();
    
        try
        {
            //fill document and get list of 
            doc = builder.build( file );
            root = doc.getRootElement();
            List points = root.getChildren();
            Iterator pointsIterator = points.iterator();   
            //Loop through list calling addWeatherPoint on each element
            while( pointsIterator.hasNext() )
            {

                Element point = (Element) pointsIterator.next();

                toReturn.add( addWeatherPoint( point ) );
            }
        }
        catch( JDOMException e )
        {
            System.out.println( file + " is not well-formed." );
            System.out.println( e.getMessage() );
        }
        catch( IOException e )
        {
            System.out.println( e );
        }
        catch( NullPointerException e )
        {
            System.out.println( e );
        }
      
        return toReturn;
    }
    
    /*
    * Function: addWeatherPoint
    * Author: Marcus Haberling
    * Goes through a supplied xml element and extracts all the data
    * into a WeatherPoint object. Which is then returned.
    */
    public static WeatherPoint addWeatherPoint( Element current )
    {
        List attributes = current.getChildren();
        Iterator attributeIterator = attributes.iterator();
        WeatherPoint toReturn = new WeatherPoint();
        String tempDate = "01/01/1970";
        String tempTime = "12:00A";
    
        //Loop through elements, selecting each one by their names.
        while( attributeIterator.hasNext() )
        {
            Element attribute = (Element) attributeIterator.next();
            
            String name = attribute.getQualifiedName();
            
            if( name.equals( "temperature" ) )
            {
                toReturn.temperature = Double.parseDouble( attribute.getValue() );
            }
            else if( name.equals( "date" ) )
            {
                tempDate = attribute.getValue();
            }
            else if( name.equals( "time" ) )
            {
                tempTime = attribute.getValue();
            }
            else if( name.equals( "humidity" ) )
            {
                toReturn.humidity = Double.parseDouble( attribute.getValue() );
            }
            else if( name.equals( "barometer" ) )
            {
                toReturn.barometer = Double.parseDouble( attribute.getValue() );
            }
            else if( name.equals( "windspeed" ) )
            {
                toReturn.windspeed = Double.parseDouble( attribute.getValue() );
            }
            else if( name.equals( "winddirection" ) )
            {
                toReturn.winddirection = attribute.getValue();
            }
            else if( name.equals( "windgust" ) )
            {
                toReturn.windgust = Double.parseDouble( attribute.getValue() );
            }
            else if( name.equals( "windchill" ) )
            {
                toReturn.windchill = Double.parseDouble( attribute.getValue() );
            }
            else if( name.equals( "heatindex" ) )
            {
                toReturn.heatindex = Double.parseDouble( attribute.getValue() );
            }
            else if( name.equals( "uvindex" ) )
            {
                toReturn.uvindex = Double.parseDouble( attribute.getValue() );
            }
            else if( name.equals( "rainfall" ) )
            {
                toReturn.rainfall = Double.parseDouble( attribute.getValue() );
            }           
        }
    
        //Need to hand the datetime specially to wedge into a LocalDateTime 
        toReturn.dateString = tempDate + " " + tempTime;
        toReturn.date = generateDate( tempDate, tempTime );

        return toReturn;
    }
    
    /*
    * Function: generateDate
    * Author: Marcus Haberling
    * Uses 2 specified strings, one containing date, the other time
    * to generate a LocalDateTime object. 
    */
    public static LocalDateTime generateDate( String date, String time )
    {
        time = time.replaceAll( "\\s", "" );//Remove spaces
        date = date.replaceAll( "\\s", "" );
        String[] dateTokens = date.split( "/" );//split on delimeters
        String[] timeTokens = time.split( ":" );
        int year = Integer.parseInt( dateTokens[2] ) + 2000; //dont use before 2000
        int month = Integer.parseInt( dateTokens[0] );
        int day = Integer.parseInt( dateTokens[1] );
        int hour = Integer.parseInt( timeTokens[0] ) % 12;
        
        int minute = Integer.parseInt( timeTokens[1].substring(0,2) );
        //If the time is PM we need to add 12 hours
        if( timeTokens[1].contains( "P" ) )
        {
            hour = hour + 12;
        }
        
        return LocalDateTime.of( year, month, day, hour, minute, 0 );
    }   
}
