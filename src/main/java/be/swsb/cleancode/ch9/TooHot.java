package be.swsb.cleancode.ch9;

public class TooHot implements HardwareTemperature {

    @Override
    public void reactToTemperature( ControlHardware controlHardware) {
        controlHardware.turnOnBlower();
        controlHardware.turnOnCooler();
        controlHardware.turnOffHiTempAlarm();
    }
}
