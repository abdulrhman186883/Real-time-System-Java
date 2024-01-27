/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import burgler_alarm.Config;
import events.MovmentDetectedIntrusion;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Burgler_Alarm;

/**
 *
 * @author dell
 */
public class MovmentSensor extends Sensor {

    private double range; // the range of the sensor.
    private Burgler_Alarm gui;

    public MovmentSensor(int id, String location, String name, boolean Sensorstate, boolean alarmState, double range, Burgler_Alarm gui) {
        super(id, location, name, Sensorstate, alarmState);
        this.range = range;
        this.gui = gui;
    }

    public double getRange() {
        return range;
    }

    public void configureRange(double range) {
        this.range = range;
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
                        Logger.getLogger(MovmentDetectedIntrusion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                  
                    Config.sendEvent(new MovmentDetectedIntrusion(random(1, 110), id));
                }
            }

        }).start();
    }

}
