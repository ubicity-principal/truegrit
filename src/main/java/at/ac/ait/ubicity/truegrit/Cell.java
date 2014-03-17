

package at.ac.ait.ubicity.truegrit;

import java.util.TreeSet;

/**
 *
 * @author jan
 */
final class Cell extends Area implements Comparable< Cell> {
    
    
    public volatile double percentage;


    
    
    @Override
    /**
     * 
     * @param o
     * @return 
     */
    public final  boolean equals( Object o )    {
        if ( o == null ) return false;
        if( ! ( o instanceof Cell) ) return false;
        return this.percentage ==  ( ( Cell ) o ).percentage ;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.percentage) ^ (Double.doubleToLongBits(this.percentage) >>> 32));
        return hash;
    }
    
    
    @Override
    public int compareTo(Cell o) {
        return ( int ) ( 100 * this.percentage - 100 * o.percentage  );
    }
    
    
    //return this cell as an Area, split up into smaller Cells
    public final Area zoom()    {
        Area a = ( Area ) this;
        a.myCells = new TreeSet();
        
        double deltaLat = topLeft[ 0 ] - bottomRight[ 0 ] / Analyzer.ZOOM_FACTOR;
        double deltaLon = bottomRight[ 1 ] - topLeft[ 1 ] / Analyzer.ZOOM_FACTOR;
        
        for( int i = 0; i < Analyzer.ZOOM_FACTOR; i++ ) {
            
            for( int j = 0; j < Analyzer.ZOOM_FACTOR; j++ ) {
                Cell __c = new Cell();
                __c.topLeft[ 0 ] = topLeft[ 0 ] - j * deltaLat;
                __c.topLeft[ 1 ] = topLeft[ 1 ] + i * deltaLon;
                __c.bottomRight[ 0 ] = bottomRight[ 0 ] + ( Analyzer.ZOOM_FACTOR - j ) * deltaLat;
                __c.bottomRight[ 1 ] = bottomRight[ 1 ] - ( Analyzer.ZOOM_FACTOR - i ) * deltaLon;
                a.myCells.add( __c ) ;
            }
        }
        return a;
    }
}
