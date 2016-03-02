/*
 * WeatherToolTip.java
 *
 * WeatherToolTip is the tool tip for the Weather Station program. When the user
 * hovers over a point in the graph, this tool tip appears and provides all of
 * the values for that point.
 *
 */

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;
import java.util.*;
import java.text.SimpleDateFormat;

public class WeatherToolTip implements XYToolTipGenerator
{
    @Override
    public String generateToolTip(XYDataset dataset, int series, int item )
    {
        // get all of the values to be displayed in the tool tip
        Number time = dataset.getX( series, item );
        Number temperatureNum = dataset.getY( SeriesList.TEMPERATURE, item );
        Number humidityNum = dataset.getY( SeriesList.HUMIDITY, item );
        Number windspeedNum = dataset.getY( SeriesList.WINDSPEED, item );
        Number windgustNum = dataset.getY( SeriesList.WINDGUST, item );
        Number windchillNum = dataset.getY( SeriesList.WINDCHILL, item );
        Number uvindexNum = dataset.getY( SeriesList.UVINDEX, item );
        Number heatindexNum = dataset.getY( SeriesList.HEATINDEX, item );
        Number barometerNum = dataset.getY( SeriesList.BAROMETER, item );
        Number rainfallNum = dataset.getY( SeriesList.RAINFALL, item );

        // the format for the date to appear in the tool tip
        SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yy HH:mm" );

        // holds the string to be printed
        StringBuilder stringBuilder = new StringBuilder();
        
        // the string is formated in html because the world isn't easy on you
        // and apparently \n isn't good enought for the java-ites.
        // the string is returned to be printed in the tool tip
        stringBuilder.append( "<html><p style='color:#000044;'>" );
        stringBuilder.append( dateFormat.format( time ) );
        stringBuilder.append( "</p>" );
        stringBuilder.append( String.format( "<br />Temperature: '%s F'<br/>", temperatureNum.doubleValue() ) );
        stringBuilder.append( String.format( "Humidity: '%s '<br/>", humidityNum.doubleValue() ) );
        
        stringBuilder.append( String.format( "Wind speed: '%s mph'<br/>", windspeedNum.doubleValue() ) );
        stringBuilder.append( String.format( "Wind gust: '%s mph'<br/>", windgustNum.doubleValue() ) );
        stringBuilder.append( String.format( "Wind chill: '%s mph'<br/>", windchillNum.doubleValue() ) );
        stringBuilder.append( String.format( "UV Index: '%s'<br/>", uvindexNum.doubleValue() ) );
        stringBuilder.append( String.format( "Heat Index: '%s'<br/>", heatindexNum.doubleValue() ) );
        stringBuilder.append( String.format( "Barometer: '%s'<br/>", barometerNum.doubleValue() ) );
        stringBuilder.append( String.format( "Rainfall: '%s inches'<br/>", rainfallNum.doubleValue() ) );
        //stringBuilder.append( "And I won't hold you to any more gory details." );
        stringBuilder.append( "</html>" );

        return stringBuilder.toString();
    }
}
