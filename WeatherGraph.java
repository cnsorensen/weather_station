// compile: javac -cp .:jcommon.jar:jfreechart.jar WeatherGraph.java
// run: java -cp .:jcommon.jar:jfreechart.jar WeatherGraph


//import org.jdom2.*;
//import org.jsom2.input.SAXBuilder;
//import java.io.IOException;
import java.util.*;
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

//import org.jfree.ui.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class WeatherGraph extends ApplicationFrame
{
    public WeatherGraph( String applicationTitle, String chartTitle, List<WeatherPoint> weatherPoints )
    {
        super( applicationTitle );
        ///JFreeChart weatherGraph = ChartFactory.createXYLinePlot(
        //JFreeChart weatherGraph = ChartFactory.createScatterPlot(
        JFreeChart weatherGraph = ChartFactory.createTimeSeriesChart( 
            chartTitle, // title of chart
            "Humidity", // x axis label
            "Temperature",  // y axis label
            graphPoints( weatherPoints ), // data
            //PlotOrientation.VERTICAL,
            true,   // create legend?
            true,   // generate tooltips?
            false   // generate urls?
        );

        ChartPanel chartPanel = new ChartPanel( weatherGraph );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560, 367 ) );
        final XYPlot plot = weatherGraph.getXYPlot();
       
        //use to make  line graph
        ///XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        // set the color
        ///renderer.setSeriesPaint( 0, Color.RED );
        //renderer.setSeriesPaint( 1, Color.GREEN );
        //renderer.setSeriesPaint( 2, Color.YELLOW );
        
        // set the width of the stroke
        //renderer.setSeriesStroke( 0, new BasicStroke( 4.0f ) );
        ///renderer.setSeriesStroke( 1, new BasicStroke( 3.0f ) );
        //renderer.setSeriesStroke( 2, new BasicStroke( 2.0f ) );
    
        ///plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    private XYDataset graphPoints( List<WeatherPoint> weatherPoints )
    {
        final XYSeries temperature = new XYSeries( "Temperature" );    
        Iterator<WeatherPoint> pointsIterator = weatherPoints.iterator();
        WeatherPoint toGraph;

        // add points
        while( pointsIterator.hasNext() )
        {
            toGraph = pointsIterator.next();
 
            //temperature.add( toGraph.date, toGraph.temperature );
            //temperature.add( toGraph.temperature, toGraph.humidity );
            //new Day( day, month, year)
            

            temperature.add( new Date( toGraph.date.getDayOfMonth(), toGraph.date.getMonthValue(), toGraph.date.getYear() ), toGraph.temperature );
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries( temperature );

        System.out.println( "I'm going to skin people." );
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
