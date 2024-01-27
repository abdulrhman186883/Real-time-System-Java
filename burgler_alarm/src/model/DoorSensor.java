/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import burgler_alarm.Config;
import events.DoorDetectedIntrusion;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class DoorSensor extends Sensor {

    private double threshold;

    public DoorSensor(int id, String location, String name, boolean Sensorstate, boolean alarmState, double threshold) {
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

    void runnn() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DoorDetectedIntrusion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Config.sendEvent(new DoorDetectedIntrusion(random(1, 1100), id));
                }
            }

        }).start();
    }

}
