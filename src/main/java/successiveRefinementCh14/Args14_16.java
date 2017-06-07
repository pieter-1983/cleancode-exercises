package successiveRefinementCh14;

import successiveRefinementCh14.theCleanSetupListings14_2till14_7.*;

import java.util.*;

public class Args14_16 {
    private String schema;
    private Map<Character, ArgumentMarshaler> marshalers =
            new HashMap<Character, ArgumentMarshaler>();
    private Set<Character> argsFound = new HashSet<Character>();
    private Iterator<String> currentArgument;
    private List<String> argsList;

    public Args14_16(String schema, String[] args) throws ArgsException14_15 {
        this.schema = schema;
        argsList = Arrays.asList(args);
        parse();
    }

    private void parse() throws ArgsException14_15 {
        parseSchema();
        parseArguments();
    }

    private boolean parseSchema() throws ArgsException14_15 {
        for (String element : schema.split(",")) {
            if (element.length() > 0) {
                parseSchemaElement(element.trim());
            }
        }
        return true;
    }

    private void parseSchemaElement(String element) throws ArgsException14_15 {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);
        if (elementTail.length() == 0)
            marshalers.put(elementId, new BooleanArgumentMarshaler());
        else if (elementTail.equals("*"))
        marshalers.put(elementId, new StringArgumentMarshaler());

       else if (elementTail.equals("#"))
            marshalers.put(elementId, new IntegerArgumentMarshaler());
        else if (elementTail.equals("##"))
            marshalers.put(elementId, new DoubleArgumentMarshaler());
        else
            throw new ArgsException14_15(ArgsException14_15.ErrorCode.INVALID_FORMAT,
                    elementId, elementTail);
    }

    private void validateSchemaElementId(char elementId) throws ArgsException14_15 {
        if (!Character.isLetter(elementId)) {
            throw new ArgsException14_15(ArgsException14_15.ErrorCode.INVALID_ARGUMENT_NAME,
                    elementId, null);
        }
    }

    private void parseArguments() throws ArgsException14_15 {
        for (currentArgument = argsList.iterator(); currentArgument.hasNext();) {
            String arg = currentArgument.next();
            parseArgument(arg);
        }
    }

    private void parseArgument(String arg) throws ArgsException14_15 {
        if (arg.startsWith("-"))
        parseElements(arg);
    }

    private void parseElements(String arg) throws ArgsException14_15 {
        for (int i = 1; i < arg.length(); i++)
            parseElement(arg.charAt(i));
    }

    private void parseElement(char argChar) throws ArgsException14_15 {
        if (setArgument(argChar))
            argsFound.add(argChar);
        else {
            throw new ArgsException14_15(ArgsException14_15.ErrorCode.UNEXPECTED_ARGUMENT,
                    argChar, null);
        }
    }

    private boolean setArgument(char argChar) throws ArgsException14_15 {
        ArgumentMarshaler m = marshalers.get(argChar);
        if (m == null)
            return false;
        try {
            m.set(currentArgument);
            return true;
        } catch (ArgsException e) {
            e.setErrorArgumentId(argChar);
            throw new ArgsException14_15();
        }
    }

    public int cardinality() {
        return argsFound.size();
    }

    public String usage() {
        if (schema.length() > 0)
            return "-[" + schema + "]";
       else
        return "";
    }

    public boolean getBoolean(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        boolean b = false;
        try {
            b = am != null && BooleanArgumentMarshaler.getValue(am);
        } catch (ClassCastException e) {
            b = false;
        }
        return b;
    }

    public String getString(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        try {
            return am == null ? "" : StringArgumentMarshaler.getValue(am);
        } catch (ClassCastException e) {
            return "";
        }
    }

    public int getInt(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        try {
            return am == null ? 0 : IntegerArgumentMarshaler.getValue(am);
        } catch (Exception e) {
            return 0;
        }
    }


    public double getDouble(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        try {
            return am == null ? 0 : DoubleArgumentMarshaler.getValue(am);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }
}
