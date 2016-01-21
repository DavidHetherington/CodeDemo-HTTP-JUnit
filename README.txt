
Demo of HTTP Techniques in Java with JUnit
------------------------------------------

HTTPTester is a little program developed in Eclipse 
that will read in a HTTP request file, send it to
a website, and echo the results back to the screen.

This is not really a full-fledged HTTP test tool,
for which I would recommend Curl instead. However,
it does demonstrate the basic of Java HTTP communication.

The class Parameters is used to provide a range of
sanity tests on the command line parameters
such as valid URL, valid port, etc..

The TestParameters is a JUnit implemented unit test
of the class Parameters.

All of this was developed and tested with Eclipse Mars 4.5.1.

David Hetherington - 14 Janaury 2016
