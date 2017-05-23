package be.swsb.cleancode.ch9;

public class NormalTemp implements HardwareTemperature {
    @Override
    public void reactToTemperature(ControlHardware controlHardware) {
        controlHardware.turnOffBlower();
        controlHardware.turnOffCooler();
        controlHardware.turnOffHeater();
        controlHardware.turnOffLoTempAlarm();
        controlHardware.turnOffHiTempAlarm();
    }
}
