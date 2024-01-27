/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class PowerCircuteMonitor {

    PowerSupply p;
    AutoDialar autoDialar;
    Alarm alarm;
    private int voltage;

    PowerCircuteMonitor(PowerSupply powerSupply,Alarm alarm,VoiceSynthesizer voice) {
        this.p =  powerSupply;
        this.autoDialar = new AutoDialar(voice);
        this.alarm = alarm;
    }

    public boolean detectVoltageLoss(int voltage) {
        return voltage < 90;
    }

    public void runPowerSupplyTest(int voltage) {
      if (voltage <= 80 && voltage > 30) {
          this.autoDialar.call("Police", "Power circute Monitor");
          alarm.raiseAlarm();
          
        } else if (voltage <= 30 && voltage >= 0) {
            this.autoDialar.call("Technecian", "Power circute Monitor");
        }
        p.switchBackup();
        this.autoDialar.call("Manager", "Power circute Monitor");
    }

}
