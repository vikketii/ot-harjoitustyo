package timestable.domain;

import java.util.ArrayList;

/**
 *
 * @author vikke
 */
public class TimesTable {

    private ArrayList<Vector> vectors;
    private int circleRadius = 380;

    public TimesTable() {
        vectors = new ArrayList<>();
    }

    public void init(double multiplier, int totalVectors) {
        if (totalVectors < 1) {
            return;
        }
        
        // First we need coordinates along 
        // the circle (which is divided by totalVectors to parts)
        //
        // For this we use simple math to calculate x and y like
        // x = a + r*cos(t)
        // y = b + r*sin(t)
        // where (a,b) is the center of the circle
        // and t is (2*Pi)/totalVectors

        for (int i = 0; i < totalVectors; i++) {
            Vector v = initVector(i + 1, multiplier, totalVectors);
            vectors.add(v);
        }
    }

    public Vector initVector(int place, double multiplier, int totalVectors) {
        double t = (place + 1) * ((2 * Math.PI) / totalVectors);
        double startX = circleRadius + circleRadius * Math.cos(t);
        double startY = circleRadius + circleRadius * Math.sin(t);

        t *= multiplier;
        double endX = circleRadius + circleRadius * Math.cos(t);
        double endY = circleRadius + circleRadius * Math.sin(t);

        return new Vector(startX, startY, endX, endY);
    }

    public void updateVectors(double multiplier, int totalVectors) {
        if (vectors.size() != totalVectors) {
            vectors.clear();
            for (int i = 0; i < totalVectors; i++) {
                Vector v = initVector(i, multiplier, totalVectors);
                vectors.add(v);
            }
        } else {
            for (int i = 0; i < totalVectors; i++) {
                double t = multiplier * (i + 1) * ((2 * Math.PI) / totalVectors);
                double endX = circleRadius + circleRadius * Math.cos(t);
                double endY = circleRadius + circleRadius * Math.sin(t);
                vectors.get(i).setEndPoints(endX, endY);
            }
        }
    }

    public ArrayList<Vector> getVectors() {
        return vectors;
    }

    public int getCircleRadius() {
        return circleRadius;
    }
}
