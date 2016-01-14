


/** ===============================================================================
 * @author David Hetherington
 *
 *			Very simple HTTP Tester. Sends a file to a URL and port. The
 *			file contains the HTTP packet to send. 
 *			The response goes to standard out.
 *			The design goal here is to create something dead simple that can
 *			easily be scripted and piped to make a simple integration
 *			test tool for RESTful APIs with.
 *
 * ================================================================================ */
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class HTTPTester {
	

	public static void main(String[] args) {
		final int SLEEP_MILLISECONDS = 100; 	// each time there is no incoming data sleep for 100 msec
		final int SLEEP_COUNT = 20; 		// wait a total of 20 * 100 msec = 2 seconds before giving up
		int sleepCount = SLEEP_COUNT;		
		String thisLine;
		Parameters params = new Parameters(args);	// constructor for Parameters does all validity checking
		
		if (params.areValid()){
			
			System.out.println("Welcome to HTTP Tester. Contacting " + params.getHostName() + " on port " + params.getPortNumber());		
			
		    //Open the file for reading
		    try (
		    	BufferedReader testFileReader = new BufferedReader(new FileReader(params.getTestFile()));
				Socket testSocket = new Socket(params.getHostName(), params.getPortNumber());
				PrintWriter outHTTP = new PrintWriter(testSocket.getOutputStream(), true);
				BufferedReader inHTTP =  new BufferedReader(
				    		new InputStreamReader(testSocket.getInputStream()));
	
		    )
		    {
		    	System.out.println("Connections seem to be OK, Sending " + params.getTestFile());
		    	// Send the test file
		    	while ((thisLine = testFileReader.readLine()) != null) { // while loop begins here
		    		System.out.println(thisLine);
		    		outHTTP.println(thisLine);
		    	} // end while 
	
			    //	Now look for response. TCP/IP does not have a clean "other end finished"
			    //  mechanism. Use a slight cludge of a timeout to make it work.
			    
		    	while (sleepCount > 0){
		    		
		    		if (inHTTP.ready()) {						// something there? (Not necessarily a full line)
		    			thisLine = inHTTP.readLine();			// Yes. OK. Wait for a full line to arrive.
		    			System.out.println(thisLine);			// Print that line.
		    			sleepCount = SLEEP_COUNT;				// Reset the timeout count.
		    		}
		    		else {
		    			try {									// Java compiler insists on try/catch
							TimeUnit.MILLISECONDS.sleep(SLEEP_MILLISECONDS);
						} catch (InterruptedException e) {
							System.err.println("Error: " + e);
						}
		    			sleepCount--;							// Decrement sleep count after sleeping
		    			
		    		}
		    		
		    	}	    	
		    	
		    } // end try
		    catch (IOException e) {
		    	System.err.println("Error: " + e);
		    }
		}
	}

}
