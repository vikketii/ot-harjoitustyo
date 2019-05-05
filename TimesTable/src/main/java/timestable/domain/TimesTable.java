package timestable.domain;

import java.util.ArrayList;

/**
 * @author vikke
 *
 * Generates needed vectors for drawing.
 */
public class TimesTable {

    private ArrayList<Vector> vectors;
    private Settings settings;

    /**
     * Generates all vectors.
     *
     * @param multiplier Speed of vectors
     * @param totalVectors Amount of total lines
     */
    public TimesTable(Settings settings) {
        this.settings = settings;
        this.vectors = new ArrayList<>();

        if (settings.getTotalVectors() < 0) {
            return;
        }

        for (int i = 0; i < settings.getTotalVectors(); i++) {
            Vector v = initVector(i + 1, settings.getMultiplier(), settings.getTotalVectors());
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
        double t = (place + 1) * ((2 * Math.PI) / totalVectors);
        double startX = settings.getCircleRadius() + settings.getCircleRadius() * Math.cos(t);
        double startY = settings.getCircleRadius() + settings.getCircleRadius() * Math.sin(t);

        t *= multiplier;
        double endX = settings.getCircleRadius() + settings.getCircleRadius() * Math.cos(t);
        double endY = settings.getCircleRadius() + settings.getCircleRadius() * Math.sin(t);

        return new Vector(startX, startY, endX, endY);
    }

    /**
     * Updates all vectors based on parameter changes.
     *
     */
    public void updateVectors() {
        if (vectors.size() != settings.getTotalVectors()) {
            vectors.clear();
            for (int i = 0; i < settings.getTotalVectors(); i++) {
                Vector v = initVector(i, settings.getMultiplier(), settings.getTotalVectors());
                vectors.add(v);
            }
        } else {
            for (int i = 0; i < settings.getTotalVectors(); i++) {
                double t = settings.getMultiplier() * (i + 2) * ((2 * Math.PI) / settings.getTotalVectors());
                double endX = settings.getCircleRadius() + settings.getCircleRadius() * Math.cos(t);
                double endY = settings.getCircleRadius() + settings.getCircleRadius() * Math.sin(t);
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
}
