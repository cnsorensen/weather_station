
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.JFreeChart;
import java.lang.Boolean;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.event.ChartChangeEvent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package weather.station;

/**
 *
 * @author 7053313
 */
public class MainWindow extends javax.swing.JFrame
{

    /**
     * Creates new form MainWindow
     */
    public MainWindow()
    {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        //bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        TimeFrame = new javax.swing.ButtonGroup();
        GraphVariables = new javax.swing.ButtonGroup();
        FileChooser = new javax.swing.JFileChooser();
        MeanTemperature = new javax.swing.JCheckBox();
        HighLowTemperature = new javax.swing.JCheckBox();
        MeanWindSpeed = new javax.swing.JCheckBox();
        MaximumWindSpeed = new javax.swing.JCheckBox();
        PrevailingWindDirection = new javax.swing.JCheckBox();
        Rainfall = new javax.swing.JCheckBox();
        Daily = new javax.swing.JRadioButton();
        Weekly = new javax.swing.JRadioButton();
        Monthly = new javax.swing.JRadioButton();
        Yearly = new javax.swing.JRadioButton();
        weatherGraph = new WeatherGraph( "shit", "fuck", new ArrayList<WeatherPoint>(), "bitch" );       
        GraphPanel = new ChartPanel( weatherGraph.getGraph() );        
        MenuBar = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Open = new javax.swing.JMenuItem();
        Seperator = new javax.swing.JPopupMenu.Separator();
        Exit = new javax.swing.JMenuItem();
        Edit = new javax.swing.JMenu();
        XMLLoaded = false;
        XMLFileName = "";
        weatherPoints = new ArrayList<WeatherPoint>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        GraphVariables.add(MeanTemperature);
        MeanTemperature.setText("Mean Temperature");
        MeanTemperature.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                MeanTemperatureActionPerformed(evt);
            }
        });

        GraphVariables.add(HighLowTemperature);
        HighLowTemperature.setText("High/Low Temperature");

        HighLowTemperature.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                HighLowTemperatureActionPerformed(evt);
            }
        });

        GraphVariables.add(MeanWindSpeed);
        MeanWindSpeed.setText("Mean Wind Speed");
        MeanWindSpeed.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                MeanWindSpeedActionPerformed(evt);
            }
        });

        GraphVariables.add(MaximumWindSpeed);
        MaximumWindSpeed.setText("Maximum Wind Speed");
        MaximumWindSpeed.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                MaximumWindSpeedActionPerformed(evt);
            }
        });

        GraphVariables.add(PrevailingWindDirection);
        PrevailingWindDirection.setText("Prevailing Wind Direction");
        PrevailingWindDirection.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                PrevailingWindDirectionActionPerformed(evt);
            }
        });

        GraphVariables.add(Rainfall);
        Rainfall.setText("Rainfall");
        Rainfall.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                RainfallActionPerformed(evt);
            }
        });

        TimeFrame.add(Daily);
        Daily.setText("Daily");

        TimeFrame.add(Weekly);
        Weekly.setText("Weekly");

        TimeFrame.add(Monthly);
        Monthly.setText("Monthly");

        TimeFrame.add(Yearly);
        Yearly.setText("Yearly");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Daily)
                    .addComponent(Weekly)
                    .addComponent(Monthly)
                    .addComponent(Yearly))
                .addGap(18, 18, 18)
                .addComponent(GraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MeanTemperature)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(HighLowTemperature, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(MaximumWindSpeed)
                        .addComponent(MeanWindSpeed))
                    .addComponent(PrevailingWindDirection)
                    .addComponent(Rainfall))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MeanTemperature)
                        .addComponent(HighLowTemperature)
                        .addComponent(MeanWindSpeed)
                        .addComponent(MaximumWindSpeed)
                        .addComponent(PrevailingWindDirection)
                        .addComponent(Rainfall, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Daily)
                        .addComponent(Weekly)
			.addComponent(Monthly)
                        .addComponent(Yearly))
                    .addComponent(GraphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100))
        );

        //pack();
    
    }// </editor-fold>//GEN-END:initComponents

    private void HighLowTemperatureActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_HighLowTemperatureActionPerformed
    
        System.out.println("High/Low Temperature chosen.");
       
        

    }//GEN-LAST:event_HighLowTemperatureActionPerformed

/* *******************************************ACTIONS********************************* */
    private void MeanTemperatureActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_MeanTemperatureActionPerformed
    
        System.out.println("Mean Temperature chosen.");
		weatherPoints = ParseXML.parseWeather( "2010-01.xml" );
		weatherGraph = new WeatherGraph( "All Data?", "WHHATTT", weatherPoints, "bitch" );
		update(GraphPanel, weatherGraph.getGraph());
    
    }//GEN-LAST:event_MeanTemperatureActionPerformed
    
    private void MeanWindSpeedActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_MeanWindSpeedActionPerformed
    
        System.out.println("Mean Wind Speed chosen.");
    
    }//GEN-LAST:event_MeanWindSpeedActionPerformed
    
    private void MaximumWindSpeedActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_MaximumWindSpeedActionPerformed
    
        System.out.println("Maximum Wind Speed chosen.");
    
    }//GEN-LAST:event_MaximumWindSpeedActionPerformed
    
    private void PrevailingWindDirectionActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_PrevailingWindDirectionActionPerformed
    
        System.out.println("Prevailing Wind Direction chosen.");
    
    }//GEN-LAST:event_PrevailingWindDirectionActionPerformed
    
    private void RainfallActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_RainfallActionPerformed
    
        System.out.println("Rainfall chosen.");
    
    }//GEN-LAST:event_RainfallActionPerformed

    private void DailyActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_DailyActionPerformed
    
        System.out.println("Daily chosen.");
    
    }//GEN-LAST:event_DailyActionPerformed

    private void WeeklyActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_WeeklyActionPerformed
    
        System.out.println("Weekly chosen.");
    
    }//GEN-LAST:event_WeeklyActionPerformed

    private void MonthlyActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_MonthlyActionPerformed
    
        System.out.println("Monthly chosen.");
    
    }//GEN-LAST:event_MonthlyActionPerformed

    private void YearlyActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_YearlyActionPerformed
    
        System.out.println("Yearly chosen.");
    
    }//GEN-LAST:event_YearlyActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_ExitActionPerformed
        
        System.exit(0);
    
    }//GEN-LAST:event_ExitActionPerformed

    private void OpenActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_OpenActionPerformed
        
        System.out.println("Open chosen.");
        
        // filter to only xml files
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter( null, "xml" );
        FileChooser.setFileFilter( xmlFilter );
        
        // show the dialog box to select a file from
        int returnVal = FileChooser.showOpenDialog( null );
        
        // if file selection was a success
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
            System.out.println( "You chose to open this file: " + FileChooser.getSelectedFile().getName() );
        XMLLoaded = true;
        XMLFileName = FileChooser.getSelectedFile().getName();
        }

        weatherPoints = ParseXML.parseWeather( XMLFileName );
		weatherGraph = new WeatherGraph( "All Data?", "WHHATTT", weatherPoints, "bitch" );
		//GraphPanel.chartChanged(evt);
		update(GraphPanel, weatherGraph.getGraph());

    	System.out.println( "Exiting Open Event Handler" );
        


    }//GEN-LAST:event_OpenActionPerformed

    public void update(ChartPanel GraphPanel, JFreeChart weatherGraph)
    {
		GraphPanel = new ChartPanel(weatherGraph);
		GraphPanel.repaint();
		this.add(GraphPanel, BorderLayout.CENTER);
		//this.add(GraphVariables, BorderLayout.WEST);
		//this.add(TimeFrame, BorderLayout.EAST);
		this.setTitle("WWWWHHHHHYYYYY");
		this.pack();
		this.setVisible(true);

		//setContentPane(GraphPanel);


		//invalidate();
		//revalidate();
		
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Daily;
    private javax.swing.JMenu Edit;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenu File;
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.ButtonGroup GraphVariables;
    private javax.swing.JCheckBox HighLowTemperature;
    private javax.swing.JCheckBox MaximumWindSpeed;
    private javax.swing.JCheckBox MeanTemperature;
    private javax.swing.JCheckBox MeanWindSpeed;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JRadioButton Monthly;
    private javax.swing.JMenuItem Open;
    private javax.swing.JCheckBox PrevailingWindDirection;
    private javax.swing.JCheckBox Rainfall;
    private javax.swing.JPopupMenu.Separator Seperator;
    private javax.swing.ButtonGroup TimeFrame;
    private javax.swing.JRadioButton Weekly;
    private javax.swing.JRadioButton Yearly;
    //private javax.swing.JPanel GraphPanel;
    private WeatherGraph weatherGraph;
    private JFreeChart Graph;
    private ChartPanel GraphPanel;
    private java.lang.Boolean XMLLoaded;
    private String XMLFileName;
    private List<WeatherPoint> weatherPoints;
    // add the panel to the window and add the graph to the panel

    //private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
