

package at.ac.ait.ubicity.truegrit;

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
}
