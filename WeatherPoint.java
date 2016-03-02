
import java.util.*;
import java.time.LocalDateTime;

public class WeatherPoint implements Comparable<WeatherPoint> 
{
    public String dateString;
    public LocalDateTime date;
    public double temperature = 0;
    public double humidity = 0;
    public double barometer = 0;
    public double windspeed = 0;
    public String winddirection = "";
    public double windgust = 0;
    public double windchill = 0;
    public double heatindex = 0;
    public double uvindex = 0;
    public double rainfall = 0;
    
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
