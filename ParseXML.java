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

import org.jfree.ui.RefineryUtilities;

public class ParseXML 
{

    public static void main( String[] args )
    {
        /*if( args.length != 1 )
        {
            System.out.println( "Usage: java -cp .:jdom.jar ParseXML file.xml" );     
        }*/

        System.out.println( "Got any grapes?" );
    
        /*
        List<WeatherPoint> weatherPoints = parseWeather( args[0] );
        Iterator<WeatherPoint> pointsIterator = weatherPoints.iterator();
    
        WeatherPoint toPrint;
    
        // print out the weather points
       
        while( pointsIterator.hasNext() )
        {
            toPrint = pointsIterator.next();
            System.out.println( toPrint );
        }
        */

        WeatherGraph chart = new WeatherGraph( "Weather Station", "Graph Title", weatherPoints, args[1] );
        chart.pack();
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );

    }
    
    public static List<WeatherPoint> parseWeather( String file )
    {
        SAXBuilder builder;
        Document doc;
        Element root;
        List<WeatherPoint> toReturn = new ArrayList<WeatherPoint>();
        builder = new SAXBuilder();
   
        //System.out.println( "Green" );
 
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
            //System.out.println( attribute.getQualifiedName() + "\t: " + attribute.getValue() );
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
        
        // this is printing out a's and p's
        //System.out.println( timeTokens[1].substring( 2, 3 ) );
        
        int minute = Integer.parseInt( timeTokens[1].substring(0,2) );
        if( timeTokens[1].contains( "P" ) )
        {
            hour = hour + 12;
        }
        
        return LocalDateTime.of( year, month, day, hour, minute, 0 );
    }
    
    public static void listChildren( Element current, int depth )
    {
        List children = current.getChildren();
        Iterator iterator = children.iterator();
        
        printSpaces( depth );
        System.out.print( current.getName() );
        if( !iterator.hasNext() )
        {
            System.out.print( " = " + current.getValue() );
        }
        System.out.println();
    
        while( iterator.hasNext() )
        {
            Element child = (Element) iterator.next();
            listChildren( child, depth + 1 );
        }
    }
    
    private static void printSpaces( int n )
    {
        for( int i = 0; i < n; i++ )
        {
            System.out.print( " " );
        }
    }
}
