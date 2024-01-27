/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burgler_alarm;

import model.Processor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author dell
 */
public class Burgler_alarm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        // Disable logging
        Logger.getRootLogger().setLevel(Level.OFF);

        // Register events
        Config.registerEvents();

        final Processor processor = new Processor();

//         //Window Sensor reading.
        Config.createStatement("select current_reading,id from WindowDetectedIntrusion")
                .setSubscriber(new Object() {
                    public void update(int Window_reading,int id) throws InterruptedException {
                        processor.WindowdetectIntrusion(Window_reading,id);
                    }
                });

        // Door Sensor reading.
        Config.createStatement("select current_reading,id from DoorDetectedIntrusion")
                .setSubscriber(new Object() {
                    public void update(int Door_reading,int id) throws InterruptedException {
                        processor.DoordetectIntrusion(Door_reading,id);
                    }
                });
//
        // Movment Sensor reading.
        Config.createStatement("select current_reading,id from MovmentDetectedIntrusion")
                .setSubscriber(new Object() {
                    public void update(int detected,int id) throws InterruptedException {
                        processor.MovmentdetectIntrusion(detected,id);
                    }
                });

         //Detect power supply loss.
        Config.createStatement("select current_reading from DetectPowerLoss")
                .setSubscriber(new Object() {
                    public void update(int voltage_percentage) throws InterruptedException {
                        processor.detectPowerLoss(voltage_percentage);
                    }
                });
        
        // Detect finger print.
        Config.createStatement("select current_reading from detectFingerPrint")
                .setSubscriber(new Object() {
                    public void update(String current_reading) throws InterruptedException, Exception {
                        System.out.println(current_reading);
                        processor.allowEntry(current_reading);
                    }
                });
        // Invalid finger print.
        Config.createStatement("select invalid from InvalidFingerPrint")
                .setSubscriber(new Object() {
                    public void update(boolean invalid) throws InterruptedException, Exception {
                        processor.emergentState(invalid,"Fingerprint","Invalid fingerprint detected");
                    }
                });
       // Concole panic button.
        Config.createStatement("select clicked from consolePanicBtn")
                .setSubscriber(new Object() {
                    public void update(boolean clicked) throws InterruptedException, Exception {
                        processor.emergentState(clicked,"panic","Panic state. excape the building!!.");
                    }
                });
           // Deactivate sensors.
        Config.createStatement("select clicked,counter from deactivateSensor")
                .setSubscriber(new Object() {
                    public void update(boolean clicked,int counter) throws InterruptedException, Exception {
                       processor.dectivateSensors(clicked, counter);
                    }
                });
        
        // stop alarm.
             Config.createStatement("select stop from stopAlarm")
                .setSubscriber(new Object() {
                    public void update(boolean stop) throws InterruptedException, Exception {
                       processor.stopAlarm(stop);
                    }
                });
          // Change power supply reading.
             Config.createStatement("select reading from changeSupplyReading")
                .setSubscriber(new Object() {
                    public void update(int reading) throws InterruptedException, Exception {
                       processor.detectPowerLoss(reading);
                    }
                });
        
    }

}
