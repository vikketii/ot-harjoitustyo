package timestable.domain;

import java.util.ArrayList;

/**
 * @author vikke
 *
 * Generates needed vectors for drawing.
 */
public class TimesTable {

    private ArrayList<Vector> vectors;
    private int circleRadius = 380;

    /**
     * Generates all vectors.
     *
     * @param multiplier Speed of vectors
     * @param totalVectors Amount of total lines
     */
    public TimesTable(double multiplier, int totalVectors) {
        vectors = new ArrayList<>();

        if (totalVectors < 1) {
            return;
        }

        for (int i = 0; i < totalVectors; i++) {
            Vector v = initVector(i + 1, multiplier, totalVectors);
            vectors.add(v);
        }
    }

    /**
     * Initialises single Vector.
     *
     * @param place Place on the circle
     * @param multiplier Speed of vector
     * @param totalVectors Amount of total lines
     * @return generated Vector
     */
    public Vector initVector(int place, double multiplier, int totalVectors) {
        // First we need coordinates along 
        // the circle (which is divided by totalVectors to parts)
        //
        // For this we use simple math to calculate x and y like
        // x = a + r*cos(t)
        // y = b + r*sin(t)
        // where (a,b) is the center of the circle
        // and t is (2*Pi)/totalVectors

        double t = (place + 1) * ((2 * Math.PI) / totalVectors);
        double startX = circleRadius + circleRadius * Math.cos(t);
        double startY = circleRadius + circleRadius * Math.sin(t);

        t *= multiplier;
        double endX = circleRadius + circleRadius * Math.cos(t);
        double endY = circleRadius + circleRadius * Math.sin(t);

        return new Vector(startX, startY, endX, endY);
    }

    /**
     * Updates all vectors based on parameter changes.
     *
     * @param multiplier Speed of vectors
     * @param totalVectors Amount of total lines
     */
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

    /**
     *
     * @return All vectors.
     */
    public ArrayList<Vector> getVectors() {
        return vectors;
    }

    /**
     *
     * @return Circle radius on which all vectors are based on.
     */
    public int getCircleRadius() {
        return circleRadius;
    }
}
