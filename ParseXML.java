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
    
        //WeatherPoint toPrint = new WeatherPoint();
        Object toPrint;
        //WeatherPoint toPrint;
        
        while( pointsIterator.hasNext() )
        {
            toPrint = pointsIterator.next();
            System.out.println( toPrint );
        }
    }
    
    public static List<WeatherPoint> parseWeather( String file )
    {
        SAXBuilder builder;
        Document doc;
        Element root;
        List<WeatherPoint> toReturn = null;
        builder = new SAXBuilder();
   
        System.out.println( "Green" );
 
        try
        {
            System.out.println( "Eggs" );
    
            doc = builder.build( file );
            root = doc.getRootElement();
            List points = root.getChildren();
            Iterator pointsIterator = points.iterator();
            
            System.out.println( "and" );    
        
            while( pointsIterator.hasNext() )
            {
                System.out.println( "Ham" );

                Element point = (Element) pointsIterator.next();
                
                System.out.println( "By" );

                // error here
                toReturn.add( addWeatherPoint( point ) );
                
                System.out.println( "Dr." );
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
        System.out.println( timeTokens[1].substring( 2, 3 ) );
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
