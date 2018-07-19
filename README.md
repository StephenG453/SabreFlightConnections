# SabreFlightConnections

- This is a Java program that will check for the most efficient flight path between two cities, as long as the file being read follows the same format as below:

AA1111/ATL/DAL/HOU
AA2222/TPA/ATL/HOU/ORD
AA3333/DAL/ORD/MSP/HOU
AA4444/DAL/TPA/ORD
ORD-MSP
ATL-HOU
DAL-HOU
TPA-ORD

- The first four lines specify a flight number along with the airports the flight lands in. The program is dynamic, so you can add as many flight numbers as you wish.

- Each of the last four lines are the origin airport and the destination airports. The program will figure out the most efficient flight given, or specify none is available if that is case.

- Only one through-flight is allowed, any more than that mean the flight is unavailable.

- I wrote this program during an interview process with Sabre Holdings.

- To get the program running on your computer, just create a Flights.txt file with the same format and change the String constant FILE_LOCATION in the FlightProblem to the appropriate location on your computer


