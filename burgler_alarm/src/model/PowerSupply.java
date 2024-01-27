/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import burgler_alarm.Config;
import events.DetectPowerLoss;
import events.DoorDetectedIntrusion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class PowerSupply {

    int voltage_percentage;
    boolean enabled;

    public PowerSupply(int voltage_percentage, boolean enabled) {
        this.voltage_percentage = voltage_percentage;
        this.enabled = enabled;
        System.out.println("The Power in now ON");
    }

    public int getVoltage_percentage() {
        return voltage_percentage;
    }

    public void setVoltage_percentage(int voltage_percentage) {
        this.voltage_percentage = voltage_percentage;
    }

    public boolean switchBackup() {
        this.voltage_percentage = 100;
        System.out.println("The volatage are now backbed: " + this.voltage_percentage);
        return true;
    }

    void runn() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DetectPowerLoss.class.getName()).log(Level.SEVERE, null, ex);
                    }

                   Config.sendEvent(new DetectPowerLoss(100));
                }
            }

        }).start();
    }

}
