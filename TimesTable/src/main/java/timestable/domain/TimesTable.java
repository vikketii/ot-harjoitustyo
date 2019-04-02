package timestable.domain;

import java.util.ArrayList;

/**
 *
 * @author vikke
 */
public class TimesTable {
    private ArrayList<Vector> vectors;
    private int circleRadius = 256;

    public TimesTable() {
        vectors = new ArrayList<>();
    }

    public void init(int multiplier, int totalVectors) {
        // First we need coordinates along 
        // the circle (which is divided by totalVectors to parts)
        
        // For this we use simple math to calculate x and y like
        // x = a + r*cos(t)
        // y = b + r*sin(t)
        // where (a,b) is the center of the circle
        // and t is (2*Pi)/totalVectors
        
        int a = circleRadius;

        for (int i = 0; i < totalVectors; i++) {
            double t = i * ((2 * Math.PI) / totalVectors);
            double startX = a + circleRadius*Math.cos(t);
            double startY = a + circleRadius*Math.sin(t);
            t = multiplier * i * ((2 * Math.PI) / totalVectors);
            double endX = a + circleRadius*Math.cos(t);
            double endY = a + circleRadius*Math.sin(t);
            
            vectors.add(new Vector(startX, startY, endX, endY));
        }
        
        
    }
    
    public ArrayList<Vector> getVectors() {
        return vectors;
    }

    public int getCircleRadius() {
        return circleRadius;
    }
    
    
}
