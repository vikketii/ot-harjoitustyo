package timestable.domain;

/**
 * 2D-vector class with start and endpoint.
 *
 * @author vikke
 */
public class Vector {

    private double startX;
    private double startY;
    private double endX;
    private double endY;

    /**
     * For initialising vectors.
     */
    public Vector() {
    }

    /**
     * Generates 2D-vector with start and endpoint.
     *
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public Vector(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public void setEndY(double endY) {
        this.endY = endY;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public void setStartY(double startY) {
        this.startY = startY;
    }

    public void setEndPoints(double endX, double endY) {
        this.endX = endX;
        this.endY = endY;
    }

    /**
     * Returns true only if all points are same.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }
        Vector other = (Vector) obj;
        return (startX == other.getStartX()
                && startY == other.getStartY()
                && endX == other.getEndX()
                && endY == other.getEndY());
    }
}
