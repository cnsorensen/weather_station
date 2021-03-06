/* MainWindow.java */
/*
 * Program 1: Weather Station
 * South Dakota School of Mines and Technology
 * CSC 468 Graphic User Interface
 * Spring 2016
 * Dr. John Weiss
 * Jason Anderson, Marcus Haberling, Chrissy Sorensen
 *
 * Compilation: javac -cp .:jdom.jar:jcommon.jar:jfreechart.jar MainWindow.java
 *              or use the makefile
 *
 * Usage: java -cp .:jdom.jar:jcommon.jar:jfreechart.jar MainWindow 
 *
 * MainWindow is the main component of this project. It is the user interface for the
 * program. It holds all of the components and their handlers. It shows a graph with
 * checkboxes, radio buttons, and statistics. The graph and statistics get updated 
 * from the user's input through the components.
 *
 */

import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javafx.stage.FileChooser;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.JFreeChart;
import java.lang.Boolean;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.event.ChartChangeEvent;
import java.awt.*;
import javax.swing.BoxLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.JTable;
import java.time.LocalDateTime;

public class MainWindow extends JFrame implements ItemListener, ActionListener
{

    /**
     * Creates new form MainWindow
     */
    public MainWindow()
    {
        initComponents();
    }

/*
* Function: initComponents
*
* Description: Initializes the GUI components on opening of the program.
*/

    private void initComponents()
    {
		TimeFrame = new ButtonGroup();
        FileChooser = new javax.swing.JFileChooser();
        Temperature = new javax.swing.JCheckBox("Temperature");
        Humidity = new javax.swing.JCheckBox("Humidity");
        WindSpeed = new javax.swing.JCheckBox("Wind Speed");
        WindGust = new javax.swing.JCheckBox("Wind Gust");
        WindChill = new javax.swing.JCheckBox("Wind Chill");
        HeatIndex = new javax.swing.JCheckBox("Heat Index");
        UVIndex = new javax.swing.JCheckBox("UV Index");
        Barometer = new javax.swing.JCheckBox("Barometer");
        Rainfall = new javax.swing.JCheckBox("Rainfall");
        Daily = new javax.swing.JRadioButton("Daily");
        Weekly = new javax.swing.JRadioButton("Weekly");
        Monthly = new javax.swing.JRadioButton("Monthly");
        Yearly = new javax.swing.JRadioButton("Yearly");
        weatherPoints = new ArrayList<WeatherPoint>();
        weatherGraph = new WeatherGraph( weatherPoints );       
        GraphPanel = new ChartPanel( weatherGraph.getGraph() );           
        MenuBar = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Seperator = new javax.swing.JPopupMenu.Separator();
        Exit = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenu();
        XMLLoaded = false;
        XMLFileName = "";
		Panel = new javax.swing.JPanel();
		TimePanel = new javax.swing.JPanel();
		GraphOptionPanel = new javax.swing.JPanel();
		StatsPanel = new javax.swing.JPanel();
		Stats = new javax.swing.JPanel();
		StatsInfo = new javax.swing.JPanel();
		StatsType = new javax.swing.JPanel();
		Arrows = new javax.swing.JPanel();
	 	MeanTemperature = new javax.swing.JLabel();
	 	HighLowTemperature = new javax.swing.JLabel();
	 	MeanWindSpeed = new javax.swing.JLabel();
		MaxWindSpeed = new javax.swing.JLabel();
	 	PrevailingWindDirection = new javax.swing.JLabel();
	 	MeanTemperatureInfo = new javax.swing.JLabel();
		HighLowTemperatureInfo = new javax.swing.JLabel();
		MeanWindSpeedInfo = new javax.swing.JLabel();
		MaxWindSpeedInfo = new javax.swing.JLabel();
		PrevailingWindDirectionInfo = new javax.swing.JLabel();
		Left = new BasicArrowButton(BasicArrowButton.WEST);
		Right = new BasicArrowButton(BasicArrowButton.EAST);
		    weatherPoints = new ArrayList<WeatherPoint>();
		weatherStats = new WeatherStats( weatherPoints );

	 	MeanTemperature.setText("Mean Temperature");
	 	HighLowTemperature.setText("High/Low Temperature");
	 	MeanWindSpeed.setText("Mean Wind Speed");
		MaxWindSpeed.setText("Max Wind Speed");
	 	PrevailingWindDirection.setText("Prevailing Wind Direction");

		

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Initialize the key event for the checkboxes
		Rainfall.setMnemonic(KeyEvent.VK_C);
		Temperature.setMnemonic(KeyEvent.VK_C);
		Humidity.setMnemonic(KeyEvent.VK_C);
		WindSpeed.setMnemonic(KeyEvent.VK_C);
		WindGust.setMnemonic(KeyEvent.VK_C);
		WindChill.setMnemonic(KeyEvent.VK_C);
		HeatIndex.setMnemonic(KeyEvent.VK_C);
		UVIndex.setMnemonic(KeyEvent.VK_C);
		Barometer.setMnemonic(KeyEvent.VK_C);

		setCheckBoxDefault();

		//Give the checkboxes item listeners
		Temperature.addItemListener(this);
		Humidity.addItemListener(this);
		WindSpeed.addItemListener(this);
		WindGust.addItemListener(this);
		WindChill.addItemListener(this);
		HeatIndex.addItemListener(this);
		UVIndex.addItemListener(this);
		Barometer.addItemListener(this);
		Rainfall.addItemListener(this);

		//Initialize the radio buttons
		Daily.setMnemonic(KeyEvent.VK_C);
		Daily.setActionCommand("Daily");
		Daily.setSelected(true);
	
		Weekly.setMnemonic(KeyEvent.VK_C);
		Weekly.setActionCommand("Weekly");

		Monthly.setMnemonic(KeyEvent.VK_C);
		Monthly.setActionCommand("Monthly");

		Yearly.setMnemonic(KeyEvent.VK_C);
		Yearly.setActionCommand("Yearly");

		Left.setMnemonic(KeyEvent.VK_C);
		Left.setActionCommand("Left");

		Right.setMnemonic(KeyEvent.VK_C);
		Right.setActionCommand("Right");

		//Add the radio buttons to the TimeFrame button group
		TimeFrame.add(Daily);
		TimeFrame.add(Weekly);
		TimeFrame.add(Monthly);
		TimeFrame.add(Yearly);

		//Give the radio buttons action listeners
		Daily.addActionListener(this);
		Weekly.addActionListener(this);
		Monthly.addActionListener(this);
		Yearly.addActionListener(this);

		Left.addActionListener(this);
		Right.addActionListener(this);

		//Initialize the menu bar items
        File.setText("File");

        Open.setText("Open");
        Open.addActionListener(this);
        
		File.add(Open);
        File.add(Seperator);

        Exit.setText("Exit");
		Exit.addActionListener(this);
		    
		File.add(Exit);
        MenuBar.add(File);
        Edit.setText("Edit");
        setJMenuBar(MenuBar);

		//Layouts for the page
		Panel.setLayout(new BorderLayout());
		TimePanel.setLayout(new BoxLayout(TimePanel, BoxLayout.Y_AXIS));
		GraphOptionPanel.setLayout(new BoxLayout(GraphOptionPanel, BoxLayout.Y_AXIS));
		Arrows.setLayout(new FlowLayout());
		StatsPanel.setLayout(new BoxLayout(StatsPanel, BoxLayout.Y_AXIS));
		StatsType.setLayout(new BoxLayout(StatsType, BoxLayout.Y_AXIS));
		Stats.setLayout(new BoxLayout(Stats, BoxLayout.X_AXIS));

		//Add radio buttons to Time Panel
		TimePanel.add(Daily);
		TimePanel.add(Weekly);
		TimePanel.add(Monthly);
		TimePanel.add(Yearly);
		//Add checkboxes to Graph Option Panel
		GraphOptionPanel.add(Temperature);
		GraphOptionPanel.add(Humidity);
		GraphOptionPanel.add(WindSpeed);
		GraphOptionPanel.add(WindGust);
		GraphOptionPanel.add(WindChill);
		GraphOptionPanel.add(HeatIndex);
		GraphOptionPanel.add(UVIndex);
		GraphOptionPanel.add(Barometer);
		GraphOptionPanel.add(Rainfall);
		//Add basic arrow buttons to the Arrows panel	
		Arrows.add(Left);
		Arrows.add(Right);
		//Arrange the Graph stat types  together
		StatsType.add(MeanTemperature);
		StatsType.add(HighLowTemperature);
		StatsType.add(MeanWindSpeed);
		StatsType.add(MaxWindSpeed);
		StatsType.add(PrevailingWindDirection);
		//Add the StatsType and the StatsInfo to Stats, StatsInfo gets set in update()
		Stats.add(StatsType);
		Stats.add(StatsInfo);
		//Arrange the Arrows and Stats panels below the chart, radio and checkboxes
		StatsPanel.add(Arrows);
		StatsPanel.add(Stats);
		//Add all the various panels onto one panel
		Panel.add(StatsPanel, BorderLayout.SOUTH);
		Panel.add(TimePanel, BorderLayout.WEST);
		Panel.add(GraphPanel, BorderLayout.CENTER);
		Panel.add(GraphOptionPanel, BorderLayout.EAST);
	
		//Set the content pane to the panel that holds everything
		setContentPane(Panel);
		this.setTitle("Weather Station");
		this.pack();
		this.setVisible(true);   
    }

	//Sets the default for the checkboxes
	public void setCheckBoxDefault()
	{
		Rainfall.setSelected(false);
		Temperature.setSelected(true);
		Humidity.setSelected(false);
		WindSpeed.setSelected(false);
		WindGust.setSelected(false);
		WindChill.setSelected(false);
		HeatIndex.setSelected(false);
		UVIndex.setSelected(false);
		Barometer.setSelected(false);
	}

	//Action event handler for the checkboxes
	public void itemStateChanged(ItemEvent e)
	{
		Object source = e.getItemSelectable();

		if(source == Temperature)
		{
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
			weatherGraph.showLine( SeriesList.TEMPERATURE );
		    }
		    else
		    {
			weatherGraph.hideLine( SeriesList.TEMPERATURE );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), weatherStats);
		}

		else if(source == Humidity)
		{
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
			weatherGraph.showLine( SeriesList.HUMIDITY  );
		    }
		    else
		    {
			weatherGraph.hideLine( SeriesList.HUMIDITY  );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), weatherStats);
		}

		else if(source == WindSpeed)
		{
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
			weatherGraph.showLine( SeriesList.WINDSPEED  );
		    }
		    else
		    {
			weatherGraph.hideLine( SeriesList.WINDSPEED );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), weatherStats);
		}

		else if(source == WindGust)
		{
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
			weatherGraph.showLine( SeriesList.WINDGUST );
		    }
		    else
		    {
			weatherGraph.hideLine( SeriesList.WINDGUST );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), weatherStats);
		}

		else if(source == WindChill)
		{
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
			weatherGraph.showLine( SeriesList.WINDCHILL );
		    }
		    else
		    {
			weatherGraph.hideLine( SeriesList.WINDCHILL );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), weatherStats);
		}

		else if(source == HeatIndex)
		{
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
			weatherGraph.showLine( SeriesList.HEATINDEX );
		    }
		    else
		    {
			weatherGraph.hideLine( SeriesList.HEATINDEX );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), weatherStats);
		}

		else if(source == UVIndex)
		{
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
			weatherGraph.showLine( SeriesList.UVINDEX );
		    }
		    else
		    {
			weatherGraph.hideLine( SeriesList.UVINDEX );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), weatherStats);
		}

		else if(source == Barometer)
		{
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
			weatherGraph.showLine( SeriesList.BAROMETER );
		    }
		    else
		    {
			weatherGraph.hideLine( SeriesList.BAROMETER );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), weatherStats);
		}
		else if(source == Rainfall)
		{
		    if( Rainfall.isSelected() )
		    {
			weatherGraph.showLine( SeriesList.RAINFALL );
		    }
		    else
		    {
			weatherGraph.hideLine( SeriesList.RAINFALL );
		    }
		    update( GraphPanel, weatherGraph.getGraph(), weatherStats);
		}
	}

	//Action event handler for radio buttons, arrows, and Open/Exit.
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getActionCommand();

		if(source == "Daily")
		{
	    	changeDateRange();
	    	weatherGraph = new WeatherGraph( weatherPoints.subList(graphStartPoint, graphEndPoint));
			weatherStats = new WeatherStats(weatherPoints.subList(graphStartPoint, graphEndPoint));
		    persistGraphLines();

		}
	
		else if(source == "Weekly")
		{
	    	changeDateRange();
	    	weatherGraph = new WeatherGraph( weatherPoints.subList(graphStartPoint, graphEndPoint));
			weatherStats = new WeatherStats(weatherPoints.subList(graphStartPoint, graphEndPoint));
		    persistGraphLines();
		}

		else if(source == "Monthly")
		{
	    	changeDateRange();
	    	weatherGraph = new WeatherGraph( weatherPoints.subList(graphStartPoint, graphEndPoint));
			weatherStats = new WeatherStats(weatherPoints.subList(graphStartPoint, graphEndPoint));
		    persistGraphLines();
		}

		else if(source == "Yearly")
		{
	    	changeDateRange();
	    	weatherGraph = new WeatherGraph( weatherPoints.subList(graphStartPoint, graphEndPoint));
			weatherStats = new WeatherStats(weatherPoints.subList(graphStartPoint, graphEndPoint));
		    persistGraphLines();
		}

		else if(source == "Left")
		{
			moveDateBackward();
            weatherGraph = new WeatherGraph( weatherPoints.subList(graphStartPoint, graphEndPoint));
            weatherStats = new WeatherStats(weatherPoints.subList(graphStartPoint, graphEndPoint));
		    persistGraphLines();
		}

		else if(source == "Right")
		{
			moveDateForward();
	    	weatherGraph = new WeatherGraph( weatherPoints.subList(graphStartPoint, graphEndPoint));
			weatherStats = new WeatherStats(weatherPoints.subList(graphStartPoint, graphEndPoint));
		    persistGraphLines();        
		}

		else if(source == "Open")
		{
		    // filter to only xml files		 
		    FileChooser.setCurrentDirectory(new java.io.File("."));
		    FileChooser.setDialogTitle("Select Data Folder");
		    FileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
		    // show the dialog box to select a file from
		    int returnVal = FileChooser.showOpenDialog( null );
		    
		    // if file selection was a success		    
		    if( returnVal == JFileChooser.APPROVE_OPTION )
		    {
		    	XMLLoaded = true;
		    	XMLFileName = FileChooser.getSelectedFile().getAbsolutePath();
            	        
		        GraphPanel.removeAll();
		        weatherPoints = ParseXML.parseDirectory( XMLFileName ); //get files
	            Collections.sort(weatherPoints);
	            graphStartPoint = 0;
	            graphEndPoint = 0;
                changeDateRange(); //change to selected date range then display
		        weatherGraph = new WeatherGraph( weatherPoints.subList(graphStartPoint, graphEndPoint));
		        persistGraphLines();		    
            }

		}
		else if(source == "Exit")
		{
			System.exit(0);
		}
	}

	//Updates the graph when a checkbox, radio button, or arrow button
    public void update(ChartPanel GraphPanel, JFreeChart weatherGraph, WeatherStats weatherStats)
    {
		//Updates and or sets the text for the statistics below the graph
		MeanTemperatureInfo.setText("  " + Double.toString(weatherStats.MeanTemperature));
		HighLowTemperatureInfo.setText("  " + Double.toString(weatherStats.HighTemperature) + " | " + Double.toString(weatherStats.LowTemperature));
		MeanWindSpeedInfo.setText("  " + Double.toString(weatherStats.MeanWindSpeed));
		MaxWindSpeedInfo.setText("  " + Double.toString(weatherStats.MaxWindGust));
		PrevailingWindDirectionInfo.setText("  " + weatherStats.PrevailingWind);

		//Add the new info to the StatsInfo panel
		StatsInfo.setLayout(new BoxLayout(StatsInfo, BoxLayout.Y_AXIS));
		StatsInfo.add(MeanTemperatureInfo);
		StatsInfo.add(HighLowTemperatureInfo);
		StatsInfo.add(MeanWindSpeedInfo);
		StatsInfo.add(MaxWindSpeedInfo);
		StatsInfo.add(PrevailingWindDirectionInfo);
		
		//remove the old information from the panels, repaints the graph with the new data
		Panel.removeAll();
		Arrows.removeAll();
		GraphPanel = new ChartPanel(weatherGraph);
		GraphPanel.repaint();

		//This to the end of the function was supposed to be in a different function,
		//but there was a bug where the data was not updating. 
		//(same code that is in initcomponents())
		Panel.setLayout(new BorderLayout());
		TimePanel.setLayout(new BoxLayout(TimePanel, BoxLayout.Y_AXIS));
		GraphOptionPanel.setLayout(new BoxLayout(GraphOptionPanel, BoxLayout.Y_AXIS));
		Arrows.setLayout(new FlowLayout());
		StatsPanel.setLayout(new BoxLayout(StatsPanel, BoxLayout.Y_AXIS));
		StatsType.setLayout(new BoxLayout(StatsType, BoxLayout.Y_AXIS));
		Stats.setLayout(new BoxLayout(Stats, BoxLayout.X_AXIS));

		TimePanel.add(Daily);
		TimePanel.add(Weekly);
		TimePanel.add(Monthly);
		TimePanel.add(Yearly);
		GraphOptionPanel.add(Temperature);
		GraphOptionPanel.add(Humidity);
		GraphOptionPanel.add(WindSpeed);
		GraphOptionPanel.add(WindGust);
		GraphOptionPanel.add(WindChill);
		GraphOptionPanel.add(HeatIndex);
		GraphOptionPanel.add(UVIndex);
		GraphOptionPanel.add(Barometer);
		GraphOptionPanel.add(Rainfall);
		Arrows.add(Left);
		Arrows.add(Right);
		StatsType.add(MeanTemperature);
		StatsType.add(HighLowTemperature);
		StatsType.add(MeanWindSpeed);
		StatsType.add(MaxWindSpeed);
		StatsType.add(PrevailingWindDirection);
		Stats.add(StatsType);
		Stats.add(StatsInfo);
		StatsPanel.add(Arrows);
		StatsPanel.add(Stats);
		Panel.add(StatsPanel, BorderLayout.SOUTH);
		Panel.add(TimePanel, BorderLayout.WEST);
		Panel.add(GraphPanel, BorderLayout.CENTER);
		Panel.add(GraphOptionPanel, BorderLayout.EAST);

		setContentPane(Panel);
		this.setTitle("Weather Station");
		this.pack();
		this.setVisible(true);
    }

    public static void main(String args[]) 
    {
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (InstantiationException ex) 
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }//</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                MainWindow mainWindow = new MainWindow();
				mainWindow.setTitle("Weather Station");
				mainWindow.pack();
				RefineryUtilities.centerFrameOnScreen(mainWindow);
				mainWindow.setVisible(true);
            }
        });
    }

    /*
    * Function: generateDate
    * Author: Marcus Haberling
    * Changes the date range base on which radial button is selected
    */
    private void changeDateRange()
    {
        if(weatherPoints.size() == 0)
            return;
        LocalDateTime startTarget;
        LocalDateTime endTarget;
        if( Yearly.isSelected() )
        {   
            startTarget = LocalDateTime.of(weatherPoints.get(graphStartPoint).date.getYear(),1,1,0,0,0);
            endTarget = startTarget.plusYears(1);
            
        }
        else if( Monthly.isSelected() )
        {
            startTarget = LocalDateTime.of(weatherPoints.get(graphStartPoint).date.getYear(), 
                                           weatherPoints.get(graphStartPoint).date.getMonth().getValue(), 
                                           1,0,0,0);
            endTarget = startTarget.plusMonths(1);
        }
        else if( Daily.isSelected() )
        {
            startTarget = LocalDateTime.of(weatherPoints.get(graphStartPoint).date.getYear(),
                                           weatherPoints.get(graphStartPoint).date.getMonth().getValue(),
                                           weatherPoints.get(graphStartPoint).date.getDayOfMonth(),
                                           0,0,0);
            endTarget = startTarget.plusDays(1);
        }
        
        else
        {
            //if weekly we need to move to the front of the week
            int year = weatherPoints.get(graphStartPoint).date.getYear();
            int month = weatherPoints.get(graphStartPoint).date.getMonth().getValue();
            int day = weatherPoints.get(graphStartPoint).date.getDayOfMonth();
            startTarget = LocalDateTime.of(year, month, day, 0 ,0 ,0 );
            startTarget = startTarget.plusDays(startTarget.getDayOfWeek().getValue()*-1+1);
            endTarget = startTarget.plusWeeks(1);
        }

        //loop for starting pointer
        for ( int x = 0; x < weatherPoints.size(); x = x + 1)
        {
            graphStartPoint = x;//isAfter(ChronoLocalDateTime<?> other)

            if( weatherPoints.get(x).date.isAfter(startTarget) )
            {                
                //graphStartPoint = x -1;
                break;
            }
        }

        if( graphStartPoint >= weatherPoints.size())
            graphStartPoint = 0;
        //loop for ending pointer
        for ( int x = graphStartPoint; x < weatherPoints.size(); x=x+1)
        {
            graphEndPoint = x;
            if(weatherPoints.get(x).date.isAfter(endTarget) )
            {                                    
                break;
            }
        }
        if( graphEndPoint >= weatherPoints.size() )
            graphEndPoint = weatherPoints.size() - 1;

        return;
    }

    /*
    * Function: moveDateBackward
    * Author: Marcus Haberling
    * Sets the graphStartPoint and graphEndPoint variables to the next range
    * of data to display. This is dependant on weather viewing by year month week or day
    */
    private void moveDateBackward()
    {
        if(weatherPoints.size() == 0)
            return;
        LocalDateTime startTarget;
        LocalDateTime endTarget;
        //Set the target start and end dates for the loop
        if( Yearly.isSelected() )
        {   
            startTarget = LocalDateTime.of(weatherPoints.get(graphStartPoint).date.getYear(),1,1,0,0,0).plusYears(-1);
            endTarget = startTarget.plusYears(1);  
        }

        else if( Monthly.isSelected() )
        {
            startTarget = LocalDateTime.of(weatherPoints.get(graphStartPoint).date.getYear(),
                                           weatherPoints.get(graphStartPoint).date.getMonth().getValue(),
                                           1,0,0,0).plusMonths(-1);
            endTarget = startTarget.plusMonths(1);
        }

        else if( Daily.isSelected() )
        {
            startTarget = LocalDateTime.of(weatherPoints.get(graphStartPoint).date.getYear(),
                                           weatherPoints.get(graphStartPoint).date.getMonth().getValue(),
                                           weatherPoints.get(graphStartPoint).date.getDayOfMonth(),
                                           0,0,0).plusDays(-1);
            endTarget = startTarget.plusDays(1);
        }

        else
        {
            //need to get to the front of the week before decrementing to the last week.
            int year = weatherPoints.get(graphStartPoint).date.getYear();
            int month = weatherPoints.get(graphStartPoint).date.getMonth().getValue();
            int day = weatherPoints.get(graphStartPoint).date.getDayOfMonth();
            startTarget = LocalDateTime.of(year, month, day, 0 ,0 ,0 );
            startTarget = startTarget.plusDays(startTarget.getDayOfWeek().getValue()*-1+1);
            startTarget = startTarget.plusWeeks(-1);
            endTarget = startTarget.plusWeeks(1);
        }
        //loop for start date
        for ( int x = graphStartPoint; x >= 0; x = x - 1)
        {
                //isAfter(ChronoLocalDateTime<?> other)

            if( weatherPoints.get(x).date.isBefore(startTarget) )
            {                
                graphStartPoint = x;
                break;
            }
            graphStartPoint = x;
        }

        if( graphStartPoint >= weatherPoints.size())
            graphStartPoint = 0;
        //loop for end date.
        for ( int x = graphStartPoint; x < weatherPoints.size(); x=x+1)
        {
            graphEndPoint = x;
            if(weatherPoints.get(x).date.isAfter(endTarget) )
            {                                    
                break;
            }
        }

        if( graphEndPoint >= weatherPoints.size() )
            graphEndPoint = weatherPoints.size() - 1;

        return;
    }

    /*
    * Function: moveDateForward
    * Author: Marcus Haberling
    * Sets the graphStartPoint and graphEndPoint variables to the next range
    * of data to display. This is dependant on weather viewing by year month week or day
    */
    private void moveDateForward()
    {
        if(weatherPoints.size() == 0)
            return;
        //Create targets to dearch for in coming loops
        LocalDateTime startTarget;
        LocalDateTime endTarget;
        if( Yearly.isSelected() )
        {   
            startTarget = LocalDateTime.of(weatherPoints.get(graphStartPoint).date.getYear(),1,1,0,0,0).plusYears(1);
            endTarget = startTarget.plusYears(1);
            
        }
        else if( Monthly.isSelected() )
        {
            startTarget = LocalDateTime.of(weatherPoints.get(graphStartPoint).date.getYear(),
                                           weatherPoints.get(graphStartPoint).date.getMonth().getValue(),
                                           1,0,0,0).plusMonths(1);
            endTarget = startTarget.plusMonths(1);
        }
        else if( Daily.isSelected() )
        {
            startTarget = LocalDateTime.of(weatherPoints.get(graphStartPoint).date.getYear(),
                                           weatherPoints.get(graphStartPoint).date.getMonth().getValue(),
                                           weatherPoints.get(graphStartPoint).date.getDayOfMonth(),
                                           0,0,0).plusDays(1);
            endTarget = startTarget.plusDays(1);
        }
        else
        {
            //Need to subtract days so we are at befining of the week before moving
            // ont to the next week.
            int year = weatherPoints.get(graphStartPoint).date.getYear();
            int month = weatherPoints.get(graphStartPoint).date.getMonth().getValue();
            int day = weatherPoints.get(graphStartPoint).date.getDayOfMonth();
            startTarget = LocalDateTime.of(year, month, day, 0 ,0 ,0 );
            startTarget = startTarget.plusDays(startTarget.getDayOfWeek().getValue()*-1+1);
            startTarget = startTarget.plusWeeks(1);
            endTarget = startTarget.plusWeeks(1);
        }

        //Loop looking for the start date
        for ( int x = graphStartPoint; x < weatherPoints.size(); x = x + 1)
        {
            graphStartPoint = x;

            if( weatherPoints.get(x).date.isAfter(startTarget) )
            {                
                //graphStartPoint = x -1;
                break;
            }
        }
        //NO reference exceptions
        if( graphStartPoint >= weatherPoints.size())
            graphStartPoint = 0;
        //loop for the end date
        for ( int x = graphStartPoint; x < weatherPoints.size(); x=x+1)
        {
            graphEndPoint = x;
            if(weatherPoints.get(x).date.isAfter(endTarget) )
            {                                    
                break;
            }
        }
        if( graphEndPoint >= weatherPoints.size() )
            graphEndPoint = weatherPoints.size() - 1;

        
        return;
    }

    /*
    * Function: persistsGraphLines
    * Author: Marcus Haberling
    * Sets all graphlines to display when the graph is redrawn
    * if their corresponding checkbox is selected then
    * calls update to redraw them. 
    */
    private void persistGraphLines()
    {
        if(Temperature.isSelected())
            weatherGraph.showLine( SeriesList.TEMPERATURE );
        if(Humidity.isSelected())
            weatherGraph.showLine( SeriesList.HUMIDITY );
        if(WindSpeed.isSelected())
            weatherGraph.showLine( SeriesList.WINDSPEED );
        if(WindGust.isSelected())
            weatherGraph.showLine( SeriesList.WINDGUST );
        if(WindChill.isSelected())
            weatherGraph.showLine( SeriesList.WINDCHILL );
        if(HeatIndex.isSelected())
            weatherGraph.showLine( SeriesList.HEATINDEX );
        if(UVIndex.isSelected())
            weatherGraph.showLine( SeriesList.UVINDEX );
        if(Barometer.isSelected())
            weatherGraph.showLine( SeriesList.BAROMETER );
        if(Rainfall.isSelected())
            weatherGraph.showLine( SeriesList.RAINFALL );
        //redraw
        update( GraphPanel, weatherGraph.getGraph(), weatherStats );
    }
    
    private javax.swing.JRadioButton Daily;
    private javax.swing.JMenu Edit;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenu File;
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JRadioButton Monthly;
    private javax.swing.JMenuItem Open;
    private javax.swing.JCheckBox Rainfall;
    private javax.swing.JCheckBox Temperature;
    private javax.swing.JCheckBox Humidity;
    private javax.swing.JCheckBox WindSpeed;
    private javax.swing.JCheckBox WindGust;
    private javax.swing.JCheckBox WindChill;
    private javax.swing.JCheckBox HeatIndex;
    private javax.swing.JCheckBox UVIndex;
    private javax.swing.JCheckBox Barometer;
    private javax.swing.JPopupMenu.Separator Seperator;
    private javax.swing.ButtonGroup TimeFrame;
    private javax.swing.JRadioButton Weekly;
    private javax.swing.JRadioButton Yearly;
	private javax.swing.JPanel StatsType;
	private javax.swing.JPanel StatsInfo;
	private javax.swing.JPanel Stats;
	private javax.swing.JPanel StatsPanel;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel TimePanel;
    private javax.swing.JPanel GraphOptionPanel;
	private javax.swing.JPanel Arrows;
	private javax.swing.JLabel MeanTemperature;
	private javax.swing.JLabel HighLowTemperature;
	private javax.swing.JLabel MeanWindSpeed;
	private javax.swing.JLabel MaxWindSpeed;
	private javax.swing.JLabel PrevailingWindDirection;
	private javax.swing.JLabel MeanTemperatureInfo;
	private javax.swing.JLabel HighLowTemperatureInfo;
	private javax.swing.JLabel MeanWindSpeedInfo;
	private javax.swing.JLabel MaxWindSpeedInfo;
	private javax.swing.JLabel PrevailingWindDirectionInfo;
	private BasicArrowButton Left;
	private BasicArrowButton Right;
	private ArrayList<String> GraphStats;
    private WeatherGraph weatherGraph;
    private int graphStartPoint;
    private int graphEndPoint;
    private JFreeChart Graph;
    private ChartPanel GraphPanel;
    private java.lang.Boolean XMLLoaded;
    private String XMLFileName;
    private List<WeatherPoint> weatherPoints;
	private WeatherStats weatherStats;
}
