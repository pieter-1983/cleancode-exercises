package be.swsb.cleancode.ch9;

public class WayTooCold implements HardwareState {

    @Override
    public void reactToTemperature(ControlHardware controlHardware) {
            controlHardware.turnOnBlower();
            controlHardware.turnOnHeater();
            controlHardware.turnOnLoTempAlarm();
        }

}
