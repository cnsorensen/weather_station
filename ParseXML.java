/*

    **** ReadXML2.java - read and dump contents of an XML file ****

Illustrates use of JDOM to parse an XML file.
This version will dive into XML tree structure.

Usage:
javac -cp .:jdom.jar ParseXML.java         (use ; instead of : on Windoze)
java  -cp .:jdom.jar ParseXML file.xml     (use ; instead of : on Windoze)

Based on Java example in Processing XML with Java (Elliotte Harold).
For more info, see e.g. https://docs.oracle.com/javase/tutorial/jaxp/dom/readingXML.html

JMW 160205
*/

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;

public class ParseXML
{
    public static void main( String[] args )
    {

        List<WeatherPoint> weatherPoints = parseWeather( args[0] );
        Iterator pointsIterator = weatherPoints.iterator();
        
        while(pointsIterator.hasNext())
        {
            WeatherPoint toPrint = pointsIterator.next();
            System.out.println(toPrint);
        }
    }
    //static List<WeatherPoint>
    public static List<WeatherPoint> parseWeather(String file)
    {
        SAXBuilder builder;
        Document doc;
        Element root;
        List<WeatherPoint> toReturn;
        builder = new SAXBuilder();

        try
        {
            doc = builder.build(file);
            root = doc.getRootElement();
            List points = root.getChildren();
            Iterator pointsIterator = points.iterator();
            while (pointsIterator.hasNext())
            {
                Element point = (Element) pointsIterator.next();
                toReturn.Add(addWeatherPoint(point));
                
            }

        }
        catch ( JDOMException e )
        {
            System.out.println( file + " is not well-formed." );
            System.out.println( e.getMessage() );
        }
        catch ( IOException e )
        {
            System.out.println( e );
        }
        

    }

    public static WeatherPoint addWeatherPoint(Element current)
    {
        List attributes = current.getChildren();
        Iterator attributeIterator = attributes.iterator();
        WeatherPoint toReturn = new WeatherPoint();
                   String tempDate = "01/01/1970";
            String tempTime = "12:00A";
        while(attributeIterator.hasNext())
        {
            Element attribute = (Element) attributeIterator.next();
            //System.out.println(attribute.getQualifiedName() + "\t: " + attribute.getValue());
            String name = attribute.getQualifiedName();
 
            if( name == "temperature")
                toReturn.temperature = Double.parseDouble(attribute.getValue());
            else if( name == "date")
                tempDate = attribute.getValue();
            else if( name == "time")
                tempTime = attribute.getValue();
            else if( name == "humidity")
                toReturn.humidity = Double.parseDouble(attribute.getValue());
            else if( name == "barometer")
                toReturn.barometer = Double.parseDouble(attribute.getValue());
            else if( name == "windspeed")
                toReturn.windspeed = Double.parseDouble(attribute.getValue());
            else if( name == "winddirection")
                toReturn.winddirection = attribute.getValue();
            else if( name == "windgust")
                toReturn.windgust = Double.parseDouble(attribute.getValue());
            else if( name == "windchill")
                toReturn.windchill = Double.parseDouble(attribute.getValue());
            else if( name == "heatindex")
                toReturn.heatindex = Double.parseDouble(attribute.getValue());
            else if( name == "uvindex")
                toReturn.uvindex = Double.parseDouble(attribute.getValue());
            else if( name == "rainfall")
                toReturn.rainfall = Double.parseDouble(attribute.getValue());
        }
        toReturn.dateString = tempDate + " " + tempTime;
        toReturn.date = generateDate(tempDate, tempTime);
        return toReturn;
    }
    
    public static LocalDateTime generateDate(String date, String time)
    {
        time = time.replaceAll("\\s","");
        date = date.replaceAll("\\s","");
        String[] dateTokens = date.split("/");
        String[] timeTokens = time.split(":");
        int year = Integer.parseInt(dateTokens[2])+2000;
        int month = Integer.parseInt(dateTokens[0]);
        int day = Integer.parseInt(dateTokens[1]);
        int hour = Integer.parseInt(timeTokens[0])%12;
        System.out.println(timeTokens[1].substring(2,3));
        int minute = Integer.parseInt(timeTokens[1].substring(0,2));
        if(timeTokens[1].contains("P"))
            hour = hour + 12;
        return LocalDateTime.of(year,month,day,hour,minute,0);
    }
    
    // print XML tags and leaf node values
    public static void listChildren( Element current, int depth )
    {
	// get children of current node
        List children = current.getChildren();
        Iterator iterator = children.iterator();

        // print node name and leaf node value, 
        //     indented one space per level in XML tree
        printSpaces( depth );
        System.out.print( current.getName() );
        if ( !iterator.hasNext() )
            System.out.print( " = " + current.getValue() );
        System.out.println();

        // recursively process each child node
        while ( iterator.hasNext() )
        {
            Element child = ( Element ) iterator.next();
            listChildren( child, depth + 1 );
        }
    }

    // indent to show hierarchical structure of XML tree
    private static void printSpaces( int n )
    {
        for ( int i = 0; i < n; i++ )
        {
            System.out.print( " " );
        }
    }
}