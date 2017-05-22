package be.swsb.cleancode.ch9;

public class WayTooHot implements HardwareStates {


    @Override
    public void reactToTemperature( ControlHardware controlHardware) {
        controlHardware.turnOnBlower();
        controlHardware.turnOnCooler();
        controlHardware.turnOnHiTempAlarm();

    }
}
