// compile: javac -cp .:jcommon.jar:jfreechart.jar WeatherGraph.java
// run: java -cp .:jcommon.jar:jfreechart.jar WeatherGraph


//import org.jdom2.*;
//import org.jsom2.input.SAXBuilder;
//import java.io.IOException;
import java.util.*;
import java.util.Date;
import java.time.LocalDateTime;
import javax.swing.*;
//import java.awt.*;
import java.awt.Color;
import java.awt.BasicStroke;
import javax.swing.JFrame;

//import org.jfree.chart.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
//import org.jfree.data.xy.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.jfree.chart.labels.StandardXYToolTipGenerator;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.time.TimeSeries;
import org.jfree.chart.axis.DateAxis;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;

//import org.jfree.ui.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class WeatherGraph extends ApplicationFrame
{
    private JFreeChart weatherGraph;
    private XYDataset graphData;
    private XYLineAndShapeRenderer renderer;
    private int seriesCount = 10;
    public WeatherToolTip toolTip;

    // The constructor for the graph    
    // Takes in the data to graph
    public WeatherGraph( List<WeatherPoint> weatherPoints )
    {
        ///it needs this if you are solely running this file
        super( "eatshit" );
        String startDate = "NULL";
        String endDate = "NULL";
        if( weatherPoints.size() > 0)
        {
            startDate = weatherPoints.get(0).date.toLocalDate().toString();
            endDate = weatherPoints.get(weatherPoints.size()-1).date.toLocalDate().toString();
        }
        // the data to be plotted on the graph
        graphData = createGraphData( weatherPoints );

        // create the graph with the data
        weatherGraph = ChartFactory.createTimeSeriesChart( 
            ("From: " + startDate + " to " + endDate), // title of chart
            "", // x axis label
            "",  // y axis label
            graphData, // data
            true,   // create legend?
            true,   // generate tooltips?
            false   // generate urls?
        );

        weatherGraph.setBackgroundPaint( Color.LIGHT_GRAY );
        XYPlot plot = (XYPlot) weatherGraph.getPlot();
        plot.setBackgroundPaint( Color.BLACK );
        
        // set the bounds of the graph
        plot.getRangeAxis().setLowerBound( -30 );
        plot.getRangeAxis().setUpperBound( 120 );

        renderer = (XYLineAndShapeRenderer) plot.getRenderer();

        // inital all of the lines to be hidden
        for( int i = 0; i < graphData.getSeriesCount(); i++ )
        {
            renderer.setSeriesLinesVisible( i, false );
        }

        StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(
            StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
            new SimpleDateFormat("d-MMM-YYY"), new DecimalFormat("0.00") );
        renderer.setToolTipGenerator(g);

        plot.setRenderer( renderer );
 
    }

    // toggle the view of the line
    public void showLine( int series )
    {
        renderer.setSeriesLinesVisible( series, true );   
    }

    // hide the line
    public void hideLine( int series )
    {
        renderer.setSeriesLinesVisible( series, false );
    }

    // return the graph
    public JFreeChart getGraph()
    {
        return weatherGraph;
    }

    // creates the xy data to graph
    private XYDataset createGraphData( List<WeatherPoint> weatherPoints )
    {
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        // to iterate through the list of weather points
        Iterator<WeatherPoint> pointsIterator = weatherPoints.iterator();
        WeatherPoint toGraph;

        // xyseries
        TimeSeries temperature = new TimeSeries( "Temperature" );
        TimeSeries humidity = new TimeSeries( "Humidity" );
        TimeSeries windSpeed = new TimeSeries( "Wind Speed" );
        TimeSeries windGust = new TimeSeries( "Wind Gust" );
        TimeSeries windChill = new TimeSeries( "Wind Chill" );
        TimeSeries heatIndex = new TimeSeries( "Heat Index" );
        TimeSeries barometer = new TimeSeries( "Barometer" );
        TimeSeries uvindex = new TimeSeries( "UV Index" );
        TimeSeries rainfall = new TimeSeries( "Rainfall" );

        // go through each point
        while( pointsIterator.hasNext() )
        {
            // weather point to graph
            toGraph = pointsIterator.next();

             // get the value to graph
            Minute now = new Minute( toGraph.date.getMinute(), toGraph.date.getHour(), toGraph.date.getDayOfMonth(), toGraph.date.getMonthValue(), toGraph.date.getYear());

            // add graphing point
            temperature.add( now, toGraph.temperature );
            humidity.add( now, toGraph.humidity );
            windSpeed.add( now, toGraph.windspeed );
            windGust.add( now, toGraph.windgust );
            windChill.add( now, toGraph.windchill );
            heatIndex.add( now, toGraph.heatindex );
            uvindex.add( now, toGraph.uvindex );
            barometer.add( now, toGraph.barometer );
            rainfall.add( now, toGraph.rainfall );
        }

        // Add these to dataset
        dataset.addSeries( temperature );
        dataset.addSeries( humidity );
        dataset.addSeries( windSpeed );
        dataset.addSeries( windGust ); 
        dataset.addSeries( windChill );
        dataset.addSeries( heatIndex );
        dataset.addSeries( uvindex );  
        dataset.addSeries( barometer );
        dataset.addSeries( rainfall ); 

        return dataset;
    }
}
