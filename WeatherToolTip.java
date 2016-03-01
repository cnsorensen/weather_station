import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;

public class WeatherToolTip implements XYToolTipGenerator
{
        @Override
        public String generateToolTip(XYDataset dataset, int series, int item )
        {
            Number x1 = dataset.getX( series, item );
            Number y1 = dataset.getY( series, item );
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append( String.format("<html><p style='color:#0000ff;'> '%s'</p>", dataset.getSeriesKey(series) ) );
            stringBuilder.append( String.format( "X: '%d'<br/>", x1.intValue() ) );
            stringBuilder.append( String.format( "Y: '%s'", y1.doubleValue() ) );
            stringBuilder.append( "</html>" );
            //stringBuilder.append( "tool tip my timbers" );
            return stringBuilder.toString();
        }
}
