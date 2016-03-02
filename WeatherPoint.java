
import java.util.*;
import java.time.LocalDateTime;

public class WeatherPoint implements Comparable<WeatherPoint> 
{
    public String dateString;
    public LocalDateTime date;
    public double temperature;
    public double humidity;
    public double barometer;
    public double windspeed;
    public String winddirection;
    public double windgust;
    public double windchill;
    public double heatindex;
    public double uvindex;
    public double rainfall;
    
    public String toString()
    {
        return "Date:\t\t" + dateString +
                "\nTemperature:\t" + temperature +
                "\nHumidity:\t" + humidity +
                "\nBarometer:\t" + barometer +
                "\nWindspeed:\t" + windspeed +
                "\nWinddirection:\t" + winddirection +
                "\nWindgust:\t" + windgust +
                "\nWindchill:\t" + windchill +
                "\nHeatindex:\t" + heatindex +
                "\nUV Index:\t" + uvindex +
                "\nRainfall:\t" + rainfall;
    }

    @Override public int compareTo(WeatherPoint o)
    {
        if( date.isBefore(o.date))
            return -1;
        else if (date.isAfter(o.date))
            return 1;
        else return 0;
    }


}
