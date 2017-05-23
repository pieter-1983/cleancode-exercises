package be.swsb.cleancode.ch9;

public class WayTooCold implements HardwareTemperature {

    @Override
    public void reactToTemperature(ControlHardware controlHardware) {
            controlHardware.turnOnBlower();
            controlHardware.turnOnHeater();
            controlHardware.turnOnLoTempAlarm();
        }

}
