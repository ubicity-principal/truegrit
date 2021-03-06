
package at.ac.ait.ubicity.truegrit;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author jan
 */
public class Area {

    
    public final static Area EUROPE;
    
    public final static Area ASIA;
    
    public final static Area AFRICA;
    
    public final static Area MIDDLE_EAST;
    
    public final static Area HORN_OF_AFRICA;
    
    public final static Area EAST_AFRICA;
    
    
    
    /*
    * TODO TODO TODO place initialization code for various pre-defined Area instances here ! 
    */
    static  {
        /* lat-lon format !
        * This Area for Europe comprises all of continental Europe down south to the Rock of Gibraltar,
        * and up north to the North Cape. It runs from the westernmost point of Ireland deeply into Russia. 
        * As such, it comprises a fair amount of water ( some Atlantic Ocean, all of the Gulf of Biscay, 
        * the Irish Sea, Tyrrhenian Sea, Adriatic Sea and Aegaean Sea, as well as the entire Black Sea and North Sea. 
        * It would be nice to have a class-static way that, before drilling down upon a Cell, checks whether it is 
        * watery or not.... 
        */
        double[] europeTopLeft = { 71.1834, -10.4730 };
        double[] europeBottomRight = { 36.0006, 36.2200 };
        EUROPE = new Area( europeTopLeft, europeBottomRight );

        
        ASIA = null;
        AFRICA = null;
        MIDDLE_EAST = null;
        HORN_OF_AFRICA = null;
        EAST_AFRICA = null;
    }
    
    
    
    protected SortedSet< Cell > myCells = new TreeSet();
    

    //lat, lon format unless specified otherwise; 
    //meant for direct use with elasticsearch Java API FilterBuilder
    protected double[] topLeft;
    protected double[] bottomRight;
    
    
    protected Area( double[] _topLeft, double[] _bottomRight )    {
        topLeft = _topLeft;
        bottomRight = _bottomRight;
    }
    
    protected Area()    {
        
    }
    
    /**
     * TODO TODO write a sensible algorithm for dividing up any Area in equally-sized (sub-Areals or ) Cells
     * 
     * Run a query against elasticsearch, and populate this Area's cells with tweet counts for each cell in both
     * the last entirely lapsed hour and in the hour before that one. 
     * @param oneHourBefore
     * @param twoHoursBefore
     * 
     * @return 
     */
    Set<Cell> populate(long[] oneHourBefore, long[] twoHoursBefore) {
        throw new UnsupportedOperationException("Not supported yet."); 
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

    
    
    /**
     * Compute the percentage increase / decrease in Twitter activity for this cell, for the 
     * last entirely lapsed hour as compared to activity in the last entirely lapsed in the hour before, 
     * and set it as the field Cell#percentage. 
     * @param c  A Cell
     */
    void computeHourlyDeltaFor(Cell c) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
    
    /**
     * Get the last entirely lapsed hour and the last hour entirely lapsed before that one, 
     * and set them on the cells. 
     */
    void initializeTimeSlots() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
}
