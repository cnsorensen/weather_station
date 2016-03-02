public class WeatherStats
{
    public float HighTemperature = 0.0;
    public float LowTemperature = 0.0;
    public float AvgTemperature = 0.0;
    public float MeanWind = 0.0;
    public float MaxWind = 0.0;
    public String PrevailingWind = "";
    public String WindDirection = "";

    public WeatherStats( List<WeatherPoint> weatherPoints )
    {
 //       GenerateStats( weatherPoints );   
    }
/*
    private GenerateStats( List<WeatherPoint> weatherPoints )
    {
        // to iterate through the list of points
        Iterator<WeatherPoint> pointsIterator = weatherPoints.iterator();
        WeatherPoint currentWeatherPoint;
        
        while( pointsIterator.hasNext() )
        {

        }
    }
*/
}
