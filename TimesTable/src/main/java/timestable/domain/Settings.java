/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestable.domain;

import javafx.scene.paint.Color;

/**
 * Class for saving generator settings.
 *
 * @author vikke
 */
public class Settings {

    private int circleRadius = 360;
    private int totalVectors = 10;
    private double multiplier = 2;
    private int speed = 1;
    private Color lineMainColor = Color.rgb(10, 100, 100, 0.8);
    private Color bgMainColor = Color.rgb(255, 255, 255);

    public Settings() {
    }

    public Settings(int totalVectors, double multiplier, int speed, Color lineMainColor, Color bgMainColor) {
        this.totalVectors = totalVectors;
        this.multiplier = multiplier;
        this.speed = speed;
        this.lineMainColor = lineMainColor;
        this.bgMainColor = bgMainColor;
    }

    public int getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
    }

    public int getTotalVectors() {
        return totalVectors;
    }

    public void setTotalVectors(int totalVectors) {
        this.totalVectors = totalVectors;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getLineMainColor() {
        return lineMainColor;
    }

    public void setLineMainColor(Color lineMainColor) {
        this.lineMainColor = lineMainColor;
    }

    public Color getBgMainColor() {
        return bgMainColor;
    }

    public void setBgMainColor(Color bgMainColor) {
        this.bgMainColor = bgMainColor;
    }
}
