package be.swsb.cleancode.ch9;

public class NormalTemp implements HardwareStates {
    @Override
    public void reactToTemperature(ControlHardware controlHardware) {
        controlHardware.turnOffBlower();
        controlHardware.turnOffCooler();
        controlHardware.turnOffHeater();
        controlHardware.turnOffLoTempAlarm();
        controlHardware.turnOffHiTempAlarm();
    }
}
