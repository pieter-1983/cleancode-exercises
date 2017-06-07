package successiveRefinementCh14.theCleanSetupListings14_2till14_7;

import java.util.Iterator;

public class DoubleArgumentMarshaler implements successiveRefinementCh14.theCleanSetupListings14_2till14_7.ArgumentMarshaler {
    private double doubleValue=0.0;
    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {

    }

    public static double getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof DoubleArgumentMarshaler)
            return ((DoubleArgumentMarshaler) am).doubleValue;
        else
            return 0.0;
    }
}
