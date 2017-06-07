package successiveRefinementCh14.theCleanSetupListings14_2till14_7;

import java.util.Iterator;

public interface ArgumentMarshaler {
    void set(Iterator<String> currentArgument) throws ArgsException;


}
