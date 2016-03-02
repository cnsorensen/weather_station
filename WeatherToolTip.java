/* WeatherToolTip.java */

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;
import java.util.*;
import java.text.SimpleDateFormat;

public class WeatherToolTip implements XYToolTipGenerator
{
    @Override
    public String generateToolTip(XYDataset dataset, int series, int item )
    {
        String windDir;
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

        SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yy HH:mm" );

        StringBuilder stringBuilder = new StringBuilder();
        
        //still need one for wind direction
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
