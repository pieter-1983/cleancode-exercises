package be.swsb.cleancode.ch9;

public class TooHot implements HardwareState {

    @Override
    public void reactToTemperature( ControlHardware controlHardware) {
        controlHardware.turnOnBlower();
        controlHardware.turnOnCooler();
        controlHardware.turnOffHiTempAlarm();
    }
}
