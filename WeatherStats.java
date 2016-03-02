import java.time.LocalDateTime;
import java.util.*;

public class WeatherStats
{
    public double HighTemperature = 0.0;
    public LocalDateTime dateHighTemperature;
    public double LowTemperature = 200.0;
    public LocalDateTime dateLowTemperature;
    public double MeanTemperature = 0.0;
    public double MeanWindSpeed = 0.0;
    public double MaxWindGust = 0.0;
    public LocalDateTime dateMaxWindGust;
    public String PrevailingWind = "";
    public String WindDirection = "";

    public WeatherStats( List<WeatherPoint> weatherPoints )
    {
        GenerateStats( weatherPoints );   
    }

    private void GenerateStats( List<WeatherPoint> weatherPoints )
    {
        // initialize values
        float windSpeedSum = 0;
        float temperatureSum = 0;

        // to iterate through the list of points
        Iterator<WeatherPoint> pointsIterator = weatherPoints.iterator();
        WeatherPoint toRead;
     
        int numPoints = weatherPoints.size();
   
        while( pointsIterator.hasNext() )
        {
            // weather to grab info from
            toRead = pointsIterator.next();
            
            // check temperatures
            if( toRead.temperature > HighTemperature )
            {
                HighTemperature = toRead.temperature;
                dateHighTemperature = toRead.date;
            }
            if( toRead.temperature < LowTemperature )
            {
                LowTemperature = toRead.temperature;
                dateLowTemperature = toRead.date;
            }
            temperatureSum += toRead.temperature;

            // check wind
            if( toRead.windgust > MaxWindGust )
            {
                MaxWindGust = toRead.windgust;
                dateMaxWindGust = toRead.date;
            }
            windSpeedSum += toRead.windspeed;
            
        }
 
        // calcluate the averages   
        MeanTemperature = temperatureSum / numPoints;
        MeanWindSpeed = windSpeedSum / numPoints;   
    }

}
