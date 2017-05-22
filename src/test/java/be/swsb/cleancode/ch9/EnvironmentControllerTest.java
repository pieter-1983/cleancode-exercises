package be.swsb.cleancode.ch9;

import org.junit.Before;
import org.junit.Test;

import static be.swsb.cleancode.ch9.EnvironmentController.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnvironmentControllerTest {

    private EnvironmentController controller;
    private MockControlHardware hardware;

    @Before
    public void setUp() throws Exception {
        hardware = new MockControlHardware();
        controller = new EnvironmentController(hardware);
    }

    @Test
    public void turnOnCoolerAndBlowerIfTooHot() throws Exception {
        hardware.setTemp(TOO_HOT);
        controller.hardwareActionsDependingOnTemperature();
        assertThatHeaterIsOff();
        assertThatBlowerIsOn();
        assertThatCoolerIsOn();
        assertThatHighTempAlarmIsOff();
        assertThatLowTempAlarmIsOff();
    }

    @Test
    public void turnOnHeaterAndBlowerIfTooCold() throws Exception {
        hardware.setTemp(TOO_COLD);
        controller.hardwareActionsDependingOnTemperature();
        assertThatHeatherIsOn();
        assertThatBlowerIsOn();
        assertThatHeatherIsOff();
        assertThatHighTempAlarmIsOff();
        assertThatLowTempAlarmIsOff();
    }

    @Test
    public void turnOnLoTempAlarmAtThreshold() throws Exception {
        hardware.setTemp(WAY_TOO_COLD);
        controller.hardwareActionsDependingOnTemperature();
        assertThatHeatherIsOn();
        assertThatBlowerIsOn();
        assertThatHeatherIsOff();
        assertThatHighTempAlarmIsOff();
        assertThatLowTempAlarmIsOn();
    }

    @Test
    public void turnOnHiTempAlarmAtThreshold() throws Exception {
        hardware.setTemp(WAY_TOO_HOT);
        controller.hardwareActionsDependingOnTemperature();
        assertThatHeaterIsOff();
        assertThatBlowerIsOn();
        assertThatCoolerIsOn();
        assertThatHighTempAlarmIsOn();
        assertThatLowTempAlarmIsOff();
    }

    private void assertThatBlowerIsOn() {
        assertTrue(hardware.blowerState());
    }

    private void assertThatLowTempAlarmIsOff() {
        assertFalse(hardware.loTempAlarm());
    }

    private void assertThatHeatherIsOff() {
        assertFalse(hardware.coolerState());
    }

    private void assertThatHighTempAlarmIsOn() {
        assertTrue(hardware.hiTempAlarm());
    }

    private void assertThatCoolerIsOn() {
        assertTrue(hardware.coolerState());
    }

    private void assertThatHeaterIsOff() {
        assertFalse(hardware.heaterState());
    }

    private void assertThatLowTempAlarmIsOn() {
        assertTrue(hardware.loTempAlarm());
    }

    private void assertThatHighTempAlarmIsOff() {
        assertFalse(hardware.hiTempAlarm());
    }

    private void assertThatHeatherIsOn() {
        assertTrue(hardware.heaterState());
    }

}