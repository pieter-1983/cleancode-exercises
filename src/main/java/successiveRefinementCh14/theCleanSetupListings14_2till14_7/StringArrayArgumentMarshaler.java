package successiveRefinementCh14.theCleanSetupListings14_2till14_7;

import java.util.Iterator;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
    private String[] stringArrayValue= new String[0];
    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {

    }

    public static String[] getValue(ArgumentMarshaler am) {
        if (am != null && am instanceof StringArrayArgumentMarshaler)
            return ((StringArrayArgumentMarshaler) am).stringArrayValue;
        else
            return new String[0];
    }
}
