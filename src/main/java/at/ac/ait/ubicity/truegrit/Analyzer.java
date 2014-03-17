package at.ac.ait.ubicity.truegrit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Analyzer   {
    
    public final static Logger logger = Logger.getLogger( Analyzer.class.getName() );
    
    public final static String DEFAULT_PROPS_FILE = "truegrit.properties";
    
    public final static int DEFAULT_TOP_N = 1;
    
    public final static int DEFAULT_ZOOM_FACTOR = 10;
    
    
    //crude model of "moment" of current Analyzer run
    static long NOW;
    
    //NB Following time slots are always relative to ENTIRE hours, e.g 14h-15h
    //value 0 = beginning of last hour, value 1 = end of last hour
    static long[] oneHourBefore = new long[ 2 ];
    //value 0 = beginning of hour before last hour
    static long[] twoHoursBefore = new long[ 2 ];
    
    static int TOP_N;
    
    
    //factor by which to zoom in on cells, when we re-cast them as Area and divide them into a new, 
    //finer Cell grid. The zoom factor is **side-oriented**, and * not * surface-oriented;
    //i.e., a zoom factor of 3 will divide a Cell into 3 * 3 = 9 new Cells !! 
    
    static int ZOOM_FACTOR;
    
    
    
    static  {
        logger.setLevel( Level.ALL );
        Properties props = new Properties();
        try {
            props.load( new FileInputStream( new File( DEFAULT_PROPS_FILE )));
            TOP_N = Integer.parseInt( props.getProperty( "topn" )  );
            ZOOM_FACTOR = Integer.parseInt( props.getProperty( "zoomfactor" ) );
        }
        catch( IOException | NumberFormatException t )    {
            TOP_N = DEFAULT_TOP_N;
            ZOOM_FACTOR = DEFAULT_ZOOM_FACTOR;
            logger.warning( t.getMessage() );
        }
    }
    
    
    
    public static void main( String[] args )  {
        
        
        
        Area a = Area.EUROPE;
    
        a.initializeTimeSlots();
        
    }
    
    static Set< Cell > analyze( Area _a )   {
        
        //place holder for results from > 1 recursive calls
        SortedSet< Cell > result = new TreeSet();
        
        
        _a.populate( oneHourBefore, twoHoursBefore );
        SortedSet< Cell > cellsForArea = _a.getCells();
        cellsForArea.stream().forEach((c) -> {
            _a.computeHourlyDeltaFor( c );
        });
        
        double _threshold = _a.getOverallThreshold();
        
        SortedSet< Cell > cellsAboveThreshold = getCellsAboveThreshold( cellsForArea, _threshold );
        
        if( cellsAboveThreshold.size() == 0 ) return cellsForArea;
        else    {
            cellsAboveThreshold.stream().forEach((_c) -> {
                result.addAll( analyze( ( Area )  _c ) );
            });
        }
        return result;
    }
    
    
    


    
    private static SortedSet<Cell> getCellsAboveThreshold( SortedSet< Cell > cells, double _threshold) {
        SortedSet< Cell > result = new TreeSet();
        for( Cell _c: cells )   {
            if( _c.percentage > _threshold ) result.add( _c );
        }
        return result;
    }
}

