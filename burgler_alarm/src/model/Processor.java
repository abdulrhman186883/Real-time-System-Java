/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.util.ArrayList;
import view.Burgler_Alarm;

/**
 *
 * @author dell
 */
public class Processor {

    private ArrayList<Sensor> sensors;
    private FingerPrintSensor fingerPrint;
    private final Burgler_Alarm gui;
    private PowerCircuteMonitor powerCircuteMonitor;
    private PowerSupply powerSupply;
    private Alarm alarm;
    private VoiceSynthesizer voiceSyn;
    private AutoDialar autoDialar;
    WindowSensor windowSensor;
    WindowSensor windowSensor2;
    WindowSensor windowSensor3;
    DoorSensor doorSensor;
    DoorSensor doorSensor2;
    MovmentSensor movmentSensor;
    MovmentSensor movmentSensor2;
    ConcolePanicBtn consolePanic;
    String fingerPrintt;
    int intrusionCount = 0;
    int entry_attempts = 0;

    public Processor() throws InterruptedException {
        // power the power supply.
        this.powerSupply = new PowerSupply(100, true);
        this.powerSupply.runn();

        // Initializing the gui.
        this.gui = new Burgler_Alarm();
        this.gui.setVisible(true);

        // sending a message in the gui informing the user that the power supply is now on.
        this.sendMsg("The Power is ON.");

        // Initialzing the alarm, voice synthesizer, auto dialar, power circute monitor, concole panic button, and the finger print.
        this.alarm = new Alarm();
        this.voiceSyn = new VoiceSynthesizer();
        this.autoDialar = new AutoDialar(this.voiceSyn);
        this.powerCircuteMonitor = new PowerCircuteMonitor(this.powerSupply, this.alarm, this.voiceSyn);
        this.consolePanic = new ConcolePanicBtn();
        this.fingerPrint = new FingerPrintSensor();
        //this.fingerPrint.runn();

        // Initializing all the SENSORS.
        this.windowSensor2 = new WindowSensor(2, Integer.toString(this.gui.getWindowRoom2TxtField().getX()) + "," + Integer.toString(this.gui.getWindowRoom2TxtField().getY()), "window", false, false, 100);
        this.windowSensor3 = new WindowSensor(3, Integer.toString(this.gui.getWindowSensorTxtField().getX()) + "," + Integer.toString(this.gui.getWindowSensorTxtField().getY()), "window", false, false, 100);
        this.doorSensor = new DoorSensor(4, Integer.toString(this.gui.getDoorSensorRoom2TxtField().getX()) + "," + Integer.toString(this.gui.getDoorSensorRoom2TxtField().getY()), "door", false, false, 100);
        this.doorSensor2 = new DoorSensor(5, Integer.toString(this.gui.getDoorSensorTxtField().getX()) + "," + Integer.toString(this.gui.getDoorSensorTxtField().getY()), "door", false, false, 100);
        this.movmentSensor = new MovmentSensor(6, Integer.toString(this.gui.getMovmentTxtField().getX()) + "," + Integer.toString(this.gui.getMovmentTxtField().getY()), "movment", false, false, 100, this.gui);
        this.movmentSensor2 = new MovmentSensor(7, Integer.toString(this.gui.getMovmentRoom2TxtField().getX()) + "," + Integer.toString(this.gui.getMovmentRoom2TxtField().getY()), "movment", false, false, 100, this.gui);
        this.sensors = new ArrayList();

        // Add the sensors into a container array list.
        AddSensor(windowSensor2);// index 1 
        AddSensor(windowSensor3);// index 2 
        AddSensor(doorSensor);// index 3
        AddSensor(doorSensor2);// index 4 
        AddSensor(movmentSensor);// index 5
        AddSensor(movmentSensor2);// index 6 
    }

    public void sendMsg(String msg) throws InterruptedException {

        this.gui.getMsgLabel().setText(msg);
        Thread.sleep(1000);
        this.gui.getMsgLabel().setText("");

    }

    public void AddSensor(Sensor sensor) {
        this.sensors.add(sensor);
    }

    public void dectivateSensors(boolean deactivate, int count) throws InterruptedException {
        if (count % 2 == 0 && this.fingerPrint.checkFingerPrint(fingerPrintt)) {
            this.ActivateSensors();
        } else if (deactivate && this.fingerPrint.checkFingerPrint(fingerPrintt)) {
            this.sendMsg("SENSORS ARE NOW DEACTIVATED.");
            for (Sensor s : sensors) {
                s.Sensorstate = false;
                System.err.println(s.Sensorstate);
            }
            this.toggleLight(false);

        }

    }

    public void ActivateSensors() throws InterruptedException {
        WindowSensor window;
        DoorSensor door;
        MovmentSensor movment;
        System.out.println("SENSORS ARE NOW ACTIVATED.");
        this.sendMsg("SENSORS ARE NOW ACTIVATED.");
        for (Sensor s : this.sensors) {
            s.Sensorstate = true;
            if (s.name == "window") {
                window = (WindowSensor) s;
                window.runnn();
                System.out.println("The Window Sensor: " + window + " is running.");
            };
            if (s.name == "door") {
                door = (DoorSensor) s;
                door.runnn();
                System.out.println("The Door Sensor: " + door + " is running.");
            };
            if (s.name == "movment") {
                movment = (MovmentSensor) s;
                movment.runnn();
                System.out.println("The Movement Sensor: " + movment + " is running.");

            };
            this.sendMsg("");
            this.setSensorsTableValues(this.sensors);

        }
    }

    public void allowEntry(String fingerPrint) throws Exception {
        // this.sendMsg("Finger print validating.");

        if (entry_attempts <= 2) {
            System.out.println("entry_attemps @ processor: " + entry_attempts);
            boolean valid = this.fingerPrint.checkFingerPrint(fingerPrint);
            if (valid) {
                this.entry_attempts = 0;
                this.fingerPrint.setFingerPrint(fingerPrint);
                this.fingerPrintt = fingerPrint;
                this.gui.getAddFingerprint().setVisible(false);
                this.gui.getAddFingerprintBtn().setVisible(false);
                this.gui.getAddFingerPrintLabel().setVisible(false);

                // this.gui.getMsgLabel2().setText("Finger print is valid.");
                System.out.println("Finger print: " + fingerPrint + " is valid.");
                this.ActivateSensors();
            } else {
                this.entry_attempts++;
                System.out.println("Your Finger print is not correct");
                // this.sendMsg("Your Finger print is not correct");
            }
        } else {
            this.gui.getAddFingerprint().setVisible(false);
            this.gui.getAddFingerprintBtn().setVisible(false);
            this.gui.getAddFingerPrintLabel().setVisible(false);

            System.err.println("Your finger print is wrong");
            this.emergentState(true, "", "fingerprint");
        }
    }

    public void detectPowerLoss(int voltage) throws InterruptedException {
        boolean lossDetected = this.powerCircuteMonitor.detectVoltageLoss(voltage);
        System.out.println("The power loss reading is now: " + voltage);
        this.gui.getTheReadingLabel().setText(Integer.toString(voltage));
        if (lossDetected) {
            this.gui.getTheReadingLabel().setText(Integer.toString(voltage) + " Switching Backup");
            this.powerCircuteMonitor.runPowerSupplyTest(voltage);
            this.emergentState(true, "", "Power supply error");
        }

    }

    public int FindIndex(int id) {
        int index = 0;
        for (Sensor s : this.sensors) {
            if (s.id == id) {
                return index;
            }
            index++;
        }
        return index;
    }

    public void WindowdetectIntrusion(int Window_reading, int id) throws InterruptedException {
        Thread.sleep(1000);
        int index = FindIndex(id);
        WindowSensor window = (WindowSensor) this.sensors.get(index);
        if (window.Sensorstate != false) {
            if (window.id == 2) {
                this.gui.getWindowSensorTxtField().setText(Integer.toString(Window_reading));
            } else if (window.id == 3) {
                this.gui.getWindowRoom2TxtField().setText(Integer.toString(Window_reading));

            }
            System.out.println("The window sensor that has the intrusion is: " + window.id + " at the location:"
                    + window.location
                    + "The window reading is: " + Window_reading);
            if (Window_reading >= 100) {
                window.alarmState = true;
                String time = String.valueOf(java.time.LocalDateTime.now());
                if (window.id == 2) {
                    this.gui.getReportTable().setValueAt(time, 0, 3);
                } else {
                    this.gui.getReportTable().setValueAt(time, 1, 3);
                }
                this.emergentState(true, window.location, "Sensor detected an intrusion");

            }
        }
    }

    public void setSensorsTableValues(ArrayList<Sensor> sensor) {
        int i = 0;
        for (Sensor s : this.sensors) {
            this.gui.getReportTable().setValueAt(s.name, i, 0);
            this.gui.getReportTable().setValueAt(s.location, i, 1);
            this.gui.getReportTable().setValueAt(100, i, 2);
            i++;

        }
    }

    public void DoordetectIntrusion(int door_reading, int id) throws InterruptedException {
        int index = FindIndex(id);
        DoorSensor door = (DoorSensor) this.sensors.get(index);
        if (door.Sensorstate != false) {
            Thread.sleep(1000);
            if (door.id == 4) {
                this.gui.getDoorSensorTxtField().setText(Integer.toString(door_reading) + "");
            } else if (door.id == 5) {
                this.gui.getDoorSensorRoom2TxtField().setText(Integer.toString(door_reading) + "");
            }
            System.out.println("The door sensor that has the intrusion is: " + door.id + " at the location:"
                    + door.location
                    + "The window reading is: " + door_reading);
            if (door_reading >= 100) {
                door.alarmState = true;
                String time = String.valueOf(java.time.LocalDateTime.now());
                if (door.id == 4) {
                    this.gui.getReportTable().setValueAt(time, 2, 3);
                } else {
                    this.gui.getReportTable().setValueAt(time, 3, 3);
                }
                this.emergentState(true, door.location, "Sensor detected an intrusion");

            }
        }
    }

    public void MovmentdetectIntrusion(int movment_reading, int id) throws InterruptedException {
        Thread.sleep(1000);
        int index = FindIndex(id);
        MovmentSensor movment = (MovmentSensor) this.sensors.get(index);
        if (movment.Sensorstate != false) {
            if (movment.id == 6) {
                this.gui.getMovmentTxtField().setText(Integer.toString(movment_reading));
            } else if (movment.id == 7) {
                this.gui.getMovmentRoom2TxtField().setText(Integer.toString(movment_reading));
            }
            System.out.println("The movment sensor: " + movment.id + " at the location: "
                    + movment.location
                    + " and The reading is: " + movment_reading);
            if (movment_reading >= 100) {
                System.out.println("The movment sensor that has the intrusion is: " + movment.id + " at the location:"
                        + movment.location + " and the reading is: " + movment_reading);
                movment.alarmState = true;
                String time = String.valueOf(java.time.LocalDateTime.now());
                if (movment.id == 6) {
                    this.gui.getReportTable().setValueAt(time, 4, 3);
                } else {
                    this.gui.getReportTable().setValueAt(time, 5, 3);
                }
                this.emergentState(true, movment.location, "Sensor detected an intrusion");

            }
        }
    }

    public void stopAlarm(boolean stop) {
        this.gui.getAlarmLable().setText("");
        this.alarm.stopAlarm();
        for (Sensor s : this.sensors) {
            s.alarmState = false;
        }
    }

    public void toggleLight(boolean state) {
        if (state) {
            this.gui.getRoom1Panel().setBackground(Color.WHITE);
            this.gui.getRoom2Panel().setBackground(Color.WHITE);
        } else {
            this.gui.getRoom1Panel().setBackground(Color.GRAY);
            this.gui.getRoom2Panel().setBackground(Color.GRAY);
        }
    }

    public synchronized void emergentState(boolean invalid, String location, String msg) throws InterruptedException {
        if (msg.contains("Sensor")) {
            this.intrusionCount++;
        }
        String panicLocation = Integer.toString(this.gui.getPanicBtn().getX()) + "," + Integer.toString(this.gui.getPanicBtn().getY());
        this.alarm.raiseAlarm();
        this.toggleLight(true);
        this.gui.getAlarmLable().setText("HELP!! " + msg + " at location: " + location);
        if (intrusionCount == 2) {
            System.err.println("TWO INTRUSION DETECTED");
            this.autoDialar.call("Police", location);
            this.intrusionCount = 0;
        }
        if (msg.equals("Panic") || msg.equals("fingerprint")) {
            this.autoDialar.call("Police", panicLocation);
        }
    }

}
