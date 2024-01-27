/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burgler_alarm;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import events.*;

/**
 *
 * @author dell
 */
public class Config {
    
    private static EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

    public static void registerEvents() {
        engine.getEPAdministrator().getConfiguration().addEventType(DetectPowerLoss.class);
        engine.getEPAdministrator().getConfiguration().addEventType(MovmentDetectedIntrusion.class);
        engine.getEPAdministrator().getConfiguration().addEventType(DoorDetectedIntrusion.class);
        engine.getEPAdministrator().getConfiguration().addEventType(WindowDetectedIntrusion.class);
        engine.getEPAdministrator().getConfiguration().addEventType(detectFingerPrint.class);
        engine.getEPAdministrator().getConfiguration().addEventType(InvalidFingerPrint.class);
        engine.getEPAdministrator().getConfiguration().addEventType(consolePanicBtn.class);
        engine.getEPAdministrator().getConfiguration().addEventType(deactivateSensor.class);
        engine.getEPAdministrator().getConfiguration().addEventType(stopAlarm.class);
        engine.getEPAdministrator().getConfiguration().addEventType(changeSupplyReading.class);


        System.out.println("Events Successfully Registered.");
    }

    public static EPStatement createStatement(String s) {
        EPStatement result = engine.getEPAdministrator().createEPL(s);
        System.out.println("EPL Statement Successfully Created.");
        return result;
    }
    
    public static void sendEvent(Object o)
    {
        engine.getEPRuntime().sendEvent(o);
    }
}
