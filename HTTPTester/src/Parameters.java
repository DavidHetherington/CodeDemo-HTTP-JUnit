import java.net.URI;
import java.net.URISyntaxException;

/**
 * 
 */

/** ===============================================================================
 * @author David Hetherington
 *
 *			Isolate the parsing and handling of parameters to a class to keep all the
 *			Gobbledygook error checking out of the main line of the program
 *
 * ================================================================================ */
public class Parameters {
	
	// ---------------------------------------------------------------------------- 
	// Key control parameters for HTTPTester.java
	// ----------------------------------------------------------------------------           

	private String hostName; 
	private int portNumber;
	String testFile;
	boolean parametersAreValid = false;
	
	// ---------------------------------------------------------------------------- 
	// The class constructor does the heavy lifting of checking the validity of the
	// parameters input by the user. Only if all checks pass is parametersAreValid
	// set to true.
	// ----------------------------------------------------------------------------           
	Parameters(String[] args) {
		
		// Test for enough arguments
		if (args.length < 3) {
			System.out.println("Insufficient arguments. Three arguments required. Only "+args.length+" input.");
			System.out.println("Proper Usage is: java -jar HTTPTest.jar <host URL> <host port> <HTTP Command Test File>");
			return;
		}
		
		// Make sure the URI passed is valid
		if (!isValidURI(args[0])) {
			System.out.println(args[0]+" is not a valid URI");
			return;
		}
		
		// Make sure the Port Number is an integer
		if (!isInteger(args[1])) {
			System.out.println("Port number must be an integer between 0 and 65,535. "+args[1]+" is not an integer.");
			return;
		}
		
		// Make sure the Port Number is in range
		int testPortNumber = Integer.parseInt(args[1]);
		if (testPortNumber <0) {
			System.out.println("Port number "+testPortNumber+" is too small. Port number must be an integer between 0 and 65,535. ");
			return;
		}
		if (testPortNumber >65535) {
			System.out.println("Port number "+testPortNumber+" is too large. Port number must be an integer between 0 and 65,535. ");
			return;
		}
			
	
		
		
		// TODO Auto-generated constructor stub
		hostName = args[0];
		portNumber = Integer.parseInt(args[1]);
		testFile = args[2];
		parametersAreValid = true;
	}

	// ---------------------------------------------------------------------------- 
	// Standard getter methods
	// ----------------------------------------------------------------------------           
	public String getHostName(){
		return hostName;
	}

	public int getPortNumber(){
		return portNumber;
	}

	public String getTestFile(){
		return testFile;
	}

	// ---------------------------------------------------------------------------- 
	// Standard this method controls execution of the main body of the program.
	// Only if all checks of the input paramerts passed will parametersAreValid
	// be true.
	// ----------------------------------------------------------------------------           
	public boolean areValid(){
		return parametersAreValid;
	}

	// ---------------------------------------------------------------------------- 
	// Local Utility functions
	// ----------------------------------------------------------------------------           
	
	private static boolean isValidURI(String uriStr) {
	    try {
	      URI uri = new URI(uriStr);
	      uri.getPath(); 				// No function. Just suppress compiler warning
	      return true;
	    }
	    catch (URISyntaxException e) {
	        return false;
	    }
	}
	
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}


}
