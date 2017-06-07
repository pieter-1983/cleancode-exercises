package successiveRefinementCh14;

import org.junit.Test;
import successiveRefinementCh14.theCleanSetupListings14_2till14_7.Args;
import successiveRefinementCh14.theCleanSetupListings14_2till14_7.ArgsException;

import static org.junit.Assert.*;

public class Args14_16Test {
    @Test
    public void testCreateWithNoSchemaOrArguments() throws Exception {
        Args14_16 args = new Args14_16("", new String[0]);
        assertEquals(0, args.cardinality());
    }
    @Test
    public void testWithNoSchemaButWithOneArgument() throws Exception {
        try {
            new Args14_16("", new String[]{"-x"});
            fail();
        } catch (ArgsException14_15 e) {
            assertEquals(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT,
                    e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }
    @Test
    public void testWithNoSchemaButWithMultipleArguments() throws Exception {
        try {
            new Args14_16("", new String[]{"-x", "-y"});
            fail();
        } catch (ArgsException14_15 e) {
            assertEquals(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT,
                    e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }

    }
    @Test
    public void testNonLetterSchema() throws Exception {
        try {
            new Args14_16("*", new String[]{});
            fail("Args14_16 constructor should have thrown exception");
        } catch (ArgsException14_15 e) {

            assertEquals(ArgsException.ErrorCode.INVALID_ARGUMENT_NAME,
                    e.getErrorCode());
            assertEquals('*', e.getErrorArgumentId());
        }
    }
    @Test
    public void testInvalidArgumentFormat() throws Exception {
        try {
            new Args14_16("f~", new String[]{});
            fail("Args14_16 constructor should have throws exception");
        } catch (ArgsException14_15 e) {
            assertEquals(ArgsException14_15.ErrorCode.INVALID_FORMAT, e.getErrorCode());
            assertEquals('f', e.getErrorArgumentId());
        }
    }
    @Test
    public void testSimpleBooleanPresent() throws Exception {
        Args14_16 args = new Args14_16("x", new String[]{"-x"});
        assertEquals(1, args.cardinality());
        assertEquals(true, args.getBoolean('x'));
    }
    @Test
    public void testSimpleStringPresent() throws Exception {
        Args14_16 args = new Args14_16("x*", new String[]{"-x", "param"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals("param", args.getString('x'));
    }
    @Test
    public void testMissingStringArgument() throws Exception {
        try {
            new Args14_16("x*", new String[]{"-x"});
            fail();
        } catch (ArgsException14_15 e) {
            assertEquals(ArgsException.ErrorCode.MISSING_STRING, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }
    @Test
    public void testSpacesInFormat() throws Exception {
        Args14_16 args = new Args14_16("x, y", new String[]{"-xy"});
        assertEquals(2, args.cardinality());
        assertTrue(args.has('x'));
        assertTrue(args.has('y'));
    }
    @Test
    public void testSimpleIntPresent() throws Exception {
        Args14_16 args = new Args14_16("x#", new String[]{"-x", "42"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42, args.getInt('x'));
    }
    @Test
    public void testInvalidInteger() throws Exception {
        try {
            new Args14_16("x#", new String[]{"-x", "Forty two"});

            fail();
        } catch (ArgsException14_15 e) {
            assertEquals(ArgsException.ErrorCode.INVALID_INTEGER, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
            assertEquals("Forty two", e.getErrorParameter());
        }
    }
    @Test
    public void testMissingInteger() throws Exception {
        try {
            new Args("x#", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ArgsException.ErrorCode.MISSING_INTEGER, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }
    @Test
    public void testSimpleDoublePresent() throws Exception {
        Args14_16 args = new Args14_16("x##", new String[]{"-x", "42.3"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42.3, args.getDouble('x'), .001);
    }
    @Test
    public void testInvalidDouble() throws Exception {
        try {
            new Args14_16("x##", new String[]{"-x", "Forty two"});
            fail();
        } catch (ArgsException14_15 e) {
            assertEquals(ArgsException.ErrorCode.INVALID_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
            assertEquals("Forty two", e.getErrorParameter());
        }
    }
    @Test
    public void testMissingDouble() throws Exception {
        try {
            new Args14_16("x##", new String[]{"-x"});
            fail();
        } catch (ArgsException14_15 e) {
            assertEquals(ArgsException.ErrorCode.MISSING_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

}