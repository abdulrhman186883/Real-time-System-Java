# Real-time Burglar Alarm System Designed by Java ( threads ) 

A Burglar alarm system is a system designed to detect intrusion – unauthorized entry – into a building or area.
Security alarms are used in residential, commercial, industrial, and military properties for protection against
burglary (theft) or property damage, as well as personal protection against intruders. The Burglar alarm system
uses several different types of sensors. These include movement detectors in individual rooms, door sensors that
detect corridor doors opening, and window sensors on ground floor windows that can detect when a window has
been opened.
When a sensor detects the presence of an intruder, the system automatically calls the local police and, using a
voice synthesizer reports the location of the alarm. It switches on lights in the rooms around the active sensor
and sets off an audible alarm. Each movement detector should be polled twice per second. 
The sensor is normally powered by mains power but is equipped with a battery backup. Power loss is detected
using a separate power circuit monitor that monitors the main voltage. If a voltage drop is detected, the system
assumes that intruders have interrupted the power supply, so an alarm is raised. The alarm system includes a
display, which reports these anomalies upon occurrence.
When one or more sensors are positive, which means that an intruder is detected, the system initiates an alarm
within half a second of an alarm being raised by a senor and turns on lights around the site of the positive sensor
within half a second of an alarm being raised by a sensor. In addition, when two or more sensors are positive, the
system calls the police with the location of the suspected break-in within 2 seconds of an alarm being raised by
a sensor. If alarms are on, each door and window alarm should be polled twice per second.
If the voltage of the alarm system drops by between 10% and 20%, a battery backup must be switched on within
a deadline of 50ms, and the system runs a power supply test. If the voltage drops more than 20%, in addition
to the previous actions, the system must initiate an alarm and call the police. In case of a power supply failure or
sensor failure, the system calls a service technician. Whenever the console panic button is positive, the system
must initiate an alarm, turn on lights around the console and call the police.
If a clear-alarms command is activated, all active alarms must be switched off, and all lights that have been
switched on should be switched off.
