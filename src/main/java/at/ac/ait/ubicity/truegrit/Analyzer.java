package at.ac.ait.ubicity.truegrit;

import java.io.File;
import java.io.FileInputStream;
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
    
    
    //crude model of "moment" of current Analyzer run
    static long NOW;
    
    //NB Following time slots are always relative to ENTIRE hours, e.g 14h-15h
    //value 0 = beginning of last hour, value 1 = end of last hour
    static long[] oneHourBefore = new long[ 2 ];
    //value 0 = beginning of hour before last hour
    static long[] twoHoursBefore = new long[ 2 ];
    
    static int TOP_N;
    
    static  {
        logger.setLevel( Level.ALL );
    }
    
    public static void main( String[] args )  {
        
        Properties props = new Properties();
        try {
            props.load( new FileInputStream( new File( DEFAULT_PROPS_FILE )));
            TOP_N = Integer.parseInt( props.getProperty( "topn" )  );
        }
        catch( Throwable t )    {
            TOP_N = DEFAULT_TOP_N;
            logger.warning( t.getMessage() );
        }
        
        
        Area a = Area.getEurope();
    
        initializeTimeSlots();
        
    }
    
    static Set< Cell > analyze( Area _a )   {
        
        //place holder for results from > 1 recursive calls
        SortedSet< Cell > result = new TreeSet();
        
        
        _a.populate( oneHourBefore, twoHoursBefore );
        SortedSet< Cell > cellsForArea = _a.getCells();
        
        for( Cell c: cellsForArea ) {
            _a.computeHourlyDeltaFor( c );
        }
        double _threshold = _a.getOverallThreshold();
        
        SortedSet< Cell > cellsAboveThreshold = getCellsAboveThreshold( cellsForArea, _threshold );
        
        if( cellsAboveThreshold.size() == 0 ) return cellsForArea;
        else    {
            for( Cell _c: cellsAboveThreshold ) {
                result.addAll( analyze( ( Area )  _c ) );
                
            }
        }
        return result;
    }
    
    
    
    private static void computeHourlyDeltaFor(Cell c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void initializeTimeSlots() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private static SortedSet<Cell> getCellsAboveThreshold( SortedSet< Cell > cells, double _threshold) {
        SortedSet< Cell > result = new TreeSet();
        for( Cell _c: cells )   {
            if( _c.percentage > _threshold ) result.add( _c );
        }
        return result;
    }
}

