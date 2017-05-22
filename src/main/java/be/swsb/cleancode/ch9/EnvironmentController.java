package be.swsb.cleancode.ch9;

public class EnvironmentController {

    public static final double TOO_HOT = 30.0;
    public static final double TOO_COLD = 11.0;
    public static final double WAY_TOO_HOT = 34.0;
    public static final double WAY_TOO_COLD = 6.0;

    private ControlHardware controlHardware;

    public EnvironmentController(ControlHardware controlHardware) {
        this.controlHardware = controlHardware;
    }

    // Just like Uncle Bob says: I'd rather you not worry about that while reading the test.
    // I'd rather you just worry about whether you agree that the end state of the system is consistent with the temperature being "way too cold".
    public void tic() {
        double currentTemperature = controlHardware.getTemp();

        if (currentTemperature <= TOO_COLD) {
            TooCold tooCold= new TooCold();
            tooCold.reactToTemperature(controlHardware);
            if (currentTemperature <= WAY_TOO_COLD) {
              WayTooCold wayTooCold = new WayTooCold();
              wayTooCold.reactToTemperature(controlHardware);
            }
        } else if (currentTemperature >= TOO_HOT) {
          TooHot tooHot = new TooHot();
          tooHot.reactToTemperature(controlHardware);
            if (currentTemperature >= WAY_TOO_HOT) {
             WayTooHot wayTooHot =  new WayTooHot();
             wayTooHot.reactToTemperature(controlHardware);
            }
        } else {
           NormalTemp normalTemp= new NormalTemp();
           normalTemp.reactToTemperature(controlHardware);
        }
    }
}