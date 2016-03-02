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

    public static void main( String[] args )
    {
        List<WeatherPoint> list = parseDirectory("./Data");
    }

  	public static List<WeatherPoint> parseDirectory( String directory )
	{
		ArrayList<WeatherPoint> toReturn = new ArrayList<WeatherPoint>();
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
		for(int i = 0; i < listOfFiles.length; i++)
		{
			String filename = listOfFiles[i].getAbsolutePath();
			if(filename.endsWith(".xml")||filename.endsWith(".XML"))
			{		
				toReturn.addAll(parseWeather(filename));
			}
   		}
        return toReturn;
  	}
    public static List<WeatherPoint> parseWeather( String file )
    {
        SAXBuilder builder;
        Document doc;
        Element root;
        List<WeatherPoint> toReturn = new ArrayList<WeatherPoint>();
        builder = new SAXBuilder();
    
        try
        {

            doc = builder.build( file );
            root = doc.getRootElement();
            List points = root.getChildren();
            Iterator pointsIterator = points.iterator();   
        
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
    
    public static WeatherPoint addWeatherPoint( Element current )
    {
        List attributes = current.getChildren();
        Iterator attributeIterator = attributes.iterator();
        WeatherPoint toReturn = new WeatherPoint();
        String tempDate = "01/01/1970";
        String tempTime = "12:00A";
    
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
    
        toReturn.dateString = tempDate + " " + tempTime;
        toReturn.date = generateDate( tempDate, tempTime );

        return toReturn;
    }
    
    public static LocalDateTime generateDate( String date, String time )
    {
        time = time.replaceAll( "\\s", "" );
        date = date.replaceAll( "\\s", "" );
        String[] dateTokens = date.split( "/" );
        String[] timeTokens = time.split( ":" );
        int year = Integer.parseInt( dateTokens[2] ) + 2000;
        int month = Integer.parseInt( dateTokens[0] );
        int day = Integer.parseInt( dateTokens[1] );
        int hour = Integer.parseInt( timeTokens[0] ) % 12;
                
        int minute = Integer.parseInt( timeTokens[1].substring(0,2) );
        if( timeTokens[1].contains( "P" ) )
        {
            hour = hour + 12;
        }
        
        return LocalDateTime.of( year, month, day, hour, minute, 0 );
    }   
}
