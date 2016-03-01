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

//import org.jfree.chart.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

//import org.jfree.data.xy.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

// pending librarys
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
    public JFreeChart weatherGraph;
    public XYDataset graphData;
    
    public WeatherGraph( String applicationTitle, String chartTitle, List<WeatherPoint> weatherPoints, String graphType )
    {
        ///it needs this for some reason?
        super( applicationTitle );

        graphData = createGraphData( weatherPoints );

        weatherGraph = ChartFactory.createTimeSeriesChart( 
            chartTitle, // title of chart
            "Time", // x axis label
            "Temperature",  // y axis label
            graphData, // data
            true,   // create legend?
            true,   // generate tooltips?
            false   // generate urls?
        );

            ChartPanel weatherGraph1 = new ChartPanel( weatherGraph );
            weatherGraph1.setPreferredSize( new java.awt.Dimension( 560, 367 ) );
            final XYPlot plot = weatherGraph.getXYPlot();
            setContentPane( weatherGraph1 );
    }
    
    public JFreeChart getGraph()
    {
        return weatherGraph;
    }

    private XYDataset createGraphData( List<WeatherPoint> weatherPoints )
    {
        TimeSeries temperatureGraph = new TimeSeries( "Temperature" );
        final TimeSeriesCollection dataset = new TimeSeriesCollection();

        // to iterate through the list of weather points
        Iterator<WeatherPoint> pointsIterator = weatherPoints.iterator();
        WeatherPoint toGraph;


        // go through each point
        while( pointsIterator.hasNext() )
        {
            // weather point to graph
            toGraph = pointsIterator.next();

             // get the value to graph
            Minute now = new Minute( toGraph.date.getMinute(), toGraph.date.getHour(), toGraph.date.getDayOfMonth(), toGraph.date.getMonthValue(), toGraph.date.getYear());

            // add graphing point
            temperatureGraph.add( now, toGraph.temperature );

        }

        dataset.addSeries( temperatureGraph );

        //System.out.println( "I'm going to skin people." );
        return dataset;
    }
    
/*
    public static void main( String[] args )
    {
        System.out.println( "I'm Marcus!" );
        WeatherGraph chart = new WeatherGraph( "Weather Station", "Temperature" );
        chart.pack();
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }   
*/
}
