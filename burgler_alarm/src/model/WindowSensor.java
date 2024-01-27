/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import burgler_alarm.Config;
import events.WindowDetectedIntrusion;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class WindowSensor extends Sensor {

    private double threshold;

    public WindowSensor(int id, String location, String name, boolean Sensorstate, boolean alarmState, double threshold) {
        super(id, location, name, Sensorstate, alarmState);
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }

    public void configureThreshold(double threshold) {
        this.threshold = threshold;
    }

    private int random(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void runnn() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(WindowDetectedIntrusion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int reading = 10; // to be reblaces with gui.getWindowReading().getText()
                    Config.sendEvent(new WindowDetectedIntrusion(random(1, 1110), id));
                }
            }

        }).start();

    }

}
