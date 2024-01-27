/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public abstract class Sensor{

    int id;
    String location;
    String name;
    boolean Sensorstate; // related to the sensor whether it is on or off duity.
    boolean alarmState; // related to the sensor whether the alarm is on or off.
    int voltage_percentage;

    public Sensor(int id, String location, String name, boolean Sensorstate, boolean alarmState) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.Sensorstate = Sensorstate;
        this.voltage_percentage = voltage_percentage;
        this.alarmState = alarmState;
       
    }
 

}
