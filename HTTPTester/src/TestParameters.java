import static org.junit.Assert.*;

import org.junit.Test;

/** ===============================================================================
 * @author David Hetherington
 *
 *			Standard JUNITs test for the Parameters class that validates all 
 *			of the input for the HTTPTester program
 *
 * ================================================================================ */
public class TestParameters {

	// ---------------------------------------------------------------------------- 
	// Tests for the parameter validation inside the constructor. These tests
	// also verify the proper operation of the .areValid() method
	// ----------------------------------------------------------------------------           

	/**
	 * Test method for {@link Parameters#Parameters()}.
	 */
	@Test
	public void testParameterValidation01TooFewParameters() throws Exception {
		String[] testdata = {""};
		Parameters params = new Parameters(testdata);
		boolean testAreValid = params.areValid();
		assertEquals(testAreValid,false);
	}
	
	/**
	 * Test method for {@link Parameters#Parameters()}.
	 */
	@Test
	public void testParameterValidation02EnoughParameters() throws Exception {
		String[] testdata = {"asattepress.com","80","Default.test"};
		Parameters params = new Parameters(testdata);
		boolean testAreValid = params.areValid();
		assertEquals(testAreValid,true);
	}
	
	/**
	 * Test method for {@link Parameters#Parameters()}.
	 */
	@Test
	public void testParameterValidation10ValidURI() throws Exception {
		String[] testdata = {"a==+&^%$sattepress.com","80","Default.test"};
		Parameters params = new Parameters(testdata);
		boolean testAreValid = params.areValid();
		assertEquals(testAreValid,false);
	}
	
	/**
	 * Test method for {@link Parameters#Parameters()}.
	 */
	@Test
	public void testParameterValidation20PortNumberIsInteger() throws Exception {
		String[] testdata = {"asattepress.com","80","Default.test"};
		Parameters params = new Parameters(testdata);
		boolean testAreValid = params.areValid();
		assertEquals(testAreValid,true);
	}

	/**
	 * Test method for {@link Parameters#Parameters()}.
	 */
	@Test
	public void testParameterValidation21PortNumberNotInteger() throws Exception {
		String[] testdata = {"asattepress.com","XYZ","Default.test"};
		Parameters params = new Parameters(testdata);
		boolean testAreValid = params.areValid();
		assertEquals(testAreValid,false);
	}
	
	/**
	 * Test method for {@link Parameters#Parameters()}.
	 */
	@Test
	public void testParameterValidation22PortNumberTooSmall() throws Exception {
		String[] testdata = {"asattepress.com","-10","Default.test"};
		Parameters params = new Parameters(testdata);
		boolean testAreValid = params.areValid();
		assertEquals(testAreValid,false);
	}

	/**
	 * Test method for {@link Parameters#Parameters()}.
	 */
	@Test
	public void testParameterValidation22PortNumberTooLarge() throws Exception {
		String[] testdata = {"asattepress.com","65536","Default.test"};
		Parameters params = new Parameters(testdata);
		boolean testAreValid = params.areValid();
		assertEquals(testAreValid,false);
	}

	
	
	// ---------------------------------------------------------------------------- 
	// Tests for the Standard getter methods
	// ----------------------------------------------------------------------------           
	
	/**
	 * Test method for {@link Parameters#getHostName()}.
	 */
	@Test
	public void testGetHostName() throws Exception {
		String[] testdata = {"asattepress.com","80","Default.test"};
		Parameters params = new Parameters(testdata);
		String testHostName = params.getHostName();
		assertEquals(testHostName,"asattepress.com");
	}

	/**
	 * Test method for {@link Parameters#getPortNumber()}.
	 */
	@Test
	public void testGetPortNumber() {
		String[] testdata = {"asattepress.com","80","Default.test"};
		Parameters params = new Parameters(testdata);
		int testPortNumber = params.getPortNumber();
		assertEquals(testPortNumber,80);
	}

	/**
	 * Test method for {@link Parameters#getTestFile()}.
	 */
	@Test
	public void testGetTestFile() {
		String[] testdata = {"asattepress.com","80","Default.test"};
		Parameters params = new Parameters(testdata);
		String testTestFile = params.getTestFile();
		assertEquals(testTestFile,"Default.test");
	}

}
