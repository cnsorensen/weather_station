
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

public class MainWindow extends JFrame implements ItemListener, ActionListener
{

    /**
     * Creates new form MainWindow
     */
    public MainWindow()
    {
        initComponents();
    }

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
        MeanTemperature = new javax.swing.JCheckBox("Mean Temperature");
        HighLowTemperature = new javax.swing.JCheckBox("High/Low Temperature");
        MeanWindSpeed = new javax.swing.JCheckBox("Mean Wind Speed");
        MaximumWindSpeed = new javax.swing.JCheckBox("Maximum Wind Speed");
        PrevailingWindDirection = new javax.swing.JCheckBox("Prevailing Wind Direction");
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
		Arrows = new javax.swing.JPanel();
		Left = new BasicArrowButton(BasicArrowButton.WEST);
		Right = new BasicArrowButton(BasicArrowButton.EAST);
        weatherPoints = new ArrayList<WeatherPoint>();
		String test = "";
		String[] columnNames = {"Type","Stat"};
		Object[][] data = 	{	{"Mean Temperature", test},
								{"High/Low Temperature", test},
								{"Mean Wind Speed", test},
								{"Max Wind Speed", test},
								{"Prevailing Wind Direction", test},
								{"Rainfall", test}
							};
		table = new JTable(data, columnNames);
		ArrayList<String> GraphStats = new ArrayList<String>();
		GraphStats.add(" ");
		GraphStats.add(" ");
		GraphStats.add(" ");
		GraphStats.add(" ");
		GraphStats.add(" ");
		GraphStats.add(" ");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MeanTemperature.setMnemonic(KeyEvent.VK_C);
		MeanTemperature.setSelected(false);

		HighLowTemperature.setMnemonic(KeyEvent.VK_C);
		HighLowTemperature.setSelected(false);

		MeanWindSpeed.setMnemonic(KeyEvent.VK_C);
		MeanWindSpeed.setSelected(false);

		MaximumWindSpeed.setMnemonic(KeyEvent.VK_C);
		MaximumWindSpeed.setSelected(false);

		PrevailingWindDirection.setMnemonic(KeyEvent.VK_C);
		PrevailingWindDirection.setSelected(false);

		Rainfall.setMnemonic(KeyEvent.VK_C);
		Rainfall.setSelected(false);

		Temperature.setMnemonic(KeyEvent.VK_C);
		Temperature.setSelected(false);

		Humidity.setMnemonic(KeyEvent.VK_C);
		Humidity.setSelected(false);

		WindSpeed.setMnemonic(KeyEvent.VK_C);
		WindSpeed.setSelected(false);

		WindGust.setMnemonic(KeyEvent.VK_C);
		WindGust.setSelected(false);

		WindChill.setMnemonic(KeyEvent.VK_C);
		WindChill.setSelected(false);

		HeatIndex.setMnemonic(KeyEvent.VK_C);
		HeatIndex.setSelected(false);

		UVIndex.setMnemonic(KeyEvent.VK_C);
		UVIndex.setSelected(false);

		Barometer.setMnemonic(KeyEvent.VK_C);
		Barometer.setSelected(false);

		Temperature.addItemListener(this);
		Humidity.addItemListener(this);
		WindSpeed.addItemListener(this);
		WindGust.addItemListener(this);
		WindChill.addItemListener(this);
		HeatIndex.addItemListener(this);
		UVIndex.addItemListener(this);
		Barometer.addItemListener(this);
		MeanTemperature.addItemListener(this);
		HighLowTemperature.addItemListener(this);
		MeanWindSpeed.addItemListener(this);
		MaximumWindSpeed.addItemListener(this);
		PrevailingWindDirection.addItemListener(this);
		Rainfall.addItemListener(this);

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

		TimeFrame.add(Daily);
		TimeFrame.add(Weekly);
		TimeFrame.add(Monthly);
		TimeFrame.add(Yearly);

		Daily.addActionListener(this);
		Weekly.addActionListener(this);
		Monthly.addActionListener(this);
		Yearly.addActionListener(this);

		Left.addActionListener(this);
		Right.addActionListener(this);

		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

        File.setText("File");

        Open.setText("Open");
        Open.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                OpenActionPerformed(evt);
            }
        });
        File.add(Open);
        File.add(Seperator);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                ExitActionPerformed(evt);
            }
        });
        File.add(Exit);

        MenuBar.add(File);

        Edit.setText("Edit");
        MenuBar.add(Edit);
        setJMenuBar(MenuBar);

		Panel.setLayout(new BorderLayout());
		TimePanel.setLayout(new BoxLayout(TimePanel, BoxLayout.Y_AXIS));
		GraphOptionPanel.setLayout(new BoxLayout(GraphOptionPanel, BoxLayout.Y_AXIS));
		Arrows.setLayout(new FlowLayout());
		StatsPanel.setLayout(new BoxLayout(StatsPanel, BoxLayout.Y_AXIS));

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
		GraphOptionPanel.add(MeanTemperature);
		GraphOptionPanel.add(HighLowTemperature);
		GraphOptionPanel.add(MeanWindSpeed);
		GraphOptionPanel.add(MaximumWindSpeed);
		GraphOptionPanel.add(PrevailingWindDirection);
		GraphOptionPanel.add(Rainfall);
		Arrows.add(Left);
		Arrows.add(Right);
		StatsPanel.add(Arrows);
		StatsPanel.add(table);
		Panel.add(StatsPanel, BorderLayout.SOUTH);
		Panel.add(TimePanel, BorderLayout.WEST);
		Panel.add(GraphPanel, BorderLayout.CENTER);
		Panel.add(GraphOptionPanel, BorderLayout.EAST);

		setContentPane(Panel);
		this.setTitle("Weather Station");
		this.pack();
		this.setVisible(true);
   
    }// </editor-fold>//GEN-END:initComponents

	public void itemStateChanged(ItemEvent e)
	{
		Object source = e.getItemSelectable();

		if(source == Temperature)
		{
			System.out.println("Temperature chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        weatherGraph.showLine( 0 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        weatherGraph.hideLine( 0 );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == Humidity)
		{
			System.out.println("Humidity chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        weatherGraph.showLine( 1 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        weatherGraph.hideLine( 1 );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == WindSpeed)
		{
			System.out.println("Wind Speed chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        weatherGraph.showLine( 2 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        weatherGraph.hideLine( 2 );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == WindGust)
		{
			System.out.println("Wind Gust chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        weatherGraph.showLine( 3 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        weatherGraph.hideLine( 3 );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == WindChill)
		{
			System.out.println("Wind Chill chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        weatherGraph.showLine( 4 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        weatherGraph.hideLine( 4 );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == HeatIndex)
		{
			System.out.println("Heat Index chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        weatherGraph.showLine( 5 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        weatherGraph.hideLine( 5 );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == UVIndex)
		{
			System.out.println("UV Index chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        weatherGraph.showLine( 6 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        weatherGraph.hideLine( 6 );
		    }        
		    update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == Barometer)
		{
			System.out.println("Barometer chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        //weatherGraph.showLine( 0 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        //weatherGraph.hideLine( 0 );
		    }        
		    //update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == MeanTemperature)
		{
			System.out.println("Mean Temperature chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        //weatherGraph.showLine( 0 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        //weatherGraph.hideLine( 0 );
		    }        
		    //update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == HighLowTemperature)
		{
			System.out.println("High/Low Temperature chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        //weatherGraph.showLine( 0 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        //weatherGraph.hideLine( 0 );
		    }        
		    //update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == MeanWindSpeed)
		{
			System.out.println("Mean Wind Speed chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        //weatherGraph.showLine( 0 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        //weatherGraph.hideLine( 0 );
		    }        
		    //update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == MaximumWindSpeed)
		{
			System.out.println("MWS chosen.");
			if( MaximumWindSpeed.isSelected() )
		    {
		        System.out.println( "is selected" );
		        //weatherGraph.showLine( 3 );
		    }
		    else
		    {
		        System.out.println( "MWS: It aint no selected" );
		        //weatherGraph.hideLine( 3 );
		    }
		    //update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == PrevailingWindDirection)
		{
			System.out.println("Prevailing Wind Direction chosen.");
		    if(e.getStateChange() == ItemEvent.SELECTED )
		    {
		        System.out.println( "is selected" );
		        //weatherGraph.showLine( 0 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        //weatherGraph.hideLine( 0 );
		    }        
		    //update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}

		else if(source == Rainfall)
		{
			System.out.println("Rainfall chosen.");
		    if( Rainfall.isSelected() )
		    {
		        System.out.println( "is selected" );
		        weatherGraph.showLine( 9 );
		    }
		    else
		    {
		        System.out.println( "It aint no selected" );
		        weatherGraph.hideLine( 9 );
		    }
		    update( GraphPanel, weatherGraph.getGraph(), GraphStats );
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getActionCommand();

		if(source == "Daily")
		{
			System.out.println("Daily chosen.");
		}
		
		else if(source == "Weekly")
		{
			System.out.println("Weekly chosen.");
		}
	
		else if(source == "Monthly")
		{
			System.out.println("Monthly chosen.");
		}
	
		else if(source == "Yearly")
		{
			System.out.println("Yearly chosen.");
		}

		else if(source == "Left")
		{
			System.out.println("Left chosen.");
		}

		else if(source == "Right")
		{
			moveDateForward();

            weatherGraph = new WeatherGraph( weatherPoints.subList(graphStartPoint, graphEndPoint));

		    update(GraphPanel, weatherGraph.getGraph(), GraphStats);
			System.out.println("Right chosen.");
            System.out.println( "Start: " + graphStartPoint + "\tEnd: " + graphEndPoint + "\tTotal: " + weatherPoints.size() );            
		}
	}

    private void ExitActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_ExitActionPerformed
        
        System.exit(0);
    
    }//GEN-LAST:event_ExitActionPerformed

    private void OpenActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_OpenActionPerformed
        
        System.out.println("Open chosen.");
        
        // filter to only xml files
     
        FileChooser.setCurrentDirectory(new java.io.File("."));
        FileChooser.setDialogTitle("Select Data Folder");
        FileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
        // show the dialog box to select a file from
        int returnVal = FileChooser.showOpenDialog( null );
        
        // if file selection was a success
        
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
            System.out.println( "You chose to open this file: " + FileChooser.getSelectedFile().getName() );
        	XMLLoaded = true;
        	XMLFileName = FileChooser.getSelectedFile().getAbsolutePath();
        }
		
		GraphPanel.removeAll();
                weatherPoints = ParseXML.parseDirectory( XMLFileName );
        Collections.sort(weatherPoints);
        graphStartPoint = 0;
        int targetYear = weatherPoints.get(graphStartPoint).date.getYear();
        System.out.println( targetYear );
        for ( int x = graphStartPoint; x < weatherPoints.size(); x=x+1)
        {
            graphEndPoint = x;
            System.out.println( weatherPoints.get(x).date.getYear() );
            if( weatherPoints.get(x).date.getYear() > targetYear )
            {
                x = weatherPoints.size();
            }
        }
        if( graphEndPoint >= weatherPoints.size() )
            graphEndPoint = weatherPoints.size() -1;
		weatherGraph = new WeatherGraph( weatherPoints.subList(graphStartPoint, graphEndPoint));
		update( GraphPanel, weatherGraph.getGraph(), GraphStats );

    	System.out.println( "Exiting Open Event Handler" );
        
    }//GEN-LAST:event_OpenActionPerformed

    public void update(ChartPanel GraphPanel, JFreeChart weatherGraph, ArrayList<String> GraphStats)
    {
		String test = "";
		String[] columnNames = {"Type","Stat"};
		Object[][] data = 	{	{"Mean Temperature", test},
								{"High/Low Temperature", test},
								{"Mean Wind Speed", test},
								{"Max Wind Speed", test},
								{"Prevailing Wind Direction", test},
								{"Rainfall", test}
							};
		table.setPreferredScrollableViewportSize(new Dimension(250, 70));
		table.setFillsViewportHeight(true);

		Panel.removeAll();
		Arrows.removeAll();
		GraphPanel = new ChartPanel(weatherGraph);
		GraphPanel.repaint();

		Panel.setLayout(new BorderLayout());
		TimePanel.setLayout(new BoxLayout(TimePanel, BoxLayout.Y_AXIS));
		GraphOptionPanel.setLayout(new BoxLayout(GraphOptionPanel, BoxLayout.Y_AXIS));
		Arrows.setLayout(new FlowLayout());
		StatsPanel.setLayout(new BoxLayout(StatsPanel, BoxLayout.Y_AXIS));

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
		GraphOptionPanel.add(MeanTemperature);
		GraphOptionPanel.add(HighLowTemperature);
		GraphOptionPanel.add(MeanWindSpeed);
		GraphOptionPanel.add(MaximumWindSpeed);
		GraphOptionPanel.add(PrevailingWindDirection);
		GraphOptionPanel.add(Rainfall);
		Arrows.add(Left);
		Arrows.add(Right);
		StatsPanel.add(Arrows);
		StatsPanel.add(table);
		Panel.add(StatsPanel, BorderLayout.SOUTH);
		Panel.add(TimePanel, BorderLayout.WEST);
		Panel.add(GraphPanel, BorderLayout.CENTER);
		Panel.add(GraphOptionPanel, BorderLayout.EAST);

		setContentPane(Panel);
		this.setTitle("Weather Station");
		this.pack();
		this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
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
        }
        //</editor-fold>

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

    private void moveDateForward()
    {
        if(weatherPoints.size() == 0)
            return;
        int targetYear = weatherPoints.get(graphStartPoint).date.getYear() + 1;
        int targetMonth = weatherPoints.get(graphStartPoint).date.getMonth().getValue() + 1;
        int targetDayOfYear = weatherPoints.get(graphStartPoint).date.getDayOfYear() + 1; 
        if( Yearly.isSelected() )
        {
            System.out.println( "WE HAVE ENTERED YEARLY" );
            for ( int x = graphStartPoint; x < weatherPoints.size(); x = x + 1)
            {
                graphStartPoint = x;
                if( weatherPoints.get(x).date.getYear() >= targetYear )
                {                
                    graphStartPoint = x + 1;
                    break;
                }
            }
            System.out.println("Target Year: " + targetYear);
            if( graphStartPoint >= weatherPoints.size())
                graphStartPoint = 0;
            for ( int x = graphStartPoint; x < weatherPoints.size(); x=x+1)
            {
                graphEndPoint = x;
                if( weatherPoints.get(x).date.getYear() > targetYear )
                {                                    
                    break;
                }
            }
            if( graphEndPoint >= weatherPoints.size() )
                graphEndPoint = weatherPoints.size() - 1;
            System.out.println("FUCK YOU TOO BUDDY");

        }
        if( Monthly.isSelected() )
        {
            for ( int x = graphStartPoint; x > -1; x = x - 1)
            {
                graphStartPoint = x;
                if( weatherPoints.get(x).date.getMonth().getValue() != targetMonth )
                {                
                    graphStartPoint = x + 1;
                    break;
                }
            }
            if( graphStartPoint >= weatherPoints.size())
                graphStartPoint = 0;
            for ( int x = graphStartPoint; x < weatherPoints.size(); x=x+1)
            {
                graphStartPoint = x;
                if( weatherPoints.get(x).date.getMonth().getValue() != targetMonth )
                {                                    
                    break;
                }
            }
            if( graphEndPoint >= weatherPoints.size() )
                graphEndPoint = weatherPoints.size() - 1;

        }
        if( Daily.isSelected() )
        {
            for ( int x = graphStartPoint; x > -1; x = x - 1)
            {
                graphStartPoint = x;
                if( weatherPoints.get(x).date.getDayOfYear() != targetDayOfYear)
                {                
                    graphStartPoint = x + 1;
                    break;
                }
            }
            if( graphStartPoint >= weatherPoints.size())
                graphStartPoint = 0;
            for ( int x = graphStartPoint; x < weatherPoints.size(); x=x+1)
            {
                graphStartPoint = x;
                if( weatherPoints.get(x).date.getDayOfYear() != targetDayOfYear )
                {                                    
                    break;
                }
            }
            if( graphEndPoint >= weatherPoints.size() )
                graphEndPoint = weatherPoints.size() - 1;

        }
        return;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Daily;
    private javax.swing.JMenu Edit;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenu File;
    private javax.swing.JFileChooser FileChooser;
    //private javax.swing.ButtonGroup GraphVariables;
    private javax.swing.JCheckBox HighLowTemperature;
    private javax.swing.JCheckBox MaximumWindSpeed;
    private javax.swing.JCheckBox MeanTemperature;
    private javax.swing.JCheckBox MeanWindSpeed;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JRadioButton Monthly;
    private javax.swing.JMenuItem Open;
    private javax.swing.JCheckBox PrevailingWindDirection;
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
	private javax.swing.JPanel StatsPanel;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel TimePanel;
    private javax.swing.JPanel GraphOptionPanel;
	private javax.swing.JPanel Arrows;
	private BasicArrowButton Left;
	private BasicArrowButton Right;
	private ArrayList<String> GraphStats;
	private JTable table;
    private WeatherGraph weatherGraph;
    private int graphStartPoint;
    private int graphEndPoint;
    private JFreeChart Graph;
    private ChartPanel GraphPanel;
    private java.lang.Boolean XMLLoaded;
    private String XMLFileName;
    private List<WeatherPoint> weatherPoints;
}
