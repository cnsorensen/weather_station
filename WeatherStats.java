/* WeatherStats.java */

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
        int countN = 0;
        int countNNE = 0;
        int countNE = 0;
        int countNEE = 0;
        int countE = 0;
        int countSEE = 0;
        int countSE = 0;
        int countSSE = 0;
        int countS = 0;
        int countSSW = 0;
        int countSW = 0;
        int countSWW = 0;
        int countW = 0;
        int countNWW = 0;
        int countNW = 0;
        int countNNW = 0;

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
            // add the count of the wind direction
            if( toRead.winddirection.equals( "N" ) )
            {
                countN++;
            } 
            else if( toRead.winddirection.equals( "NNE" ) )
            {
                countNNE++;
            }
            else if( toRead.winddirection.equals( "NE" ) )
            {
                countNE++;
            }
            else if( toRead.winddirection.equals( "NEE" ) )
            {
                countNEE++;
            }
            else if( toRead.winddirection.equals( "E" ) )
            {
                countE++;
            }
            else if( toRead.winddirection.equals( "SEE" ) )
            {
                countSEE++;
            }
            else if( toRead.winddirection.equals( "SE" ) )
            {
                countSE++;
            }
            else if( toRead.winddirection.equals( "SSE" ) )
            {
                countSSE++;
            }
            else if( toRead.winddirection.equals( "S" ) )
            {
                countS++;
            }
            else if( toRead.winddirection.equals( "SSW" ) )
            {
                countSSW++;
            }
            else if( toRead.winddirection.equals( "SW" ) )
            {
                countSW++;
            }
            else if( toRead.winddirection.equals( "SWW" ) )
            {
                countSWW++;
            }
            else if( toRead.winddirection.equals( "W" ) )
            {
                countW++;
            }
            else if( toRead.winddirection.equals( "NWW" ) )
            {
                countNWW++;
            }
            else if( toRead.winddirection.equals( "NW" ) )
            {
                countNW++;
            }
            else if( toRead.winddirection.equals( "NNW" ) )
            {
                countNNW++;
            }
            
        }
        // calcluate the averages   
        MeanTemperature = temperatureSum / numPoints;
        MeanWindSpeed = windSpeedSum / numPoints;   

        // find the max prevailing winddirection
        int maxCount = countN;
        PrevailingWind = "N";
        if( maxCount < countNNE )
        {
            maxCount = countNNE;
            PrevailingWind = "NNE";
        }       
        if( maxCount < countNE )
        {
            maxCount = countNE;
            PrevailingWind = "NE";
        }
        if( maxCount < countNEE )
        {
            maxCount = countNEE;
            PrevailingWind = "NEE";
        }
        if( maxCount < countE )
        {
            maxCount = countE;
            PrevailingWind = "E";
        }
        if( maxCount < countSEE )
        {
            maxCount = countSEE;
            PrevailingWind = "SEE";
        }
        if( maxCount < countSE )
        {
            maxCount = countSE;
            PrevailingWind = "SE";
        }
        if( maxCount < countSSE )
        {
            maxCount = countSSE;
            PrevailingWind = "SSE";
        }
        if( maxCount < countS )
        {
            maxCount = countS;
            PrevailingWind = "S";
        }
        if( maxCount < countSSW )
        {
            maxCount = countSSW;
            PrevailingWind = "SSW";
        }
        if( maxCount < countSW )
        {
            maxCount = countSW;
            PrevailingWind = "SW";
        }
        if( maxCount < countSWW )
        {
            maxCount = countSWW;
            PrevailingWind = "SWW";
        }
        if( maxCount < countW )
        {
            maxCount = countW;
            PrevailingWind = "W";
        }
        if( maxCount < countNWW )
        {
            maxCount = countNWW;
            PrevailingWind = "NWW";
        }
        if( maxCount < countNW )
        {
            maxCount = countNW;
            PrevailingWind = "NW";
        }
        if( maxCount < countNNW )
        {
            maxCount = countNNW;
            PrevailingWind = "NNW";
        }   
    }
}
