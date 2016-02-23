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



//import org.jfree.ui.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class WeatherGraph extends ApplicationFrame
{
    public WeatherGraph( String applicationTitle, String chartTitle )
    {
        super( applicationTitle );
        JFreeChart weatherGraph = ChartFactory.createXYLineChart(
            chartTitle,
            "x axis",
            "y axis",
            graphPoint(),
            PlotOrientation.VERTICAL,
            true, true, false);

        ChartPanel chartPanel = new ChartPanel( weatherGraph );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560, 367 ) );
        final XYPlot plot = weatherGraph.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint( 0, Color.RED );
        //renderer.setSeriesPaint( 1, Color.GREEN );
        //renderer.setSeriesPaint( 2, Color.YELLOW );
    
        renderer.setSeriesStroke( 0, new BasicStroke( 4.0f ) );
        //renderer.setSeriesStroke( 1, new BasicStroke( 3.0f ) );
        //renderer.setSeriesStroke( 2, new BasicStroke( 2.0f ) );
    
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
    }

    private XYDataset graphPoint()
    {
        final XYSeries firefox = new XYSeries( "Firefox" );   
        firefox.add( 1.0, 1.0 );
        firefox.add( 2.0, 4.0 );
        firefox.add( 3.0, 3.0 );
    
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries( firefox );

        System.out.println( "I'm going to skin people." );
        return dataset;
    }

    public static void main( String[] args )
    {
        System.out.println( "I'm Marcus!" );
        WeatherGraph chart = new WeatherGraph( "Weather!!", "Yea!!" );
        chart.pack();
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }   

}
