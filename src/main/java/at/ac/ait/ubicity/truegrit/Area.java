
package at.ac.ait.ubicity.truegrit;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author jan
 */
public class Area {

    
    protected SortedSet< Cell > myCells = new TreeSet();
    
    static Area getEurope() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    /**
     * TODO TODO write a sensible algorithm for dividing up any Area in equally-sized (sub-Areals or ) Cells
     * @param oneHourBefore
     * @param twoHoursBefore
     * @param __zoomlevel
     * @return 
     */
    Set<Cell> populate(long[] oneHourBefore, long[] twoHoursBefore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public SortedSet< Cell > getCells()   {
        return myCells;
    }
    
    
    public final double getOverallThreshold()   {
        double result = 0;
        for( Cell c: myCells )  {
            result += c.percentage;
        }
        return result / myCells.size();
    }

    void computeHourlyDeltaFor(Cell c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
