/*
 * WeatherGraph.java
 *
 * This is the graph for the program. It creates a weather graph for the
 * set of weather points it's passed in. It also holds the renderer for graph, this
 * includes the tooltip. It also shows and hides series lines.
 *
 *
 */

import java.util.*;
import java.util.Date;
import java.time.LocalDateTime;

import java.awt.Color;
import java.awt.BasicStroke;
import javax.swing.JFrame;
import javax.swing.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.axis.DateAxis;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;

import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class WeatherGraph extends ApplicationFrame
{
    private JFreeChart weatherGraph;
    private XYDataset graphData;
    private XYLineAndShapeRenderer renderer;
    private int seriesCount;
    private WeatherToolTip toolTip;

    // The constructor for the graph    
    // Takes in the data to graph
    public WeatherGraph( List<WeatherPoint> weatherPoints )
    {
        super( "" );
        
        // for the header of the graph
        String startDate = "NULL";
        String endDate = "NULL";

        // this retrieves time range for the data to be placed as the graph title        
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

        // make the graph look purrrrtttyyyyy
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

        // the weather tool tip is generated
        toolTip = new WeatherToolTip();
        renderer.setBaseToolTipGenerator( toolTip );
        
        plot.setRenderer( renderer ); 
    }

    // show the view of the line
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

        // all the 'lines' being graphed
        TimeSeries temperature = new TimeSeries( "Temperature(F)" );
        TimeSeries humidity = new TimeSeries( "Humidity(%)" );
        TimeSeries windSpeed = new TimeSeries( "Wind Speed(mph)" );
        TimeSeries windGust = new TimeSeries( "Wind Gust(mph)" );
        TimeSeries windChill = new TimeSeries( "Wind Chill" );
        TimeSeries heatIndex = new TimeSeries( "Heat Index" );
        TimeSeries barometer = new TimeSeries( "Barometer(inches)" );
        TimeSeries uvindex = new TimeSeries( "UV Index" );
        TimeSeries rainfall = new TimeSeries( "Rainfall(inches)" );
        
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
